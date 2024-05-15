package com.uraneptus.sullysmod.client.renderer.entities.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.caps.SMEntityCap;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;

public class StuckInAmberLayer <T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public StuckInAmberLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        SMEntityCap.getCapOptional(pLivingEntity).ifPresent(cap -> {
            pPoseStack.pushPose();
            if (cap.stuckInAmber) {
                VertexConsumer consumer = pBuffer.getBuffer(RenderType.entityTranslucent(SullysMod.modPrefix("textures/misc/amber_layer.png")));
                this.getParentModel().renderToBuffer(pPoseStack, consumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.65F);
            }
            pPoseStack.popPose();
        });
    }
}
