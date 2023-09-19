package com.uraneptus.sullysmod.client.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.CopperGolem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class CopperGolemModel <T extends CopperGolem> extends DefaultedEntityGeoModel<T> {

    public CopperGolemModel() {
        super(SullysMod.modPrefix("copper_golem"), true);
    }

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
}