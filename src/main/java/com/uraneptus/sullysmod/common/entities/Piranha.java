package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.other.tags.SMEntityTags;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
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

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

public class Piranha extends AbstractSchoolingFish implements GeoEntity, NeutralMob {
    private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation SWIMMING_ANIM = RawAnimation.begin().thenLoop("animation.piranha.swim");
    protected static final RawAnimation SWIMMING_ANGRY_ANIM = RawAnimation.begin().thenLoop("animation.piranha.swim_angry");
    protected static final RawAnimation JUMPING_ANIM = RawAnimation.begin().thenPlay("animation.piranha.in_air");
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Piranha.class, EntityDataSerializers.INT);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(5, 10);
    private static final EntityDataAccessor<Boolean> HAS_BOAT_TARGET = SynchedEntityData.defineId(Piranha.class, EntityDataSerializers.BOOLEAN);
    @Nullable
    private UUID persistentAngerTarget;
    @Nullable
    private Boat boatTarget;

    public Piranha(EntityType<? extends AbstractSchoolingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractFish.createAttributes().add(Attributes.ATTACK_DAMAGE, 2);
    }

    @Override
    protected void registerGoals() {
        //NOTE: Never call the super method! We don't want the panic goal in AbstractFish
        this.goalSelector.addGoal(0, new PiranhaAttackGoal(this));
        this.goalSelector.addGoal(0, new PiranhaAttackBoatGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(4, new PiranhaSwimGoal(this));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, this::piranhaAngryAtPlayer));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, true, this::isPiranhaAngry));
        this.targetSelector.addGoal(2, new PiranhaTargetBoatGoal(this));
        this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    public int getMaxSchoolSize() {
        return 5;
    }

    public boolean isPiranhaAngry(LivingEntity pTarget) {
        return (isAngryAt(pTarget)
                || pTarget.getHealth() < pTarget.getMaxHealth()
                || pTarget.isBaby()
                || pTarget.getType().is(SMEntityTags.PIRANHA_ALWAYS_ATTACKS)
        ) && !(pTarget instanceof Piranha
                || pTarget.getType().is(SMEntityTags.IS_LIVING_INORGANIC)
                || pTarget.hasCustomName()
                || (pTarget instanceof Bucketable bucketable && bucketable.fromBucket()))
                || this.boatTarget != null;
    }

    public boolean piranhaAngryAtPlayer(LivingEntity target) {
        return isAngryAt(target) || (target instanceof Player player && !player.getAbilities().instabuild) || target.getHealth() < target.getMaxHealth();
    }

    public static boolean checkPiranhaSpawnRules(EntityType<? extends WaterAnimal> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        this.entityData.define(HAS_BOAT_TARGET, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.addPersistentAngerSaveData(pCompound);
        pCompound.putBoolean("hasBoatTarget", this.hasBoatTarget());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.readPersistentAngerSaveData(this.level(), pCompound);
        this.setHasBoatTarget(pCompound.getBoolean("hasBoatTarget"));
    }

    public void tick() {
        super.tick();
        /*
        System.out.println("Regular Target: " + this.getTarget());
        System.out.println(hasBoatTarget());

        if (boatTarget != null) {
            System.out.println(this.boatTarget.getDamage());
            System.out.println("Boat Target: " + this.boatTarget);
        }

         */

    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
            this.setHasBoatTarget(this.getBoatTarget() != null);
        }
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
        if ((event.isMoving() && this.getRemainingPersistentAngerTime() > 0) || this.hasBoatTarget()) {
            return event.setAndContinue(SWIMMING_ANGRY_ANIM);
        } else if (event.isMoving()) {
            return event.setAndContinue(SWIMMING_ANIM);
        }
        return PlayState.STOP;
    }

    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int pTime) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, pTime);
    }

    public boolean hasBoatTarget() {
        return this.entityData.get(HAS_BOAT_TARGET);
    }

    public void setHasBoatTarget(boolean hasBoatTarget) {
        this.entityData.set(HAS_BOAT_TARGET, hasBoatTarget);
    }

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @Nullable
    @Override
    public LivingEntity getTarget() {
        return this.boatTarget == null ? super.getTarget() : null;
    }

    @Nullable
    public Boat getBoatTarget() {
        return this.boatTarget;
    }

    public void setBoatTarget(@Nullable Boat boatTarget) {
        this.boatTarget = boatTarget;
    }

    static class PiranhaSwimGoal extends RandomSwimmingGoal {
        private final Piranha fish;

        public PiranhaSwimGoal(Piranha pFish) {
            super(pFish, 1.0D, 40);
            this.fish = pFish;
        }

        public boolean canUse() {
            return this.fish.canRandomSwim() && super.canUse();
        }
    }

    static class PiranhaAttackGoal extends MeleeAttackGoal {
        Piranha piranha;

        PiranhaAttackGoal(PathfinderMob pMob) {
            super(pMob, 1.5F, true);
            piranha = (Piranha) pMob;
        }

        @Override
        public void tick() {
            super.tick();
            LivingEntity target = this.piranha.getTarget();
            if (target != null) {
                if ((piranha.getLastHurtByMob() == null || !piranha.getLastHurtByMob().is(target)) && target.getHealth() >= target.getMaxHealth()) {
                    this.piranha.stopBeingAngry();
                }
            }
        }
    }

    static class PiranhaTargetBoatGoal extends Goal {
        Piranha piranha;
        protected final int randomInterval;
        @Nullable
        protected Boat target;
        private int unseenTicks;
        protected int unseenMemoryTicks = 60;

        public PiranhaTargetBoatGoal(PathfinderMob pMob) {
            this.piranha = (Piranha) pMob;
            this.randomInterval = reducedTickDelay(10);
            this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        }

        @Override
        public boolean canUse() {
            if (piranha.getTarget() != null && this.randomInterval > 0 && this.piranha.getRandom().nextInt(this.randomInterval) != 0) {
                return false;
            } else {
                this.findTarget();
                return true;
            }
        }

        @Override
        public boolean canContinueToUse() {
            Boat boatTarget = this.piranha.getBoatTarget();
            if (boatTarget == null) {
                boatTarget = this.target;
            }

            if (boatTarget == null) {
                return false;
            } else if (boatTarget.isRemoved() || boatTarget.isInvulnerable()) {
                return false;
            } else {
                double d0 = this.getFollowDistance();
                if (this.piranha.distanceToSqr(boatTarget) > d0 * d0) {
                    return false;
                } else {
                    if (this.piranha.getSensing().hasLineOfSight(boatTarget)) {
                        this.unseenTicks = 0;
                    } else if (++this.unseenTicks > reducedTickDelay(this.unseenMemoryTicks)) {
                        return false;
                    }

                    this.piranha.setBoatTarget(boatTarget);
                    return true;
                }
            }
        }

        protected AABB getTargetSearchArea(double pTargetDistance) {
            return this.piranha.getBoundingBox().inflate(pTargetDistance, 4.0D, pTargetDistance);
        }

        protected void findTarget() {
            this.target = this.getNearestBoat(
                    this.piranha.level().getEntitiesOfClass(Boat.class, this.getTargetSearchArea(this.getFollowDistance()), (p_148152_) -> true),
                    this.piranha.getX(), this.piranha.getEyeY(), this.piranha.getZ()
            );
        }

        @Nullable
        private <T extends Boat> T getNearestBoat(List<? extends T> pEntities, double pX, double pY, double pZ) {
            double d0 = -1.0D;
            T target = null;

            for(T possibleTarget : pEntities) {
                double distanceToTarget = possibleTarget.distanceToSqr(pX, pY, pZ);
                if (d0 == -1.0D || distanceToTarget < d0) {
                    d0 = distanceToTarget;
                    target = possibleTarget;
                }
            }

            return target;
        }

        protected double getFollowDistance() {
            return this.piranha.getAttributeValue(Attributes.FOLLOW_RANGE);
        }

        @Override
        public void start() {
            this.piranha.setBoatTarget(this.target);
            this.unseenTicks = 0;
        }

        @Override
        public void stop() {
            this.piranha.setBoatTarget(null);
            this.target = null;
        }
    }

    static class PiranhaAttackBoatGoal extends Goal {
        Piranha piranha;
        private final double speedModifier;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;
        private int ticksUntilNextAttack;
        private long lastCanUseCheck;

        public PiranhaAttackBoatGoal(PathfinderMob pMob) {
            this.piranha = (Piranha) pMob;
            this.speedModifier = 1.5F;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            long i = this.piranha.level().getGameTime();
            if (i - this.lastCanUseCheck < 20L) {
                return false;
            } else {
                this.lastCanUseCheck = i;
                Boat boatTarget = this.piranha.getBoatTarget();
                if (boatTarget == null) {
                    return false;
                } else if (!boatTarget.isAlive()) {
                    return false;
                } else {
                    this.path = this.piranha.getNavigation().createPath(boatTarget, 0);
                    if (this.path != null) {
                        return true;
                    } else {
                        return this.getAttackReachSqr(boatTarget) >= this.piranha.distanceToSqr(boatTarget.getX(), boatTarget.getY(), boatTarget.getZ());
                    }
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            Boat boat = this.piranha.getBoatTarget();
            if (boat == null) {
                return false;
            } else if (!boat.isAlive()) {
                return false;
            } else return this.piranha.isWithinRestriction(boat.blockPosition());
        }

        @Override
        public void start() {
            this.piranha.getNavigation().moveTo(this.path, this.speedModifier);
            this.piranha.setAggressive(true);
            this.ticksUntilNextPathRecalculation = 0;
            this.ticksUntilNextAttack = 0;
        }

        @Override
        public void stop() {
            this.piranha.setTarget(null);
            this.piranha.setAggressive(false);
            this.piranha.getNavigation().stop();
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            Boat boatTarget = this.piranha.getBoatTarget();
            if (boatTarget != null) {
                this.piranha.getLookControl().setLookAt(boatTarget, 30.0F, 30.0F);
                double d0 = piranha.distanceToSqr(boatTarget.position());
                this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
                if (this.piranha.getSensing().hasLineOfSight(boatTarget) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || boatTarget.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.piranha.getRandom().nextFloat() < 0.05F)) {
                    this.pathedTargetX = boatTarget.getX();
                    this.pathedTargetY = boatTarget.getY();
                    this.pathedTargetZ = boatTarget.getZ();
                    this.ticksUntilNextPathRecalculation = 4 + this.piranha.getRandom().nextInt(7);
                    if (d0 > 1024.0D) {
                        this.ticksUntilNextPathRecalculation += 10;
                    } else if (d0 > 256.0D) {
                        this.ticksUntilNextPathRecalculation += 5;
                    }

                    if (!this.piranha.getNavigation().moveTo(boatTarget, this.speedModifier)) {
                        this.ticksUntilNextPathRecalculation += 15;
                    }

                    this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
                }

                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
                this.checkAndPerformAttack(boatTarget, d0);
            }
        }

        protected void checkAndPerformAttack(Boat pEnemy, double pDistToEnemySqr) {
            double d0 = this.getAttackReachSqr(pEnemy);
            if (pDistToEnemySqr <= d0 && this.ticksUntilNextAttack <= 0) {
                this.resetAttackCooldown();
                float damage = (float)piranha.getAttributeValue(Attributes.ATTACK_DAMAGE);
                pEnemy.hurt(piranha.damageSources().mobAttack(piranha), damage * 1.3F);
            }
        }

        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = this.adjustedTickDelay(20);
        }

        protected double getAttackReachSqr(Boat pAttackTarget) {
            return this.piranha.getBbWidth() * 2.0F * this.piranha.getBbWidth() * 2.0F + pAttackTarget.getBbWidth();
        }
    }
}