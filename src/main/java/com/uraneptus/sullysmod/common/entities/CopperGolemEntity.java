package com.uraneptus.sullysmod.common.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class CopperGolemEntity extends AbstractGolem implements IAnimatable {
    public static final EntityDataAccessor<Integer> OXIDIZATION = SynchedEntityData.defineId(CopperGolemEntity.class, EntityDataSerializers.INT);
    private final AnimationFactory factory = new AnimationFactory(this);
    int cachedState;
    int cachedGameTime;
    boolean isStatue;

    public CopperGolemEntity(EntityType<? extends AbstractGolem> entityType, Level world) {
        super(entityType, world);
        //this.flag = false;
        this.cachedGameTime = Mth.nextInt(random, 100, 200);
        this.cachedState = 0;

    }

    @Override
    protected void registerGoals() {
        if (cachedState < 3) {
            this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
            this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
            this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OXIDIZATION, cachedState);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("OxidizationState", this.getOxidization());
        nbt.putInt("CachedGameTime", cachedGameTime);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setOxidization(nbt.getInt("OxidizationState"));
        this.cachedGameTime = nbt.getInt("CachedGameTime");
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        cachedState = getEntityData().get(OXIDIZATION);
    }

    protected int decreaseAirSupply(int pAir) {
        return pAir;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.MAX_HEALTH, 45.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
    }


    public void tick() {
        super.tick();

        if (!level.isClientSide()) {
            if (cachedGameTime > 0) {
                cachedGameTime--;
            }
            if (cachedGameTime == 0 && cachedState <= 3) {
                cachedGameTime = Mth.nextInt(random, 100, 200);
                this.getEntityData().set(OXIDIZATION, cachedState++);
            }
        }
        if (this.getEntityData().get(CopperGolemEntity.OXIDIZATION) == 3) {
            this.makeStatue();

        }

    }

    public void makeStatue() {
        this.isStatue = true;
        this.setNoAi(true);
        this.goalSelector.removeAllGoals();
        this.setSpeed(0.0F);
    }


    public <E extends IAnimatable> PlayState setAnimation(AnimationEvent<E> event) {
        boolean onGround = isOnGround();
        if (!((double)animationSpeed < 0.1D) && onGround && !isStatue) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.copper_golem.walking", true));
            return PlayState.CONTINUE;
        } /*else {
            //System.out.println("Flag is true. Animation should be called");
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.copper_golem.head_spin", false));
            //this.setHeadSpinDelay(200);
            //flag = false;
            return PlayState.CONTINUE;
        }*/
        return PlayState.STOP;
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::setAnimation));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


    private void setOxidization(int state) {
        this.entityData.set(OXIDIZATION, state);
    }

    public int getOxidization() {
        return this.entityData.get(OXIDIZATION);
    }

}
