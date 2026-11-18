package com.mosaicmodding.sullysmod.server.worldgen;

import com.google.common.collect.ImmutableList;
import com.mosaicmodding.sullysmod.content.petrified.PetrifiedTreeGravelDecorator;
import com.mosaicmodding.sullysmod.registry.SMBlocks;
import com.mosaicmodding.sullysmod.registry.SMWorldgenFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

import static com.mosaicmodding.sullysmod.registry.SMWorldgenFeatures.*;

public class SMConfiguredFeatureGen {

    public static void add(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(CONFIGURED_PETRIFIED_TREE_SMALL.getLeft(),
                addTreeConfig(new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(SMBlocks.PETRIFIED_WOOD_BLOCK_SET.getLog().get()),
                        new FancyTrunkPlacer(3, 11, 0),
                        BlockStateProvider.simple(Blocks.AIR),
                        new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .decorators(ImmutableList.of(new PetrifiedTreeGravelDecorator()))
                        )
        );
    }

    private static ConfiguredFeature<?, ?> addOreConfig(Feature<OreConfiguration> feature, List<OreConfiguration.TargetBlockState> targetList, int veinSize, float airDiscardChance) {
        return new ConfiguredFeature<>(feature, new OreConfiguration(targetList, veinSize, airDiscardChance));
    }

    private static ConfiguredFeature<?, ?> addTreeConfig(TreeConfiguration.TreeConfigurationBuilder treeBuilder) {
        return new ConfiguredFeature<>(Feature.TREE, treeBuilder.build());
    }
}
