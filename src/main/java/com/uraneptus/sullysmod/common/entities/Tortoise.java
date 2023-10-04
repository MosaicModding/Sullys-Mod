package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.common.blocks.TortoiseEggBlock;
import com.uraneptus.sullysmod.core.other.tags.SMEntityTags;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
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

import java.util.List;

public class Tortoise extends Animal implements GeoEntity {
    public static final EntityDataAccessor<Integer> HIDE_TIMER = SynchedEntityData.defineId(Tortoise.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(Tortoise.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> LAYING_EGG = SynchedEntityData.defineId(Tortoise.class, EntityDataSerializers.BOOLEAN);
    public static final Ingredient FOOD_ITEMS = Ingredient.of(SMItemTags.TORTOISE_FOOD);
    protected static final RawAnimation WALKING_ANIM = RawAnimation.begin().thenLoop("animation.tortoise.walking");
    protected static final RawAnimation HIDING_ANIM = RawAnimation.begin().thenPlayAndHold("animation.tortoise.hide").thenLoop("animation.tortoise.hiding");
    protected static final RawAnimation REVEAL_ANIM = RawAnimation.begin().thenPlayAndHold("animation.tortoise.reveal");
    private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);
    int layEggCounter;

    public Tortoise(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.275D)
                .add(Attributes.MAX_HEALTH, 30.0D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob mob) {
        return SMEntityTypes.TORTOISE.get().create(level);
    }

    @Override
    public float getScale() {
        return this.isBaby() ? 0.15f : 1.0f;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new TortoiseBreedGoal(this, 0.5D));
        this.goalSelector.addGoal(3, new TortoiseLayEggGoal(this, 0.5));
        this.goalSelector.addGoal(4, new RiderIgnoringTemptGoal(this, 0.45D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new RiderAllowingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(6, new TortoiseLookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new TortoiseLookAroundGoal(this));
    }

    @Override
    protected void updateControlFlags() {
        boolean flag = !(this.getVehicle() instanceof Boat);
        this.goalSelector.setControlFlag(Goal.Flag.MOVE, true);
        this.goalSelector.setControlFlag(Goal.Flag.JUMP, flag);
        this.goalSelector.setControlFlag(Goal.Flag.LOOK, true);
    }

    @Override
    protected boolean isImmobile() {
        return super.isImmobile() && isVehicle();
    }

    @Override
    public boolean isSteppingCarefully() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return this.getHideTimerDuration() > 0;
    }

    @Override
    public void knockback(double pStrength, double pX, double pZ) {
        if (this.getHideTimerDuration() > 200) {
            super.knockback(pStrength, pX, pZ);
        } else super.knockback(pStrength * 0.25D, pX * 0.25D, pZ * 0.25D);
    }

    @Override
    public void tick() {
        super.tick();
        Level level = this.level();

        //Hiding core stuff
        if (this.getHideTimerDuration() > 0) {
            setHideTimerDuration(getHideTimerDuration() - 1);
        }
        if (this.getHideTimerDuration() == 1) {
            this.gameEvent(GameEvent.CONTAINER_OPEN, null);

            level.playSound(null, this.blockPosition(), SMSounds.TORTOISE_EMERGE.get(), SoundSource.AMBIENT, 1.0F, 1.0F);
        }

        //Hiding from mobs
        AABB hidingRange = this.getBoundingBox().inflate(8.0D);

        List<Entity> withinRange = level.getEntities(null, hidingRange);

        for (Entity e : withinRange) {
            if (e.getType().is(SMEntityTags.SCARES_TORTOISES)) {
                if (this.getHideTimerDuration() == 0) {
                    this.setHideTimerDuration(205);
                    this.gameEvent(GameEvent.CONTAINER_CLOSE, null);
                } else this.setHideTimerDuration(200);
            }
        }

        //Hiding During Raids
        if (level instanceof ServerLevel serverLevel) {
            if (serverLevel.isRaided(this.blockPosition())) {
                if (this.getHideTimerDuration() == 0) {
                    this.setHideTimerDuration(205);
                    this.gameEvent(GameEvent.CONTAINER_CLOSE, null);
                } else this.setHideTimerDuration(200);
            }
        }
    }

    @Override
    protected void ageBoundaryReached() {
        super.ageBoundaryReached();
        if (!this.isBaby() && this.level().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.spawnAtLocation(new ItemStack(SMItems.TORTOISE_SCUTE.get(), 2));
        }
    }

    public @NotNull InteractionResult mobInteract(Player pPlayer, @NotNull InteractionHand pHand) {
        boolean flag = this.isFood(pPlayer.getItemInHand(pHand)) || this.isBaby();
        if (!flag && !this.isVehicle() && !pPlayer.isSecondaryUseActive()) {
            if (!this.level().isClientSide) {
                pPlayer.startRiding(this);
                this.setHideTimerDuration(100);
            }
            this.gameEvent(GameEvent.ENTITY_INTERACT, null);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public boolean isFood(@NotNull ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }

    @Override
    protected float getStandingEyeHeight(@NotNull Pose pose, EntityDimensions size) {
        return size.height * 0.45f;
    }

    @Override
    public double getPassengersRidingOffset() {
        if (this.hasPassenger(entity -> entity instanceof Villager)) {
            if (this.getHideTimerDuration() > 1) {
                return super.getPassengersRidingOffset() + 0.3 * 0.5D;
            } else {
                return super.getPassengersRidingOffset() + 0.3;
            }
        }
        if (this.getHideTimerDuration() > 1) {
            return super.getPassengersRidingOffset() * 0.75D;
        } else return super.getPassengersRidingOffset();
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return null;
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
        if (event.isMoving() && getHideTimerDuration() == 0 && onGround()) {
            return event.setAndContinue(WALKING_ANIM);
        } else if (getHideTimerDuration() > 1) {
            return event.setAndContinue(HIDING_ANIM);
        } else if (getHideTimerDuration() == 1) {
            return event.setAndContinue(REVEAL_ANIM);
        }
        return PlayState.STOP;
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        Level level = this.level();
        if (this.getHideTimerDuration() > 1) {
            if (source.getDirectEntity() instanceof Projectile) {
                level.playSound(null, this.blockPosition(), SMSounds.TORTOISE_HURT_HIDDEN.get(), SoundSource.AMBIENT, 1.0F, 1.0F);
                this.setHideTimerDuration(200);
                return false;
            } else {
                this.setHideTimerDuration(200);
                return super.hurt(source, amount * 0.5f);
            }
        } else {
            if (this.isInvulnerableTo(source)) {
                return false;
            } else {
                this.setHideTimerDuration(205);
                return super.hurt(source, amount);
            }
        }
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource source) {
        if (this.getHideTimerDuration() > 200) {
            return this.isBaby() ? SMSounds.BABY_TORTOISE_HURT.get() : SMSounds.TORTOISE_HURT.get();
        } else return SMSounds.TORTOISE_HURT_HIDDEN.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SMSounds.TORTOISE_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return this.isBaby() ? SMSounds.BABY_TORTOISE_DEATH.get() : SMSounds.TORTOISE_DEATH.get();
    }

    public int getHideTimerDuration() {
        return this.entityData.get(HIDE_TIMER);
    }

    public void setHideTimerDuration(int durationInTicks) {
        Level level = this.level();

        if (this.getHideTimerDuration() < durationInTicks || durationInTicks == this.getHideTimerDuration() - 1) {
            if (this.getHideTimerDuration() == 0) {
                level.playSound(null, this.blockPosition(), SMSounds.TORTOISE_HIDE.get(), SoundSource.AMBIENT, 1.0F, 1.0F);
            }
            this.entityData.set(HIDE_TIMER, durationInTicks);
        }
    }

    public boolean hasEgg() {
        return this.entityData.get(HAS_EGG);
    }

    void setHasEgg(boolean pHasEgg) {
        this.entityData.set(HAS_EGG, pHasEgg);
    }

    public boolean isLayingEgg() {
        return this.entityData.get(LAYING_EGG);
    }

    void setLayingEgg(boolean pIsDigging) {
        this.layEggCounter = pIsDigging ? 1 : 0;
        this.entityData.set(LAYING_EGG, pIsDigging);
    }

    @Override
    public boolean canFallInLove() {
        return this.getHideTimerDuration() == 0 && !this.hasEgg() && super.canFallInLove();
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }

    public void aiStep() {
        super.aiStep();
        if (this.isAlive() && this.isLayingEgg() && this.layEggCounter >= 1 && this.layEggCounter % 5 == 0) {
            BlockPos blockpos = this.blockPosition();
            if (TortoiseEggBlock.onDirt(this.level(), blockpos)) {
                this.level().levelEvent(2001, blockpos, Block.getId(this.level().getBlockState(blockpos.below())));
            }
        }

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HIDE_TIMER, 0);
        this.entityData.define(HAS_EGG, false);
        this.entityData.define(LAYING_EGG, false);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("HideTimer", getHideTimerDuration());
        nbt.putBoolean("HasEgg", hasEgg());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setHideTimerDuration(nbt.getInt("HideTimer"));
        this.setHasEgg(nbt.getBoolean("HasEgg"));
    }

    public static class RiderAllowingRandomStrollGoal extends RandomStrollGoal {
        public RiderAllowingRandomStrollGoal(PathfinderMob pMob, double pSpeedModifier) {
            super(pMob, pSpeedModifier);
        }

        @Override
        public boolean canUse() {
            if (this.mob instanceof Tortoise tortoise) {
                if (tortoise.getHideTimerDuration() > 1 || tortoise.hasEgg()) {
                    return false;
                }
            }

            if (!this.forceTrigger) {
                if (this.mob.getNoActionTime() >= 100) {
                    return false;
                }

                if (this.mob.getRandom().nextInt(reducedTickDelay(this.interval)) != 0) {
                    return false;
                }
            }

            Vec3 vec3 = this.getPosition();
            if (vec3 == null) {
                return false;
            } else {
                this.wantedX = vec3.x;
                this.wantedY = vec3.y;
                this.wantedZ = vec3.z;
                this.forceTrigger = false;
                return true;
            }
        }

        @Override
        public boolean canContinueToUse() {
            if (this.mob instanceof Tortoise tortoise) {
                if (tortoise.getHideTimerDuration() > 1 || tortoise.hasEgg()) {
                    return false;
                }
            }

            return !this.mob.getNavigation().isDone();
        }
    }

    public static class RiderIgnoringTemptGoal extends TemptGoal {
        public RiderIgnoringTemptGoal(PathfinderMob pMob, double pSpeedModifier, Ingredient pItems, boolean pCanScare) {
            super(pMob, pSpeedModifier, pItems, pCanScare);
        }

        @Override
        public boolean canUse() {
            if (this.mob instanceof Tortoise tortoise) {
                if (tortoise.getHideTimerDuration() > 1) {
                    return false;
                }
            }

            if (this.player != null && this.player.isPassenger()) {
                return false;
            }

            return super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            if (this.mob instanceof Tortoise tortoise) {
                if (tortoise.getHideTimerDuration() > 1) {
                    return false;
                }
            }

            if (this.player != null && this.player.isPassenger()) {
                return false;
            }

            return super.canContinueToUse();
        }
    }

    public static class TortoiseLookAtPlayerGoal extends LookAtPlayerGoal {
        public TortoiseLookAtPlayerGoal(Mob pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance) {
            super(pMob, pLookAtType, pLookDistance);
        }

        @Override
        public boolean canUse() {
            if (this.mob instanceof Tortoise tortoise) {
                if (tortoise.getHideTimerDuration() > 1) {
                    return false;
                }
            }

            if (this.lookAt != null && this.lookAt.isPassenger()) {
                return false;
            }

            return super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            if (this.mob instanceof Tortoise tortoise) {
                if (tortoise.getHideTimerDuration() > 1) {
                    return false;
                }
            }

            if (this.lookAt != null && this.lookAt.isPassenger()) {
                return false;
            }

            return super.canContinueToUse();
        }
    }

    public static class TortoiseLookAroundGoal extends RandomLookAroundGoal {
        private final Tortoise tortoise;

        public TortoiseLookAroundGoal(Tortoise tortoise) {
            super(tortoise);

            this.tortoise = tortoise;
        }

        @Override
        public boolean canUse() {
            if (this.tortoise.getHideTimerDuration() > 1) {
                return false;
            }

            return super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            if (this.tortoise.getHideTimerDuration() > 1) {
                return false;
            }

            return super.canContinueToUse();
        }
    }

    public static class TortoiseBreedGoal extends BreedGoal {
        private final Tortoise tortoise;

        public TortoiseBreedGoal(Tortoise tortoise, double pSpeedModifier) {
            super(tortoise, pSpeedModifier);
            this.tortoise = tortoise;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !this.tortoise.hasEgg() && this.tortoise.getHideTimerDuration() == 0;
        }

        @Override
        protected void breed() {
            ServerPlayer player = this.animal.getLoveCause();

            if (this.partner != null) {
                if (player == null && this.partner.getLoveCause() != null) {
                    player = this.partner.getLoveCause();
                }
                if (player != null) {
                    player.awardStat(Stats.ANIMALS_BRED);
                    CriteriaTriggers.BRED_ANIMALS.trigger(player, this.animal, this.partner, null);

                }
            }

            this.tortoise.setHasEgg(true);
            this.tortoise.resetLove();
            this.partner.resetLove();

            RandomSource random = this.animal.getRandom();
            if (this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                this.level.addFreshEntity(new ExperienceOrb(this.level, this.animal.getX(), this.animal.getY(), this.animal.getZ(), random.nextInt(7) + 1));
            }

        }
    }

    public static class TortoiseLayEggGoal extends MoveToBlockGoal {
        private final Tortoise tortoise;

        public TortoiseLayEggGoal(Tortoise tortoise, double pSpeedModifier) {
            super(tortoise, pSpeedModifier, 32);
            this.tortoise = tortoise;
        }

        @Override
        public boolean canUse() {
            return this.tortoise.hasEgg() && tortoise.getHideTimerDuration() == 0 && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return this.tortoise.hasEgg() && tortoise.getHideTimerDuration() == 0 && super.canContinueToUse();
        }

        @Override
        protected boolean isValidTarget(LevelReader level, BlockPos pos) {
            boolean flag = false;

            for (int i = 1; i < 9; i++) {
                if (level.getBlockState(pos.above(i)).is(BlockTags.LEAVES)) {
                    flag = true;
                    break;
                }
            }

            return level.isEmptyBlock(pos.above()) && level.getBlockState(pos).is(BlockTags.DIRT) && flag;
        }

        @Override
        public double acceptedDistance() {
            return 1.50D;
        }

        @Override
        public void tick() {
            super.tick();

            if (!this.tortoise.isInWater() && this.isReachedTarget()) {

                if (this.tortoise.layEggCounter < 1) {
                    this.tortoise.setLayingEgg(true);
                } else if (this.tortoise.layEggCounter > this.adjustedTickDelay(200)) {
                    Level level = this.tortoise.level();
                    level.playSound(null, blockPos, SMSounds.TORTOISE_LAY_EGG.get(), SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);
                    level.setBlock(this.blockPos.above(), SMBlocks.TORTOISE_EGG.get().defaultBlockState().setValue(TurtleEggBlock.EGGS, this.tortoise.random.nextInt(4) + 1), 3);
                    this.tortoise.setHasEgg(false);
                    this.tortoise.setLayingEgg(false);
                    this.tortoise.setInLoveTime(600);
                }

                if (this.tortoise.isLayingEgg()) {
                    ++this.tortoise.layEggCounter;
                }
            }
        }
    }
}
