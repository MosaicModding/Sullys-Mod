package com.uraneptus.sullysmod.client.particles;

import com.uraneptus.sullysmod.core.registry.SMFluids;
import com.uraneptus.sullysmod.core.registry.SMParticleTypes;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("unused")
public class AmberParticle extends TextureSheetParticle {
    private final Fluid type;

    public AmberParticle(ClientLevel pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);
        this.setSize(0.01F, 0.01F);
        this.type = SMFluids.SOURCE_MOLTEN_AMBER.get();
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

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
                if (this.type != Fluids.EMPTY) {
                    BlockPos blockpos = BlockPos.containing(this.x, this.y, this.z);
                    FluidState fluidstate = this.level.getFluidState(blockpos);
                    if (fluidstate.getType() == this.type && this.y < (double)((float)blockpos.getY() + fluidstate.getHeight(this.level, blockpos))) {
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
    }

    public static TextureSheetParticle createAmberHangParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        return new AmberHangParticle(pLevel, pX, pY, pZ);
    }

    public static TextureSheetParticle createAmberFallParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        return new AmberFallAndLandParticle(pLevel, pX, pY, pZ);
    }

    public static TextureSheetParticle createAmberLandParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        return new DripLandParticle(pLevel, pX, pY, pZ);
    }

    @OnlyIn(Dist.CLIENT)
    static class DripLandParticle extends AmberParticle {
        DripLandParticle(ClientLevel p_106102_, double p_106103_, double p_106104_, double p_106105_) {
            super(p_106102_, p_106103_, p_106104_, p_106105_);
            this.lifetime = (int)(128.0D / (Math.random() * 0.8D + 0.2D));
            this.hasPhysics = false;
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class AmberHangParticle extends AmberParticle {
        private final ParticleOptions fallingParticle;

        AmberHangParticle(ClientLevel pLevel, double pX, double pY, double pZ) {
            super(pLevel, pX, pY, pZ);
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

    @OnlyIn(Dist.CLIENT)
    static class AmberFallAndLandParticle extends AmberParticle {
        protected final ParticleOptions landParticle;

        AmberFallAndLandParticle(ClientLevel pLevel, double pX, double pY, double pZ) {
            super(pLevel, pX, pY, pZ);
            this.landParticle = SMParticleTypes.AMBER_LAND.get();
            this.gravity = 0.01F;
            this.lifetime = (int)(64.0D / (Math.random() * 0.8D + 0.2D));
        }

        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y + 0.14, this.z, 0.0D, 0.0D, 0.0D);
                float f = Mth.randomBetween(this.random, 0.3F, 1.0F);
                this.level.playLocalSound(this.x, this.y, this.z, SMSounds.AMBER_DRIP.get(), SoundSource.BLOCKS, f, 1.0F, false);
            }
        }
    }
}
