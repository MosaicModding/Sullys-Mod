package com.uraneptus.sullysmod.core.data;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class SMDatagenUtil {
    public static final String GENERATED = "item/generated";
    public static final String HANDHELD = "item/handheld";
    public static final String SPAWN_EGG = "item/template_spawn_egg";

    public static String name(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    public static String name(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    public static ResourceLocation modBlockLocation(String path) {
        return SullysMod.modPrefix(ModelProvider.BLOCK_FOLDER + "/" + path);
    }

    public static ResourceLocation modItemLocation(String path) {
        return SullysMod.modPrefix(ModelProvider.ITEM_FOLDER + "/" + path);
    }

    public static ResourceLocation vanillaBlockLocation(String path) {
        return new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/" + path);
    }

    public static ResourceLocation vanillaItemLocation(String path) {
        return new ResourceLocation(ModelProvider.ITEM_FOLDER + "/" + path);
    }

    public static ResourceLocation blueprintBlockLocation(String path) {
        return SullysMod.blueprintPrefix(ModelProvider.BLOCK_FOLDER + "/" + path);
    }

    public static ResourceLocation blueprintItemLocation(String path) {
        return SullysMod.blueprintPrefix(ModelProvider.ITEM_FOLDER + "/" + path);
    }

    //Quark Flag (We're gonna need this a fair amount once that wood gets added)
    public static ResourceLocation QUARK_FLAG = SullysMod.blueprintPrefix("quark_flag");

    //Textures
    public static final String COPPER_BLOCK = name(Blocks.COPPER_BLOCK);
    public static final String EXPOSED_COPPER = name(Blocks.EXPOSED_COPPER);
    public static final String WEATHERED_COPPER = name(Blocks.WEATHERED_COPPER);
    public static final String OXIDIZED_COPPER = name(Blocks.OXIDIZED_COPPER);
    public static final String JADE_BRICKS = name(SMBlocks.POLISHED_JADE_BRICKS.get());
    public static final String SMALL_JADE_BRICKS = name(SMBlocks.POLISHED_SMALL_JADE_BRICKS.get());
    public static final String JADE_SHINGLES = name(SMBlocks.POLISHED_JADE_SHINGLES.get());
    public static final String JADE_TILES = name(SMBlocks.POLISHED_JADE_TILES.get());
    public static final String ROUGH_JADE_BRICKS = name(SMBlocks.ROUGH_JADE_BRICKS.get());
    public static final String SMOOTHED_ROUGH_JADE = name(SMBlocks.SMOOTHED_ROUGH_JADE.get());
    public static final String ROUGH_JADE_TILES = name(SMBlocks.ROUGH_JADE_TILES.get());
    public static final String POLISHED_CHISELED_JADE = name(SMBlocks.POLISHED_CHISELED_JADE.get());
}
