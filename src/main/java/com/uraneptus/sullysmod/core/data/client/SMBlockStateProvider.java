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

import java.util.function.Supplier;

import static com.uraneptus.sullysmod.core.data.SMDatagenUtil.*;

@SuppressWarnings("SameParameterValue")
public class SMBlockStateProvider extends BlockStateProvider {
    public SMBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SullysMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        basicBlock(SMBlocks.JADE_ORE);
        basicBlock(SMBlocks.DEEPSLATE_JADE_ORE);
        basicBlock(SMBlocks.ROUGH_JADE_BLOCK);
        basicBlock(SMBlocks.ROUGH_JADE_BRICKS);
        basicBlock(SMBlocks.SMOOTHED_ROUGH_JADE);
        basicBlock(SMBlocks.ROUGH_JADE_TILES);
        basicBlock(SMBlocks.POLISHED_JADE_BLOCK);
        basicBlock(SMBlocks.POLISHED_JADE_BRICKS);
        basicBlock(SMBlocks.POLISHED_SMALL_JADE_BRICKS);
        basicBlock(SMBlocks.POLISHED_JADE_SHINGLES);
        basicBlock(SMBlocks.POLISHED_JADE_TILES);
        basicBlock(SMBlocks.POLISHED_CHISELED_JADE);
        pillarBlock(SMBlocks.POLISHED_JADE_PILLAR, POLISHED_CHISELED_JADE);
        totemBlock(SMBlocks.JADE_TOTEM);
        totemBlock(SMBlocks.JADE_FLINGER_TOTEM);
        basicButtonBlock(SMBlocks.COPPER_BUTTON, COPPER_BLOCK);
        basicButtonBlock(SMBlocks.EXPOSED_COPPER_BUTTON, EXPOSED_COPPER);
        basicButtonBlock(SMBlocks.WEATHERED_COPPER_BUTTON, WEATHERED_COPPER);
        basicButtonBlock(SMBlocks.OXIDIZED_COPPER_BUTTON, OXIDIZED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_COPPER_BUTTON, COPPER_BLOCK);
        basicButtonBlock(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON, EXPOSED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON, WEATHERED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON, OXIDIZED_COPPER);
        modStairsBlock(SMBlocks.POLISHED_JADE_BRICK_STAIRS, JADE_BRICKS);
        modStairsBlock(SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS, SMALL_JADE_BRICKS);
        modStairsBlock(SMBlocks.POLISHED_JADE_SHINGLE_STAIRS, JADE_SHINGLES);
        modStairsBlock(SMBlocks.POLISHED_JADE_TILE_STAIRS, JADE_TILES);
        modStairsBlock(SMBlocks.ROUGH_JADE_BRICK_STAIRS, ROUGH_JADE_BRICKS);
        modStairsBlock(SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS, SMOOTHED_ROUGH_JADE);
        modStairsBlock(SMBlocks.ROUGH_JADE_TILE_STAIRS, ROUGH_JADE_TILES);
        modSlabBlock(SMBlocks.POLISHED_JADE_BRICK_SLAB, JADE_BRICKS);
        modSlabBlock(SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB, SMALL_JADE_BRICKS);
        modSlabBlock(SMBlocks.POLISHED_JADE_SHINGLE_SLAB, JADE_SHINGLES);
        modSlabBlock(SMBlocks.POLISHED_JADE_TILE_SLAB, JADE_TILES);
        modSlabBlock(SMBlocks.ROUGH_JADE_BRICK_SLAB, ROUGH_JADE_BRICKS);
        modSlabBlock(SMBlocks.SMOOTHED_ROUGH_JADE_SLAB, SMOOTHED_ROUGH_JADE);
        modSlabBlock(SMBlocks.ROUGH_JADE_TILE_SLAB, ROUGH_JADE_TILES);
        modVerticalSlabBlock(SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB, JADE_BRICKS);
        modVerticalSlabBlock(SMBlocks.POLISHED_SMALL_JADE_BRICK_VERTICAL_SLAB, SMALL_JADE_BRICKS);
        modVerticalSlabBlock(SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB, JADE_SHINGLES);
        modVerticalSlabBlock(SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB, JADE_TILES);
        modVerticalSlabBlock(SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB, ROUGH_JADE_BRICKS);
        modVerticalSlabBlock(SMBlocks.SMOOTHED_ROUGH_JADE_VERTICAL_SLAB, SMOOTHED_ROUGH_JADE);
        modVerticalSlabBlock(SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB, ROUGH_JADE_TILES);
        modEggBlock(SMBlocks.TORTOISE_EGG);
    }

    private void basicBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    private void pillarBlock(Supplier<? extends Block> block, String topTexture) {
        axisBlock((RotatedPillarBlock) block.get(), modBlockLocation(name(block.get())), modBlockLocation(topTexture));
    }

    private void totemBlock(Supplier<? extends Block> block) {
        ModelFile totemModel = models().cube(name(block.get()),
              modBlockLocation(name(block.get()) + "_top"),
              modBlockLocation(name(block.get()) + "_top"),
              modBlockLocation(name(block.get()) + "_front"),
              modBlockLocation(name(block.get()) + "_back"),
              modBlockLocation(name(block.get()) + "_right"),
              modBlockLocation(name(block.get()) + "_left"))
                .texture("particle", modBlockLocation(name(block.get()) + "_back"));

        getVariantBuilder(block.get()).forAllStates(blockState -> ConfiguredModel.builder()
                .modelFile(totemModel)
                .rotationY(((int) blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                .build());
    }

    private void basicButtonBlock(Supplier<? extends Block> block, String texture) {
        buttonBlock((ButtonBlock)block.get(), vanillaBlockLocation(texture));
    }

    private void modStairsBlock(Supplier<? extends Block> block, String texture) {
        stairsBlock((StairBlock) block.get(), modBlockLocation(texture));
    }

    private void modSlabBlock(Supplier<? extends Block> block, String texture) {
        slabBlock((SlabBlock) block.get(), modBlockLocation(texture), modBlockLocation(texture));
    }

    private void modEggBlock(Supplier<? extends Block> block) {
        getVariantBuilder(block.get()).forAllStates(blockState -> {
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
                    .withExistingParent(variants + prefix + name(block.get()) + suffix, vanillaBlockLocation(templatePath))
                    .texture("all", modBlockLocation(prefix + name(block.get())));

            return ConfiguredModel.builder()
                    .modelFile(modelFile)
                    .nextModel().modelFile(modelFile).rotationY(90)
                    .nextModel().modelFile(modelFile).rotationY(180)
                    .nextModel().modelFile(modelFile).rotationY(270)
                    .build();
        });
    }

    private void modVerticalSlabBlock(Supplier<? extends Block> slab, String path) {
        ModelFile model = this.models()
                .withExistingParent(name(slab.get()), blueprintBlockLocation("vertical_slab"))
                .texture("top", modBlockLocation(path))
                .texture("bottom", modBlockLocation(path))
                .texture("side", modBlockLocation(path));

        getVariantBuilder(slab.get())
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.NORTH).addModels(new ConfiguredModel(model, 0, 0, true))
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.SOUTH).addModels(new ConfiguredModel(model, 0, 180, true))
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.EAST).addModels(new ConfiguredModel(model, 0, 90, true))
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.WEST).addModels(new ConfiguredModel(model, 0, 270, true))
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.DOUBLE).addModels(new ConfiguredModel(this.models().getExistingFile(modBlockLocation(path))));
    }
}
