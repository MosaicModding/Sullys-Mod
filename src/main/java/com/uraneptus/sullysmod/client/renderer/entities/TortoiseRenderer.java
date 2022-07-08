package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.client.model.TortoiseModel;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TortoiseRenderer extends GeoEntityRenderer<Tortoise> {
    public TortoiseRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TortoiseModel());
        this.shadowRadius = 0.75f;
    }

    @Override
    public void renderEarly(Tortoise animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);

        if (animatable.isBaby()) {
            stackIn.scale(0.15F, 0.15F, 0.15F);
            this.shadowRadius *= 0.15F;
        }
    }
}
