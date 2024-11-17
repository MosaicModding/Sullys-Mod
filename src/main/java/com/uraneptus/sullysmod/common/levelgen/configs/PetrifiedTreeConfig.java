package com.uraneptus.sullysmod.common.levelgen.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.uraneptus.sullysmod.common.levelgen.PetrifiedTreeVariant;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public record PetrifiedTreeConfig(List<PetrifiedTreeVariant> variants, Holder<PlacedFeature> amberBlobs) implements FeatureConfiguration {
    public static final Codec<PetrifiedTreeConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(PetrifiedTreeVariant.CODEC
                    .listOf().fieldOf("variants").forGetter(config -> config.variants),
                    PlacedFeature.CODEC.fieldOf("amberBlob").forGetter(PetrifiedTreeConfig::amberBlobs))
                    .apply(instance, PetrifiedTreeConfig::new)
    );
}
