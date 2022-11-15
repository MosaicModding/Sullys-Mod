package com.uraneptus.sullysmod.client.renderer.entities;

import com.uraneptus.sullysmod.client.model.RascalModel;
import com.uraneptus.sullysmod.common.entities.Rascal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RascalRenderer <E extends Rascal> extends GeoEntityRenderer<E> {

    public RascalRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RascalModel<>());
        this.shadowRadius = 0.5F;
    }

}
