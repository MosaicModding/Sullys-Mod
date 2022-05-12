package com.uraneptus.sullysmod.client.renderer.entities;

import com.uraneptus.sullysmod.client.renderer.entities.model.TortoiseModel;
import com.uraneptus.sullysmod.common.entities.TortoiseEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TortoiseRenderer extends GeoEntityRenderer<TortoiseEntity> {
    public TortoiseRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TortoiseModel());
        this.shadowRadius = 0.75f;
    }
}
