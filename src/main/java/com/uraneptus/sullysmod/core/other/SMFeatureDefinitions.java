package com.uraneptus.sullysmod.core.other;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class SMFeatureDefinitions {
    //CONFIGURED
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_JADE_ORE = ResourceKey.create(Registries.CONFIGURED_FEATURE, SullysMod.modPrefix("jade_ore"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_PETRIFIED_TREE_SMALL = ResourceKey.create(Registries.CONFIGURED_FEATURE, SullysMod.modPrefix("petrified_tree_small"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_PETRIFIED_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, SullysMod.modPrefix("petrified_tree"));

    //PLACED
    public static final ResourceKey<PlacedFeature> PLACED_JADE_ORE = ResourceKey.create(Registries.PLACED_FEATURE, SullysMod.modPrefix("jade_ore"));
    public static final ResourceKey<PlacedFeature> PLACED_PETRIFIED_TREE_SMALL = ResourceKey.create(Registries.PLACED_FEATURE, SullysMod.modPrefix("petrified_tree_small"));
    public static final ResourceKey<PlacedFeature> PLACED_PETRIFIED_TREE = ResourceKey.create(Registries.PLACED_FEATURE, SullysMod.modPrefix("petrified_tree"));
}
