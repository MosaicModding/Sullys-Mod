package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LanternfishEntity extends AbstractFish {
    private static final EntityDataAccessor<Integer> DATA_DARK_TICKS_REMAINING = SynchedEntityData.defineId(LanternfishEntity.class, EntityDataSerializers.INT);


    public LanternfishEntity(EntityType<? extends LanternfishEntity> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_DARK_TICKS_REMAINING, 0);
    }

    public void addAdditionalSaveData(CompoundTag p_147122_) {
        super.addAdditionalSaveData(p_147122_);
        p_147122_.putInt("DarkTicksRemaining", this.getDarkTicksRemaining());
    }

    public void readAdditionalSaveData(CompoundTag p_147117_) {
        super.readAdditionalSaveData(p_147117_);
        this.setDarkTicks(p_147117_.getInt("DarkTicksRemaining"));
    }

    public void aiStep() {
        super.aiStep();
        int i = this.getDarkTicksRemaining();
        if (i > 0) {
            this.setDarkTicks(i - 1);
        }

    }

    public boolean hurt(DamageSource p_147114_, float p_147115_) {
        boolean flag = super.hurt(p_147114_, p_147115_);
        if (flag) {
            this.setDarkTicks(100);
        }

        return flag;
    }

    private void setDarkTicks(int p_147120_) {
        this.entityData.set(DATA_DARK_TICKS_REMAINING, p_147120_);
    }

    public int getDarkTicksRemaining() {
        return this.entityData.get(DATA_DARK_TICKS_REMAINING);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return null;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(SMItems.LANTERNFISH_BUCKET.get());
    }
}
