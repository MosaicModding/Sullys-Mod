package com.mosaicmodding.sullysmod.core.particle;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public record DirectionalParticleData(ParticleType<DirectionalParticleData> type, Direction direction) implements ParticleOptions {
    public static MapCodec<DirectionalParticleData> codec(ParticleType<DirectionalParticleData> particleType) {
        return Direction.CODEC.xmap(dir -> new DirectionalParticleData(particleType, dir), particleData -> particleData.direction).fieldOf("direction");
    }

    public static StreamCodec<? super RegistryFriendlyByteBuf, DirectionalParticleData> streamCodec(ParticleType<DirectionalParticleData> particleType) {
        return Direction.STREAM_CODEC.map(dir -> new DirectionalParticleData(particleType, dir), particleData -> particleData.direction);
    }

    @Override
    public ParticleType<DirectionalParticleData> getType() {
        return this.type;
    }
}
