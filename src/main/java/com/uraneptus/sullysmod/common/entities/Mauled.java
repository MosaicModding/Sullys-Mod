package com.uraneptus.sullysmod.common.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.level.Level;

public class Mauled extends AbstractSkeleton {
    private static final EntityDataAccessor<Boolean> SKINLESS = SynchedEntityData.defineId(Mauled.class, EntityDataSerializers.BOOLEAN);


    public Mauled(EntityType<? extends AbstractSkeleton> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractSkeleton.createAttributes();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SKINLESS, false);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        boolean isHurt = super.hurt(pSource, pAmount);
        if (isHurt && (this.isDeadOrDying() || this.getHealth() <= this.getMaxHealth() / 2)) {
            this.setSkinless(true);
            return true;
        }
        return isHurt;
    }

    public boolean isSkinless() {
        return this.entityData.get(SKINLESS);
    }

    public void setSkinless(boolean skinless) {
        this.entityData.set(SKINLESS, skinless);
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.SKELETON_STEP; //TODO change to own
    }
}
