package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

import static com.uraneptus.sullysmod.core.data.SMDatagenUtil.*;

@SuppressWarnings({"unused", "SameParameterValue"})
public class SMItemModelProvider extends ItemModelProvider {

    public SMItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SullysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicBlockItem(SMBlocks.JADE_ORE);
        basicBlockItem(SMBlocks.DEEPSLATE_JADE_ORE);
        basicBlockItem(SMBlocks.ROUGH_JADE_BLOCK);
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICKS);
        basicBlockItem(SMBlocks.SMOOTHED_ROUGH_JADE);
        basicBlockItem(SMBlocks.ROUGH_JADE_TILES);
        basicBlockItem(SMBlocks.POLISHED_JADE_BLOCK);
        basicBlockItem(SMBlocks.POLISHED_JADE_BRICKS);
        basicBlockItem(SMBlocks.POLISHED_SMALL_JADE_BRICKS);
        basicBlockItem(SMBlocks.POLISHED_JADE_SHINGLES);
        basicBlockItem(SMBlocks.POLISHED_JADE_TILES);
        basicBlockItem(SMBlocks.POLISHED_CHISELED_JADE);
        basicBlockItem(SMBlocks.POLISHED_JADE_PILLAR);
        basicBlockItem(SMBlocks.JADE_TOTEM);
        basicBlockItem(SMBlocks.JADE_FLINGER_TOTEM);
        basicItem(SMItems.ROUGH_JADE);
        basicItem(SMItems.POLISHED_JADE);
        basicItem(SMItems.LANTERNFISH_BUCKET);
        basicSpawnEggItem(SMItems.LANTERNFISH_SPAWN_EGG);
        basicSpawnEggItem(SMItems.TORTOISE_SPAWN_EGG);
        basicSpawnEggItem(SMItems.RASCAL_SPAWN_EGG);
        basicItem(SMItems.LANTERNFISH);
        basicSpawnEggItem(SMItems.CHAMELEON_SPAWN_EGG);
        basicItem(SMItems.COOKED_LANTERNFISH);
        blockItemWithItemTexture(SMBlocks.TORTOISE_EGG);
        copperButtonBlockItem(SMBlocks.COPPER_BUTTON, COPPER_BLOCK);
        copperButtonBlockItem(SMBlocks.EXPOSED_COPPER_BUTTON, EXPOSED_COPPER);
        copperButtonBlockItem(SMBlocks.WEATHERED_COPPER_BUTTON, WEATHERED_COPPER);
        copperButtonBlockItem(SMBlocks.OXIDIZED_COPPER_BUTTON, OXIDIZED_COPPER);
        copperButtonBlockItem(SMBlocks.WAXED_COPPER_BUTTON, COPPER_BLOCK);
        copperButtonBlockItem(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON, EXPOSED_COPPER);
        copperButtonBlockItem(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON, WEATHERED_COPPER);
        copperButtonBlockItem(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON, OXIDIZED_COPPER);
        basicBlockItem(SMBlocks.POLISHED_JADE_BRICK_STAIRS);
        basicBlockItem(SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS);
        basicBlockItem(SMBlocks.POLISHED_JADE_SHINGLE_STAIRS);
        basicBlockItem(SMBlocks.POLISHED_JADE_TILE_STAIRS);
        basicBlockItem(SMBlocks.POLISHED_JADE_BRICK_SLAB);
        basicBlockItem(SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB);
        basicBlockItem(SMBlocks.POLISHED_JADE_SHINGLE_SLAB);
        basicBlockItem(SMBlocks.POLISHED_JADE_TILE_SLAB);
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICK_STAIRS);
        basicBlockItem(SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS);
        basicBlockItem(SMBlocks.ROUGH_JADE_TILE_STAIRS);
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICK_SLAB);
        basicBlockItem(SMBlocks.SMOOTHED_ROUGH_JADE_SLAB);
        basicBlockItem(SMBlocks.ROUGH_JADE_TILE_SLAB);
        basicBlockItem(SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB);
        basicBlockItem(SMBlocks.POLISHED_SMALL_JADE_BRICK_VERTICAL_SLAB);
        basicBlockItem(SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB);
        basicBlockItem(SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB);
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB);
        basicBlockItem(SMBlocks.SMOOTHED_ROUGH_JADE_VERTICAL_SLAB);
        basicBlockItem(SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB);
        basicItem(SMItems.MUSIC_DISC_SCOUR);
        jadeShieldItem(SMItems.JADE_SHIELD);
        basicItem(SMItems.LANTERNFISH_SLICE);
        basicItem(SMItems.COOKED_LANTERNFISH_SLICE);
        basicItem(SMItems.LANTERNFISH_ROLL);
        basicItem(SMItems.CAVE_CHUM_BUCKET);
        basicItem(SMItems.TORTOISE_SCUTE);
        basicItem(SMItems.TORTOISE_SHELL);
    }

    private void basicBlockItem(Supplier<? extends Block> blockForItem) {
        withExistingParent(name(blockForItem.get()), modBlockLocation(name(blockForItem.get())));
    }

    private void basicItem(Supplier<? extends Item> item) {
        basicItem(item.get());
    }

    private void blockItemWithBlockTexture(Supplier<? extends Block> blockForItem) {
        withExistingParent(name(blockForItem.get()), GENERATED).texture(LAYER0, modBlockLocation(name(blockForItem.get())));
    }

    private void blockItemWithItemTexture(Supplier<? extends Block> blockForItem) {
        basicItem(blockForItem.get().asItem());
    }

    private void copperButtonBlockItem(Supplier<? extends Block> blockForItem, String texture) {
        buttonInventory(name(blockForItem.get()), vanillaBlockLocation(texture));
    }

    private void basicSpawnEggItem(Supplier<? extends Item> item) {
        withExistingParent(name(item.get()), SPAWN_EGG);
    }

    private void jadeShieldItem(Supplier<? extends Item> item) {
        getBuilder(name(item.get()) + "_blocking")
                .parent(new ModelFile.UncheckedModelFile("builtin/entity"))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("particle", vanillaBlockLocation(DARK_OAK_PLANKS))
                .transforms()
                .transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND).rotation(45, 135, 0).translation(3.51F, 11, -2).end()
                .transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND).rotation(45, 135, 0).translation(13.51F, 3, 5).end()
                .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND).rotation(0, 180, -5).translation(-15, 5, -11).scale(1.25F, 1.25F, 1.25F).end()
                .transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND).rotation(0, 180, -5).translation(5, 5, -11).scale(1.25F, 1.25F, 1.25F).end()
                .transform(ItemTransforms.TransformType.GUI).rotation(15, -25, -5).translation(2, 2.5F, 0).scale(0.65F, 0.65F, 0.65F).end()
                .end();

        getBuilder(name(item.get()))
                .parent(new ModelFile.UncheckedModelFile("builtin/entity"))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("particle", vanillaBlockLocation(DARK_OAK_PLANKS))
                .transforms()
                .transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND).rotation(0, 90, 0).translation(10, 6, -4).end()
                .transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND).rotation(0, 90, 0).translation(10, 6, 12).end()
                .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND).rotation(0, 180, 5).translation(-10, 2, -10).scale(1.25F, 1.25F, 1.25F).end()
                .transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND).rotation(0, 180, 5).translation(10, 0, -10).scale(1.25F, 1.25F, 1.25F).end()
                .transform(ItemTransforms.TransformType.GUI).rotation(15, -25, -5).translation(2, 2.5F, 0).scale(0.65F, 0.65F, 0.65F).end()
                .transform(ItemTransforms.TransformType.FIXED).rotation(0, 180, 0).translation(-4.5F, 4.5F, -5).scale(0.55F, 0.55F, 0.55F).end()
                .transform(ItemTransforms.TransformType.GROUND).rotation(0, 0, 0).translation(2, 4, 2).scale(0.25F, 0.25F, 0.25F).end()
                .end()
                .override().predicate(new ResourceLocation("blocking"), 1).model(new ModelFile.UncheckedModelFile(modItemLocation(name(item.get()) + "_blocking")));
    }
}
