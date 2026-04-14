package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import java.util.UUID;

public class Mauled extends AbstractSkeleton {
    private static final UUID SPEED_SKINLESS_MODIFIER_UUID = UUID.fromString("25c241f4-b183-4134-b44b-5cc1203d8889");
    private static final EntityDataAccessor<Boolean> SKINLESS = SynchedEntityData.defineId(Mauled.class, EntityDataSerializers.BOOLEAN); //TODO this isn't properly safed

    public Mauled(EntityType<? extends Mauled> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractSkeleton.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.15).add(Attributes.MAX_HEALTH, 26);
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = this.getTarget();
        if (target == null) return;
        var distanceToTarget = this.distanceToSqr(target);
        ItemStack itemstack = this.getItemInHand(InteractionHand.MAIN_HAND);
        double requiredDistance = this.isSkinless() ? 50 : 20;
        if (distanceToTarget < requiredDistance) {
            if (itemstack.is(Items.STONE_SWORD)) return;
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
        } else {
            if (itemstack.is(Items.BOW)) return;
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        }
    }

    private void removeSkinlessSpeedBoost() {
        AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attributeinstance != null) {
            if (attributeinstance.getModifier(SPEED_SKINLESS_MODIFIER_UUID) != null) {
                attributeinstance.removeModifier(SPEED_SKINLESS_MODIFIER_UUID);
            }
        }
    }

    protected void addSkinlessSpeedBoost() {
        AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attributeinstance == null || attributeinstance.getModifier(SPEED_SKINLESS_MODIFIER_UUID) != null) {
            return;
        }
        attributeinstance.addTransientModifier(new AttributeModifier(SPEED_SKINLESS_MODIFIER_UUID, "Mauled Skinless Speed Boost", 0.1, AttributeModifier.Operation.ADDITION));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SKINLESS, false);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        boolean isHurt = super.hurt(pSource, pAmount);
        if (!this.isSkinless() && isHurt && (this.isDeadOrDying() || this.getHealth() <= this.getMaxHealth() / 2)) {
            turnSkinless();
            return true;
        }
        return isHurt;
    }

    public void turnSkinless() {
        if (!(this.level() instanceof ServerLevel level)) return;

        RandomSource random = level.getRandom();
        if (random.nextFloat() < 0.5F) {
            this.spawnAtLocation(Items.ROTTEN_FLESH);
        }
        level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.ROTTEN_FLESH)), this.getX(), this.getY(), this.getZ(), 8, 0, 0.8, 0 , 0.1D);
        //TODO add sound as well
        this.setSkinless(true);
        addSkinlessSpeedBoost();
    }

    public static boolean checkMauledSpawnRules(EntityType<? extends Mauled> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return pos.getY() < 0 && Monster.checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
    }

    public boolean isSkinless() {
        return this.entityData.get(SKINLESS);
    }

    public void setSkinless(boolean skinless) {
        this.entityData.set(SKINLESS, skinless);
    }

    protected SoundEvent getAmbientSound() {
        return SMSounds.MAULED_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SMSounds.MAULED_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return SMSounds.MAULED_DEATH.get();
    }

    @Override
    protected SoundEvent getStepSound() {
        return SMSounds.MAULED_STEP.get();
    }
}
