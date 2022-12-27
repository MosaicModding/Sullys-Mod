package com.uraneptus.sullysmod.client.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.Chameleon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ChameleonModel <T extends Chameleon> extends AnimatedGeoModel<T> {
    @Override
    public ResourceLocation getModelResource(Chameleon object) {
        return SullysMod.modPrefix("geo/chameleon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Chameleon object) {
        return SullysMod.modPrefix("textures/entity/chameleon/chameleon.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Chameleon animatable) {
        return SullysMod.modPrefix("animations/chameleon.animation.json");
    }

    @SuppressWarnings({ "unchecked", "unused"})
    @Override
    public void setCustomAnimations(T entity, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(entity, instanceId, animationEvent);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
