package com.uraneptus.sullysmod.data.server.builtin;

import com.google.common.collect.ImmutableList;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.levelgen.ArtifactGravelFeature;
import com.uraneptus.sullysmod.common.levelgen.PetrifiedTreeConfig;
import com.uraneptus.sullysmod.common.levelgen.PetrifiedTreeGravelDecorator;
import com.uraneptus.sullysmod.core.other.SMFeatureDefinitions;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

public class SMConfiguredFeaturesProvider {
    private static final RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    private static final RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    private static final List<OreConfiguration.TargetBlockState> JADE_ORE_TARGET_LIST = List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, SMBlocks.JADE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, SMBlocks.DEEPSLATE_JADE_ORE.get().defaultBlockState()));

    public static void create(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, SMFeatureDefinitions.CONFIGURED_JADE_ORE, () -> addOreConfig(JADE_ORE_TARGET_LIST, 10));
        register(context, SMFeatureDefinitions.CONFIGURED_PETRIFIED_TREE_SMALL, () -> addTreeConfig(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(SMBlocks.PETRIFIED_LOG.get()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).decorators(ImmutableList.of(new PetrifiedTreeGravelDecorator()))));
        register(context, SMFeatureDefinitions.CONFIGURED_PETRIFIED_TREE, () -> new ConfiguredFeature<>(SMFeatures.PETRIFIED_TREE.get(), new PetrifiedTreeConfig(List.of(SullysMod.modPrefix("petrified/petrified_tree_0"), SullysMod.modPrefix("petrified/petrified_tree_1"), SullysMod.modPrefix("petrified/petrified_tree_2"), SullysMod.modPrefix("petrified/petrified_tree_3")))));
        register(context, SMFeatureDefinitions.CONFIGURED_ARTIFACT_GRAVEL, () -> new ConfiguredFeature<>(SMFeatures.ARTIFACT_GRAVEL.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SUSPICIOUS_GRAVEL))));
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
