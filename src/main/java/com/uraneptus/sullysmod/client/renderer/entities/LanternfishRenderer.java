package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.LanternfishModel;
import com.uraneptus.sullysmod.client.renderer.entities.layer.LanternfishGlowLayer;
import com.uraneptus.sullysmod.common.entities.Lanternfish;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class LanternfishRenderer <E extends Lanternfish> extends MobRenderer<E, LanternfishModel<E>> {
    public static final ResourceLocation TEXTURE = SullysMod.modPrefix("textures/entity/lanternfish/lanternfish.png");

    public LanternfishRenderer(EntityRendererProvider.Context context) {
        super(context, new LanternfishModel<>(context.bakeLayer(LanternfishModel.LAYER_LOCATION)), 0.3F);
        this.addLayer(new LanternfishGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return TEXTURE;
    }

    protected void setupRotations(E pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float f = 4.3F * Mth.sin(0.6F * pAgeInTicks);
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!pEntityLiving.isInWater()) {
            pMatrixStack.translate(0.1F, 0.1F, -0.1F);
            pMatrixStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }

    }
}
