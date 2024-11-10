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
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Piranha extends AbstractSchoolingFish implements NeutralMob {
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Piranha.class, EntityDataSerializers.INT);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(5, 10);
    private static final EntityDataAccessor<Boolean> HAS_BOAT_TARGET = SynchedEntityData.defineId(Piranha.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_LEAPING = SynchedEntityData.defineId(Piranha.class, EntityDataSerializers.BOOLEAN);
    public final AnimationState swimState = new AnimationState();
    public final AnimationState angrySwimState = new AnimationState();
    @Nullable
    private UUID persistentAngerTarget;
    @Nullable
    private Boat boatTarget;

    public Piranha(EntityType<? extends AbstractSchoolingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractFish.createAttributes().add(Attributes.ATTACK_DAMAGE, 2).add(Attributes.MAX_HEALTH, 8).add(Attributes.MOVEMENT_SPEED, 0.70);
    }


    @Override
    protected void registerGoals() {
        //NOTE: Never call the super method! We don't want the panic goal in AbstractFish
        this.goalSelector.addGoal(1, new PiranhaLeapTowardsPlayerGoal(this));
        this.goalSelector.addGoal(1, new PiranhaJumpOnLandGoal(this));
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
        this.entityData.define(IS_LEAPING, false);
    }

    public boolean getLeaping() {
        return this.getEntityData().get(IS_LEAPING);
    }

    public void setLeaping(boolean bool) {
        this.getEntityData().set(IS_LEAPING, bool);
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
        if (level().isClientSide()) {
            boolean angryFlag = this.getRemainingPersistentAngerTime() > 0 || this.hasBoatTarget();
            this.swimState.animateWhen(this.isInWater() && !angryFlag, this.tickCount);
            this.angrySwimState.animateWhen(this.isInWater() && angryFlag, this.tickCount);
        }
    }

    @Override
    protected int calculateFallDamage(float pFallDistance, float pDamageMultiplier) {
        if (this.getType().is(EntityTypeTags.FALL_DAMAGE_IMMUNE)) {
            return 0;
        } else {
            MobEffectInstance mobeffectinstance = this.getEffect(MobEffects.JUMP);
            float f = mobeffectinstance == null ? 0.0F : (float)(mobeffectinstance.getAmplifier() + 1);
            return Mth.ceil((pFallDistance - 6.0F - f) * pDamageMultiplier);
        }
    }

    @Nullable
    public BlockPos findWater() {
        for (BlockPos blockPos : BlockPos.randomBetweenClosed(this.getRandom(), 2, this.getBlockX() - 5, this.getBlockY() - 5, this.getBlockZ() - 5, this.getBlockX() + 5, this.getBlockY(), this.getBlockZ() + 5)) {
            if (level().getFluidState(blockPos).is(Fluids.WATER)) {
                return blockPos;
            }
        }
        for (BlockPos blockPos : BlockPos.randomBetweenClosed(this.getRandom(), 2, this.getBlockX() - 20, this.getBlockY() - 5, this.getBlockZ() - 20, this.getBlockX() + 20, this.getBlockY(), this.getBlockZ() + 20)) {
            if (level().getFluidState(blockPos).is(Fluids.WATER)) {
                return blockPos;
            }
        }
        return null;
    }


    public void leapTowardsWater(BlockPos blockPos) {
        this.getLookControl().setLookAt(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        Vec3 vec3;
        if (this.distanceToSqr(blockPos.getX(), blockPos.getY(), blockPos.getX()) < 5) {
            vec3 = (new Vec3(blockPos.getX() - this.getX(), blockPos.getY() - this.getY(), blockPos.getZ() - this.getZ())).normalize();
        } else {
            vec3 = (new Vec3(blockPos.getX() - (this.getX() - 5), blockPos.getY() - this.getY(), blockPos.getZ() - (this.getZ() - 5))).normalize();
        }
        double yVelocity = 0.2D;
        if (this.distanceToSqr(blockPos.getX(), blockPos.getY(), blockPos.getX()) < 4) {
            yVelocity = 0.1D;
        }
        this.setDeltaMovement(this.getDeltaMovement().add(vec3.x, yVelocity, vec3.z));
        this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.isInWater() && this.onGround() && this.verticalCollision) {
            BlockPos blockPos = findWater();
            if (blockPos != null) {
                if (this.getHealth() < (this.getMaxHealth() / 2) || this.getAirSupply() < (this.getMaxAirSupply() / 2)) {
                    leapTowardsWater(blockPos);
                }
            }
        }
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

        PiranhaAttackGoal(Piranha pMob) {
            super(pMob, 1.5F, true);
            piranha = pMob;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && piranha.isInWater() && Objects.requireNonNull(piranha.getTarget()).isInWater();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && piranha.isInWater() && Objects.requireNonNull(piranha.getTarget()).isInWater();
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

        public PiranhaTargetBoatGoal(Piranha pMob) {
            this.piranha = pMob;
            this.randomInterval = reducedTickDelay(10);
            this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        }

        @Override
        public boolean canUse() {
            if (piranha.getTarget() != null && this.randomInterval > 0 && this.piranha.getRandom().nextInt(this.randomInterval) != 0) return false;
            this.findTarget();
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            Boat boatTarget = this.piranha.getBoatTarget();
            if (boatTarget == null) {
                boatTarget = this.target;
            }

            if (boatTarget == null) {
                return false;
            } else if (boatTarget.isRemoved() || boatTarget.isInvulnerable() || !boatTarget.isInWater()) {
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
                    if (possibleTarget.isInWater()) {
                        d0 = distanceToTarget;
                        target = possibleTarget;
                    }
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

        public PiranhaAttackBoatGoal(Piranha pMob) {
            this.piranha = pMob;
            this.speedModifier = 1.5F;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            long gameTime = this.piranha.level().getGameTime();
            Boat boatTarget = this.piranha.getBoatTarget();
            if (gameTime - this.lastCanUseCheck < 20L) return false;
            this.lastCanUseCheck = gameTime;

            if (boatTarget == null || !boatTarget.isAlive() || !boatTarget.isInWater()) return false;
            this.path = this.piranha.getNavigation().createPath(boatTarget, 0);
            if (this.path != null) {
                return true;
            } else {
                return this.getAttackReachSqr(boatTarget) >= this.piranha.distanceToSqr(boatTarget.getX(), boatTarget.getY(), boatTarget.getZ());
            }
        }

        @Override
        public boolean canContinueToUse() {
            Boat boat = this.piranha.getBoatTarget();
            if (boat == null || !boat.isAlive()) return false;

            return this.piranha.isWithinRestriction(boat.blockPosition());
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

    static class PiranhaLeapTowardsPlayerGoal extends JumpGoal {
        Piranha piranha;
        boolean didAttack;

        public PiranhaLeapTowardsPlayerGoal(Piranha pMob) {
            this.piranha = pMob;
        }

        @Override
        public boolean canUse() {
            LivingEntity target = piranha.getTarget();
            if (piranha.getRandom().nextInt(4) != 0) return false;
            if (piranha.getHealth() < (piranha.getMaxHealth() / 2)) return false;
            if (piranha.getAirSupply() < (piranha.getMaxAirSupply() / 2)) return false;
            if (piranha.getBlockStateOn().getFluidState().is(Fluids.WATER) || piranha.getBlockStateOn().isAir()) return false;
            if (target == null || !target.isAlive()) return false;
            if (target.getMotionDirection() != target.getDirection()) return false;

            boolean pathClear = isPathClear(piranha, target);
            if (!pathClear) {
                piranha.getNavigation().createPath(target, 0);
            }
            return pathClear;
        }

        protected boolean isPathClear(Piranha piranha, LivingEntity pLivingEntity) {
            double d0 = pLivingEntity.getZ() - piranha.getZ();
            double d1 = pLivingEntity.getX() - piranha.getX();
            double d2 = d0 / d1;
            int i = 6;

            for(int j = 0; j < 6; ++j) {
                double d3 = d2 == 0.0D ? 0.0D : d0 * (double)((float)j / 6.0F);
                double d4 = d2 == 0.0D ? d1 * (double)((float)j / 6.0F) : d3 / d2;

                for(int k = 1; k < 4; ++k) {
                    if (!piranha.level().getBlockState(BlockPos.containing(piranha.getX() + d4, piranha.getY() + (double)k, piranha.getZ() + d3)).canBeReplaced()) {
                        return false;
                    }
                }
            }

            return true;
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = piranha.getTarget();
            if (piranha.getRandom().nextInt(4) != 0) return false;
            if (target == null || !target.isAlive()) return false;
            if (piranha.getBlockStateOn().getFluidState().is(Fluids.WATER) || piranha.getBlockStateOn().isAir()) return false;

            double yMovement = piranha.getDeltaMovement().y;
            return (!(yMovement * yMovement < (double)0.05F) || !(Math.abs(piranha.getXRot()) < 15.0F) || !piranha.onGround());
        }

        @Override
        public boolean isInterruptable() {
            return false;
        }

        @Override
        public void start() {
            piranha.setJumping(true);
            piranha.setLeaping(true);
            LivingEntity target = piranha.getTarget();
            if (target != null) {
                double yVelocity = 0.3D;
                if (piranha.distanceTo(target) < 5) {
                    yVelocity = 0.2D;
                }
                piranha.getLookControl().setLookAt(target, 60.0F, 30.0F);
                Vec3 vec3 = (new Vec3(target.getX() - piranha.getX(), target.getY() - piranha.getY(), target.getZ() - piranha.getZ())).normalize();
                piranha.setDeltaMovement(piranha.getDeltaMovement().add(vec3.x, yVelocity, vec3.z));
            }

           // piranha.getNavigation().stop();
        }

        @Override
        public void stop() {
            piranha.setJumping(false);
            piranha.setLeaping(false);
        }

        @Override
        public void tick() {
            LivingEntity target = piranha.getTarget();
            if (target != null) {
                piranha.getLookControl().setLookAt(target, 60.0F, 30.0F);
            }

            Vec3 vec3 = piranha.getDeltaMovement();
            if (vec3.y * vec3.y < (double)0.03F && piranha.getXRot() != 0.0F) {
                piranha.setXRot(Mth.rotLerp(0.2F, piranha.getXRot(), 0.0F));
            } else {
                double d0 = vec3.horizontalDistance();
                double d1 = Math.signum(-vec3.y) * Math.acos(d0 / vec3.length()) * (double)(180F / (float)Math.PI);
                piranha.setXRot((float)d1);
            }
            if (target != null && piranha.distanceTo(target) <= 2.0F) {
                piranha.doHurtTarget(target);
            } else if (piranha.getXRot() > 0.0F && piranha.onGround() && (float)piranha.getDeltaMovement().y != 0.0F) {
                piranha.setXRot(60.0F);
                piranha.setTarget(null);
            }

        }
    }

    static class PiranhaJumpOnLandGoal extends JumpGoal {
        Piranha piranha;
        boolean didAttack;

        public PiranhaJumpOnLandGoal(Piranha pMob) {
            this.piranha = pMob;
        }
        
        @Override
        public boolean canUse() {
            LivingEntity target = piranha.getTarget();
            if (!piranha.level().getFluidState(piranha.getOnPos().above()).is(Fluids.WATER)) return false;
            if (target == null || !target.isAlive()) return false;
            if (target.isInWater() || !piranha.isInWater()) return false;
            if (target.getMotionDirection() != target.getDirection()) return false;

            boolean pathClear = isPathClear(piranha, target);
            if (!pathClear) {
                piranha.getNavigation().createPath(target, 0);
            }
            return pathClear;
        }

        protected boolean isPathClear(Piranha piranha, LivingEntity pLivingEntity) {
            double d0 = pLivingEntity.getZ() - piranha.getZ();
            double d1 = pLivingEntity.getX() - piranha.getX();
            double d2 = d0 / d1;
            int i = 6;

            for(int j = 0; j < 6; ++j) {
                double d3 = d2 == 0.0D ? 0.0D : d0 * (double)((float)j / 6.0F);
                double d4 = d2 == 0.0D ? d1 * (double)((float)j / 6.0F) : d3 / d2;

                for(int k = 1; k < 4; ++k) {
                    if (!piranha.level().getBlockState(BlockPos.containing(piranha.getX() + d4, piranha.getY() + (double)k, piranha.getZ() + d3)).canBeReplaced()) {
                        return false;
                    }
                }
            }

            return true;
        }
        
        @Override
        public boolean canContinueToUse() {
            LivingEntity target = piranha.getTarget();
            if (target == null || !target.isAlive()) return false;
            if (target.isInWater() || !piranha.isInWater()) return false;

            double yMovement = piranha.getDeltaMovement().y;
            return (!(yMovement * yMovement < (double)0.05F) || !(Math.abs(piranha.getXRot()) < 15.0F) || !piranha.onGround());
        }

        @Override
        public boolean isInterruptable() {
            return false;
        }
        
        @Override
        public void start() {
            piranha.setJumping(true);
            piranha.setLeaping(true);
            LivingEntity target = piranha.getTarget();
            if (target != null) {
                double yVelocity = 0.5D;
                if (piranha.distanceTo(target) < 5) {
                    yVelocity = 0.4D;
                }
                piranha.getLookControl().setLookAt(target, 60.0F, 30.0F);
                Vec3 vec3 = (new Vec3(target.getX() - piranha.getX(), target.getY() - piranha.getY(), target.getZ() - piranha.getZ())).normalize();
                piranha.setDeltaMovement(piranha.getDeltaMovement().add(vec3.x, yVelocity, vec3.z));
            }

            piranha.getNavigation().stop();
        }

        @Override
        public void stop() {
            piranha.setJumping(false);
            piranha.setLeaping(false);
        }

        @Override
        public void tick() {
            LivingEntity target = piranha.getTarget();
            if (target != null) {
                piranha.getLookControl().setLookAt(target, 60.0F, 30.0F);
            }

            Vec3 vec3 = piranha.getDeltaMovement();
            if (vec3.y * vec3.y < (double)0.03F && piranha.getXRot() != 0.0F) {
                piranha.setXRot(Mth.rotLerp(0.2F, piranha.getXRot(), 0.0F));
            } else {
                double d0 = vec3.horizontalDistance();
                double d1 = Math.signum(-vec3.y) * Math.acos(d0 / vec3.length()) * (double)(180F / (float)Math.PI);
                piranha.setXRot((float)d1);
            }
            if (target != null && piranha.distanceTo(target) <= 2.0F) {
                piranha.doHurtTarget(target);
            } else if (piranha.getXRot() > 0.0F && piranha.onGround() && (float)piranha.getDeltaMovement().y != 0.0F) {
                piranha.setXRot(60.0F);
                piranha.setTarget(null);
            }

        }
    }
}