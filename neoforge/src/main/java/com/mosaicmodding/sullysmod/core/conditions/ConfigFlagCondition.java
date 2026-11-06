package com.mosaicmodding.sullysmod.core.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.function.Function;

/**
 * This Condition Object is used when parsing the json in game. It's not meant to be used when running datagen. Use the Fabricated equivalent for that.
 * This should also not be registered when running datagen.
 */
public record ConfigFlagCondition(String feature) implements ICondition {
    public static final MapCodec<ConfigFlagCondition> CODEC = RecordCodecBuilder.mapCodec(
            builder -> builder.group(
                    Codec.STRING.fieldOf("feature").forGetter(ConfigFlagCondition::feature)
            ).apply(builder, ConfigFlagCondition::new));


    @Override
    public boolean test(IContext iContext) {
        return SMCommonConfig.isEnabled(feature);
    }

    @Override
    public MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}
