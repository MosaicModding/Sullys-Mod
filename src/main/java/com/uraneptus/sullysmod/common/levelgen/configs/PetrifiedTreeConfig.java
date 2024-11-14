package com.uraneptus.sullysmod.common.levelgen.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public record PetrifiedTreeConfig(List<ResourceLocation> structures, Holder<PlacedFeature> amberBlobs) implements FeatureConfiguration {
    public static final Codec<PetrifiedTreeConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(ResourceLocation.CODEC
                    .listOf().fieldOf("structure_variants").forGetter(config -> config.structures),
                    PlacedFeature.CODEC.fieldOf("amberBlob").forGetter(PetrifiedTreeConfig::amberBlobs))
                    .apply(instance, PetrifiedTreeConfig::new)
    );
}
