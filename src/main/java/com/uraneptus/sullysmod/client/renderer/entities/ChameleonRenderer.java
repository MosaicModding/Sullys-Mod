package com.uraneptus.sullysmod.client.renderer.entities;

import com.uraneptus.sullysmod.client.model.ChameleonModel;
import com.uraneptus.sullysmod.common.entities.chameleon.Chameleon;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ChameleonRenderer <E extends Chameleon> extends GeoEntityRenderer<E> {
    public ChameleonRenderer(EntityRendererProvider.Context context) {
        super(context, new ChameleonModel<>());
    }

    @Override
    public float getMotionAnimThreshold(E animatable) {
        return 0.00001F;
    }
}
