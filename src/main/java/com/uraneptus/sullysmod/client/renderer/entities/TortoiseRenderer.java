package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.client.model.TortoiseModel;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TortoiseRenderer <E extends Tortoise> extends GeoEntityRenderer<E> {
    public TortoiseRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TortoiseModel<>());
        this.shadowRadius = 0.75F;
    }

    //Reduced motionThreshold to detect entities that are moving slowly e.g. Tortoise
    @Override
    public float getMotionAnimThreshold(E animatable) {
        return 0.005F;
    }

    @Override
    public void preRender(PoseStack poseStack, E animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

        if (animatable.isBaby()) {
            poseStack.scale(0.15F, 0.15F, 0.15F);
            this.shadowRadius *= 0.15F;
        }
    }
}
