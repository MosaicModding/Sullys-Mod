package com.uraneptus.sullysmod.common.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
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

import java.util.Random;

public class BoulderingZombie extends Zombie implements GeoEntity {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(BoulderingZombie.class, EntityDataSerializers.BYTE);
    private static final RawAnimation CLIMBING_ANIM = RawAnimation.begin().thenLoop("animation.bouldering_zombie.climb");
    private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);

    public BoulderingZombie(EntityType<? extends BoulderingZombie> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes().add(Attributes.MAX_HEALTH, 23.0D).add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return this.getTarget() == null ? new GroundPathNavigation(this, pLevel) : new WallClimberNavigation(this, pLevel);
    }

    public static boolean checkBoulderingZombieSpawnRules(EntityType<? extends BoulderingZombie> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        System.out.println(isInDeepslateLayer(pos, random));
        return isInDeepslateLayer(pos, random) && Monster.checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
    }
    public static boolean isInDeepslateLayer(BlockPos pos, RandomSource random) {
        int chance = random.nextInt(100);
        double y = pos.getY();

        return (y <= -4 && y >= -15 && chance < 50) || (y <= -16 && y >= -30 && chance < 68) || (y <= -31 && y >= -39 && chance < 80) || (y <= -40 && y >= -63 && chance < 90);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            this.setClimbing(this.horizontalCollision);
        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.is(DamageTypeTags.IS_FIRE)) {
            pAmount *= 1.5F;
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    public boolean onClimbable() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0 && this.getTarget() != null;
    }

    public void setClimbing(boolean pClimbing) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (pClimbing) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    @Override
    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "BoulderingZombieAnimations", 0, this::setAnimation));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.instanceCache;
    }

    public <E extends GeoAnimatable> PlayState setAnimation(final AnimationState<E> event) {
        if (this.onClimbable()) {
            return event.setAndContinue(CLIMBING_ANIM);
        }
        return PlayState.STOP;
    }
}
