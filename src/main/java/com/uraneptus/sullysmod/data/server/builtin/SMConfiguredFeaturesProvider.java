package com.uraneptus.sullysmod.data.server.builtin;

import com.google.common.collect.ImmutableList;
import com.uraneptus.sullysmod.common.levelgen.PetrifiedTreeGravelDecorator;
import com.uraneptus.sullysmod.common.levelgen.configs.PetrifiedTreeConfig;
import com.uraneptus.sullysmod.core.other.SMFeatureDefinitions;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMFeatures;
import com.uraneptus.sullysmod.core.registry.SMPetrifiedTreeVariants;
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

    static Holder<ConfiguredFeature<?, ?>> amberBlobConfig = Holder.direct(new ConfiguredFeature<>(SMFeatures.AMBER_BLOB.get(), NoneFeatureConfiguration.INSTANCE));
    static Holder<PlacedFeature> amberBlobPlacement = Holder.direct(new PlacedFeature(amberBlobConfig, List.of(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(Blocks.AIR))))));

    public static void create(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, SMFeatureDefinitions.CONFIGURED_JADE_ORE, () -> addOreConfig(JADE_ORE_TARGET_LIST, 10));
        register(context, SMFeatureDefinitions.CONFIGURED_PETRIFIED_TREE_SMALL, () -> addTreeConfig(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(SMBlocks.PETRIFIED_LOG.get()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).decorators(ImmutableList.of(new PetrifiedTreeGravelDecorator()))));
        register(context, SMFeatureDefinitions.CONFIGURED_PETRIFIED_TREE, () -> new ConfiguredFeature<>(SMFeatures.PETRIFIED_TREE.get(), new PetrifiedTreeConfig(List.of(
                SMPetrifiedTreeVariants.GROUND0,
                SMPetrifiedTreeVariants.GROUND1,
                SMPetrifiedTreeVariants.GROUND2,
                SMPetrifiedTreeVariants.GROUND_SMALL0,
                SMPetrifiedTreeVariants.GROUND_SMALL1,
                SMPetrifiedTreeVariants.GROUND_SMALL2,
                SMPetrifiedTreeVariants.GROUND_SMALL3,
                SMPetrifiedTreeVariants.SMALL0,
                SMPetrifiedTreeVariants.SMALL1,
                SMPetrifiedTreeVariants.SMALL2,
                SMPetrifiedTreeVariants.SMALL3,
                SMPetrifiedTreeVariants.MIDDLE0,
                SMPetrifiedTreeVariants.MIDDLE1,
                SMPetrifiedTreeVariants.SIDE0,
                SMPetrifiedTreeVariants.SIDE1,
                SMPetrifiedTreeVariants.SIDE2,
                SMPetrifiedTreeVariants.SIDE3,
                SMPetrifiedTreeVariants.HOLLOW,
                SMPetrifiedTreeVariants.BIG0,
                SMPetrifiedTreeVariants.BIG1),
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
