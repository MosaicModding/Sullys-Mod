package com.mosaicmodding.sullysmod.client.rendering.entity;

import com.mosaicmodding.sullysmod.client.model.BoulderingZombieModel;
import com.mosaicmodding.sullysmod.content.undead_variants.BoulderingZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

import static com.mosaicmodding.sullysmod.registry.SMRegUtils.path;

public class BoulderingZombieRenderer<E extends BoulderingZombieEntity> extends HumanoidMobRenderer<E, BoulderingZombieModel<E>> {
    private static final ResourceLocation TEXTURE = path("textures/entity/bouldering_zombie/bouldering_zombie.png");

    public BoulderingZombieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BoulderingZombieModel<>(pContext.bakeLayer(BoulderingZombieModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new BoulderingZombieModel<>(pContext.bakeLayer(BoulderingZombieModel.INNER_ARMOR)), new BoulderingZombieModel<>(pContext.bakeLayer(BoulderingZombieModel.OUTER_ARMOR)), pContext.getModelManager()));
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(E pEntity) {
        return super.isShaking(pEntity) || pEntity.isUnderWaterConverting();
    }
}
