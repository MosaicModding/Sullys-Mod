package com.uraneptus.sullysmod.client.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.chameleon.Chameleon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class ChameleonModel<E extends Chameleon> extends DefaultedEntityGeoModel<E> {
    public ChameleonModel() {
        super(SullysMod.modPrefix("chameleon"), true);
    }

    @Override
    public ResourceLocation getModelResource(E animatable) {
        return SullysMod.modPrefix("geo/chameleon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(E animatable) {
        return SullysMod.modPrefix("textures/entity/chameleon/chameleon.png");
    }

    @Override
    public ResourceLocation getAnimationResource(E animatable) {
        return SullysMod.modPrefix("animations/chameleon.animation.json");
    }
}
