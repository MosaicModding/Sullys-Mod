package com.mosaicmodding.sullysmod.client.rendering.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mosaicmodding.sullysmod.registry.SMEntityAttachments;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;

import static com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils.path;

public class StuckInAmberLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public StuckInAmberLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        var stuckData = SMEntityAttachments.STUCK_DATA.getOrCreate(pLivingEntity);
        if (!stuckData.isStuck()) return;

        pPoseStack.pushPose();
        VertexConsumer consumer = pBuffer.getBuffer(RenderType.entityTranslucent(path("textures/misc/amber_layer.png")));
        this.getParentModel().renderToBuffer(pPoseStack, consumer, pPackedLight, OverlayTexture.NO_OVERLAY, 0xA6FFFFFF); //ARGB
        pPoseStack.popPose();
    }
}
