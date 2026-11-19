package com.mosaicmodding.sullysmod.content.petrified;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public record PetrifiedTreeVariant(ResourceLocation nbtLocation, boolean allowAmber, int susGravelLimit) {
    public static final Codec<PetrifiedTreeVariant> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                    ResourceLocation.CODEC.fieldOf("nbt_location").forGetter(PetrifiedTreeVariant::nbtLocation),
                    Codec.BOOL.fieldOf("allow_amber_blobs").forGetter(PetrifiedTreeVariant::allowAmber),
                    Codec.INT.fieldOf("suspicious_gravel_limit").forGetter(PetrifiedTreeVariant::susGravelLimit))
            .apply(builder, PetrifiedTreeVariant::new)
    );
}
