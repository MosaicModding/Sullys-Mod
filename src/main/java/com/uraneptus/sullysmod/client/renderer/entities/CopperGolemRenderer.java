package com.uraneptus.sullysmod.client.renderer.entities;

import com.uraneptus.sullysmod.client.model.CopperGolemModel;
import com.uraneptus.sullysmod.common.entities.CopperGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CopperGolemRenderer <E extends CopperGolem> extends GeoEntityRenderer<E> {
    //private static final ResourceLocation TEXTURE = new ResourceLocation(SullysMod.MOD_ID, "textures/entity/copper_golem/copper_golem_0.png");

    public CopperGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new CopperGolemModel<>());
        this.shadowRadius = 0.5F;
    }
}
