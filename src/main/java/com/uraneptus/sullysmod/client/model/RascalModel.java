package com.uraneptus.sullysmod.client.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.CopperGolem;
import com.uraneptus.sullysmod.common.entities.Rascal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class RascalModel extends AnimatedGeoModel<Rascal> {

    @Override
    public ResourceLocation getModelResource(Rascal object) {
        return SullysMod.modPrefix("geo/rascal.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Rascal object) {
        return SullysMod.modPrefix("textures/entity/rascal/rascal.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Rascal animatable) {
        return SullysMod.modPrefix("animations/rascal.animation.json");
    }

    @SuppressWarnings({ "unchecked", "unused"})
    @Override
    public void setLivingAnimations(Rascal entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        LivingEntity entityIn = entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
