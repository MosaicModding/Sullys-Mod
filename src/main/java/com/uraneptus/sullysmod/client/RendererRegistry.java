package com.uraneptus.sullysmod.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.renderer.entities.CopperGolemRenderer;
import com.uraneptus.sullysmod.client.renderer.entities.model.CopperGolemModel;
import com.uraneptus.sullysmod.core.registry.SMEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RendererRegistry {

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SMEntityType.COPPER_GOLEM.get(), CopperGolemRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerLocation(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CopperGolemModel.LAYER_LOCATION, CopperGolemModel::createBodyLayer);
    }
}
