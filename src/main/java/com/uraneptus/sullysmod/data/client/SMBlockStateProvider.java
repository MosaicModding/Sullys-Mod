package com.uraneptus.sullysmod.data.client;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.FlingerTotem;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.uraneptus.sullysmod.data.SMDatagenUtil.*;

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
        basicBlock(SMBlocks.JADE_BLOCK);
        basicBlock(SMBlocks.JADE_BRICKS);
        basicBlock(SMBlocks.CHISELED_JADE);
        pillarBlock(SMBlocks.JADE_PILLAR, name(SMBlocks.CHISELED_JADE.get()));
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
        modStairsBlock(SMBlocks.JADE_BRICK_STAIRS, SMBlocks.JADE_BRICKS);
        modStairsBlock(SMBlocks.ROUGH_JADE_BRICK_STAIRS, SMBlocks.ROUGH_JADE_BRICKS);
        modSlabBlock(SMBlocks.JADE_BRICK_SLAB, SMBlocks.JADE_BRICKS);
        modWallBlock(SMBlocks.JADE_BRICK_WALL, SMBlocks.JADE_BRICKS);
        modSlabBlock(SMBlocks.ROUGH_JADE_BRICK_SLAB, SMBlocks.ROUGH_JADE_BRICKS);
        modWallBlock(SMBlocks.ROUGH_JADE_BRICK_WALL, SMBlocks.ROUGH_JADE_BRICKS);
        modEggBlock(SMBlocks.TORTOISE_EGG);
        basicBlockWRenderType(SMBlocks.AMBER, "translucent");
        basicBlock(SMBlocks.AMBER_BRICKS);
        modSlabBlock(SMBlocks.AMBER_BRICK_SLAB, SMBlocks.AMBER_BRICKS);
        modWallBlock(SMBlocks.AMBER_BRICK_WALL, SMBlocks.AMBER_BRICKS);
        modStairsBlock(SMBlocks.AMBER_BRICK_STAIRS, SMBlocks.AMBER_BRICKS);
        basicBlock(SMBlocks.ROUGH_AMBER);
        basicBlock(SMBlocks.CHISELED_AMBER);
        basicBlock(SMBlocks.AMBER_LANTERN);
        pillarBlock(SMBlocks.AMBER_PILLAR, name(SMBlocks.AMBER_PILLAR.get()) + "_top");
        basicBlock(SMBlocks.PETRIFIED_PLANKS);
        modLogBlock(SMBlocks.PETRIFIED_LOG);
        modLogBlock(SMBlocks.STRIPPED_PETRIFIED_LOG);
        modWoodBlock(SMBlocks.PETRIFIED_WOOD, SMBlocks.PETRIFIED_LOG);
        modWoodBlock(SMBlocks.STRIPPED_PETRIFIED_WOOD, SMBlocks.STRIPPED_PETRIFIED_LOG);
        modPressurePlateBlock(SMBlocks.PETRIFIED_PRESSURE_PLATE, SMBlocks.PETRIFIED_PLANKS);
        modTrapdoorWithRenderType(SMBlocks.PETRIFIED_TRAPDOOR, "cutout");
        modStairsBlock(SMBlocks.PETRIFIED_STAIRS, SMBlocks.PETRIFIED_PLANKS);
        modSlabBlock(SMBlocks.PETRIFIED_SLAB, SMBlocks.PETRIFIED_PLANKS);
        basicButtonBlock(SMBlocks.PETRIFIED_BUTTON, SMBlocks.PETRIFIED_PLANKS);
        modFenceGateBlock(SMBlocks.PETRIFIED_FENCE_GATE, SMBlocks.PETRIFIED_PLANKS);
        modFenceBlock(SMBlocks.PETRIFIED_FENCE, SMBlocks.PETRIFIED_PLANKS);
        modSignBlock(SMBlocks.PETRIFIED_SIGN, SMBlocks.PETRIFIED_PLANKS);
        modHangingSignBlock(SMBlocks.PETRIFIED_HANGING_SIGN, SMBlocks.PETRIFIED_PLANKS);
        modDoorBlockWithRenderType(SMBlocks.PETRIFIED_DOOR, "cutout");
        plantWithPottedBlock(SMBlocks.PETRIFIED_SAPLING, SMBlocks.POTTED_PETRIFIED_SAPLING);
        itemStandBlock(SMBlocks.ITEM_STAND);
        ancientSkull(SMBlocks.CRACKED_ANCIENT_SKULL);
        ancientSkull(SMBlocks.CRESTED_ANCIENT_SKULL);
        ancientSkull(SMBlocks.FLATBILLED_ANCIENT_SKULL);
        ancientSkull(SMBlocks.GIGANTIC_ANCIENT_SKULL);
        ancientSkull(SMBlocks.HORNED_ANCIENT_SKULL);
        ancientSkull(SMBlocks.LONG_ANCIENT_SKULL);
        ancientSkull(SMBlocks.TINY_ANCIENT_SKULL);
        ancientSkull(SMBlocks.WIDE_ANCIENT_SKULL);
        ancientSkull(SMBlocks.RIBBED_ANCIENT_SKULL);
        ancientSkull(SMBlocks.UNICORN_ANCIENT_SKULL);
        basicBlock(SMBlocks.JADE_LANTERN);
        basicBlock(SMBlocks.DIAMOND_LANTERN);
        basicBlock(SMBlocks.EMERALD_LANTERN);
        basicBlock(SMBlocks.LAPIS_LANTERN);
        basicBlock(SMBlocks.AMETHYST_LANTERN);
        basicBlock(SMBlocks.QUARTZ_LANTERN);
    }

    private void basicBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    private void basicBlockWRenderType(Supplier<? extends Block> block, String renderType) {
        ModelFile modelFile = models().cubeAll(name(block.get()), blockTexture(block.get())).renderType(renderType);
        getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(modelFile));
    }

    private void modCrossBlock(Supplier<? extends Block> block, String renderType) {
        getVariantBuilder(block.get()).forAllStates(blockState -> ConfiguredModel.builder()
                .modelFile(models().cross(name(block.get()), modBlockLocation(name(block.get()))).renderType(renderType)).build());
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

    private void modDoorBlockWithRenderType(Supplier<? extends Block> block, String rendertype) {
        doorBlockWithRenderType((DoorBlock) block.get(), modBlockLocation(name(block.get()) + "_bottom"), modBlockLocation(name(block.get()) + "_top"), rendertype);
    }

    private void modTrapdoorBlock(Supplier<? extends Block> block) {
        trapdoorBlock((TrapDoorBlock) block.get(), modBlockLocation(name(block.get())), true);
    }
    private void modTrapdoorWithRenderType(Supplier<? extends Block> block, String renderType) {
        trapdoorBlockWithRenderType((TrapDoorBlock)block.get(), modBlockLocation(name(block.get())), true, renderType);
    }

    private void modSignBlock(Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> pair, Supplier<? extends Block> blockForTexture) {
        signBlock(pair.getFirst().get(), pair.getSecond().get(), modBlockLocation(name(blockForTexture.get())));
    }

    private void modHangingSignBlock(Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> pair, Supplier<? extends Block> blockForTexture) {
        ModelFile sign = models().sign(name(pair.getFirst().get()), modBlockLocation(name(blockForTexture.get())));
        simpleBlock(pair.getFirst().get(), sign);
        simpleBlock(pair.getSecond().get(), sign);
    }

    private void plantWithPottedBlock(Supplier<? extends Block> plant, Supplier<? extends Block> potted_plant) {
        modCrossBlock(plant, "cutout");
        simpleBlock(potted_plant.get(), models().withExistingParent(name(potted_plant.get()), POTTED_CROSS)
                .texture(PLANT, modBlockLocation(name(plant.get()))).renderType("cutout"));
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

    private void itemStandBlock(Supplier<? extends Block> block) {
        ModelFile file = models().getBuilder(name(block.get()))
                .parent(new ModelFile.UncheckedModelFile("block/block"))
                .renderType("cutout")
                .texture("stick", vanillaBlockLocation(name(Blocks.OAK_PLANKS)))
                .texture("base", vanillaBlockLocation(name(Blocks.SMOOTH_STONE)))
                .texture("particle", "#base")
                .element().from(7, 1, 7).to(9, 10, 9)
                .face(Direction.NORTH).texture("#stick").uvs(6, 1, 8, 10).end()
                .face(Direction.EAST).texture("#stick").uvs(6, 1, 8, 10).end()
                .face(Direction.SOUTH).texture("#stick").uvs(6, 1, 8, 10).end()
                .face(Direction.WEST).texture("#stick").uvs(6, 1, 8, 10).end()
                .face(Direction.UP).texture("#stick").uvs(6, 0, 8, 2).end()
                .face(Direction.DOWN).texture("#stick").uvs(6, 0, 8, 2).end()
                .end()
                .element().from(2, 0, 2).to(14, 1, 14)
                .face(Direction.NORTH).texture("#base").uvs(15, 2, 1, 1).end()
                .face(Direction.EAST).texture("#base").uvs(15, 2, 1, 1).end()
                .face(Direction.SOUTH).texture("#base").uvs(15, 2, 1, 1).end()
                .face(Direction.WEST).texture("#base").uvs(15, 2, 1, 1).end()
                .face(Direction.UP).texture("#base").uvs(16, 16, 0, 0).end()
                .face(Direction.DOWN).texture("#base").uvs(16, 16, 0, 0).end()
                .end();

        getVariantBuilder(block.get()).forAllStates(blockState -> ConfiguredModel.builder()
                .modelFile(file).rotationY(((int) blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                .build());
    }

    private void ancientSkull(Pair<RegistryObject<Block>, RegistryObject<Block>> skull) {
        getVariantBuilder(skull.getFirst().get()).forAllStatesExcept(blockstate -> ConfiguredModel.builder().modelFile(models().getExistingFile(vanillaBlockLocation("skull"))).build(), SkullBlock.ROTATION);
        getVariantBuilder(skull.getSecond().get()).forAllStatesExcept(blockstate -> ConfiguredModel.builder().modelFile(models().getExistingFile(vanillaBlockLocation("skull"))).build(), WallSkullBlock.FACING);
    }
}
