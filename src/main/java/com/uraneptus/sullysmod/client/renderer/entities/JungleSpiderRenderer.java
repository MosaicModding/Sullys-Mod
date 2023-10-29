package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.JungleSpiderModel;
import com.uraneptus.sullysmod.client.renderer.entities.layer.JungleSpiderEyes;
import com.uraneptus.sullysmod.common.entities.JungleSpider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JungleSpiderRenderer<T extends JungleSpider> extends MobRenderer<T, JungleSpiderModel<T>> {
    private static final ResourceLocation SPIDER_LOCATION = SullysMod.modPrefix("textures/entity/jungle_spider/jungle_spider.png");

    public JungleSpiderRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new JungleSpiderModel<>(pContext.bakeLayer(JungleSpiderModel.LAYER_LOCATION)), 0.65F);
        this.addLayer(new JungleSpiderEyes<>(this));
    }

    @Override
    protected void scale(JungleSpider pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(0.85F, 0.85F, 0.85F);
    }


    @Override
    protected float getFlipDegrees(T pLivingEntity) {
        return 180.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return SPIDER_LOCATION;
    }
}
