package com.mosaicmodding.sullysmod.core.events;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;

@FunctionalInterface
public interface BiomeSpawnAdditionEvent {
    void register(TagKey<Biome> biomeTag, EntityType<?> type, int weight, int min, int max, String... requiredModFeatures);
}
