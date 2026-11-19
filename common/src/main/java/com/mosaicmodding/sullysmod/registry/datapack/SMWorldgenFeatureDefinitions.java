package com.mosaicmodding.sullysmod.registry.datapack;

import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.core.events.BiomeFeatureAdditionEvent;
import com.mosaicmodding.sullysmod.core.events.BiomeSpawnAdditionEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.apache.commons.lang3.tuple.Pair;

import static com.mosaicmodding.sullysmod.core.SMUtils.path;

public class SMWorldgenFeatureDefinitions {

    public static final Pair<ResourceKey<ConfiguredFeature<?, ?>>, ResourceKey<PlacedFeature>> CONFIGURED_PETRIFIED_TREE_SAPLING = registerConfigPlaced("petrified_tree_sapling");
    public static final Pair<ResourceKey<ConfiguredFeature<?, ?>>, ResourceKey<PlacedFeature>> CONFIGURED_PETRIFIED_TREE = registerConfigPlaced("petrified_tree");
    public static final Pair<ResourceKey<ConfiguredFeature<?, ?>>, ResourceKey<PlacedFeature>> ARTIFACT_GRAVEL = registerConfigPlaced("artifact_gravel");

    public static void registerBiomeFeatureAdditions(BiomeFeatureAdditionEvent event) {
        event.register(SMTags.Biomes.PETRIFIED_TREE_GENERATES_IN, CONFIGURED_PETRIFIED_TREE.getRight(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, SMCommonConfig.PETRIFIED_TREES.getSecond());
    }

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
