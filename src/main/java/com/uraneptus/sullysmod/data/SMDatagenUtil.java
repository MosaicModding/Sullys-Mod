package com.uraneptus.sullysmod.data;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@SuppressWarnings("unused")
public class SMDatagenUtil {
    public static final String LAYER0 = "layer0";
    public static final String PLANT = "plant";
    public static final String ENTITY = "builtin/entity";
    public static final ResourceLocation POTTED_CROSS  = vanillaBlockLocation("flower_pot_cross");
    public static final ResourceLocation GENERATED = vanillaItemLocation("generated");
    public static final ResourceLocation HANDHELD = vanillaItemLocation("handheld");
    public static final ResourceLocation SPAWN_EGG = vanillaItemLocation("template_spawn_egg");

    //Quark Flag (We're going to need this a fair amount once that wood gets added)
    public static ResourceLocation QUARK_FLAG = SullysMod.blueprintPrefix("quark_flag");

    @SafeVarargs
    public static HolderSet<PlacedFeature> getPlacedHolderSet(BootstapContext<?> context, ResourceKey<PlacedFeature>... placedFeatures) {
        List<Holder<PlacedFeature>> holders = new ObjectArrayList<>();
        for (ResourceKey<PlacedFeature> feature : placedFeatures) {
            holders.add(context.lookup(Registries.PLACED_FEATURE).getOrThrow(feature));
        }
        return HolderSet.direct(holders);
    }

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

    public static ResourceLocation craftingPath(String name) {
        return SullysMod.modPrefix("crafting/" + name);
    }

    public static ResourceLocation smeltingPath(String name) {
        return SullysMod.modPrefix("smelting/" + name);
    }

    public static ResourceLocation blastingPath(String name) {
        return SullysMod.modPrefix("blasting/" + name);
    }

    public static ResourceLocation smokingPath(String name) {
        return SullysMod.modPrefix("smoking/" + name);
    }

    public static ResourceLocation campfire_cookingPath(String name) {
        return SullysMod.modPrefix("campfire_cooking/" + name);
    }

    public static ResourceLocation stonecuttingPath(String name) {
        return SullysMod.modPrefix("stonecutting/" + name);
    }

    public static ResourceLocation smithingPath(String name) {
        return SullysMod.modPrefix("smithing/" + name);
    }
}