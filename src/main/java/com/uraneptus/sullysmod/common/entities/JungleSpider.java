package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.other.tags.SMMobEffectTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class JungleSpider extends Monster {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(JungleSpider.class, EntityDataSerializers.BYTE);
    private final List<MobEffect> BENEFICIAL_MOB_EFFECTS = new ArrayList<>();
    private final List<MobEffect> HARMFUL_MOB_EFFECTS = new ArrayList<>();

    private MobEffect beneficialEffect;
    private MobEffect harmfulEffect;

    private static final float SPIDER_SPECIAL_EFFECT_CHANCE = 0.1F;

    public JungleSpider(EntityType<? extends JungleSpider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.createEffectLists();
        this.beneficialEffect = this.chooseBeneficialEffect();
        this.harmfulEffect = this.chooseHarmfulEffect();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new JungleSpider.JungleSpiderAttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new JungleSpider.JungleSpiderTargetGoal<>(this, Player.class));
        this.targetSelector.addGoal(3, new JungleSpider.JungleSpiderTargetGoal<>(this, IronGolem.class));
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getPassengersRidingOffset() {
        return (double)(this.getBbHeight() * 0.5F);
    }

    protected PathNavigation createNavigation(Level pLevel) {
        return new WallClimberNavigation(this, pLevel);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            this.setClimbing(this.horizontalCollision);
        }

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SPIDER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SPIDER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SPIDER_DEATH;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    /**
     * Returns {@code true} if this entity should move as if it were on a ladder (either because it's actually on a
     * ladder, or for AI reasons)
     */
    public boolean onClimbable() {
        return this.isClimbing();
    }

    public void makeStuckInBlock(BlockState pState, Vec3 pMotionMultiplier) {
        if (!pState.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(pState, pMotionMultiplier);
        }

    }

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    public boolean canBeAffected(MobEffectInstance pPotioneffect) {
        if (pPotioneffect.getEffect() == MobEffects.POISON) {
            net.minecraftforge.event.entity.living.MobEffectEvent.Applicable event = new net.minecraftforge.event.entity.living.MobEffectEvent.Applicable(this, pPotioneffect);
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
            return event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW;
        }
        return super.canBeAffected(pPotioneffect);
    }

    /**
     * Returns {@code true} if the WatchableObject (Byte) is 0x01 otherwise returns {@code false}. The WatchableObject is
     * updated using setBesideClimbableBlock.
     */
    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    /**
     * Updates the WatchableObject (Byte) created in entityInit(), setting it to 0x01 if par1 is true or 0x00 if it is
     * false.
     */
    public void setClimbing(boolean pClimbing) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (pClimbing) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    public boolean doHurtTarget(Entity pEntity) {
        if (super.doHurtTarget(pEntity)) {
            if (pEntity instanceof LivingEntity) {
                int i = 0;
                if (this.level().getDifficulty() == Difficulty.NORMAL) {
                    i = 5;
                } else if (this.level().getDifficulty() == Difficulty.HARD) {
                    i = 8;
                }
                ((LivingEntity)pEntity).addEffect(new MobEffectInstance(this.harmfulEffect, i * 20, 0), this);
            }

            return true;
        } else {
            return false;
        }
    }

    public void createEffectLists() {
        ForgeRegistries.MOB_EFFECTS.iterator().forEachRemaining(mobEffect -> {

            if (mobEffect.getCategory() == MobEffectCategory.BENEFICIAL && ForgeRegistries.MOB_EFFECTS.getHolder(ForgeRegistries.MOB_EFFECTS.getKey(mobEffect)).orElseThrow().is(SMMobEffectTags.JUNGLE_SPIDER_BENEFICIAL_EFFECTS)) {
                BENEFICIAL_MOB_EFFECTS.add(mobEffect);
            }
            if (mobEffect.getCategory() == MobEffectCategory.HARMFUL && ForgeRegistries.MOB_EFFECTS.getHolder(ForgeRegistries.MOB_EFFECTS.getKey(mobEffect)).orElseThrow().is(SMMobEffectTags.JUNGLE_SPIDER_HARMFUL_EFFECTS)) {
                HARMFUL_MOB_EFFECTS.add(mobEffect);
            }
        });
    }

    private MobEffect chooseBeneficialEffect() {
        return BENEFICIAL_MOB_EFFECTS.get(random.nextInt(BENEFICIAL_MOB_EFFECTS.size()));
    }

    private MobEffect chooseHarmfulEffect() {
        return HARMFUL_MOB_EFFECTS.get(this.level().random.nextInt(HARMFUL_MOB_EFFECTS.size()));
    }
    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("BeneficialEffect", MobEffect.getId(this.beneficialEffect));
        compoundTag.putInt("HarmfulEffect", MobEffect.getId(this.harmfulEffect));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.beneficialEffect = MobEffect.byId(compoundTag.getInt("BeneficialEffect"));
        this.harmfulEffect = MobEffect.byId(compoundTag.getInt("HarmfulEffect"));
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        pSpawnData = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        RandomSource randomsource = pLevel.getRandom();




        //this.entityData.define(NEGATIVE_VENOM_EFFECT_NUMBER, random.nextInt(SullysMod.HARMFUL_MOB_EFFECTS.size()));

        /*switch (chance) {
            case 0:
                this.entityData.define(NEGATIVE_VENOM_EFFECT, "poison");
                break;
            case 1:
                this.entityData.define(NEGATIVE_VENOM_EFFECT, "blindness");
                break;
            case 2:
                this.entityData.define(NEGATIVE_VENOM_EFFECT, "slowness");
                break;
            case 3:
                this.entityData.define(NEGATIVE_VENOM_EFFECT, "hunger");
                break;
            case 4:
                this.entityData.define(NEGATIVE_VENOM_EFFECT, "weakness");
                break;
            case 5:
                this.entityData.define(NEGATIVE_VENOM_EFFECT, "darkness");
                break;
            case 6:
                this.entityData.define(NEGATIVE_VENOM_EFFECT, "nausea");
                break;
        }
        int chance2 = random.nextInt(6);
        switch (chance2) {
            case 0:
                if (!this.entityData.get(NEGATIVE_VENOM_EFFECT).equals("slowness")) {
                    this.entityData.define(POSITIVE_VENOM_EFFECT, "speed");
                }
                this.entityData.define(POSITIVE_VENOM_EFFECT, "water_breathing");
                break;
            case 1:
                this.entityData.define(POSITIVE_VENOM_EFFECT, "jump_boost");
                break;
            case 2:
                this.entityData.define(POSITIVE_VENOM_EFFECT, "glowing");
                break;
            case 3:
                this.entityData.define(POSITIVE_VENOM_EFFECT, "slow_falling");
                break;
            case 4:
                if (!this.entityData.get(NEGATIVE_VENOM_EFFECT).equals("hunger")) {
                    this.entityData.define(POSITIVE_VENOM_EFFECT, "saturation");
                }
                this.entityData.define(POSITIVE_VENOM_EFFECT, "fire_resistance");
                break;
            case 5:
                if (!this.entityData.get(NEGATIVE_VENOM_EFFECT).equals("darkness") && !this.entityData.get(NEGATIVE_VENOM_EFFECT).equals("blindness")) {
                    this.entityData.define(POSITIVE_VENOM_EFFECT, "night_vision");
                }
                this.entityData.define(POSITIVE_VENOM_EFFECT, "luck");
                break;
        }


         */




        if (randomsource.nextInt(100) == 0) {
            Skeleton skeleton = EntityType.SKELETON.create(this.level());
            if (skeleton != null) {
                skeleton.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                skeleton.finalizeSpawn(pLevel, pDifficulty, pReason, (SpawnGroupData)null, (CompoundTag)null);
                skeleton.startRiding(this);
            }
        }

        if (pSpawnData == null) {
            pSpawnData = new net.minecraft.world.entity.monster.Spider.SpiderEffectsGroupData();
            if (pLevel.getDifficulty() == Difficulty.HARD && randomsource.nextFloat() < 0.1F * pDifficulty.getSpecialMultiplier()) {
                ((net.minecraft.world.entity.monster.Spider.SpiderEffectsGroupData)pSpawnData).setRandomEffect(randomsource);
            }
        }

        if (pSpawnData instanceof net.minecraft.world.entity.monster.Spider.SpiderEffectsGroupData spider$spidereffectsgroupdata) {
            MobEffect mobeffect = spider$spidereffectsgroupdata.effect;
            if (mobeffect != null) {
                this.addEffect(new MobEffectInstance(mobeffect, -1));
            }
        }

        return pSpawnData;
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 0.65F;
    }

    static class JungleSpiderAttackGoal extends MeleeAttackGoal {
        public JungleSpiderAttackGoal(JungleSpider jungleSpider) {
            super(jungleSpider, 1.0D, true);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return super.canUse() && !this.mob.isVehicle();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            float f = this.mob.getLightLevelDependentMagicValue();
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                this.mob.setTarget((LivingEntity)null);
                return false;
            } else {
                return super.canContinueToUse();
            }
        }

        protected double getAttackReachSqr(LivingEntity pAttackTarget) {
            return (double)(4.0F + pAttackTarget.getBbWidth());
        }
    }

    public static class SpiderEffectsGroupData implements SpawnGroupData {
        @Nullable
        public MobEffect effect;

        public void setRandomEffect(RandomSource pRandom) {
            int i = pRandom.nextInt(5);
            if (i <= 1) {
                this.effect = MobEffects.MOVEMENT_SPEED;
            } else if (i <= 2) {
                this.effect = MobEffects.DAMAGE_BOOST;
            } else if (i <= 3) {
                this.effect = MobEffects.REGENERATION;
            } else if (i <= 4) {
                this.effect = MobEffects.INVISIBILITY;
            }

        }
    }

    static class JungleSpiderTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public JungleSpiderTargetGoal(JungleSpider pSpider, Class<T> pEntityTypeToTarget) {
            super(pSpider, pEntityTypeToTarget, true);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            float f = this.mob.getLightLevelDependentMagicValue();
            return f >= 0.5F ? false : super.canUse();
        }
    }
}
