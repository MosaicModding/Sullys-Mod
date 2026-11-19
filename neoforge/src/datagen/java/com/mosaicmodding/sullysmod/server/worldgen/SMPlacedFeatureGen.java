package com.mosaicmodding.sullysmod.server.worldgen;

import com.google.common.collect.ImmutableList;
import com.mosaicmodding.sullysmod.registry.SMBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

import static com.mosaicmodding.sullysmod.registry.datapack.SMWorldgenFeatureDefinitions.CONFIGURED_PETRIFIED_TREE;
import static com.mosaicmodding.sullysmod.registry.datapack.SMWorldgenFeatureDefinitions.CONFIGURED_PETRIFIED_TREE_SAPLING;

public class SMPlacedFeatureGen {

    public static void add(BootstrapContext<PlacedFeature> context) {

        registerBasicPlacedFeature(context, CONFIGURED_PETRIFIED_TREE_SAPLING, PlacementUtils.filteredByBlockSurvival(SMBlocks.PETRIFIED_WOOD_BLOCK_SET.getSapling().get()));
        registerBasicPlacedFeature(context, CONFIGURED_PETRIFIED_TREE,
                RarityFilter.onAverageOnceEvery(7),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(0)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                BiomeFilter.biome()
        );
    }

    private static PlacedFeature orePlacement(Holder<ConfiguredFeature<?, ?>> configureFeature, int minHeight, int maxHeight, int count, PlacementModifier... extraModifiers) {
        final List<PlacementModifier> modifiers = ImmutableList.<PlacementModifier>builder().add(
                        CountPlacement.of(count),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome())
                .add(extraModifiers)
                .build();
        return new PlacedFeature(configureFeature, modifiers);
    }

    private static PlacedFeature addFeaturePlacement(BootstrapContext<PlacedFeature> context, ResourceKey<ConfiguredFeature<?, ?>> config, PlacementModifier... placementModifiers) {
        return new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).get(config).orElseThrow(), List.of(placementModifiers));
    }

    private static void registerBasicPlacedFeature(BootstrapContext<PlacedFeature> context, Pair<ResourceKey<ConfiguredFeature<?, ?>>, ResourceKey<PlacedFeature>> features, PlacementModifier... placementModifiers) {
        context.register(features.getRight(), addFeaturePlacement(context, features.getLeft(), placementModifiers));
    }
}
