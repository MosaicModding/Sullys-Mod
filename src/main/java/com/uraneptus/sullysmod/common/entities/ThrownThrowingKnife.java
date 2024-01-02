package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

import javax.annotation.Nullable;

//TODO reduce hitbox
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

    @Override
    public ItemStack getPickupItem() {
        return this.knifeItem.copy();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();
        float f = 2.0F;
        if (entity instanceof LivingEntity livingentity) {
            f += EnchantmentHelper.getDamageBonus(this.knifeItem, livingentity.getMobType());
        }

        Entity owner = this.getOwner();
        DamageSource damagesource = this.damageSources().trident(this, owner == null ? this : owner); //TODO add own damagesource
        SoundEvent soundevent = SoundEvents.TRIDENT_HIT; //TODO change sound
        if (entity.hurt(damagesource, f)) {
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
        }

        this.discard();
        this.playSound(soundevent, 1.0F, 1.0F);
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND; //TODO add own sound
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
        return 0.7F; //This is how projectiles moves through water
    }

    @Override
    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }
}
