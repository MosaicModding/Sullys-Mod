package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SullysMod.MOD_ID, exFileHelper);
    }

    private String name(Block block) {
        return block.getRegistryName().getPath();
    }

    public ResourceLocation location(String path) {
        return new ResourceLocation(SullysMod.MOD_ID, ModelProvider.BLOCK_FOLDER + "/" + path);
    }

    public ResourceLocation vanillaLocation(String path) {
        return new ResourceLocation("minecraft", ModelProvider.BLOCK_FOLDER + "/" + path);
    }

    @Override
    protected void registerStatesAndModels() {
        basicBlock(SMBlocks.JADE_ORE.get());
        basicBlock(SMBlocks.DEEPSLATE_JADE_ORE.get());
        basicBlock(SMBlocks.RAW_JADE_BLOCK.get());
        basicBlock(SMBlocks.RAW_JADE_BRICKS.get());
        basicBlock(SMBlocks.RAW_JADE_TILE.get());
        basicBlock(SMBlocks.RAW_JADE_TILES.get());
        basicBlock(SMBlocks.JADE_BRICKS.get());
        basicBlock(SMBlocks.SMALL_JADE_BRICKS.get());
        basicBlock(SMBlocks.JADE_SHINGLES.get());
        basicBlock(SMBlocks.JADE_TILES.get());
        basicBlock(SMBlocks.CHISELED_JADE.get());
        pillarBlock(SMBlocks.JADE_PILLAR.get(), "chiseled_jade");
        totemBlock(SMBlocks.JADE_TOTEM.get());
        basicButtonBlock((ButtonBlock) SMBlocks.COPPER_BUTTON.get(), "copper_block");
        basicButtonBlock((ButtonBlock) SMBlocks.EXPOSED_COPPER_BUTTON.get(), "exposed_copper");
        basicButtonBlock((ButtonBlock) SMBlocks.WEATHERED_COPPER_BUTTON.get(), "weathered_copper");
        basicButtonBlock((ButtonBlock) SMBlocks.OXIDIZED_COPPER_BUTTON.get(), "oxidized_copper");
        basicButtonBlock((ButtonBlock) SMBlocks.WAXED_COPPER_BUTTON.get(), "copper_block");
        basicButtonBlock((ButtonBlock) SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), "exposed_copper");
        basicButtonBlock((ButtonBlock) SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), "weathered_copper");
        basicButtonBlock((ButtonBlock) SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), "oxidized_copper");


        SullysMod.LOGGER.info("BLOCKSTATE GENERATION COMPLETE");
    }

    private void basicBlock(Block block) {
        simpleBlock(block);
    }

    private void pillarBlock(Block block, String topTexture) {
        axisBlock((RotatedPillarBlock) block, location(name(block)), location(topTexture));
    }

    private void totemBlock(Block block) {
        ModelFile totemModel = models().cube(name(block),
                location(name(block) + "_top"),
                location(name(block) + "_top"),
                location(name(block) + "_front"),
                location(name(block) + "_back"),
                location(name(block) + "_right"),
                location(name(block) + "_left"))
                .texture("particle", location(name(block) + "_back"));

        getVariantBuilder(block).forAllStates(blockState -> ConfiguredModel.builder()
                .modelFile(totemModel)
                .rotationY(((int) blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                .build());
    }

    private void basicButtonBlock(ButtonBlock block, String texture) {
        buttonBlock(block, vanillaLocation(texture));
    }
}
