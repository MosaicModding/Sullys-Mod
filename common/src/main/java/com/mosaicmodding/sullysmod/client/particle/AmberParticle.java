package com.mosaicmodding.sullysmod.client.particle;

import com.mosaicmodding.sullysmod.registry.SMParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class AmberParticle extends TextureSheetParticle {

    public AmberParticle(SpriteSet spriteSet, ClientLevel pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);
        this.pickSprite(spriteSet);
        this.scale(0.6F);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed) {
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed) {
                this.xd *= 0.98F;
                this.yd *= 0.98F;
                this.zd *= 0.98F;
            }
        }
    }

    protected void preMoveUpdate() {
        if (this.lifetime-- <= 0) {
            this.remove();
        }
    }

    protected void postMoveUpdate() {
    }

    public static class AmberHangParticleProvider implements ParticleProvider<SimpleParticleType> {
        final SpriteSet spriteSet;

        public AmberHangParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType defaultParticleType, @NotNull ClientLevel clientLevel, double x, double y, double z, double speedX, double speedY, double speedZ) {
            return new AmberHangParticle(this.spriteSet, clientLevel, x, y, z);
        }
    }

    public static class AmberFallParticleProvider implements ParticleProvider<SimpleParticleType> {
        final SpriteSet spriteSet;

        public AmberFallParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType defaultParticleType, @NotNull ClientLevel clientLevel, double x, double y, double z, double speedX, double speedY, double speedZ) {
            return new AmberFallAndLandParticle(this.spriteSet, clientLevel, x, y, z);
        }
    }

    public static class AmberLandedParticleProvider implements ParticleProvider<SimpleParticleType> {
        final SpriteSet spriteSet;

        public AmberLandedParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType defaultParticleType, @NotNull ClientLevel clientLevel, double x, double y, double z, double speedX, double speedY, double speedZ) {
            return new AmberLandedParticle(this.spriteSet, clientLevel, x, y, z);
        }
    }

    static class AmberLandedParticle extends AmberParticle {
        AmberLandedParticle(SpriteSet spriteSet, ClientLevel clientLevel, double x, double y, double z) {
            super(spriteSet, clientLevel, x, y, z);
            this.lifetime = (int)(128.0D / (Math.random() * 0.8D + 0.2D));
            this.hasPhysics = false;
        }
    }

    static class AmberHangParticle extends AmberParticle {
        private final ParticleOptions fallingParticle;

        AmberHangParticle(SpriteSet spriteSet, ClientLevel pLevel, double pX, double pY, double pZ) {
            super(spriteSet, pLevel, pX, pY, pZ);
            this.fallingParticle = SMParticleTypes.AMBER_FALL.get();
            this.gravity *= 0.01F;
            this.lifetime = 100;
        }

        protected void preMoveUpdate() {
            if (this.lifetime-- <= 0) {
                this.remove();
                this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }
        }

        protected void postMoveUpdate() {
            this.xd *= 0.02D;
            this.yd *= 0.02D;
            this.zd *= 0.02D;
        }
    }

    static class AmberFallAndLandParticle extends AmberParticle {
        protected final ParticleOptions landParticle;

        AmberFallAndLandParticle(SpriteSet spriteSet, ClientLevel pLevel, double pX, double pY, double pZ) {
            super(spriteSet, pLevel, pX, pY, pZ);
            this.landParticle = SMParticleTypes.AMBER_LAND.get();
            this.gravity = 0.01F;
            this.lifetime = (int)(64.0D / (Math.random() * 0.8D + 0.2D));
        }

        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y + 0.14, this.z, 0.0D, 0.0D, 0.0D);
                float f = Mth.randomBetween(this.random, 0.3F, 1.0F);
                //todo this.level.playLocalSound(this.x, this.y, this.z, SMSoundEvents.AMBER_DRIP.get(), SoundSource.BLOCKS, f, 1.0F, false);
            }
        }
    }
}
