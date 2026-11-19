package com.mosaicmodding.sullysmod.core.events;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

@FunctionalInterface
public interface BiomeFeatureAdditionEvent {
    void register(TagKey<Biome> biomeTag, ResourceKey<PlacedFeature> placed, GenerationStep.Decoration decoration, String... requiredModFeatures);
}
