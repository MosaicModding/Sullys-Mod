package com.uraneptus.sullysmod.client.renderer.entities;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.DodoModel;
import com.uraneptus.sullysmod.common.entities.Dodo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DodoRenderer extends MobRenderer<Dodo, DodoModel<Dodo>> {
    public static final ResourceLocation TEXTURE = SullysMod.modPrefix("textures/entity/dodo/dodo.png");


    public DodoRenderer(EntityRendererProvider.Context context) {
        super(context, new DodoModel<>(context.bakeLayer(DodoModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Dodo pEntity) {
        return TEXTURE;
    }

}
