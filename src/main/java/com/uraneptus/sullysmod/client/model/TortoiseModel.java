package com.uraneptus.sullysmod.client.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TortoiseModel <T extends Tortoise> extends GeoModel<T> {
    @Override
    public ResourceLocation getModelResource(T object) {
        return SullysMod.modPrefix("geo/tortoise.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return SullysMod.modPrefix("textures/entity/tortoise/tortoise.png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return SullysMod.modPrefix("animations/tortoise.animation.json");
    }
}
