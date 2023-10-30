package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.JungleSpiderModel;
import com.uraneptus.sullysmod.client.renderer.entities.layer.JungleSpiderBeneficialPatternLayer;
import com.uraneptus.sullysmod.client.renderer.entities.layer.JungleSpiderEyesLayer;
import com.uraneptus.sullysmod.client.renderer.entities.layer.JungleSpiderHarmfulPatternLayer;
import com.uraneptus.sullysmod.common.entities.JungleSpider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class JungleSpiderRenderer<T extends JungleSpider> extends MobRenderer<T, JungleSpiderModel<T>> {
    private static final ResourceLocation SPIDER_LOCATION = SullysMod.modPrefix("textures/entity/jungle_spider/jungle_spider.png");

    public JungleSpiderRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new JungleSpiderModel<>(pContext.bakeLayer(JungleSpiderModel.LAYER_LOCATION)), 0.65F);
        this.addLayer(new JungleSpiderEyesLayer<>(this));
        this.addLayer(new JungleSpiderBeneficialPatternLayer<>(this));
        this.addLayer(new JungleSpiderHarmfulPatternLayer<>(this));
    }

    @Override
    protected void scale(@NotNull JungleSpider pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(0.85F, 0.85F, 0.85F);
    }


    @Override
    protected float getFlipDegrees(@NotNull T pLivingEntity) {
        return 180.0F;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T pEntity) {
        return SPIDER_LOCATION;
    }
}
