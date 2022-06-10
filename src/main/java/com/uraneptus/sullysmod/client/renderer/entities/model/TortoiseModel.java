package com.uraneptus.sullysmod.client.renderer.entities.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.TortoiseEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class TortoiseModel extends AnimatedGeoModel<TortoiseEntity> {
    @Override
    public ResourceLocation getModelLocation(TortoiseEntity object) {
        return new ResourceLocation(SullysMod.MOD_ID, "geo/tortoise.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TortoiseEntity object) {
        return new ResourceLocation(SullysMod.MOD_ID, "textures/entity/tortoise/tortoise.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TortoiseEntity animatable) {
        return new ResourceLocation(SullysMod.MOD_ID, "animations/tortoise.animation.json");
    }


    @SuppressWarnings({ "unchecked", "unused", "rawtypes" })
    @Override
    public void setLivingAnimations(TortoiseEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        LivingEntity entityIn = entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
