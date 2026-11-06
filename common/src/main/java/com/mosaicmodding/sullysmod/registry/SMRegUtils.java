package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.SullysMod;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SMRegUtils {

    public static ResourceLocation path(String path) {
        return ResourceLocation.fromNamespaceAndPath(SullysMod.ID, path);
    }

    public static List<Supplier<Item>> getAllModItemsNoBlocks() {
        return getAllModItems().stream().filter(i -> !(i.get() instanceof BlockItem && !(i.get() instanceof SolidBucketItem))).toList();
    }

    public static List<Supplier<Item>> getAllModItems() {
        return getAllFromModReg(BuiltInRegistries.ITEM);
    }

    public static List<Supplier<Block>> getAllModBlocks() {
        return getAllFromModReg(BuiltInRegistries.BLOCK);
    }

    public static List<Supplier<EntityType<?>>> getAllModEntityTypes() {
        return getAllFromModReg(BuiltInRegistries.ENTITY_TYPE);
    }

    public static List<Supplier<MobEffect>> getAllModMobEffects() {
        return getAllFromModReg(BuiltInRegistries.MOB_EFFECT);
    }

    public static <T> List<Supplier<T>> getAllFromModReg(Registry<T> registry) {
        var list = new ArrayList<Supplier<T>>();
        var modLocations = registry.keySet().stream().filter(r -> r.getNamespace().equals(SullysMod.ID)).toList();
        for (ResourceLocation location : modLocations) {
            Supplier<T> item = () -> registry.get(location);
            list.add(item);
        }
        return list;
    }
}
