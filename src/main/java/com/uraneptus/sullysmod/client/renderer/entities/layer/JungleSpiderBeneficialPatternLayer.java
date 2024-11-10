package com.uraneptus.sullysmod.client.renderer.entities.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.JungleSpiderModel;
import com.uraneptus.sullysmod.common.entities.JungleSpider;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class JungleSpiderBeneficialPatternLayer<T extends JungleSpider, M extends JungleSpiderModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation BENEFICIAL_LAYER = SullysMod.modPrefix("textures/entity/jungle_spider/beneficial.png");

    public JungleSpiderBeneficialPatternLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, @NotNull T jungleSpider, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        MobEffect mobEffect = jungleSpider.getBeneficialVenomEffect();
        Color color = new Color(0xFF000000 | mobEffect.getColor()).brighter();
        VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.entityTranslucent(BENEFICIAL_LAYER));
        this.getParentModel().renderToBuffer(pPoseStack,vertexConsumer, pPackedLight, LivingEntityRenderer.getOverlayCoords(jungleSpider, 0.0F), (float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255, 1.0F);
    }
}
