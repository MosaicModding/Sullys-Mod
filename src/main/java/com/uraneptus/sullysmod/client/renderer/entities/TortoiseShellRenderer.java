package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.TortoiseShellModel;
import com.uraneptus.sullysmod.common.entities.TortoiseShell;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class TortoiseShellRenderer <E extends TortoiseShell> extends EntityRenderer<E> {
    public static final ResourceLocation TEXTURE = SullysMod.modPrefix("textures/entity/tortoise/tortoise.png");
    protected final EntityModel<E> model;

    public TortoiseShellRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.75F;
        this.model = new TortoiseShellModel<>(context.bakeLayer(TortoiseShellModel.LAYER_LOCATION));
    }

    @Override
    public void render(E pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
        pMatrixStack.pushPose();
        pMatrixStack.translate(0.0D, -1.0F, 0.0D);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity)));
        this.model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pMatrixStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return TEXTURE;
    }
}
