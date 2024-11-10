package com.uraneptus.sullysmod.client.renderer.entities;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.BoulderingZombieModel;
import com.uraneptus.sullysmod.common.entities.BoulderingZombie;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class BoulderingZombieRenderer<E extends BoulderingZombie> extends HumanoidMobRenderer<E, BoulderingZombieModel<E>> {
    private static final ResourceLocation TEXTURE = SullysMod.modPrefix("textures/entity/bouldering_zombie/bouldering_zombie.png");

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
