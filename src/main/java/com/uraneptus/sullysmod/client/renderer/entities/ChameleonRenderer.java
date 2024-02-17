package com.uraneptus.sullysmod.client.renderer.entities;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.ChameleonModel;
import com.uraneptus.sullysmod.client.renderer.entities.layer.ChameleonColorLayer;
import com.uraneptus.sullysmod.common.entities.Chameleon;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.earlydisplay.ColourScheme;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ChameleonRenderer extends GeoEntityRenderer<Chameleon> {
    public ChameleonRenderer(EntityRendererProvider.Context context) {
        super(context, new ChameleonModel<>());
        addRenderLayer(new ChameleonColorLayer(this));
    }

    @Override
    public float getMotionAnimThreshold(Chameleon animatable) {
        return 0.00001F;
    }
}
