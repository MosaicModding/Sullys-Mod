package com.uraneptus.sullysmod.data.server.builtin;

import com.uraneptus.sullysmod.core.other.SMFeatureDefinitions;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class SMPlacedFeaturesProvider {
    public static void create(BootstapContext<PlacedFeature> context) {
        register(context, SMFeatureDefinitions.PLACED_JADE_ORE, addOreFeature(context, SMFeatureDefinitions.CONFIGURED_JADE_ORE, -16, 112, 16));
        register(context, SMFeatureDefinitions.PLACED_PETRIFIED_TREE_SMALL, addFeaturePlacement(context, SMFeatureDefinitions.CONFIGURED_PETRIFIED_TREE_SMALL, PlacementUtils.filteredByBlockSurvival(SMBlocks.PETRIFIED_SAPLING.get())));
        register(context, SMFeatureDefinitions.PLACED_PETRIFIED_TREE, addFeaturePlacement(context, SMFeatureDefinitions.CONFIGURED_PETRIFIED_TREE,
                RarityFilter.onAverageOnceEvery(5), //TODO adjust this when all variants are added (set to 25)
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(0)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                /*RandomOffsetPlacement.vertical(ConstantInt.of(1)),*/
                BiomeFilter.biome()
        ));
        register(context, SMFeatureDefinitions.PLACED_ARTIFACT_GRAVEL, addFeaturePlacement(context, SMFeatureDefinitions.CONFIGURED_ARTIFACT_GRAVEL,
                CountPlacement.of(40),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(62)),
                BiomeFilter.biome()
        ));
    }

    private static PlacedFeature addOreFeature(BootstapContext<PlacedFeature> context, ResourceKey<ConfiguredFeature<?, ?>> resourceKey, int minHeight, int maxHeight, int count) {
        return addFeaturePlacement(context, resourceKey, HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)), CountPlacement.of(count), InSquarePlacement.spread(), BiomeFilter.biome());
    }

    private static PlacedFeature addFeaturePlacement(BootstapContext<PlacedFeature> context, ResourceKey<ConfiguredFeature<?, ?>> resourceKey, PlacementModifier... placementModifiers) {
        return new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).get(resourceKey).orElseThrow(), List.of(placementModifiers));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> featureKey, PlacedFeature feature) {
        context.register(featureKey, feature);
    }
}
