package com.uraneptus.sullysmod.client.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.CopperGolem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CopperGolemModel <T extends CopperGolem> extends AnimatedGeoModel<T> {

    @Override
    public ResourceLocation getModelResource(T entity) {
        return SullysMod.modPrefix("geo/copper_golem.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T entity) {
        int id = entity.getEntityData().get(CopperGolem.OXIDIZATION);
        return SullysMod.modPrefix("textures/entity/copper_golem/copper_golem_" + id + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T entity) {
        return SullysMod.modPrefix("animations/copper_golem.animation.json");
    }

    @SuppressWarnings({ "unchecked", "unused"})
    @Override
    public void setCustomAnimations(T animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}