package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.other.tags.SMEntityTags;
import com.uraneptus.sullysmod.core.registry.SMDamageTypes;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
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
        }

        this.discard();
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
