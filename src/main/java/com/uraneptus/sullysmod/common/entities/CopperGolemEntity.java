package com.uraneptus.sullysmod.common.entities;

import com.google.common.collect.ImmutableList;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CopperGolemEntity extends AbstractGolem implements IAnimatable {
    public static final EntityDataAccessor<Integer> OXIDIZATION = SynchedEntityData.defineId(CopperGolemEntity.class, EntityDataSerializers.INT);
    //private static final EntityDataAccessor<Integer> HEAD_SPIN_DELAY = SynchedEntityData.defineId(CopperGolemEntity.class, EntityDataSerializers.INT);
    private final AnimationFactory factory = new AnimationFactory(this);
    //public int headSpinTime = this.random.nextInt(7) + 15;
    //boolean flag;
    //public OxidizationState state = OxidizationState.UNAFFECTED;
    int cachedState = 0;
    int cachedGameTime = 400;

    public CopperGolemEntity(EntityType<? extends AbstractGolem> entityType, Level world) {
        super(entityType, world);
        //this.flag = false;

    }

    /*private boolean oxidizationStateAllowsGoals() {
        return this.getEntityData().get(OXIDIZATION) != 3;
    }*/

    @Override
    protected void registerGoals() {
        /*if (!oxidizationStateAllowsGoals()) {
            return;
        }*/
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OXIDIZATION, cachedState);
        //this.entityData.define(HEAD_SPIN_DELAY, 0);
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

    public void aiStep() {
        super.aiStep();
        /*int i = this.getHeadSpinDelay();
        if (i > 0) {
            this.setHeadSpinDelay(i - 1);
        }
        if (i == 0) {
            flag = true;
            //System.out.println("i equals 0. Flag should be true");
        }*/

        if (getEntityData().get(OXIDIZATION) == 0) {
            System.out.println("OXIDIZATION STATE: UNAFFECTED");
        }

        //this.setOxidization(cachedState);
        if (cachedGameTime < level.getGameTime() && cachedState < 3) {
            cachedGameTime = (int) level.getGameTime() + Mth.nextInt(random, 200, 400);
            this.getEntityData().set(OXIDIZATION, cachedState += 1);
        }

    }


    public <E extends IAnimatable> PlayState setAnimation(AnimationEvent<E> event) {
        boolean onGround = isOnGround();
        if (!((double)animationSpeed < 0.1D) && onGround) {
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

    /*private void setOxidization(OxidizationState state) {
        this.state = state;
        this.getEntityData().set(OXIDIZATION, Util.make(() -> state.stateIdentifier));
    }*/

    private void setOxidization(int state) {
        cachedState = state;
        this.entityData.set(OXIDIZATION, state);
    }

    /*private void setHeadSpinDelay(int delay) {
        this.entityData.set(HEAD_SPIN_DELAY, delay);
    }*/

    /*public enum OxidizationState {
        UNAFFECTED(0),
        EXPOSED(1),
        WEATHERED(2),
        OXIDIZED(3);

        public final int stateIdentifier;

        OxidizationState(int stateValue) {
            this.stateIdentifier = stateValue;
        }

        public static CopperGolemEntity.OxidizationState getStateValue(int stateValue) {
            for (OxidizationState oxidizationState : values()) {
                if (oxidizationState.stateIdentifier == stateValue) {
                    return oxidizationState;
                }
            }
            return UNAFFECTED;
        }
    }*/


}
