package com.uraneptus.sullysmod.client.renderer.entities;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.renderer.entities.model.CopperGolemModel;
import com.uraneptus.sullysmod.common.entities.CopperGolemEntity;
import net.java.games.input.Component;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class CopperGolemRenderer extends GeoEntityRenderer<CopperGolemEntity> {
    //private static final ResourceLocation TEXTURE = new ResourceLocation(SullysMod.MOD_ID, "textures/entity/copper_golem/copper_golem_0.png");

    public CopperGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new CopperGolemModel());
        this.shadowRadius = 0.5F;
    }

    /*@Override
    public ResourceLocation getTextureLocation(CopperGolemEntity pEntity) {
        return TEXTURE;
    }*/
}
