package com.uraneptus.sullysmod.client.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class TortoiseModel extends AnimatedGeoModel<Tortoise> {
    @Override
    public ResourceLocation getModelResource(Tortoise object) {
        return SullysMod.modPrefix("geo/tortoise.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Tortoise object) {
        return SullysMod.modPrefix("textures/entity/tortoise/tortoise.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Tortoise animatable) {
        return SullysMod.modPrefix("animations/tortoise.animation.json");
    }


    @SuppressWarnings({ "unchecked", "unused", "rawtypes" })
    @Override
    public void setLivingAnimations(Tortoise entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        LivingEntity entityIn = entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
