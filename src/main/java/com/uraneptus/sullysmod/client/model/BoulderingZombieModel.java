package com.uraneptus.sullysmod.client.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.BoulderingZombie;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

//Somehow add vanilla idle & walking animations that zombies use
public class BoulderingZombieModel <E extends BoulderingZombie> extends DefaultedEntityGeoModel<E> {

    public BoulderingZombieModel() {
        super(SullysMod.modPrefix("bouldering_zombie"), true);
    }

    @Override
    public ResourceLocation getModelResource(E animatable) {
        return SullysMod.modPrefix("geo/bouldering_zombie.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(E animatable) {
        return SullysMod.modPrefix("textures/entity/bouldering_zombie/bouldering_zombie.png");
    }

    @Override
    public ResourceLocation getAnimationResource(E animatable) {
        return SullysMod.modPrefix("animations/bouldering_zombie.animation.json");
    }

    //All of this is just copied and modified from the zombie model & renderer
    @Override
    public void setCustomAnimations(E animatable, long instanceId, AnimationState<E> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        boolean flag = animatable.getFallFlyingTicks() > 4;
        float pLimbSwing = animationState.getLimbSwing();
        float pLimbSwingAmount = animationState.getLimbSwingAmount();
        double tick = animationState.getAnimationTick();

        GeoBone body = (GeoBone) this.getAnimationProcessor().getBone("Body");
        GeoBone rightLeg = (GeoBone) this.getAnimationProcessor().getBone("RightLeg");
        GeoBone leftLeg = (GeoBone) this.getAnimationProcessor().getBone("LeftLeg");
        GeoBone rightArm = (GeoBone) this.getAnimationProcessor().getBone("RightArm");
        GeoBone leftArm = (GeoBone) this.getAnimationProcessor().getBone("LeftArm");

        if (!animatable.onClimbable()) {
            /*
            body.setRotY(0.0F);
            rightArm.setPosZ(0.0F);
            rightArm.setPosX(0.0F);
            leftArm.setPosZ(0.0F);
            leftArm.setPosX(0.0F);
            */
            float f = 1.0F;
            if (flag) {
                f = (float)animatable.getDeltaMovement().lengthSqr();
                f /= 0.2F;
                f *= f * f;
            }

            if (f < 1.0F) {
                f = 1.0F;
            }

            rightArm.setRotX(Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 2.0F * pLimbSwingAmount * 0.5F / f);
            leftArm.setRotX(Mth.cos(pLimbSwing * 0.6662F) * 2.0F * pLimbSwingAmount * 0.5F / f);
            rightArm.setRotZ(0.0F);
            leftArm.setRotZ(0.0F);
            rightLeg.setRotX(Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount / f);
            leftLeg.setRotX(Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount / f);
            rightLeg.setRotY(0.005F);
            leftLeg.setRotY(-0.005F);
            rightLeg.setRotZ(0.005F);
            leftLeg.setRotZ(-0.005F);
            if (animatable.isPassenger()) {
                rightArm.setRotX(rightArm.getRotX() + (-(float)Math.PI / 5F));
                leftArm.setRotX(leftArm.getRotX() + (-(float)Math.PI / 5F));
                rightLeg.setRotX(-1.4137167F);
                rightLeg.setRotY(((float)Math.PI / 10F));
                rightLeg.setRotZ(0.07853982F);
                leftLeg.setRotX(-1.4137167F);
                leftLeg.setRotY((-(float)Math.PI / 10F));
                leftLeg.setRotZ(-0.07853982F);
            }


            animateZombieArms(leftArm, rightArm, animatable.isAggressive(), animatable.attackAnim, (float) tick);
            //this.setupAttackAnimation(pEntity, pAgeInTicks);
        }
    }

    public static void animateZombieArms(GeoBone pLeftArm, GeoBone pRightArm, boolean pIsAggressive, float pAttackTime, float pAgeInTicks) {
        float f = Mth.sin(pAttackTime * (float)Math.PI);
        float f1 = Mth.sin((1.0F - (1.0F - pAttackTime) * (1.0F - pAttackTime)) * (float)Math.PI);
        pRightArm.setRotZ(0.0F);
        pLeftArm.setRotZ(0.0F);
        pRightArm.setRotY(-(-0.1F - f * 0.6F));
        pLeftArm.setRotY(-0.1F - f * 0.6F);
        float f2 = (float)Math.PI / (pIsAggressive ? 1.5F : 2.25F);
        pRightArm.setRotX(f2);
        pLeftArm.setRotX(f2);
        pRightArm.setRotX(pRightArm.getRotX() + f * 1.2F - f1 * 0.4F);
        pLeftArm.setRotX(pLeftArm.getRotX() + f * 1.2F - f1 * 0.4F);
        bobArms(pRightArm, pLeftArm, pAgeInTicks);
    }

    public static void bobModelPart(GeoBone geoBone, float pAgeInTicks, float pMultiplier) {
        geoBone.setRotZ(geoBone.getRotZ() + pMultiplier * (Mth.cos(pAgeInTicks * 0.09F) * 0.05F + 0.05F));
        geoBone.setRotX(geoBone.getRotX() + pMultiplier * Mth.sin(pAgeInTicks * 0.067F) * 0.05F);
    }

    public static void bobArms(GeoBone pRightArm, GeoBone pLeftArm, float pAgeInTicks) {
        bobModelPart(pRightArm, pAgeInTicks, 1.0F);
        bobModelPart(pLeftArm, pAgeInTicks, -1.0F);
    }
}
