package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SullysMod.MOD_ID, exFileHelper);
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
        basicButtonBlock(SMBlocks.COPPER_BUTTON.get(), DataUtil.COPPER_BLOCK);
        basicButtonBlock(SMBlocks.EXPOSED_COPPER_BUTTON.get(), DataUtil.EXPOSED_COPPER);
        basicButtonBlock(SMBlocks.WEATHERED_COPPER_BUTTON.get(), DataUtil.WEATHERED_COPPER);
        basicButtonBlock(SMBlocks.OXIDIZED_COPPER_BUTTON.get(), DataUtil.OXIDIZED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_COPPER_BUTTON.get(), DataUtil.COPPER_BLOCK);
        basicButtonBlock(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), DataUtil.EXPOSED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), DataUtil.WEATHERED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), DataUtil.OXIDIZED_COPPER);
        modStairsBlock(SMBlocks.JADE_BRICKS_STAIRS.get(), DataUtil.JADE_BRICKS);
        modStairsBlock(SMBlocks.SMALL_JADE_BRICKS_STAIRS.get(), DataUtil.SMALL_JADE_BRICKS);
        modStairsBlock(SMBlocks.JADE_SHINGLES_STAIRS.get(), DataUtil.JADE_SHINGLES);
        modStairsBlock(SMBlocks.JADE_TILES_STAIRS.get(), DataUtil.JADE_TILES);
        modSlabBlock(SMBlocks.JADE_BRICKS_SLAB.get(), DataUtil.JADE_BRICKS);
        modSlabBlock(SMBlocks.SMALL_JADE_BRICKS_SLAB.get(), DataUtil.SMALL_JADE_BRICKS);
        modSlabBlock(SMBlocks.JADE_SHINGLES_SLAB.get(), DataUtil.JADE_SHINGLES);
        modSlabBlock(SMBlocks.JADE_TILES_SLAB.get(), DataUtil.JADE_TILES);

        SullysMod.LOGGER.info("BLOCKSTATE GENERATION COMPLETE");
    }

    private void basicBlock(Block block) {
        simpleBlock(block);
    }

    private void pillarBlock(Block block, String topTexture) {
        axisBlock((RotatedPillarBlock) block, DataUtil.modBlockLocation(DataUtil.name(block)), DataUtil.modBlockLocation(topTexture));
    }

    private void totemBlock(Block block) {
        ModelFile totemModel = models().cube(DataUtil.name(block),
              DataUtil.modBlockLocation(DataUtil.name(block) + "_top"),
              DataUtil.modBlockLocation(DataUtil.name(block) + "_top"),
              DataUtil.modBlockLocation(DataUtil.name(block) + "_front"),
              DataUtil.modBlockLocation(DataUtil.name(block) + "_back"),
              DataUtil.modBlockLocation(DataUtil.name(block) + "_right"),
              DataUtil.modBlockLocation(DataUtil.name(block) + "_left"))
                .texture("particle", DataUtil.modBlockLocation(DataUtil.name(block) + "_back"));

        getVariantBuilder(block).forAllStates(blockState -> ConfiguredModel.builder()
                .modelFile(totemModel)
                .rotationY(((int) blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                .build());
    }

    private void basicButtonBlock(Block block, String texture) {
        buttonBlock((ButtonBlock)block, DataUtil.vanillaBlockLocation(texture));
    }

    private void modStairsBlock(Block block, String texture) {
        stairsBlock((StairBlock) block, DataUtil.modBlockLocation(texture));
    }

    private void modSlabBlock(Block block, String texture) {
        slabBlock((SlabBlock) block, DataUtil.modBlockLocation(texture), DataUtil.modBlockLocation(texture));
    }

}
