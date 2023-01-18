package com.uraneptus.sullysmod.common.particletypes;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.serialization.Codec;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

public class DirectionParticleOptions implements ParticleOptions {
    public static final ParticleOptions.Deserializer<DirectionParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<DirectionParticleOptions>() {
        public DirectionParticleOptions fromCommand(ParticleType<DirectionParticleOptions> particleType, StringReader stringReader) throws CommandSyntaxException {
            stringReader.expect(' ');

            int cursor = stringReader.getCursor();

            try {
                Direction dir = Direction.byName(stringReader.readString());
                if (dir != null) {
                    return new DirectionParticleOptions(particleType, dir);
                }
                throw new SimpleCommandExceptionType(new LiteralMessage("Expected Direction")).create();
            } catch (CommandSyntaxException commandsyntaxexception) {
                stringReader.setCursor(cursor);
                throw commandsyntaxexception;
            }
        }

        public DirectionParticleOptions fromNetwork(ParticleType<DirectionParticleOptions> particleType, FriendlyByteBuf buf) {
            return new DirectionParticleOptions(particleType, Direction.values()[buf.readInt()]);
        }
    };

    private final ParticleType<DirectionParticleOptions> type;
    private final Direction face;

    public static Codec<DirectionParticleOptions> codec(ParticleType<DirectionParticleOptions> particleType) {
        return Direction.CODEC.xmap((face) -> {
            return new DirectionParticleOptions(particleType, face);
        }, (particle) -> {
            return particle.face;
        });
    }

    public DirectionParticleOptions(ParticleType<DirectionParticleOptions> particleType, Direction face) {
        this.type = particleType;
        this.face = face;
    }

    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        pBuffer.writeInt(this.face.ordinal());
    }

    public String writeToString() {
        return Registry.PARTICLE_TYPE.getKey(this.getType()) + " " + this.face.toString();
    }

    public ParticleType<DirectionParticleOptions> getType() {
        return this.type;
    }

    public Direction getFace() {
        return this.face;
    }

}
