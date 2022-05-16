package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.data.SMDatagenUtil;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SMBlockStateProvider extends BlockStateProvider {

    public SMBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SullysMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        basicBlock(SMBlocks.JADE_ORE.get());
        basicBlock(SMBlocks.DEEPSLATE_JADE_ORE.get());
        basicBlock(SMBlocks.RAW_JADE_BLOCK.get());
        basicBlock(SMBlocks.RAW_JADE_BRICKS.get());
        basicBlock(SMBlocks.SMOOTH_RAW_JADE.get());
        basicBlock(SMBlocks.RAW_JADE_TILES.get());
        basicBlock(SMBlocks.JADE_BLOCK.get());
        basicBlock(SMBlocks.JADE_BRICKS.get());
        basicBlock(SMBlocks.SMALL_JADE_BRICKS.get());
        basicBlock(SMBlocks.JADE_SHINGLES.get());
        basicBlock(SMBlocks.JADE_TILES.get());
        basicBlock(SMBlocks.CHISELED_JADE.get());
        pillarBlock(SMBlocks.JADE_PILLAR.get(), "chiseled_jade");
        totemBlock(SMBlocks.JADE_TOTEM.get());
        totemBlock(SMBlocks.JADE_FLINGER_TOTEM.get());
        basicButtonBlock(SMBlocks.COPPER_BUTTON.get(), SMDatagenUtil.COPPER_BLOCK);
        basicButtonBlock(SMBlocks.EXPOSED_COPPER_BUTTON.get(), SMDatagenUtil.EXPOSED_COPPER);
        basicButtonBlock(SMBlocks.WEATHERED_COPPER_BUTTON.get(), SMDatagenUtil.WEATHERED_COPPER);
        basicButtonBlock(SMBlocks.OXIDIZED_COPPER_BUTTON.get(), SMDatagenUtil.OXIDIZED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_COPPER_BUTTON.get(), SMDatagenUtil.COPPER_BLOCK);
        basicButtonBlock(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), SMDatagenUtil.EXPOSED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), SMDatagenUtil.WEATHERED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), SMDatagenUtil.OXIDIZED_COPPER);
        modStairsBlock(SMBlocks.JADE_BRICK_STAIRS.get(), SMDatagenUtil.JADE_BRICKS);
        modStairsBlock(SMBlocks.SMALL_JADE_BRICK_STAIRS.get(), SMDatagenUtil.SMALL_JADE_BRICKS);
        modStairsBlock(SMBlocks.JADE_SHINGLE_STAIRS.get(), SMDatagenUtil.JADE_SHINGLES);
        modStairsBlock(SMBlocks.JADE_TILE_STAIRS.get(), SMDatagenUtil.JADE_TILES);
        modStairsBlock(SMBlocks.RAW_JADE_BRICK_STAIRS.get(), SMDatagenUtil.RAW_JADE_BRICKS);
        modStairsBlock(SMBlocks.SMOOTH_RAW_JADE_STAIRS.get(), SMDatagenUtil.SMOOTH_RAW_JADE);
        modStairsBlock(SMBlocks.RAW_JADE_TILE_STAIRS.get(), SMDatagenUtil.RAW_JADE_TILES);
        modSlabBlock(SMBlocks.JADE_BRICK_SLAB.get(), SMDatagenUtil.JADE_BRICKS);
        modSlabBlock(SMBlocks.SMALL_JADE_BRICK_SLAB.get(), SMDatagenUtil.SMALL_JADE_BRICKS);
        modSlabBlock(SMBlocks.JADE_SHINGLE_SLAB.get(), SMDatagenUtil.JADE_SHINGLES);
        modSlabBlock(SMBlocks.JADE_TILE_SLAB.get(), SMDatagenUtil.JADE_TILES);
        modSlabBlock(SMBlocks.RAW_JADE_BRICK_SLAB.get(), SMDatagenUtil.RAW_JADE_BRICKS);
        modSlabBlock(SMBlocks.SMOOTH_RAW_JADE_SLAB.get(), SMDatagenUtil.SMOOTH_RAW_JADE);
        modSlabBlock(SMBlocks.RAW_JADE_TILE_SLAB.get(), SMDatagenUtil.RAW_JADE_TILES);

        SullysMod.LOGGER.info("BLOCKSTATE GENERATION COMPLETE");
    }

    private void basicBlock(Block block) {
        simpleBlock(block);
    }

    private void pillarBlock(Block block, String topTexture) {
        axisBlock((RotatedPillarBlock) block, SMDatagenUtil.modBlockLocation(SMDatagenUtil.name(block)), SMDatagenUtil.modBlockLocation(topTexture));
    }

    private void totemBlock(Block block) {
        ModelFile totemModel = models().cube(SMDatagenUtil.name(block),
              SMDatagenUtil.modBlockLocation(SMDatagenUtil.name(block) + "_top"),
              SMDatagenUtil.modBlockLocation(SMDatagenUtil.name(block) + "_top"),
              SMDatagenUtil.modBlockLocation(SMDatagenUtil.name(block) + "_front"),
              SMDatagenUtil.modBlockLocation(SMDatagenUtil.name(block) + "_back"),
              SMDatagenUtil.modBlockLocation(SMDatagenUtil.name(block) + "_right"),
              SMDatagenUtil.modBlockLocation(SMDatagenUtil.name(block) + "_left"))
                .texture("particle", SMDatagenUtil.modBlockLocation(SMDatagenUtil.name(block) + "_back"));

        getVariantBuilder(block).forAllStates(blockState -> ConfiguredModel.builder()
                .modelFile(totemModel)
                .rotationY(((int) blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                .build());
    }

    private void basicButtonBlock(Block block, String texture) {
        buttonBlock((ButtonBlock)block, SMDatagenUtil.vanillaBlockLocation(texture));
    }

    private void modStairsBlock(Block block, String texture) {
        stairsBlock((StairBlock) block, SMDatagenUtil.modBlockLocation(texture));
    }

    private void modSlabBlock(Block block, String texture) {
        slabBlock((SlabBlock) block, SMDatagenUtil.modBlockLocation(texture), SMDatagenUtil.modBlockLocation(texture));
    }

}
