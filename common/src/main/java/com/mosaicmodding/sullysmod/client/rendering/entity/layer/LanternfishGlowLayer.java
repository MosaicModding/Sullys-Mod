package com.mosaicmodding.sullysmod.client.rendering.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mosaicmodding.sullysmod.client.model.LanternfishModel;
import com.mosaicmodding.sullysmod.content.lanternfish.LanternfishEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import static com.mosaicmodding.sullysmod.registry.SMRegUtils.path;

public class LanternfishGlowLayer <E extends LanternfishEntity> extends RenderLayer<E, LanternfishModel<E>> {
    public static final ResourceLocation GLOW_LAYER = path("textures/entity/lanternfish/glow.png");

    public LanternfishGlowLayer(RenderLayerParent<E, LanternfishModel<E>> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, E pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (pLivingEntity.renderGlow()) {
            RenderType renderType = RenderType.eyes(GLOW_LAYER);
            VertexConsumer vertexconsumer = pBuffer.getBuffer(renderType);
            this.getParentModel().renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFF);
        }
    }
}
