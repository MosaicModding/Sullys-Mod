package com.uraneptus.sullysmod.client.particles;

import com.uraneptus.sullysmod.core.registry.SMFluids;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class SMDripParticle extends TextureSheetParticle {
    private final Fluid type;
    protected boolean isGlowing;

    protected SMDripParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType) {
        super(pLevel, pX, pY, pZ);
        type = pType;
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int getLightColor(float pPartialTick) {
        return this.isGlowing ? 240 : super.getLightColor(pPartialTick);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed) {
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed) {
                this.xd *= 0.9800000190734863;
                this.yd *= 0.9800000190734863;
                this.zd *= 0.9800000190734863;
                if (this.type != Fluids.EMPTY) {
                    BlockPos $$0 = BlockPos.containing(this.x, this.y, this.z);
                    FluidState $$1 = this.level.getFluidState($$0);
                    if ($$1.getType() == this.type && this.y < (double)((float)$$0.getY() + $$1.getHeight(this.level, $$0))) {
                        this.remove();
                    }

                }
            }
        }
    }

    protected void preMoveUpdate() {
        if (this.lifetime-- <= 0) {
            this.remove();
        }

    }

    protected void postMoveUpdate() {
        if (this.onGround) {
            this.remove();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public Factory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(@NotNull SimpleParticleType defaultParticleType, @NotNull ClientLevel clientWorld, double d, double e, double f, double g, double h, double i) {
            return new SMDripParticle(clientWorld, d, e, f, SMFluids.SOURCE_MOLTEN_AMBER.get());
        }
    }
}
