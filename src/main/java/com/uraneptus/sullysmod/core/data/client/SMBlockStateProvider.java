package com.uraneptus.sullysmod.core.data.client;

import com.teamabnormals.blueprint.common.block.VerticalSlabBlock;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.uraneptus.sullysmod.core.data.SMDatagenUtil.*;

public class SMBlockStateProvider extends BlockStateProvider {
    public SMBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SullysMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        basicBlock(SMBlocks.JADE_ORE.get());
        basicBlock(SMBlocks.DEEPSLATE_JADE_ORE.get());
        basicBlock(SMBlocks.ROUGH_JADE_BLOCK.get());
        basicBlock(SMBlocks.ROUGH_JADE_BRICKS.get());
        basicBlock(SMBlocks.SMOOTHED_ROUGH_JADE.get());
        basicBlock(SMBlocks.ROUGH_JADE_TILES.get());
        basicBlock(SMBlocks.POLISHED_JADE_BLOCK.get());
        basicBlock(SMBlocks.POLISHED_JADE_BRICKS.get());
        basicBlock(SMBlocks.POLISHED_SMALL_JADE_BRICKS.get());
        basicBlock(SMBlocks.POLISHED_JADE_SHINGLES.get());
        basicBlock(SMBlocks.POLISHED_JADE_TILES.get());
        basicBlock(SMBlocks.POLISHED_CHISELED_JADE.get());
        pillarBlock(SMBlocks.POLISHED_JADE_PILLAR.get(), POLISHED_CHISELED_JADE);
        totemBlock(SMBlocks.JADE_TOTEM.get());
        totemBlock(SMBlocks.JADE_FLINGER_TOTEM.get());
        basicButtonBlock(SMBlocks.COPPER_BUTTON.get(), COPPER_BLOCK);
        basicButtonBlock(SMBlocks.EXPOSED_COPPER_BUTTON.get(), EXPOSED_COPPER);
        basicButtonBlock(SMBlocks.WEATHERED_COPPER_BUTTON.get(), WEATHERED_COPPER);
        basicButtonBlock(SMBlocks.OXIDIZED_COPPER_BUTTON.get(), OXIDIZED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_COPPER_BUTTON.get(), COPPER_BLOCK);
        basicButtonBlock(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), EXPOSED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), WEATHERED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), OXIDIZED_COPPER);
        modStairsBlock(SMBlocks.POLISHED_JADE_BRICK_STAIRS.get(), JADE_BRICKS);
        modStairsBlock(SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get(), SMALL_JADE_BRICKS);
        modStairsBlock(SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get(), JADE_SHINGLES);
        modStairsBlock(SMBlocks.POLISHED_JADE_TILE_STAIRS.get(), JADE_TILES);
        modStairsBlock(SMBlocks.ROUGH_JADE_BRICK_STAIRS.get(), ROUGH_JADE_BRICKS);
        modStairsBlock(SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS.get(), SMOOTHED_ROUGH_JADE);
        modStairsBlock(SMBlocks.ROUGH_JADE_TILE_STAIRS.get(), ROUGH_JADE_TILES);
        modSlabBlock(SMBlocks.POLISHED_JADE_BRICK_SLAB.get(), JADE_BRICKS);
        modSlabBlock(SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get(), SMALL_JADE_BRICKS);
        modSlabBlock(SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get(), JADE_SHINGLES);
        modSlabBlock(SMBlocks.POLISHED_JADE_TILE_SLAB.get(), JADE_TILES);
        modSlabBlock(SMBlocks.ROUGH_JADE_BRICK_SLAB.get(), ROUGH_JADE_BRICKS);
        modSlabBlock(SMBlocks.SMOOTHED_ROUGH_JADE_SLAB.get(), SMOOTHED_ROUGH_JADE);
        modSlabBlock(SMBlocks.ROUGH_JADE_TILE_SLAB.get(), ROUGH_JADE_TILES);
        modVerticalSlabBlock(SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB.get(), JADE_BRICKS);
        modVerticalSlabBlock(SMBlocks.SMALL_POLISHED_JADE_BRICK_VERTICAL_SLAB.get(), SMALL_JADE_BRICKS);
        modVerticalSlabBlock(SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB.get(), JADE_SHINGLES);
        modVerticalSlabBlock(SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB.get(), JADE_TILES);
        modVerticalSlabBlock(SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB.get(), ROUGH_JADE_BRICKS);
        modVerticalSlabBlock(SMBlocks.SMOOTHED_ROUGH_JADE_VERTICAL_SLAB.get(), SMOOTHED_ROUGH_JADE);
        modVerticalSlabBlock(SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB.get(), ROUGH_JADE_TILES);
        modEggBlock(SMBlocks.TORTOISE_EGG.get());

        SullysMod.LOGGER.info("BLOCKSTATE GENERATION COMPLETE");
    }

    private void basicBlock(Block block) {
        simpleBlock(block);
    }

    private void pillarBlock(Block block, String topTexture) {
        axisBlock((RotatedPillarBlock) block, modBlockLocation(name(block)), modBlockLocation(topTexture));
    }

    private void totemBlock(Block block) {
        ModelFile totemModel = models().cube(name(block),
              modBlockLocation(name(block) + "_top"),
              modBlockLocation(name(block) + "_top"),
              modBlockLocation(name(block) + "_front"),
              modBlockLocation(name(block) + "_back"),
              modBlockLocation(name(block) + "_right"),
              modBlockLocation(name(block) + "_left"))
                .texture("particle", modBlockLocation(name(block) + "_back"));

        getVariantBuilder(block).forAllStates(blockState -> ConfiguredModel.builder()
                .modelFile(totemModel)
                .rotationY(((int) blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                .build());
    }

    private void basicButtonBlock(Block block, String texture) {
        buttonBlock((ButtonBlock)block, vanillaBlockLocation(texture));
    }

    private void modStairsBlock(Block block, String texture) {
        stairsBlock((StairBlock) block, modBlockLocation(texture));
    }

    private void modSlabBlock(Block block, String texture) {
        slabBlock((SlabBlock) block, modBlockLocation(texture), modBlockLocation(texture));
    }

    private void modEggBlock(Block block) {
        getVariantBuilder(block).forAllStates(blockState -> {
            int hatch = blockState.getValue(BlockStateProperties.HATCH);
            int eggs = blockState.getValue(BlockStateProperties.EGGS);

            String variants = switch (eggs) {
                case 2 -> "two_";
                case 3 -> "three_";
                case 4 -> "four_";
                default -> "";
            };

            String templatePath = eggs > 1 ? "template_" + variants + "turtle_eggs" : "template_turtle_egg";
            String prefix = hatch == 1 ? "slightly_cracked_" : hatch == 2 ? "very_cracked_" : "";
            String suffix = eggs > 1 ? "s" : "";

            ModelFile modelFile = models()
                    .withExistingParent(variants + prefix + name(block) + suffix, vanillaBlockLocation(templatePath))
                    .texture("all", modBlockLocation(prefix + name(block)));

            return ConfiguredModel.builder()
                    .modelFile(modelFile)
                    .nextModel().modelFile(modelFile).rotationY(90)
                    .nextModel().modelFile(modelFile).rotationY(180)
                    .nextModel().modelFile(modelFile).rotationY(270)
                    .build();
        });
    }

    private void modVerticalSlabBlock(Block slab, String path) {
        ModelFile model = this.models()
                .withExistingParent(name(slab), blueprintBlockLocation("vertical_slab"))
                .texture("top", modBlockLocation(path))
                .texture("bottom", modBlockLocation(path))
                .texture("side", modBlockLocation(path));

        getVariantBuilder(slab)
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.NORTH).addModels(new ConfiguredModel(model, 0, 0, true))
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.SOUTH).addModels(new ConfiguredModel(model, 0, 180, true))
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.EAST).addModels(new ConfiguredModel(model, 0, 90, true))
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.WEST).addModels(new ConfiguredModel(model, 0, 270, true))
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.DOUBLE).addModels(new ConfiguredModel(this.models().getExistingFile(modBlockLocation(path))));
    }
}
