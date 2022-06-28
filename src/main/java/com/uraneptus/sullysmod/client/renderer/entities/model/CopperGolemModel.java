package com.uraneptus.sullysmod.client.renderer.entities.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.CopperGolem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CopperGolemModel extends AnimatedGeoModel<CopperGolem> {

    @Override
    public ResourceLocation getModelResource(CopperGolem entity) {
        return SullysMod.modPrefix("geo/copper_golem.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CopperGolem entity) {
        int id = entity.getEntityData().get(CopperGolem.OXIDIZATION);
        return SullysMod.modPrefix("textures/entity/copper_golem/copper_golem_" + id + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(CopperGolem entity) {
        return SullysMod.modPrefix("animations/copper_golem.animation.json");
    }

    @SuppressWarnings({ "unchecked", "unused"})
    @Override
    public void setLivingAnimations(CopperGolem entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        LivingEntity entityIn = entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}