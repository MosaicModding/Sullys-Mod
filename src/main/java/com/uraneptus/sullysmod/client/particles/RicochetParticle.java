package com.uraneptus.sullysmod.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RicochetParticle extends TextureSheetParticle {
    private final SpriteSet animatedSprite;

    public RicochetParticle(SpriteSet animatedSprite, ClientLevel level, double posX, double posY, double posZ) {
        super(level, posX, posY, posZ);
        //this.setSize(0.5F, 0.5F);
        this.quadSize = 0.45F;
        this.animatedSprite = animatedSprite;
        this.lifetime = 5;
        this.hasPhysics = false;
        this.setSpriteFromAge(animatedSprite);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.animatedSprite);
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class RicochetParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public RicochetParticleProvider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new RicochetParticle(this.sprites, pLevel, pX, pY, pZ);
        }
    }
}
