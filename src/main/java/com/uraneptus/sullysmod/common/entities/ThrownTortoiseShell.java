package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class ThrownTortoiseShell extends ThrowableItemProjectile {

    public ThrownTortoiseShell(EntityType<? extends ThrownTortoiseShell> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrownTortoiseShell(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(SMEntityTypes.THROWN_TORTOISE_SHELL.get(), level);
    }

    public ThrownTortoiseShell(Level pLevel, LivingEntity pShooter) {
        super(SMEntityTypes.THROWN_TORTOISE_SHELL.get(), pShooter, pLevel);
    }

    public ThrownTortoiseShell(Level pLevel, double pX, double pY, double pZ) {
        super(SMEntityTypes.THROWN_TORTOISE_SHELL.get(), pX, pY, pZ, pLevel);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            double d0 = 0.08D;
            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * d0, ((double)this.random.nextFloat() - 0.5D) * d0, ((double)this.random.nextFloat() - 0.5D) * d0);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        pResult.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 0.0F);
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level.isClientSide) {
            TortoiseShell shell = SMEntityTypes.TORTOISE_SHELL.get().create(this.level);
            if (shell == null) {
                return;
            }
            Vec3 vec3 = this.getDeltaMovement();
            shell.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
            shell.setDeltaMovement(vec3.x(), -0.04D, vec3.z());
            shell.setGotThrown(true);
            this.level.addFreshEntity(shell);
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return SMItems.TORTOISE_SHELL.get();
    }
}