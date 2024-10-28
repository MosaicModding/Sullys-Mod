package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.uraneptus.sullysmod.client.model.PiranhaModel;
import com.uraneptus.sullysmod.common.entities.Piranha;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PiranhaRenderer <E extends Piranha> extends GeoEntityRenderer<E> {

    public PiranhaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PiranhaModel<>());
        this.shadowRadius = 0.3F;
    }

    @Override
    protected void applyRotations(E animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!animatable.isInWater() && !animatable.getLeaping()) {
            poseStack.translate(0.1F, 0.1F, -0.1F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }
    }
}
