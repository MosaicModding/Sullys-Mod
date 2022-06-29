package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.uraneptus.sullysmod.core.data.SMDatagenUtil.*;
public class SMItemModelProvider extends ItemModelProvider {

    public SMItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SullysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicBlockItem(SMBlocks.JADE_ORE.get());
        basicBlockItem(SMBlocks.DEEPSLATE_JADE_ORE.get());
        basicBlockItem(SMBlocks.ROUGH_JADE_BLOCK.get());
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICKS.get());
        basicBlockItem(SMBlocks.SMOOTHED_ROUGH_JADE.get());
        basicBlockItem(SMBlocks.ROUGH_JADE_TILES.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_BLOCK.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_BRICKS.get());
        basicBlockItem(SMBlocks.POLISHED_SMALL_JADE_BRICKS.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_SHINGLES.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_TILES.get());
        basicBlockItem(SMBlocks.POLISHED_CHISELED_JADE.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_PILLAR.get());
        basicBlockItem(SMBlocks.JADE_TOTEM.get());
        basicBlockItem(SMBlocks.JADE_FLINGER_TOTEM.get());
        basicItem(SMItems.ROUGH_JADE.get());
        basicItem(SMItems.POLISHED_JADE.get());
        basicItem(SMItems.LANTERNFISH_BUCKET.get());
        basicSpawnEggItem(SMItems.LANTERNFISH_SPAWN_EGG.get());
        basicSpawnEggItem(SMItems.TORTOISE_SPAWN_EGG.get());
        basicItem(SMItems.RAW_LANTERNFISH.get());
        basicItem(SMItems.COOKED_LANTERNFISH.get());
        basicItem(SMBlocks.TORTOISE_EGG.get().asItem());
        basicButtonBlockItem(SMBlocks.COPPER_BUTTON.get(), COPPER_BLOCK);
        basicButtonBlockItem(SMBlocks.EXPOSED_COPPER_BUTTON.get(), EXPOSED_COPPER);
        basicButtonBlockItem(SMBlocks.WEATHERED_COPPER_BUTTON.get(), WEATHERED_COPPER);
        basicButtonBlockItem(SMBlocks.OXIDIZED_COPPER_BUTTON.get(), OXIDIZED_COPPER);
        basicButtonBlockItem(SMBlocks.WAXED_COPPER_BUTTON.get(), COPPER_BLOCK);
        basicButtonBlockItem(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), EXPOSED_COPPER);
        basicButtonBlockItem(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), WEATHERED_COPPER);
        basicButtonBlockItem(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), OXIDIZED_COPPER);
        basicBlockItem(SMBlocks.POLISHED_JADE_BRICK_STAIRS.get());
        basicBlockItem(SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_TILE_STAIRS.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_BRICK_SLAB.get());
        basicBlockItem(SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_TILE_SLAB.get());
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICK_STAIRS.get());
        basicBlockItem(SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS.get());
        basicBlockItem(SMBlocks.ROUGH_JADE_TILE_STAIRS.get());
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICK_SLAB.get());
        basicBlockItem(SMBlocks.SMOOTHED_ROUGH_JADE_SLAB.get());
        basicBlockItem(SMBlocks.ROUGH_JADE_TILE_SLAB.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB.get());
        basicBlockItem(SMBlocks.SMALL_POLISHED_JADE_BRICK_VERTICAL_SLAB.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB.get());
        basicBlockItem(SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB.get());
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB.get());
        basicBlockItem(SMBlocks.SMOOTH_ROUGH_JADE_VERTICAL_SLAB.get());
        basicBlockItem(SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB.get());


        SullysMod.LOGGER.info("ITEM MODEL GENERATION COMPLETE");
    }

    private void basicBlockItem(Block blockForItem) {
        withExistingParent(name(blockForItem), modBlockLocation(name(blockForItem)));
    }

    private void basicButtonBlockItem(Block blockForItem, String texture) {
        buttonInventory(name(blockForItem), vanillaBlockLocation(texture));
    }

    private void basicGeneratedItem(Item item) {
        withExistingParent(name(item), GENERATED).texture("layer0", modItemLocation(name(item)));
    }

    private void basicSpawnEggItem(Item item) {
        withExistingParent(name(item), "item/template_spawn_egg");
    }

}
