package com.uraneptus.sullysmod.client.renderer.entities.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.blueprint.client.BlueprintRenderTypes;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.renderer.entities.model.LanternfishModel;
import com.uraneptus.sullysmod.common.entities.Lanternfish;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class LanternfishGlowLayer extends RenderLayer<Lanternfish, LanternfishModel<Lanternfish>> {
    public static final ResourceLocation GLOW_LAYER = SullysMod.modPrefix("textures/entity/lanternfish/glow.png");

    public LanternfishGlowLayer(RenderLayerParent<Lanternfish, LanternfishModel<Lanternfish>> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, Lanternfish pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        int i = (int) Mth.clampedLerp(0.0F, 15.0F, 1.0F - (float) pLivingEntity.getDarkTicksRemaining() / 10.0F);
        int packedLight = i == 15 ? 240 : pPackedLight;
        RenderType renderType = i == 15 ? BlueprintRenderTypes.getUnshadedCutoutEntity(GLOW_LAYER, true) : RenderType.entityCutoutNoCull(GLOW_LAYER);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(renderType);
        this.getParentModel().renderToBuffer(pMatrixStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
