package com.uraneptus.sullysmod.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class BlotEyesParticle extends SimpleAnimatedParticle {
    private float fadeR;
    private float fadeG;
    private float fadeB;
    private boolean hasFade;

    BlotEyesParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider) {
        super(world, x, y, z, spriteProvider, 0.0F);
        this.quadSize = 0.24F;
        this.hasPhysics = false;
        this.gravity = 0F;
        this.lifetime = 340;
        this.setSprite(spriteProvider.get(this.random));
    }


    public void tick() {
        this.scale(0.9994F);
        if (this.age++ >= this.lifetime) {
            this.remove();
        }
        if (this.age > this.lifetime / 2) {
            this.setAlpha(1.0F - ((float)this.age - (float)(this.lifetime / 2)) / (float)this.lifetime);
            if (this.hasFade) {
                this.rCol += (this.fadeR - this.rCol) * 0.2F;
                this.gCol += (this.fadeG - this.gCol) * 0.2F;
                this.bCol += (this.fadeB - this.bCol) * 0.2F;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public Factory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(@NotNull SimpleParticleType defaultParticleType, @NotNull ClientLevel clientWorld, double d, double e, double f, double g, double h, double i) {
            return new BlotEyesParticle(clientWorld, d, e, f, this.spriteProvider);
        }
    }
}
