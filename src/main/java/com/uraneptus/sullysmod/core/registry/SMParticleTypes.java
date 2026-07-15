package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.particletypes.ParticleWithDirectionType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = SullysMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class SMParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, SullysMod.MOD_ID);

    public static final Supplier<ParticleWithDirectionType> RICOCHET = PARTICLES.register("ricochet", () -> new ParticleWithDirectionType(false));

    public static final Supplier<SimpleParticleType> BLOT_EYES = PARTICLES.register("blot_eyes", () -> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> AMBER_DRIPPING = PARTICLES.register("amber_drip", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> AMBER_FALL = PARTICLES.register("amber_fall", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> AMBER_LAND = PARTICLES.register("amber_land", () -> new SimpleParticleType(false));
}
