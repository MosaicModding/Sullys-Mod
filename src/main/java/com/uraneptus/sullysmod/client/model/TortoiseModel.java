package com.uraneptus.sullysmod.client.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class TortoiseModel <T extends Tortoise> extends DefaultedEntityGeoModel<T> {
    public TortoiseModel() {
        super(SullysMod.modPrefix("tortoise"), true);
    }

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

    @Override
    public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        CoreGeoBone craftingSaddle = getAnimationProcessor().getBone("CraftingSaddle");

        if (!animatable.hasCraftingTable) {
            craftingSaddle.setHidden(true);
        } else {
            craftingSaddle.setHidden(false);
        }
    }
}
