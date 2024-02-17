package com.uraneptus.sullysmod.client.renderer.entities.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.Chameleon;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class ChameleonColorLayer extends GeoRenderLayer<Chameleon> {
    private static final ResourceLocation TEXTURE = SullysMod.modPrefix("textures/entity/chameleon/chameleon_tinted.png");
    public ChameleonColorLayer(GeoRenderer<Chameleon> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, Chameleon animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType tintRenderType = RenderType.entityTranslucent(TEXTURE);
        Color current = Color.ofOpaque(animatable.getCurrentColor());
        Color target = Color.ofOpaque(animatable.getTargetColor());

        if (!current.equals(target)) {
            current = Color.ofRGB(Mth.lerp(partialTick / 30F, current.getRedFloat(), target.getRedFloat()), Mth.lerp(partialTick / 30F, current.getGreenFloat(), target.getGreenFloat()), Mth.lerp(partialTick / 30F, current.getBlueFloat(), target.getBlueFloat()));
            animatable.setCurrentColor(current.hashCode());
        }

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, tintRenderType, bufferSource.getBuffer(tintRenderType), partialTick, packedLight, LivingEntityRenderer.getOverlayCoords(animatable, 0.0F), current.brighter(1.2F).getRedFloat(), current.brighter(1.2F).getGreenFloat(), current.brighter(1.2F).getBlueFloat(), 1F);
    }
}
