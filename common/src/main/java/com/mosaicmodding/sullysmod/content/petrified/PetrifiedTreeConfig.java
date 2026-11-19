package com.mosaicmodding.sullysmod.content.petrified;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record PetrifiedTreeConfig(Holder<PlacedFeature> amberBlobs) implements FeatureConfiguration {
    public static final Codec<PetrifiedTreeConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    PlacedFeature.CODEC.fieldOf("amber_blob_placements").forGetter(PetrifiedTreeConfig::amberBlobs)
            ).apply(instance, PetrifiedTreeConfig::new)
    );
}
