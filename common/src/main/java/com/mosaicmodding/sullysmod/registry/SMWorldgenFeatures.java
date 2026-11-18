package com.mosaicmodding.sullysmod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.apache.commons.lang3.tuple.Pair;

import static com.mosaicmodding.sullysmod.core.SMUtils.path;

public class SMWorldgenFeatures {

    public static final Pair<ResourceKey<ConfiguredFeature<?, ?>>, ResourceKey<PlacedFeature>> CONFIGURED_PETRIFIED_TREE_SMALL = registerConfigPlaced("petrified_tree_small");


    public static Pair<ResourceKey<ConfiguredFeature<?, ?>>, ResourceKey<PlacedFeature>> registerConfigPlaced(String name) {
        var configured = registerConfigured(name);
        var placed = registerPlaced(name);
        return Pair.of(configured, placed);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerConfigured(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, path(name));
    }

    public static ResourceKey<PlacedFeature> registerPlaced(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, path(name));
    }
}
