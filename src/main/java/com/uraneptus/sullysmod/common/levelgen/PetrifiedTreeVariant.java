package com.uraneptus.sullysmod.common.levelgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public record PetrifiedTreeVariant(ResourceLocation name, boolean allowAmber, int susGravelLimit) {
    public static final Codec<PetrifiedTreeVariant> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            ResourceLocation.CODEC.fieldOf("name").forGetter(PetrifiedTreeVariant::name),
            Codec.BOOL.fieldOf("allowAmber").forGetter(PetrifiedTreeVariant::allowAmber),
            Codec.INT.fieldOf("susGravelLimit").forGetter(PetrifiedTreeVariant::susGravelLimit))
            .apply(builder, PetrifiedTreeVariant::new));
    }
