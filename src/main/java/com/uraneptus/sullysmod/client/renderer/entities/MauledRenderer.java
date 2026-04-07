package com.uraneptus.sullysmod.client.renderer.entities;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.MauledModel;
import com.uraneptus.sullysmod.common.entities.Mauled;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class MauledRenderer<E extends Mauled> extends HumanoidMobRenderer<E, MauledModel<E>> {
    private static final ResourceLocation TEXTURE = SullysMod.modPrefix("textures/entity/mauled/mauled_skin.png");
    private static final ResourceLocation TEXTURE_SKINLESS = SullysMod.modPrefix("textures/entity/mauled/mauled_skinless.png");

    public MauledRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MauledModel<>(pContext.bakeLayer(MauledModel.MAIN_LAYER)), 0.5F);
        //TODO armor currently looks a bit shitty. I should create a modified humanoidArmorLayer
        this.addLayer(new HumanoidArmorLayer<>(this, new MauledModel<>(pContext.bakeLayer(MauledModel.INNER_ARMOR)), new MauledModel<>(pContext.bakeLayer(MauledModel.OUTER_ARMOR)), pContext.getModelManager()));
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(E pEntity) {
        return pEntity.isShaking();
    }
}
