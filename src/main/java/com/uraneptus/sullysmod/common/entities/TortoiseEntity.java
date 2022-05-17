package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.SMEntityTags;
import com.uraneptus.sullysmod.core.other.SMItemTags;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.KnockbackEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public class TortoiseEntity extends Animal implements IAnimatable {

    public static final EntityDataAccessor<Integer> HIDE_TIMER = SynchedEntityData.defineId(TortoiseEntity.class, EntityDataSerializers.INT);
    private final AnimationFactory factory = new AnimationFactory(this);
    public static final Ingredient FOOD_ITEMS = Ingredient.of(SMItemTags.TORTOISE_FOOD);

    public TortoiseEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new TemptGoal(this, 0.45D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new RiderAllowingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    protected void updateControlFlags() {
        boolean flag = this.getHideTimerDuration() == 0;
        boolean flag1 = !(this.getVehicle() instanceof Boat);
        this.goalSelector.setControlFlag(Goal.Flag.MOVE, flag);
        this.goalSelector.setControlFlag(Goal.Flag.JUMP, flag && flag1);
        this.goalSelector.setControlFlag(Goal.Flag.LOOK, flag);
    }

    @Override
    protected boolean isImmobile() {
        return super.isImmobile() && isVehicle();
    }

    @Override
    public void knockback(double p_147241_, double p_147242_, double p_147243_) {
        if (this.getHideTimerDuration() > 200) {
            super.knockback(p_147241_, p_147242_, p_147243_);
        }
        else super.knockback(p_147241_ * 0.25D, p_147242_ * 0.25D, p_147243_ * 0.25D);

    }

    @Override
    public void tick() {
        super.tick();
        Level level = this.getLevel();

        //Hiding core stuff
        if (this.getHideTimerDuration() > 0) {
            setHideTimerDuration(getHideTimerDuration() - 1);
        }
        if (this.getHideTimerDuration() == 200) {
            this.goalSelector.removeAllGoals();
            this.goalSelector.addGoal(1, new FloatGoal(this));
        } else if (this.getHideTimerDuration() == 100) {
            this.goalSelector.removeAllGoals();
            this.goalSelector.addGoal(1, new FloatGoal(this));
        } else if (this.getHideTimerDuration() == 1) {
            this.goalSelector.addGoal(2, new TemptGoal(this, 0.45D, FOOD_ITEMS, false));
            this.goalSelector.addGoal(3, new RiderAllowingRandomStrollGoal(this, 0.4D));
            this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
            this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
            level.playSound(null, this.blockPosition(), SMSounds.TORTOISE_EMERGE.get(), SoundSource.AMBIENT, 1.0F, 1.0F);

            SullysMod.LOGGER.info("Gave Tortoise its AI back.");
        }

        //Hiding from mobs
        AABB hidingRange = this.getBoundingBox().inflate(8.0D);

        List<Entity> withinRange = level.getEntities(null, hidingRange);

        for (Entity e : withinRange) {
            if (e.getType().is(SMEntityTags.SCARES_TORTOISES)) {
                this.setHideTimerDuration(200);
            }
        }

        //Hiding During Raids
        if (level instanceof ServerLevel serverLevel) {
            if (serverLevel.isRaided(this.blockPosition())) {
                this.setHideTimerDuration(200);
            }
        }
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        boolean flag = this.isFood(pPlayer.getItemInHand(pHand));
        if (!flag && !this.isVehicle() && !pPlayer.isSecondaryUseActive()) {
            if (!this.level.isClientSide) {
                pPlayer.startRiding(this);
                this.setHideTimerDuration(100);
            }
            this.gameEvent(GameEvent.MOB_INTERACT, this.eyeBlockPosition());
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else return InteractionResult.PASS;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }

    @Override
    public boolean canBeControlledByRider() {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.MAX_HEALTH, 30.0D);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return size.height * 0.45f;
    }


    @Override
    public double getPassengersRidingOffset() {
        if (this.getHideTimerDuration() > 1) {
            return super.getPassengersRidingOffset() * 0.75D;
        }
        else return super.getPassengersRidingOffset();
    }

    public <E extends IAnimatable> PlayState setAnimation(AnimationEvent<E> event) {
        boolean onGround = isOnGround();

        if (!((double) animationSpeed < 0.1D) && getHideTimerDuration() == 0 && onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tortoise.walking", true));
            return PlayState.CONTINUE;
        } else if (getHideTimerDuration() > 1) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tortoise.hide", false).addAnimation("animation.tortoise.hiding", true));
            return PlayState.CONTINUE;
        } else if (getHideTimerDuration() == 1) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tortoise.reveal", false));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Level level = this.getLevel();
        if (this.getHideTimerDuration() > 1) {
            if (source.isProjectile()) {
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
    protected SoundEvent getHurtSound(DamageSource source) {
        if (this.getHideTimerDuration() > 200) {
            return SMSounds.TORTOISE_HURT.get();
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
        return SMSounds.TORTOISE_DEATH.get();
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::setAnimation));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public int getHideTimerDuration() {
        return this.entityData.get(HIDE_TIMER);
    }

    public void setHideTimerDuration(int durationInTicks) {
        Level level = this.getLevel();

        if (this.getHideTimerDuration() < durationInTicks || durationInTicks == this.getHideTimerDuration() - 1) {
            if (this.getHideTimerDuration() == 0) {
                level.playSound(null, this.blockPosition(), SMSounds.TORTOISE_HIDE.get(), SoundSource.AMBIENT, 1.0F, 1.0F);
            }
            this.entityData.set(HIDE_TIMER, durationInTicks);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HIDE_TIMER, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("HideTimer", getHideTimerDuration());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setHideTimerDuration(nbt.getInt("HideTimer"));
    }

    public static class RiderAllowingRandomStrollGoal extends RandomStrollGoal {
        public RiderAllowingRandomStrollGoal(PathfinderMob pMob, double pSpeedModifier) {
            super(pMob, pSpeedModifier);
        }

        @Override
        public boolean canUse() {
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
            return !this.mob.getNavigation().isDone();
        }
    }
}
