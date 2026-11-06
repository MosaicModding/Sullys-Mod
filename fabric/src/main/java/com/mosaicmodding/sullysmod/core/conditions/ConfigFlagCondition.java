package com.mosaicmodding.sullysmod.core.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.registry.SMFabricConditions;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.Nullable;

public record ConfigFlagCondition(String feature) implements ResourceCondition {
    public static final MapCodec<ConfigFlagCondition> CODEC = RecordCodecBuilder.mapCodec(
            builder -> builder.group(
                    Codec.STRING.fieldOf("feature").forGetter(ConfigFlagCondition::feature)
            ).apply(builder, ConfigFlagCondition::new));

    @Override
    public ResourceConditionType<?> getType() {
        return SMFabricConditions.CONFIG_FLAG_CONDITION_TYPE;
    }

    @Override
    public boolean test(HolderLookup.@Nullable Provider registryLookup) {
        return SMCommonConfig.isEnabled(feature);
    }
}
