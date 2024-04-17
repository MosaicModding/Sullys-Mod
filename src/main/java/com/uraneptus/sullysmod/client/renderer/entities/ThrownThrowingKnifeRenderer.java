package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.uraneptus.sullysmod.common.entities.ThrownThrowingKnife;
import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class ThrownThrowingKnifeRenderer extends EntityRenderer<ThrownThrowingKnife> {
    private final ItemRenderer itemRenderer;

    public ThrownThrowingKnifeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.itemRenderer = pContext.getItemRenderer();
    }

    public void render(ThrownThrowingKnife pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        ItemStack itemstack = pEntity.getPickupItem();
        BakedModel bakedmodel = this.itemRenderer.getModel(itemstack, pEntity.level(), null, pEntity.getId());

        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot()) - 90.0F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot()) - 130.0F));

        if (!pEntity.inGround || (pEntity.lastState != null && pEntity.lastState.is(SMBlockTags.PROJECTILES_BOUNCE_ON))) {
            float time = pEntity.tickCount + pPartialTicks;
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(time * 50F));
        }

        this.itemRenderer.render(itemstack, ItemDisplayContext.FIXED, false, pPoseStack, pBuffer, pPackedLight, OverlayTexture.NO_OVERLAY, bakedmodel);

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownThrowingKnife pEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
