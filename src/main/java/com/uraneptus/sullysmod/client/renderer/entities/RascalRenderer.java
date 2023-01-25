package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.client.model.RascalModel;
import com.uraneptus.sullysmod.common.entities.Rascal;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class RascalRenderer <E extends Rascal> extends GeoEntityRenderer<E> {

    public RascalRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RascalModel<>());
        this.shadowRadius = 0.5F;
    }

    //TODO Fix Water Render Bug
    @Override
    public Color getRenderColor(E animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight) {
        Color color = super.getRenderColor(animatable, partialTick, poseStack, bufferSource, buffer, packedLight);
        return animatable.getLookedAtCooldown() != 0 ? Color.ofRGBA(1, 1, 1, Mth.lerp(partialTick, animatable.getOldAlphaTick(), animatable.getAlphaTick()) / 80F) : color;
    }

    @Override
    public RenderType getRenderType(E animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }
}