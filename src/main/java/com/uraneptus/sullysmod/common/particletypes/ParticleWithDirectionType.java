package com.uraneptus.sullysmod.common.particletypes;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;

public class ParticleWithDirectionType extends ParticleType<DirectionParticleOptions> {

    public ParticleWithDirectionType(boolean pOverrideLimiter) {
        super(pOverrideLimiter, DirectionParticleOptions.DESERIALIZER);
    }

    @Override
    public Codec<DirectionParticleOptions> codec() {
        return DirectionParticleOptions.codec(this);
    }

}
