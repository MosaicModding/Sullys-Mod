package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
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
    protected static final RawAnimation SWIMMING_ANGRY_ANIM = RawAnimation.begin().thenLoop("animation.piranha.swim_angry");
    //protected static final RawAnimation JUMPING_ANIM = RawAnimation.begin().thenPlay("animation.piranha.jump");
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Piranha.class, EntityDataSerializers.INT);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(5, 10);
    @Nullable
    private UUID persistentAngerTarget;

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
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(4, new PiranhaSwimGoal(this));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, this::isPiranhaAngry));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, true, this::isPiranhaAngry));
        this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    public int getMaxSchoolSize() {
        return 5;
    }

    public boolean isPiranhaAngry(LivingEntity pTarget) {
        return (isAngryAt(pTarget) || pTarget.getHealth() < pTarget.getMaxHealth()) && !(pTarget instanceof Piranha);
    }

    public static boolean checkPiranhaSpawnRules(EntityType<? extends LivingEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getFluidState(pos.below()).is(Fluids.WATER) && level.getFluidState(pos.above()).is(FluidTags.WATER);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.addPersistentAngerSaveData(pCompound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.readPersistentAngerSaveData(this.level(), pCompound);
    }

    public void tick() {
        super.tick();
        if (level().isClientSide()) System.out.println("Client: " + this.getTarget());
        if (!level().isClientSide()) System.out.println("Server: " + this.getTarget());
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
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
        if (event.isMoving()) {
            if ((this.getRemainingPersistentAngerTime() > 0)) {
                return event.setAndContinue(SWIMMING_ANGRY_ANIM);
            } else {
                return event.setAndContinue(SWIMMING_ANIM);
            }
        }
        return PlayState.STOP;
    }

    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int pTime) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, pTime);
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
        public boolean canUse() {
            return super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse();
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
}
