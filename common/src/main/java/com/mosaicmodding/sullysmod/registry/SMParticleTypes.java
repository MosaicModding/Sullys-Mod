package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.core.particle.DirectionalParticleData;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;

import static com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils.path;

public class SMParticleTypes {

    public static final RegSupplier<ParticleType<DirectionalParticleData>> RICOCHET = RegHelper.registerParticle(path("ricochet"), false, DirectionalParticleData::codec, DirectionalParticleData::streamCodec);

    public static final RegSupplier<SimpleParticleType> BLOT_EYES = RegHelper.registerParticle(path("blot_eyes"));

    public static final RegSupplier<SimpleParticleType> AMBER_DRIPPING = RegHelper.registerParticle(path("amber_drip"));
    public static final RegSupplier<SimpleParticleType> AMBER_FALL = RegHelper.registerParticle(path("amber_fall"));
    public static final RegSupplier<SimpleParticleType> AMBER_LAND = RegHelper.registerParticle(path("amber_land"));

    public static void init() {

    }
}
