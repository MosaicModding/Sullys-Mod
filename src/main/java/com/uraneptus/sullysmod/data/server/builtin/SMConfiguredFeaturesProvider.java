package com.uraneptus.sullysmod.data.server.builtin;

import com.google.common.collect.ImmutableList;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.levelgen.PetrifiedTreeConfig;
import com.uraneptus.sullysmod.common.levelgen.PetrifiedTreeGravelDecorator;
import com.uraneptus.sullysmod.core.other.SMFeatureDefinitions;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

public class SMConfiguredFeaturesProvider {
    private static final RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    private static final RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    private static final List<OreConfiguration.TargetBlockState> JADE_ORE_TARGET_LIST = List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, SMBlocks.JADE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, SMBlocks.DEEPSLATE_JADE_ORE.get().defaultBlockState()));

    static Holder<ConfiguredFeature<?, ?>> amberBlobConfig = Holder.direct(new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(SMBlocks.AMBER.get().defaultBlockState()))); //TODO add custom config to include entities
    static Holder<PlacedFeature> amberBlobPlacement = Holder.direct(new PlacedFeature(amberBlobConfig, List.of(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(Blocks.AIR))))));

    public static void create(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, SMFeatureDefinitions.CONFIGURED_JADE_ORE, () -> addOreConfig(JADE_ORE_TARGET_LIST, 10));
        register(context, SMFeatureDefinitions.CONFIGURED_PETRIFIED_TREE_SMALL, () -> addTreeConfig(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(SMBlocks.PETRIFIED_LOG.get()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).decorators(ImmutableList.of(new PetrifiedTreeGravelDecorator()))));
        register(context, SMFeatureDefinitions.CONFIGURED_PETRIFIED_TREE, () -> new ConfiguredFeature<>(SMFeatures.PETRIFIED_TREE.get(), new PetrifiedTreeConfig(List.of(
                SullysMod.modPrefix("petrified/big0"),
                SullysMod.modPrefix("petrified/big1"),
                SullysMod.modPrefix("petrified/ground0"),
                SullysMod.modPrefix("petrified/ground1"),
                SullysMod.modPrefix("petrified/ground2"),
                SullysMod.modPrefix("petrified/ground_small0"),
                SullysMod.modPrefix("petrified/ground_small1"),
                SullysMod.modPrefix("petrified/ground_small2"),
                SullysMod.modPrefix("petrified/ground_small3"),
                SullysMod.modPrefix("petrified/hollow"),
                SullysMod.modPrefix("petrified/middle0"),
                SullysMod.modPrefix("petrified/middle1"),
                SullysMod.modPrefix("petrified/side0"),
                SullysMod.modPrefix("petrified/side1"),
                SullysMod.modPrefix("petrified/side2"),
                SullysMod.modPrefix("petrified/side3"),
                SullysMod.modPrefix("petrified/small0"),
                SullysMod.modPrefix("petrified/small1"),
                SullysMod.modPrefix("petrified/small2"),
                SullysMod.modPrefix("petrified/small3")),
                amberBlobPlacement
                )));
        register(context, SMFeatureDefinitions.CONFIGURED_ARTIFACT_GRAVEL, () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(32, 4, 2, PlacementUtils.filtered(SMFeatures.ARTIFACT_GRAVEL.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SUSPICIOUS_GRAVEL)), BlockPredicate.allOf(BlockPredicate.matchesBlocks(Blocks.GRAVEL), BlockPredicate.solid(Direction.DOWN.getNormal()), BlockPredicate.matchesBlocks(Direction.UP.getNormal(), Blocks.AIR))))));

    }

    private static ConfiguredFeature<?, ?> addOreConfig(List<OreConfiguration.TargetBlockState> targetList, int veinSize) {
        return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targetList, veinSize));
    }

    private static ConfiguredFeature<?, ?> addTreeConfig(TreeConfiguration.TreeConfigurationBuilder treeBuilder) {
        return new ConfiguredFeature<>(Feature.TREE, treeBuilder.build());
    }

    private static void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> featureKey, Supplier<? extends ConfiguredFeature<?, ?>> feature) {
        context.register(featureKey, feature.get());
    }
}
