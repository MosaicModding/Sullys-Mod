package com.uraneptus.sullysmod.common.entities;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.level.Level;

public class Mauled extends AbstractSkeleton {

    public Mauled(EntityType<? extends AbstractSkeleton> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractSkeleton.createAttributes();
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.SKELETON_STEP; //TODO change to own
    }
}
