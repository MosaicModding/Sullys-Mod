package com.uraneptus.sullysmod.core.data.server.datapack_registries;

import com.mojang.serialization.JsonOps;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.data.SMDatagenUtil;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SMConfiguredFeatureProvider {
    private static final Map<ResourceLocation, ConfiguredFeature<?, ?>> ENTRIES = new HashMap<>();
    private static final List<OreConfiguration.TargetBlockState> JADE_ORE_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SMBlocks.JADE_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SMBlocks.DEEPSLATE_JADE_ORE.get().defaultBlockState()));

    public static JsonCodecProvider<ConfiguredFeature<?, ?>> createConfiguredFeatures(DataGenerator generator, ExistingFileHelper fileHelper) {
        addOreConfig("jade_ore", JADE_ORE_TARGET_LIST, 10);

        return JsonCodecProvider.forDatapackRegistry(generator, fileHelper, SullysMod.MOD_ID,  RegistryOps.create(JsonOps.INSTANCE, SMDatagenUtil.REGISTRY_ACCESS), Registry.CONFIGURED_FEATURE_REGISTRY, ENTRIES);
    }

    private static void addOreConfig(String name, List<OreConfiguration.TargetBlockState> targetList, int veinSize) {
        addEntry(name, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targetList, veinSize)));
    }

    private static void addEntry(String name, ConfiguredFeature<?, ?> feature) {
        ENTRIES.put(SullysMod.modPrefix(name), feature);
    }
}