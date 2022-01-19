package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.renderer.entities.layer.LanternfishGlowLayer;
import com.uraneptus.sullysmod.client.renderer.entities.model.LanternfishModel;
import com.uraneptus.sullysmod.common.entities.LanternfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class LanternfishRenderer extends MobRenderer<LanternfishEntity, LanternfishModel<LanternfishEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(SullysMod.MOD_ID, "textures/entity/lanternfish/lanternfish.png");

    public LanternfishRenderer(EntityRendererProvider.Context context) {
        super(context, new LanternfishModel<>(context.bakeLayer(LanternfishModel.LAYER_LOCATION)), 0.3F);
        this.addLayer(new LanternfishGlowLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(LanternfishEntity pEntity) {
        return TEXTURE;
    }

    protected void setupRotations(LanternfishEntity pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float f = 4.3F * Mth.sin(0.6F * pAgeInTicks);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!pEntityLiving.isInWater()) {
            pMatrixStack.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }

    }

    /*protected int getBlockLightLevel(LanternfishEntity entity, BlockPos pos) {
        int i = (int)Mth.clampedLerp(0.0F, 15.0F, 1.0F - (float)entity.getDarkTicksRemaining() / 10.0F);
        return i == 15 ? 15 : Math.max(i, super.getBlockLightLevel(entity, pos));
    }*/
}
