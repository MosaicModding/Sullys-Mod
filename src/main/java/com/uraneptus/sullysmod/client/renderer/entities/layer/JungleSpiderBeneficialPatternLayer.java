package com.uraneptus.sullysmod.client.renderer.entities.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.JungleSpiderModel;
import com.uraneptus.sullysmod.common.entities.JungleSpider;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.object.Color;

public class JungleSpiderBeneficialPatternLayer<T extends JungleSpider, M extends JungleSpiderModel<T>> extends RenderLayer<T, M> {
    public JungleSpiderBeneficialPatternLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }
    @Override
    public void render(@NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, @NotNull T jungleSpider, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        MobEffect mobEffect = jungleSpider.getBeneficialVenomEffect();
        Color effectColor = Color.ofOpaque(mobEffect.getColor()).brighter(1.3F);
        renderColoredCutoutModel(this.getParentModel(), SullysMod.modPrefix("textures/entity/jungle_spider/beneficial.png"), pPoseStack, pBuffer, pPackedLight, jungleSpider, effectColor.getRedFloat(), effectColor.getGreenFloat(), effectColor.getBlueFloat());
    }
}
