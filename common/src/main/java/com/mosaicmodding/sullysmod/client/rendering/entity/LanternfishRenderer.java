package com.mosaicmodding.sullysmod.client.rendering.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mosaicmodding.sullysmod.client.model.LanternfishModel;
import com.mosaicmodding.sullysmod.client.rendering.entity.layer.LanternfishGlowLayer;
import com.mosaicmodding.sullysmod.content.lanternfish.LanternfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import static com.mosaicmodding.sullysmod.registry.SMRegUtils.path;

public class LanternfishRenderer <E extends LanternfishEntity> extends MobRenderer<E, LanternfishModel<E>> {
    public static final ResourceLocation TEXTURE = path("textures/entity/lanternfish/lanternfish.png");

    public LanternfishRenderer(EntityRendererProvider.Context context) {
        super(context, new LanternfishModel<>(context.bakeLayer(LanternfishModel.LAYER_LOCATION)), 0.3F);
        this.addLayer(new LanternfishGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return TEXTURE;
    }

    protected void setupRotations(E pEntityLiving, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        super.setupRotations(pEntityLiving, poseStack, bob, yBodyRot, partialTick, scale);
        float f = 4.3F * Mth.sin(0.6F * partialTick);
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!pEntityLiving.isInWater()) {
            poseStack.translate(0.1F, 0.1F, -0.1F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }

    }
}
