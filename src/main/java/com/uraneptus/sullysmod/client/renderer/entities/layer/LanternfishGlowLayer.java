package com.uraneptus.sullysmod.client.renderer.entities.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.LanternfishModel;
import com.uraneptus.sullysmod.common.entities.Lanternfish;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class LanternfishGlowLayer <E extends Lanternfish> extends RenderLayer<E, LanternfishModel<E>> {
    public static final ResourceLocation GLOW_LAYER = SullysMod.modPrefix("textures/entity/lanternfish/glow.png");

    public LanternfishGlowLayer(RenderLayerParent<E, LanternfishModel<E>> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, E pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (pLivingEntity.renderGlow()) {
            RenderType renderType = RenderType.eyes(GLOW_LAYER);
            VertexConsumer vertexconsumer = pBuffer.getBuffer(renderType);
            this.getParentModel().renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
