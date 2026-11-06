package com.mosaicmodding.sullysmod.fabricated_conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.uraneptus.fabricatedconditions.fake_fabric.FakeFabricCondition;

public record FakeFabricConfigFlagCondition(String feature) implements FakeFabricCondition {
    public static final MapCodec<FakeFabricConfigFlagCondition> CODEC = RecordCodecBuilder.mapCodec(
            builder -> builder.group(
                    Codec.STRING.fieldOf("feature").forGetter(FakeFabricConfigFlagCondition::feature)
            ).apply(builder, FakeFabricConfigFlagCondition::new));

    @Override
    public MapCodec<? extends FakeFabricCondition> codec() {
        return CODEC;
    }
}
