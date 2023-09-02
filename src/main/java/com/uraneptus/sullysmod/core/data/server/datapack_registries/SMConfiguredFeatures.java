package com.uraneptus.sullysmod.core.data.server.datapack_registries;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.function.Supplier;

public class SMConfiguredFeatures {
    private static final RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    private static final RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    private static final List<OreConfiguration.TargetBlockState> JADE_ORE_TARGET_LIST = List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, SMBlocks.JADE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, SMBlocks.DEEPSLATE_JADE_ORE.get().defaultBlockState()));

    public static void create(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, "jade_ore", () -> addOreConfig(JADE_ORE_TARGET_LIST, 10));
    }

    private static ConfiguredFeature<?, ?> addOreConfig(List<OreConfiguration.TargetBlockState> targetList, int veinSize) {
        return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targetList, veinSize));
    }

    private static void register(BootstapContext<ConfiguredFeature<?, ?>> context, String name, Supplier<? extends ConfiguredFeature<?, ?>> feature) {
        context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, SullysMod.modPrefix(name)), feature.get());
    }
}