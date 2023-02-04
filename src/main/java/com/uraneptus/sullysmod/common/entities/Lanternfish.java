package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

@SuppressWarnings({"deprecation", "unused"})
public class Lanternfish extends AbstractFish {
    private static final EntityDataAccessor<Integer> DATA_DARK_TICKS_REMAINING = SynchedEntityData.defineId(Lanternfish.class, EntityDataSerializers.INT);


    public Lanternfish(EntityType<? extends Lanternfish> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new Lanternfish.LightAvoidingRandomSwimmingGoal(this));
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.0D, 40));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_DARK_TICKS_REMAINING, 0);
    }

    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("DarkTicksRemaining", this.getDarkTicksRemaining());
    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setDarkTicks(nbt.getInt("DarkTicksRemaining"));
    }

    public void aiStep() {
        super.aiStep();
        int i = this.getDarkTicksRemaining();
        if (i > 0) {
            this.setDarkTicks(i - 1);
        }

    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        boolean flag = super.hurt(pSource, pAmount);
        if (flag) {
            this.setDarkTicks(100);
        }

        return flag;
    }

    private void setDarkTicks(int ticksRemaining) {
        this.entityData.set(DATA_DARK_TICKS_REMAINING, ticksRemaining);
    }

    public int getDarkTicksRemaining() {
        return this.entityData.get(DATA_DARK_TICKS_REMAINING);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SMSounds.LANTERNFISH_FLOP.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SMSounds.LANTERNFISH_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SMSounds.LANTERNFISH_DEATH.get();
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(SMItems.LANTERNFISH_BUCKET.get());
    }

    public static boolean checkLanternfishSpawnRules(EntityType<? extends LivingEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        int seaLevel = level.getSeaLevel();
        int maxLanternfishSeaLevel = seaLevel - 47;
        return pos.getY() <= maxLanternfishSeaLevel && level.getFluidState(pos.below()).is(FluidTags.WATER) && level.getFluidState(pos.above()).is(FluidTags.WATER) && level.getRawBrightness(pos, 0) == 0;
    }

    static class LightAvoidingRandomSwimmingGoal extends Goal {
        private final Lanternfish lanternfish;
        private double wantedX;
        private double wantedY;
        private double wantedZ;

        public LightAvoidingRandomSwimmingGoal(Lanternfish lanternfish) {
            this.lanternfish = lanternfish;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            if (this.lanternfish.getTarget() != null) {
                return false;
            } else if (this.lanternfish.getLevel().getMaxLocalRawBrightness(this.lanternfish.blockPosition()) <= 0) {
                return false;
            } else {
                return this.setWantedPos();
            }
        }

        protected boolean setWantedPos() {
            Vec3 vec3 = this.getPosition();
            if (vec3 == null) {
                return false;
            } else {
                this.wantedX = vec3.x;
                this.wantedY = vec3.y;
                this.wantedZ = vec3.z;
                return true;
            }
        }

        public boolean canContinueToUse() {
            return !this.lanternfish.getNavigation().isDone();
        }

        public void start() {
            this.lanternfish.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, 1.0D);
        }

        @javax.annotation.Nullable
        protected Vec3 getPosition() {
            RandomSource randomsource = this.lanternfish.getRandom();
            BlockPos blockpos = this.lanternfish.blockPosition();

            for(int i = 0; i < 10; ++i) {
                BlockPos blockpos1 = blockpos.offset(randomsource.nextInt(20) - 10, randomsource.nextInt(6) - 3, randomsource.nextInt(20) - 10);
                if ((this.lanternfish.getLevel().getMaxLocalRawBrightness(blockpos1) <= 0)) {
                    return Vec3.atBottomCenterOf(blockpos1);
                }
            }
            return null;
        }
    }
}
