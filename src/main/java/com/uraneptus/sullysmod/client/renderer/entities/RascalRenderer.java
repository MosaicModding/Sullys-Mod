package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.client.model.RascalModel;
import com.uraneptus.sullysmod.common.entities.Rascal;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RascalRenderer extends GeoEntityRenderer<Rascal> {

    public RascalRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RascalModel());
        this.shadowRadius = 0.5F;
    }

}
