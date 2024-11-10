package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.TortoiseModel;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TortoiseRenderer<E extends Tortoise> extends MobRenderer<E, TortoiseModel<E>> {
    private static final ResourceLocation TEXTURE_CRAFTING = SullysMod.modPrefix("textures/entity/tortoise/tortoise_crafting.png");
    private static final ResourceLocation TEXTURE_JUKEBOX = SullysMod.modPrefix("textures/entity/tortoise/tortoise_jukebox.png");

    public TortoiseRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new TortoiseModel<>(pContext.bakeLayer(TortoiseModel.LAYER_LOCATION)), 0.75F);
    }

    @Override
    public void render(E pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

        if (pEntity.isBaby()) {
            pPoseStack.scale(0.15F, 0.15F, 0.15F);
            this.shadowRadius *= 0.15F;
        }
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return pEntity.isCraftingTable() ? TEXTURE_CRAFTING : TEXTURE_JUKEBOX;
    }
}
