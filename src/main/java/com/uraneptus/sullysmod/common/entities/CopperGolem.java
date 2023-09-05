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
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class CopperGolem extends AbstractGolem implements GeoEntity {
    public static final EntityDataAccessor<Integer> OXIDIZATION = SynchedEntityData.defineId(CopperGolem.class, EntityDataSerializers.INT);
    protected static final RawAnimation WALKING = RawAnimation.begin().thenLoop("animation.copper_golem.walking");
    private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);
    int cachedState;
    int cachedGameTime;
    boolean isStatue;

    public CopperGolem(EntityType<? extends AbstractGolem> entityType, Level world) {
        super(entityType, world);
        this.cachedGameTime = Mth.nextInt(random, 100, 200);
        this.cachedState = 0;

    }

    @Override
    protected void registerGoals() {
        if (!isStatue) {
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

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return this.isStatue;
    }

    public void tick() {
        super.tick();

        if (!level().isClientSide()) {
            if (cachedGameTime > 0) {
                cachedGameTime--;
            }
            if (cachedGameTime == 0 && cachedState <= 3) {
                cachedGameTime = Mth.nextInt(random, 100, 200);
                this.getEntityData().set(OXIDIZATION, cachedState++);
            }
        }
        if (this.getEntityData().get(CopperGolem.OXIDIZATION) == 3) {
            this.makeStatue();

        }

    }

    public void makeStatue() {
        this.isStatue = true;
        this.setNoAi(true);
        this.removeFreeWill();
        this.setSpeed(0.0F);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "Walking", 3, this::setWalking));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return instanceCache;
    }

    public <E extends GeoAnimatable> PlayState setWalking(final AnimationState<E> event) {
        boolean onGround = this.onGround();
        if (event.isMoving() && onGround && !isStatue) {
            return event.setAndContinue(WALKING);
        }
        return PlayState.STOP;
    }

    private void setOxidization(int state) {
        this.entityData.set(OXIDIZATION, state);
    }

    public int getOxidization() {
        return this.entityData.get(OXIDIZATION);
    }
}
