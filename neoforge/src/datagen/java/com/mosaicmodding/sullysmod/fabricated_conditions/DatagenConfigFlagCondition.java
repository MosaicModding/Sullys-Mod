package com.mosaicmodding.sullysmod.fabricated_conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import dev.uraneptus.fabricatedconditions.ConvertibleCondition;
import dev.uraneptus.fabricatedconditions.fake_fabric.FakeFabricCondition;
import net.neoforged.neoforge.common.conditions.ICondition;

public record DatagenConfigFlagCondition(String feature) implements ICondition, ConvertibleCondition {
    public static final MapCodec<DatagenConfigFlagCondition> CODEC = RecordCodecBuilder.mapCodec(
            builder -> builder.group(
                    Codec.STRING.fieldOf("feature").forGetter(DatagenConfigFlagCondition::feature)
            ).apply(builder, DatagenConfigFlagCondition::new));

    @Override
    public boolean test(IContext iContext) {
        return SMCommonConfig.isEnabled(feature);
    }

    @Override
    public FakeFabricCondition fabricatedconditions_convert() {
        return new FakeFabricConfigFlagCondition(feature);
    }

    @Override
    public MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}
