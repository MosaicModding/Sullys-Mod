package com.uraneptus.sullysmod.common.levelgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public record PetrifiedTreeConfig(List<ResourceLocation> structures) implements FeatureConfiguration {
    public static Codec<PetrifiedTreeConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(ResourceLocation.CODEC
                    .listOf().fieldOf("structure_variants")
                    .forGetter(config -> config.structures))
                    .apply(instance, PetrifiedTreeConfig::new)
    );
}
