package com.mosaicmodding.sullysmod.client.registry;

import com.mosaicmodding.sullysmod.client.rendering.entity.layer.StuckInAmberLayer;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public class SMEntityLayers implements ClientHelper.EntityLayerEvent {

    @Override
    public void onRendererCreated(EntityType<? extends LivingEntity> type, LivingEntityRenderer<?, ?> renderer, LayerAdder adder, EntityRendererProvider.Context context) {
        if (!type.equals(EntityType.ENDER_DRAGON)) {
            adder.add(new StuckInAmberLayer<>(renderer));
        }
    }
}
