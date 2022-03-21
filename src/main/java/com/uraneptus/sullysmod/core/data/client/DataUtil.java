package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.ModelProvider;

public class DataUtil {
    public static final String GENERATED = "item/generated";
    private static final String HANDHELD = "item/handheld";

    public static String name(Block block) {
        return block.getRegistryName().getPath();
    }

    public static String name(Item item) {
        return item.getRegistryName().getPath();
    }

    public static ResourceLocation modBlockLocation(String path) {
        return new ResourceLocation(SullysMod.MOD_ID, ModelProvider.BLOCK_FOLDER + "/" + path);
    }

    public static ResourceLocation modItemLocation(String path) {
        return new ResourceLocation(SullysMod.MOD_ID, ModelProvider.ITEM_FOLDER + "/" + path);
    }

    public static ResourceLocation vanillaBlockLocation(String path) {
        return new ResourceLocation("minecraft", ModelProvider.BLOCK_FOLDER + "/" + path);
    }

    public static ResourceLocation vanillaItemLocation(String path) {
        return new ResourceLocation("minecraft", ModelProvider.ITEM_FOLDER + "/" + path);
    }

    //Textures
    public static final String COPPER_BLOCK = name(Blocks.COPPER_BLOCK);
    public static final String EXPOSED_COPPER = name(Blocks.EXPOSED_COPPER);
    public static final String WEATHERED_COPPER = name(Blocks.WEATHERED_COPPER);
    public static final String OXIDIZED_COPPER = name(Blocks.OXIDIZED_COPPER);
    public static final String JADE_BRICKS = name(SMBlocks.JADE_BRICKS.get());
    public static final String SMALL_JADE_BRICKS = name(SMBlocks.SMALL_JADE_BRICKS.get());
    public static final String JADE_SHINGLES = name(SMBlocks.JADE_SHINGLES.get());
    public static final String JADE_TILES = name(SMBlocks.JADE_TILES.get());

}
