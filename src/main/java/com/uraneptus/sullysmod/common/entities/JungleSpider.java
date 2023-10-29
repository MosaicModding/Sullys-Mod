package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.other.tags.SMMobEffectTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class JungleSpider extends Spider {
    private final List<MobEffect> BENEFICIAL_VENOM_EFFECTS = new ArrayList<>();
    private final List<MobEffect> HARMFUL_VENOM_EFFECTS = new ArrayList<>();

    private MobEffect beneficialEffect;
    private MobEffect harmfulEffect;


    public JungleSpider(EntityType<? extends JungleSpider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.createEffectLists();
        this.beneficialEffect = this.chooseBeneficialEffect();
        this.harmfulEffect = this.chooseHarmfulEffect();
    }

    protected void registerGoals() {
        super.registerGoals();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void tick() {
        super.tick();
        if (this.harmfulEffect == MobEffects.BLINDNESS && this.beneficialEffect == MobEffects.NIGHT_VISION) {
            this.harmfulEffect = chooseHarmfulEffect();
        }
        if (this.harmfulEffect == MobEffects.DIG_SLOWDOWN && this.beneficialEffect == MobEffects.DIG_SPEED) {
            this.beneficialEffect = chooseBeneficialEffect();
        }
        if (this.harmfulEffect == MobEffects.MOVEMENT_SLOWDOWN && this.beneficialEffect == MobEffects.MOVEMENT_SPEED) {
            this.beneficialEffect = chooseBeneficialEffect();
        }
        if(this.harmfulEffect == MobEffects.UNLUCK && this.beneficialEffect == MobEffects.LUCK) {
            this.harmfulEffect = chooseHarmfulEffect();
        }
        if (this.harmfulEffect == null) {
            this.harmfulEffect = chooseHarmfulEffect();
        }
        if (this.beneficialEffect == null) {
            this.beneficialEffect = chooseBeneficialEffect();
        }

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 18.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F);
    }

    @Override
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
                ((LivingEntity)pEntity).addEffect(new MobEffectInstance(this.beneficialEffect, i * 20, 0), this);
            }

            return true;
        } else {
            return false;
        }
    }

    public void createEffectLists() {
        ForgeRegistries.MOB_EFFECTS.iterator().forEachRemaining(mobEffect -> {

            if ((mobEffect.getCategory() == MobEffectCategory.BENEFICIAL || mobEffect.getCategory() == MobEffectCategory.NEUTRAL) && ForgeRegistries.MOB_EFFECTS.getHolder(ForgeRegistries.MOB_EFFECTS.getKey(mobEffect)).orElseThrow().is(SMMobEffectTags.JUNGLE_SPIDER_BENEFICIAL_OR_NEUTRAL_VENOM_EFFECTS)) {
                BENEFICIAL_VENOM_EFFECTS.add(mobEffect);
            }
            if (mobEffect.getCategory() == MobEffectCategory.HARMFUL && ForgeRegistries.MOB_EFFECTS.getHolder(ForgeRegistries.MOB_EFFECTS.getKey(mobEffect)).orElseThrow().is(SMMobEffectTags.JUNGLE_SPIDER_HARMFUL_VENOM_EFFECTS)) {
                HARMFUL_VENOM_EFFECTS.add(mobEffect);
            }
        });
    }

    private MobEffect chooseBeneficialEffect() {
        return BENEFICIAL_VENOM_EFFECTS.get(random.nextInt(BENEFICIAL_VENOM_EFFECTS.size()));
    }

    private MobEffect chooseHarmfulEffect() {
        return HARMFUL_VENOM_EFFECTS.get(random.nextInt(HARMFUL_VENOM_EFFECTS.size()));
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

        this.harmfulEffect = this.chooseHarmfulEffect();
        this.beneficialEffect = this.chooseBeneficialEffect();

        return pSpawnData;
    }

    @Override
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 0.45F;
    }
}
