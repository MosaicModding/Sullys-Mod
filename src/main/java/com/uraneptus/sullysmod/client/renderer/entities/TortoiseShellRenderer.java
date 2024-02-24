package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.TortoiseShellModel;
import com.uraneptus.sullysmod.common.entities.TortoiseShell;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

public class TortoiseShellRenderer <E extends TortoiseShell> extends EntityRenderer<E> {
    public static final ResourceLocation TEXTURE_CRAFTING = SullysMod.modPrefix("textures/entity/tortoise/tortoise_crafting.png");
    public static final ResourceLocation TEXTURE_JUKEBOX = SullysMod.modPrefix("textures/entity/tortoise/tortoise_jukebox.png");
    protected final TortoiseShellModel<E> model;

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

        float spinTime = pEntity.tickCount + pPartialTicks;
        if (pEntity.getSpinTicksEntityData() > 0) {
            pMatrixStack.mulPose(new Quaternionf().rotateY(spinTime * 0.56F));
        }

        float hurtTime = (float)pEntity.getHurtTime() - pPartialTicks;
        float damage = pEntity.getDamage() - pPartialTicks;
        if (damage < 0.0F) {
            damage = 0.0F;
        }
        if (hurtTime > 0.0F) {
            pMatrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.sin(hurtTime) * hurtTime * damage / 96.0F * (float)pEntity.getHurtDir()));
        }

        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity)));
        this.model.root().getChild("workstationSaddle").visible = pEntity.hasAppliedWorkstation();
        this.model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pMatrixStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return pEntity.isCraftingTable() ? TEXTURE_CRAFTING : TEXTURE_JUKEBOX;
    }
}
