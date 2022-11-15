package com.uraneptus.sullysmod.common.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class Rascal extends PathfinderMob implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public Rascal(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.LAVA, -1.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(0, new RandomLookAroundGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.4F)
                .add(Attributes.MAX_HEALTH, 30.0D);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();

    }

    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        super.onSyncedDataUpdated(pKey);
    }


    public void aiStep() {
        super.aiStep();

    }

    public <E extends IAnimatable> PlayState setAnimation(AnimationEvent<E> event) {

        if (/*!((double) animationSpeed < 0.08D) && isOnGround() &&*/ event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rascal.running", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void registerControllers(AnimationData data) {
        data.setResetSpeedInTicks(4);
        data.addAnimationController(new AnimationController(this, "controller", 3, this::setAnimation));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

}
