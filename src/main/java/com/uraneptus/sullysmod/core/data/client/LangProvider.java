package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangProvider extends LanguageProvider {

    public LangProvider(DataGenerator gen) {
        super(gen, SullysMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(SMBlocks.JADE_ORE.get(), "Jade Ore");
        add(SMBlocks.DEEPSLATE_JADE_ORE.get(), "Deepslate Jade Ore");
        add(SMBlocks.RAW_JADE_BLOCK.get(), "Raw Jade Block");
        add(SMBlocks.RAW_JADE_BRICKS.get(), "Raw Jade Bricks");
        add(SMBlocks.RAW_JADE_TILE.get(), "Raw Jade Tile");
        add(SMBlocks.RAW_JADE_TILES.get(), "Raw Jade Tiles");
        add(SMBlocks.JADE_BRICKS.get(), "Jade Bricks");
        add(SMBlocks.SMALL_JADE_BRICKS.get(), "Small Jade Bricks");
        add(SMBlocks.JADE_SHINGLES.get(), "Jade Shingles");
        add(SMBlocks.JADE_TILES.get(), "Jade Tiles");
        add(SMBlocks.CHISELED_JADE.get(), "Chiseled Jade");
        add(SMBlocks.JADE_PILLAR.get(), "Jade Pillar");
        add(SMBlocks.JADE_TOTEM.get(), "Jade Totem");
        add(SMItems.RAW_JADE.get(), "Raw Jade");
        add(SMItems.JADE.get(), "Jade");
        add(SMItems.LANTERNFISH_BUCKET.get(), "Bucket of Lanternfish");
        add(SMItems.LANTERNFISH_SPAWN_EGG.get(), "Lanternfish Spawn Egg");
        add(SMItems.RAW_LANTERNFISH.get(), "Raw Lanternfish");
        add(SMItems.COOKED_LANTERNFISH.get(), "Cooked Lanternfish");

        System.out.println("LANGUAGE GENERATION COMPLETE");
    }
}
