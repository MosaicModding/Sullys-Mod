package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class SMLangProvider extends LanguageProvider {

    public SMLangProvider(DataGenerator gen) {
        super(gen, SullysMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //Blocks
        add(SMBlocks.JADE_ORE.get(), "Jade Ore");
        add(SMBlocks.DEEPSLATE_JADE_ORE.get(), "Deepslate Jade Ore");
        add(SMBlocks.RAW_JADE_BLOCK.get(), "Block of Raw Jade");
        add(SMBlocks.RAW_JADE_BRICKS.get(), "Raw Jade Bricks");
        add(SMBlocks.SMOOTH_RAW_JADE.get(), "Smooth Raw Jade");
        add(SMBlocks.RAW_JADE_TILES.get(), "Raw Jade Tiles");
        add(SMBlocks.JADE_BRICKS.get(), "Jade Bricks");
        add(SMBlocks.SMALL_JADE_BRICKS.get(), "Small Jade Bricks");
        add(SMBlocks.JADE_SHINGLES.get(), "Jade Shingles");
        add(SMBlocks.JADE_TILES.get(), "Jade Tiles");
        add(SMBlocks.CHISELED_JADE.get(), "Chiseled Jade");
        add(SMBlocks.JADE_PILLAR.get(), "Jade Pillar");
        add(SMBlocks.JADE_TOTEM.get(), "Jade Totem");
        add(SMBlocks.COPPER_BUTTON.get(), "Copper Button");
        add(SMBlocks.EXPOSED_COPPER_BUTTON.get(), "Exposed Copper Button");
        add(SMBlocks.WEATHERED_COPPER_BUTTON.get(), "Weathered Copper Button");
        add(SMBlocks.OXIDIZED_COPPER_BUTTON.get(), "Oxidized Copper Button");
        add(SMBlocks.WAXED_COPPER_BUTTON.get(), "Waxed Copper Button");
        add(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), "Waxed Exposed Copper Button");
        add(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), "Waxed Weathered Copper Button");
        add(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), "Waxed Oxidized Copper Button");
        add(SMBlocks.JADE_BRICK_STAIRS.get(), "Jade Brick Stairs");
        add(SMBlocks.SMALL_JADE_BRICK_STAIRS.get(), "Small Jade Brick Stairs");
        add(SMBlocks.JADE_SHINGLE_STAIRS.get(), "Jade Shingle Stairs");
        add(SMBlocks.JADE_TILE_STAIRS.get(), "Jade Tile Stairs");
        add(SMBlocks.JADE_BRICK_SLAB.get(), "Jade Brick Slab");
        add(SMBlocks.SMALL_JADE_BRICK_SLAB.get(), "Small Jade Brick Slab");
        add(SMBlocks.JADE_SHINGLE_SLAB.get(), "Jade Shingle Slab");
        add(SMBlocks.JADE_TILE_SLAB.get(), "Jade Tile Slab");
        add(SMBlocks.RAW_JADE_BRICK_STAIRS.get(), "Raw Jade Brick Stairs");
        add(SMBlocks.SMOOTH_RAW_JADE_STAIRS.get(), "Smooth Raw Jade Stairs");
        add(SMBlocks.RAW_JADE_TILE_STAIRS.get(), "Raw Jade Tile Slabs");
        add(SMBlocks.RAW_JADE_BRICK_SLAB.get(), "Raw Jade Brick Slab");
        add(SMBlocks.SMOOTH_RAW_JADE_SLAB.get(), "Smooth Raw Jade Slab");
        add(SMBlocks.RAW_JADE_TILE_SLAB.get(), "Raw Jade Tile Slab");

        //Items
        add(SMItems.RAW_JADE.get(), "Raw Jade");
        add(SMItems.JADE.get(), "Jade");
        add(SMItems.LANTERNFISH_BUCKET.get(), "Bucket of Lanternfish");
        add(SMItems.LANTERNFISH_SPAWN_EGG.get(), "Lanternfish Spawn Egg");
        add(SMItems.RAW_LANTERNFISH.get(), "Raw Lanternfish");
        add(SMItems.COOKED_LANTERNFISH.get(), "Cooked Lanternfish");

        SullysMod.LOGGER.info("LANGUAGE GENERATION COMPLETE");
    }
}
