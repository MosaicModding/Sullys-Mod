package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.JungleSpiderModel;
import com.uraneptus.sullysmod.client.model.TortoiseShellModel;
import com.uraneptus.sullysmod.common.entities.JungleSpider;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JungleSpiderRenderer<T extends JungleSpider> extends MobRenderer<T, JungleSpiderModel<T>> {
    private static final ResourceLocation SPIDER_LOCATION = SullysMod.modPrefix("textures/entity/jungle_spider/jungle_spider.png");


    public JungleSpiderRenderer(EntityRendererProvider.Context p_174401_) {
        this(p_174401_, ModelLayers.SPIDER);
    }

    public JungleSpiderRenderer(EntityRendererProvider.Context pContext, ModelLayerLocation pLayer) {
        super(pContext, new JungleSpiderModel<>(pContext.bakeLayer(JungleSpiderModel.LAYER_LOCATION)), 0.65F);
        //this.addLayer(new SpiderEyesLayer<>(this));
    }

    @Override
    protected void scale(JungleSpider pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(0.85F, 0.85F, 0.85F);
    }

    protected float getFlipDegrees(T pLivingEntity) {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(T pEntity) {
        return SPIDER_LOCATION;
    }
}
