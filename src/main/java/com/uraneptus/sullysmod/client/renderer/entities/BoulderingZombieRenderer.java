package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.client.model.BoulderingZombieModel;
import com.uraneptus.sullysmod.common.entities.BoulderingZombie;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BoulderingZombieRenderer <E extends BoulderingZombie> extends GeoEntityRenderer<E> {

    public BoulderingZombieRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BoulderingZombieModel<>());
        this.shadowRadius = 0.5F;
    }

    @Override
    public void preRender(PoseStack poseStack, E animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.scale(1.0625F, 1.0625F, 1.0625F);
    }
}
