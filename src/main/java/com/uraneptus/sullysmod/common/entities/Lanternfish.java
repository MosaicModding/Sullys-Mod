package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.common.entities.goals.LightAvoidingRandomSwimmingGoal;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
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
        this.goalSelector.addGoal(3, new LightAvoidingRandomSwimmingGoal(this, 1.0D, 3));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_DARK_TICKS_REMAINING, 0);
    }

    public void addAdditionalSaveData(CompoundTag p_147122_) {
        super.addAdditionalSaveData(p_147122_);
        p_147122_.putInt("DarkTicksRemaining", this.getDarkTicksRemaining());
    }

    public void readAdditionalSaveData(CompoundTag p_147117_) {
        super.readAdditionalSaveData(p_147117_);
        this.setDarkTicks(p_147117_.getInt("DarkTicksRemaining"));
    }

    public void aiStep() {
        super.aiStep();
        int i = this.getDarkTicksRemaining();
        if (i > 0) {
            this.setDarkTicks(i - 1);
        }

    }

    public boolean hurt(DamageSource p_147114_, float p_147115_) {
        boolean flag = super.hurt(p_147114_, p_147115_);
        if (flag) {
            this.setDarkTicks(100);
        }

        return flag;
    }

    private void setDarkTicks(int p_147120_) {
        this.entityData.set(DATA_DARK_TICKS_REMAINING, p_147120_);
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

    public static boolean checkLanternfishSpawnRules(EntityType<? extends LivingEntity> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return levelAccessor.getBlockState(pos).is(Blocks.WATER) && pos.getY() <= levelAccessor.getSeaLevel() - 47 && !levelAccessor.canSeeSkyFromBelowWater(pos);
    }
}
