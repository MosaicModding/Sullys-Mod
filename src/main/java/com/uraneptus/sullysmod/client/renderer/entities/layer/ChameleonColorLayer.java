package com.uraneptus.sullysmod.client.renderer.entities.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.Chameleon;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class ChameleonColorLayer extends GeoRenderLayer<Chameleon> {
    private static final ResourceLocation TEXTURE = SullysMod.modPrefix("textures/entity/chameleon/chameleon_tinted.png");
    public ChameleonColorLayer(GeoRenderer<Chameleon> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, Chameleon animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType tintRenderType = RenderType.entityCutout(TEXTURE);

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, tintRenderType, bufferSource.getBuffer(tintRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        
    }
}
