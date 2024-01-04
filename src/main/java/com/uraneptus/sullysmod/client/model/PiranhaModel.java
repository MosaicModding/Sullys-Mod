package com.uraneptus.sullysmod.client.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.Piranha;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class PiranhaModel <T extends Piranha> extends DefaultedEntityGeoModel<T> {
    public PiranhaModel() {
        super(SullysMod.modPrefix("piranha"), true);
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return SullysMod.modPrefix("geo/piranha.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return SullysMod.modPrefix("textures/entity/piranha/piranha.png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return SullysMod.modPrefix("animations/piranha.animation.json");
    }
}
