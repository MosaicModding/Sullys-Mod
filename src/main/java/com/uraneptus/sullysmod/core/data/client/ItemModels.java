package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.data.DatagenUtil;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SullysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicBlockItem(SMBlocks.JADE_ORE.get());
        basicBlockItem(SMBlocks.DEEPSLATE_JADE_ORE.get());
        basicBlockItem(SMBlocks.RAW_JADE_BLOCK.get());
        basicBlockItem(SMBlocks.RAW_JADE_BRICKS.get());
        basicBlockItem(SMBlocks.SMOOTH_RAW_JADE.get());
        basicBlockItem(SMBlocks.RAW_JADE_TILES.get());
        basicBlockItem(SMBlocks.JADE_BRICKS.get());
        basicBlockItem(SMBlocks.SMALL_JADE_BRICKS.get());
        basicBlockItem(SMBlocks.JADE_SHINGLES.get());
        basicBlockItem(SMBlocks.JADE_TILES.get());
        basicBlockItem(SMBlocks.CHISELED_JADE.get());
        basicBlockItem(SMBlocks.JADE_PILLAR.get());
        basicBlockItem(SMBlocks.JADE_TOTEM.get());
        basicItem(SMItems.RAW_JADE.get());
        basicItem(SMItems.JADE.get());
        basicItem(SMItems.LANTERNFISH_BUCKET.get());
        basicSpawnEggItem(SMItems.LANTERNFISH_SPAWN_EGG.get());
        basicItem(SMItems.RAW_LANTERNFISH.get());
        basicItem(SMItems.COOKED_LANTERNFISH.get());
        basicButtonBlockItem(SMBlocks.COPPER_BUTTON.get(), DatagenUtil.COPPER_BLOCK);
        basicButtonBlockItem(SMBlocks.EXPOSED_COPPER_BUTTON.get(), DatagenUtil.EXPOSED_COPPER);
        basicButtonBlockItem(SMBlocks.WEATHERED_COPPER_BUTTON.get(), DatagenUtil.WEATHERED_COPPER);
        basicButtonBlockItem(SMBlocks.OXIDIZED_COPPER_BUTTON.get(), DatagenUtil.OXIDIZED_COPPER);
        basicButtonBlockItem(SMBlocks.WAXED_COPPER_BUTTON.get(), DatagenUtil.COPPER_BLOCK);
        basicButtonBlockItem(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), DatagenUtil.EXPOSED_COPPER);
        basicButtonBlockItem(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), DatagenUtil.WEATHERED_COPPER);
        basicButtonBlockItem(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), DatagenUtil.OXIDIZED_COPPER);
        basicBlockItem(SMBlocks.JADE_BRICK_STAIRS.get());
        basicBlockItem(SMBlocks.SMALL_JADE_BRICK_STAIRS.get());
        basicBlockItem(SMBlocks.JADE_SHINGLE_STAIRS.get());
        basicBlockItem(SMBlocks.JADE_TILE_STAIRS.get());
        basicBlockItem(SMBlocks.JADE_BRICK_SLAB.get());
        basicBlockItem(SMBlocks.SMALL_JADE_BRICK_SLAB.get());
        basicBlockItem(SMBlocks.JADE_SHINGLE_SLAB.get());
        basicBlockItem(SMBlocks.JADE_TILE_SLAB.get());
        basicBlockItem(SMBlocks.RAW_JADE_BRICK_STAIRS.get());
        basicBlockItem(SMBlocks.SMOOTH_RAW_JADE_STAIRS.get());
        basicBlockItem(SMBlocks.RAW_JADE_TILE_STAIRS.get());
        basicBlockItem(SMBlocks.RAW_JADE_BRICK_SLAB.get());
        basicBlockItem(SMBlocks.SMOOTH_RAW_JADE_SLAB.get());
        basicBlockItem(SMBlocks.RAW_JADE_TILE_SLAB.get());

        SullysMod.LOGGER.info("ITEM MODEL GENERATION COMPLETE");
    }

    private void basicBlockItem(Block blockForItem) {
        withExistingParent(DatagenUtil.name(blockForItem), DatagenUtil.modBlockLocation(DatagenUtil.name(blockForItem)));
    }

    private void basicButtonBlockItem(Block blockForItem, String texture) {
        buttonInventory(DatagenUtil.name(blockForItem), DatagenUtil.vanillaBlockLocation(texture));
    }

    private void basicItem(Item item) {
        withExistingParent(DatagenUtil.name(item), DatagenUtil.GENERATED).texture("layer0", DatagenUtil.modItemLocation(DatagenUtil.name(item)));
    }

    private void basicSpawnEggItem(Item item) {
        withExistingParent(DatagenUtil.name(item), "item/template_spawn_egg");
    }

}
