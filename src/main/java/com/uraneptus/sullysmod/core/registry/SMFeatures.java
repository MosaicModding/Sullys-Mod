package com.uraneptus.sullysmod.core.registry;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class SMFeatures {
    public static Holder<PlacedFeature> JADE_ORE_PLACEMENT;
    public static final List<OreConfiguration.TargetBlockState> JADE_ORE_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SMBlocks.JADE_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SMBlocks.DEEPSLATE_JADE_ORE.get().defaultBlockState()));

    public static void registerFeatures() {
        OreConfiguration jade_ore = new OreConfiguration(JADE_ORE_TARGET_LIST, 10);
        JADE_ORE_PLACEMENT = register("jade_ore", new ConfiguredFeature<>(Feature.ORE, jade_ore), CountPlacement.of(16), HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112)));

    }

    public static void onBiomeLoad(BiomeLoadingEvent event) {
        BiomeGenerationSettings.Builder generation = event.getGeneration();

        if (event.getCategory() == Biome.BiomeCategory.JUNGLE) {
            generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, JADE_ORE_PLACEMENT);
        }
    }

    public static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> register(String name, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(name, Holder.direct(feature), placementModifiers);
    }
}
