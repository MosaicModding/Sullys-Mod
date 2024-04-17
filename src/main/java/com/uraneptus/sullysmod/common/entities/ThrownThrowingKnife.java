package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.registry.SMDamageTypes;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class ThrownThrowingKnife extends AbstractArrow {
    private ItemStack knifeItem = new ItemStack(SMItems.THROWING_KNIFE.get());

    public ThrownThrowingKnife(EntityType<? extends ThrownThrowingKnife> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrownThrowingKnife(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(SMEntityTypes.THROWN_THROWING_KNIFE.get(), level);
    }

    public ThrownThrowingKnife(Level pLevel, LivingEntity pShooter, ItemStack pStack) {
        super(SMEntityTypes.THROWN_THROWING_KNIFE.get(), pShooter, pLevel);
        if (pShooter instanceof Player) {
            this.pickup = AbstractArrow.Pickup.ALLOWED;
        }
    }

    public ThrownThrowingKnife(Level pLevel, double pX, double pY, double pZ) {
        super(SMEntityTypes.THROWN_THROWING_KNIFE.get(), pX, pY, pZ, pLevel);
    }

    @Override
    public ItemStack getPickupItem() {
        return this.knifeItem.copy();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();
        float f = 2.0F;

        if (!entity.onGround() && !entity.isInFluidType()) {
            f += 4.0F;
        }


        Entity owner = this.getOwner();
        if (entity.hurt(this.damageSources().source(SMDamageTypes.THROWING_KNIFE, this, owner == null ? this : owner), f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity livingEntity) {
                if (owner instanceof LivingEntity ownerEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingEntity, ownerEntity);
                    EnchantmentHelper.doPostDamageEffects(ownerEntity, livingEntity);
                }

                this.doPostHurtEffects(livingEntity);
            }
            this.discard();
        } else {
            this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
            this.setYRot(this.getYRot() + 180.0F);
            this.yRotO += 180.0F;
            if (!this.level().isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {
                if (this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            }
        }
        this.playSound(SMSounds.THROWING_KNIFE_HIT.get(), 1.0F, 1.0F);
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SMSounds.THROWING_KNIFE_HIT_GROUND.get();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("ThrowingKnife", 10)) {
            this.knifeItem = ItemStack.of(pCompound.getCompound("ThrowingKnife"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.put("ThrowingKnife", this.knifeItem.save(new CompoundTag()));
    }

    @Override
    protected float getWaterInertia() {
        return 0.7F;
    }

    @Override
    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }
}
