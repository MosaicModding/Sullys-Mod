package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Chameleon extends Animal implements GeoEntity {
    private static final EntityDataAccessor<Integer> TARGET_COLOR = SynchedEntityData.defineId(Chameleon.class, EntityDataSerializers.INT);
    private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation WALKING = RawAnimation.begin().thenLoop("animation.chameleon.walking");
    public static final Ingredient FOOD_ITEMS = Ingredient.of(SMItemTags.CHAMELEON_FOOD);

    public Chameleon(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pDimensions) {
        return pDimensions.height * 0.7647F;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) {
        return null;
    }


    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "ChameleonAnimations", 3, this::setAnimation));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return instanceCache;
    }

    public <E extends GeoAnimatable> PlayState setAnimation(final AnimationState<E> event) {
        boolean onGround = this.onGround();

        if (event.isMoving() && onGround) {
            return event.setAndContinue(WALKING);
        }

        return PlayState.STOP;
    }

    @Override
    public void tick() {
        super.tick();
        this.determineTargetColor();
    }

    public int getTargetColor() {
        return this.entityData.get(TARGET_COLOR);
    }

    public void setTargetColor(int targetColor) {
        this.entityData.set(TARGET_COLOR, targetColor);
    }

    private void determineTargetColor() {
        int color = this.level().getBlockState(this.getOnPos()).getMapColor(this.level(), this.getOnPos()).col;
        if (color != this.getTargetColor()) {
            SullysMod.LOGGER.info("SERVER: " + color);
            this.setTargetColor(color);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TARGET_COLOR, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("targetColor", getTargetColor());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setTargetColor(nbt.getInt("targetColor"));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.28D)
                .add(Attributes.MAX_HEALTH, 10.0D);
    }
}