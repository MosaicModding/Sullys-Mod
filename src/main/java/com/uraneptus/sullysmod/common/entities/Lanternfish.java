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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

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
        super.registerGoals();
        this.goalSelector.addGoal(3, new Lanternfish.LightAvoidingRandomSwimmingGoal(this, 1.0D, 3));
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

    static class LightAvoidingRandomSwimmingGoal extends RandomSwimmingGoal {
        private final Lanternfish lanternfish;
        protected final float probability;

        public LightAvoidingRandomSwimmingGoal(Lanternfish lanternfish, double speedModifier, int probability) {
            super(lanternfish, speedModifier, probability);
            this.lanternfish = lanternfish;
            this.probability = probability;
        }

        /**
         *  I think this works, couldn't test it 100% tho!
         **/
        @javax.annotation.Nullable
        protected Vec3 getPosition() {
            Vec3 vec3 = this.lanternfish.getViewVector(0.0F);
            if (this.lanternfish.getLevel().getBrightness(LightLayer.BLOCK, lanternfish.blockPosition()) > 0) {
                Vec3 vec31 = AirAndWaterRandomPos.getPos(this.lanternfish, 16, 16, (int) vec3.reverse().y, vec3.reverse().x, vec3.reverse().z, (float)Math.PI / 2F);
                return vec31 == null ? super.getPosition() : vec31;
            } else {
                return this.lanternfish.getRandom().nextFloat() >= this.probability ? AirAndWaterRandomPos.getPos(this.lanternfish, 16, 16, (int) vec3.reverse().y, vec3.x, vec3.z, (float)Math.PI / 2F) : super.getPosition();
            }
        }
    }
}
