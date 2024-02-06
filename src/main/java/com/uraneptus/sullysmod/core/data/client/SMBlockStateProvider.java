package com.uraneptus.sullysmod.core.data.client;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.FlingerTotem;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.uraneptus.sullysmod.core.data.SMDatagenUtil.*;

@SuppressWarnings("SameParameterValue")
public class SMBlockStateProvider extends BlockStateProvider {
    public SMBlockStateProvider(PackOutput packOutput, ExistingFileHelper exFileHelper) {
        super(packOutput, SullysMod.MOD_ID, exFileHelper);
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
        pillarBlock(SMBlocks.POLISHED_JADE_PILLAR, name(SMBlocks.POLISHED_CHISELED_JADE.get()));
        totemBlock(SMBlocks.JADE_TOTEM);
        flingerTotem(SMBlocks.JADE_FLINGER_TOTEM);
        basicButtonBlock(SMBlocks.COPPER_BUTTON, () -> Blocks.COPPER_BLOCK);
        basicButtonBlock(SMBlocks.EXPOSED_COPPER_BUTTON, () -> Blocks.EXPOSED_COPPER);
        basicButtonBlock(SMBlocks.WEATHERED_COPPER_BUTTON, () -> Blocks.WEATHERED_COPPER);
        basicButtonBlock(SMBlocks.OXIDIZED_COPPER_BUTTON, () -> Blocks.OXIDIZED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_COPPER_BUTTON, () -> Blocks.COPPER_BLOCK);
        basicButtonBlock(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON, () -> Blocks.EXPOSED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON, () -> Blocks.WEATHERED_COPPER);
        basicButtonBlock(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON, () -> Blocks.OXIDIZED_COPPER);
        modStairsBlock(SMBlocks.POLISHED_JADE_BRICK_STAIRS, SMBlocks.POLISHED_JADE_BRICKS);
        modStairsBlock(SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS, SMBlocks.POLISHED_SMALL_JADE_BRICKS);
        modStairsBlock(SMBlocks.POLISHED_JADE_SHINGLE_STAIRS, SMBlocks.POLISHED_JADE_SHINGLES);
        modStairsBlock(SMBlocks.POLISHED_JADE_TILE_STAIRS, SMBlocks.POLISHED_JADE_TILES);
        modStairsBlock(SMBlocks.ROUGH_JADE_BRICK_STAIRS, SMBlocks.ROUGH_JADE_BRICKS);
        modStairsBlock(SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS, SMBlocks.SMOOTHED_ROUGH_JADE);
        modStairsBlock(SMBlocks.ROUGH_JADE_TILE_STAIRS, SMBlocks.ROUGH_JADE_TILES);
        modSlabBlock(SMBlocks.POLISHED_JADE_BRICK_SLAB, SMBlocks.POLISHED_JADE_BRICKS);
        modSlabBlock(SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB, SMBlocks.POLISHED_SMALL_JADE_BRICKS);
        modSlabBlock(SMBlocks.POLISHED_JADE_SHINGLE_SLAB, SMBlocks.POLISHED_JADE_SHINGLES);
        modSlabBlock(SMBlocks.POLISHED_JADE_TILE_SLAB, SMBlocks.POLISHED_JADE_TILES);
        modSlabBlock(SMBlocks.ROUGH_JADE_BRICK_SLAB, SMBlocks.ROUGH_JADE_BRICKS);
        modSlabBlock(SMBlocks.SMOOTHED_ROUGH_JADE_SLAB, SMBlocks.SMOOTHED_ROUGH_JADE);
        modSlabBlock(SMBlocks.ROUGH_JADE_TILE_SLAB, SMBlocks.ROUGH_JADE_TILES);
        modEggBlock(SMBlocks.TORTOISE_EGG);
        basicBlockWRenderType(SMBlocks.AMBER, "translucent");
        basicBlock(SMBlocks.AMBER_BRICKS);
        modSlabBlock(SMBlocks.AMBER_BRICK_SLAB, SMBlocks.AMBER_BRICKS);
        modWallBlock(SMBlocks.AMBER_BRICK_WALL, SMBlocks.AMBER_BRICKS);
        modStairsBlock(SMBlocks.AMBER_BRICK_STAIRS, SMBlocks.AMBER_BRICKS);
        basicBlock(SMBlocks.PETRIFIED_PLANKS);
        modLogBlock(SMBlocks.PETRIFIED_LOG);
        modLogBlock(SMBlocks.STRIPPED_PETRIFIED_LOG);
        modWoodBlock(SMBlocks.PETRIFIED_WOOD, SMBlocks.PETRIFIED_LOG);
        modWoodBlock(SMBlocks.STRIPPED_PETRIFIED_WOOD, SMBlocks.STRIPPED_PETRIFIED_LOG);
        modPressurePlateBlock(SMBlocks.PETRIFIED_PRESSURE_PLATE, SMBlocks.PETRIFIED_PLANKS);
        modTrapdoorBlock(SMBlocks.PETRIFIED_TRAPDOOR);
        modStairsBlock(SMBlocks.PETRIFIED_STAIRS, SMBlocks.PETRIFIED_PLANKS);
        modSlabBlock(SMBlocks.PETRIFIED_SLAB, SMBlocks.PETRIFIED_PLANKS);
        basicButtonBlock(SMBlocks.PETRIFIED_BUTTON, SMBlocks.PETRIFIED_PLANKS);
        modFenceGateBlock(SMBlocks.PETRIFIED_FENCE_GATE, SMBlocks.PETRIFIED_PLANKS);
        modFenceBlock(SMBlocks.PETRIFIED_FENCE, SMBlocks.PETRIFIED_PLANKS);
    }

    private void basicBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    private void basicBlockWRenderType(Supplier<? extends Block> block, String renderType) {
        ModelFile modelFile = models().cubeAll(name(block.get()), blockTexture(block.get())).renderType(renderType);
        getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(modelFile));
    }

    private void pillarBlock(Supplier<? extends Block> block, String topTexture) {
        axisBlock((RotatedPillarBlock) block.get(), modBlockLocation(name(block.get())), modBlockLocation(topTexture));
    }

    private void modLogBlock(Supplier<? extends Block> block) {
        this.logBlock((RotatedPillarBlock) block.get());
    }

    private void modWoodBlock(Supplier<? extends Block> block, Supplier<? extends Block> blockForTexture) {
        axisBlock((RotatedPillarBlock) block.get(),
                models().cubeColumn(name(block.get()), modBlockLocation(name(blockForTexture.get())), modBlockLocation(name(blockForTexture.get()))),
                models().cubeColumnHorizontal(name(block.get()), modBlockLocation(name(blockForTexture.get())), modBlockLocation(name(blockForTexture.get()))));
    }

    private void modFenceBlock(Supplier<? extends Block> block, Supplier<? extends Block> blockForTexture) {
        fenceBlock((FenceBlock) block.get(), modBlockLocation(name(blockForTexture.get())));
    }

    private void modFenceGateBlock(Supplier<? extends Block> block, Supplier<? extends Block> blockForTexture) {
        fenceGateBlock((FenceGateBlock) block.get(), modBlockLocation(name(blockForTexture.get())));
    }

    private void modPressurePlateBlock(Supplier<? extends Block> block, Supplier<? extends Block> blockForTexture) {
        pressurePlateBlock((PressurePlateBlock) block.get(), modBlockLocation(name(blockForTexture.get())));
    }

    private void modDoorBlock(Supplier<? extends Block> block) {
        doorBlock((DoorBlock) block.get(), modBlockLocation(name(block.get()) + "_bottom"), modBlockLocation(name(block.get()) + "_top"));
    }

    private void modTrapdoorBlock(Supplier<? extends Block> block) {
        trapdoorBlock((TrapDoorBlock) block.get(), modBlockLocation(name(block.get())), true);
    }

    private void signBlock(Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> pair, String texture) {
        simpleBlock(pair.getFirst().get(), models().sign(name(pair.getFirst().get()), modBlockLocation(texture)));
        simpleBlock(pair.getSecond().get(), models().sign(name(pair.getSecond().get()), modBlockLocation(texture)));
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

    private void flingerTotem(Supplier<? extends Block> block) {
        getVariantBuilder(block.get()).forAllStates(blockState -> {
            int honey = blockState.getValue(FlingerTotem.HONEY_AMOUNT);
            String state = "";
            for (int i = 1; i <= honey; i++) {
                state = "_" + honey;
            }

            ModelFile totemModel = models().cube(name(block.get()) + state,
                            modBlockLocation(name(block.get()) + "_top" + state),
                            modBlockLocation(name(block.get()) + "_top" + state),
                            modBlockLocation(name(block.get()) + "_front"),
                            modBlockLocation(name(block.get()) + "_back" + state),
                            modBlockLocation(name(block.get()) + "_right" + state),
                            modBlockLocation(name(block.get()) + "_left" + state))
                    .texture("particle", modBlockLocation(name(block.get()) + "_back"));

            return ConfiguredModel.builder()
                    .modelFile(totemModel)
                    .rotationY(((int) blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                    .build();
        });
    }

    private void basicButtonBlock(Supplier<? extends Block> block, Supplier<? extends Block> blockForTexture) {
        buttonBlock((ButtonBlock)block.get(), blockTexture(blockForTexture.get()));
    }

    private void modStairsBlock(Supplier<? extends Block> block, Supplier<? extends Block> blockForTexture) {
        stairsBlock((StairBlock) block.get(), blockTexture(blockForTexture.get()));
    }
    private void modWallBlock(Supplier<? extends Block> block, Supplier<? extends Block> blockForTexture) {
        wallBlock((WallBlock) block.get(), blockTexture(blockForTexture.get()));
    }

    private void modSlabBlock(Supplier<? extends Block> block, Supplier<? extends Block> blockForTexture) {
        slabBlock((SlabBlock) block.get(), blockTexture(blockForTexture.get()), blockTexture(blockForTexture.get()));
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
}
