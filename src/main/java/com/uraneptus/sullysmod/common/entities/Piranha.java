package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.UUID;

public class Piranha extends AbstractSchoolingFish implements GeoEntity, NeutralMob {
    private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation SWIMMING_ANIM = RawAnimation.begin().thenLoop("animation.piranha.swim");
    protected static final RawAnimation JUMPING_ANIM = RawAnimation.begin().thenPlay("animation.piranha.jump");

    public Piranha(EntityType<? extends AbstractSchoolingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D);
    }

    protected void registerGoals() {
        super.registerGoals();
    }

    public int getMaxSchoolSize() {
        return 5;
    }

    public static boolean checkPiranhaSpawnRules(EntityType<? extends LivingEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getFluidState(pos.below()).is(Fluids.WATER) && level.getFluidState(pos.above()).is(FluidTags.WATER);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SMSounds.PIRANHA_FLOP.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SMSounds.PIRANHA_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SMSounds.PIRANHA_DEATH.get();
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(SMItems.PIRANHA_BUCKET.get());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "Animations", 3, this::setAnimation));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return instanceCache;
    }

    public <E extends GeoAnimatable> PlayState setAnimation(final AnimationState<E> event) {
        if (event.isMoving()) {
            return event.setAndContinue(SWIMMING_ANIM);
        }
        return PlayState.STOP;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int pRemainingPersistentAngerTime) {

    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID pPersistentAngerTarget) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }
}
