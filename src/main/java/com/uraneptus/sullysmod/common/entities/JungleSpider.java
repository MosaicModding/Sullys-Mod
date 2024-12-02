package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.SMFeatures;
import com.uraneptus.sullysmod.core.other.tags.SMMobEffectTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
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
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JungleSpider extends Spider implements IEntityAdditionalSpawnData {
    private static final EntityDataAccessor<String> BENEFICIAL_VENOM_EFFECT = SynchedEntityData.defineId(JungleSpider.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> HARMFUL_VENOM_EFFECT = SynchedEntityData.defineId(JungleSpider.class, EntityDataSerializers.STRING);


    private static final List<MobEffect> BENEFICIAL_VENOM_EFFECTS = new ArrayList<>();
    private static final List<MobEffect> HARMFUL_VENOM_EFFECTS = new ArrayList<>();

    public JungleSpider(EntityType<? extends JungleSpider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void registerGoals() {
        super.registerGoals();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.createEffectLists();
        this.entityData.define(BENEFICIAL_VENOM_EFFECT, Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getKey(this.chooseBeneficialEffect())).toString());
        this.entityData.define(HARMFUL_VENOM_EFFECT, Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getKey(this.chooseHarmfulEffect())).toString());
    }

    public static boolean checkJungleSpiderSpawnRules(EntityType<? extends Monster> pType, ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return Monster.checkMonsterSpawnRules(pType, pLevel, pSpawnType, pPos, pRandom) && SMFeatures.isEnabled(SMFeatures.JUNGLE_SPIDER);
    }

    public MobEffect getBeneficialVenomEffect() {
        return Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(this.entityData.get(BENEFICIAL_VENOM_EFFECT))));
    }

    public MobEffect getHarmfulVenomEffect() {
        return Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(this.entityData.get(HARMFUL_VENOM_EFFECT))));
    }

    public void setBeneficialVenomEffect(MobEffect mobEffect) {
        this.entityData.set(BENEFICIAL_VENOM_EFFECT, Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getKey(mobEffect)).toString());
    }

    public void setHarmfulVenomEffect(MobEffect mobEffect) {
        this.entityData.set(HARMFUL_VENOM_EFFECT, Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getKey(mobEffect)).toString());
    }

    public boolean isEffectExtended(MobEffect mobEffect) {
        return Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.tags()).getTag(SMMobEffectTags.EXTENDED_VENOM_EFFECTS).contains(mobEffect);
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void tick() {
        super.tick();
        if (this.getHarmfulVenomEffect() == MobEffects.BLINDNESS && this.getBeneficialVenomEffect() == MobEffects.NIGHT_VISION) {
            setHarmfulVenomEffect(chooseHarmfulEffect());
        }
        if (this.getHarmfulVenomEffect() == MobEffects.DIG_SLOWDOWN && this.getBeneficialVenomEffect() == MobEffects.DIG_SPEED) {
            setBeneficialVenomEffect(chooseBeneficialEffect());
        }
        if (this.getHarmfulVenomEffect() == MobEffects.MOVEMENT_SLOWDOWN && this.getBeneficialVenomEffect() == MobEffects.MOVEMENT_SPEED) {
            this.setBeneficialVenomEffect(chooseBeneficialEffect());
        }
        if (this.getHarmfulVenomEffect() == null) {
            this.setHarmfulVenomEffect(chooseHarmfulEffect());
        }
        if (this.getBeneficialVenomEffect() == null) {
            this.setBeneficialVenomEffect(chooseHarmfulEffect());
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

                if (this.level().getDifficulty().equals(Difficulty.EASY)) {
                    i = 5;
                } else if (this.level().getDifficulty().equals(Difficulty.NORMAL)) {
                    i = 10;
                } else if (this.level().getDifficulty().equals(Difficulty.HARD)) {
                    i = 15;
                }

                if (i > 0) {
                    ((LivingEntity)pEntity).addEffect(new MobEffectInstance(this.getHarmfulVenomEffect(), isEffectExtended(this.getHarmfulVenomEffect()) ? (i * 20) + 5 : i * 20, 0), this);
                    ((LivingEntity)pEntity).addEffect(new MobEffectInstance(this.getBeneficialVenomEffect(), isEffectExtended(this.getBeneficialVenomEffect()) ? (i * 20) + 5 : i * 20, 0), this);
                }
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
        compoundTag.putString("BeneficialEffect", this.entityData.get(BENEFICIAL_VENOM_EFFECT));
        compoundTag.putString("HarmfulEffect", this.entityData.get(HARMFUL_VENOM_EFFECT));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        MobEffect BENEFICIAL = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(compoundTag.getString("BeneficialEffect")));
        MobEffect HARMFUL = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(compoundTag.getString("HarmfulEffect")));

        this.setBeneficialVenomEffect(BENEFICIAL != null ? BENEFICIAL : chooseBeneficialEffect());
        this.setHarmfulVenomEffect(HARMFUL != null ? HARMFUL : chooseHarmfulEffect());
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getKey(this.getBeneficialVenomEffect())));
        buffer.writeResourceLocation(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getKey(this.getHarmfulVenomEffect())));
    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
        this.setBeneficialVenomEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(additionalData.readResourceLocation())));
        this.setHarmfulVenomEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(additionalData.readResourceLocation())));
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 0.45F;
    }
}
