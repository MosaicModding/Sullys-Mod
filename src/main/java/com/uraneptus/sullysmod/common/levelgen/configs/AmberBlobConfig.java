package com.uraneptus.sullysmod.common.levelgen.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public record AmberBlobConfig(List<ResourceLocation> possibleEntities) implements FeatureConfiguration {
    public static final Codec<AmberBlobConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(ResourceLocation.CODEC
                            .listOf().fieldOf("possibleEntities").forGetter(config -> config.possibleEntities))
                    .apply(instance, AmberBlobConfig::new)
    );
}
