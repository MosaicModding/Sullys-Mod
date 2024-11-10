package com.uraneptus.sullysmod.client.renderer.be;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.uraneptus.sullysmod.common.blockentities.AmberBE;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class AmberBER implements BlockEntityRenderer<AmberBE> {
    private final EntityRenderDispatcher renderDispatcher;
    private final ItemRenderer itemRenderer;

    public AmberBER(BlockEntityRendererProvider.Context pContext) {
        this.renderDispatcher = pContext.getEntityRenderer();
        this.itemRenderer = pContext.getItemRenderer();
    }

    @Override
    public void render(AmberBE amberBlockEntity, float pPartialTick, @NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        Level level = amberBlockEntity.getLevel();

        pPoseStack.pushPose();
        if (level != null) {
            if (amberBlockEntity.renderEntity) {
                CompoundTag compoundtag = amberBlockEntity.getEntityStuck();
                if (!compoundtag.isEmpty()) {
                    AmberBE.removeIgnoredNBT(compoundtag);
                    Entity renderEntity = EntityType.loadEntityRecursive(compoundtag, level, Function.identity());

                    if (renderEntity instanceof LivingEntity livingEntity) {
                        pPoseStack.translate(0.5F, 0.0F, 0.5F);
                        pPoseStack.mulPose(Axis.YN.rotationDegrees(livingEntity.getVisualRotationYInDegrees()));
                        this.renderDispatcher.setRenderShadow(false);
                        this.renderDispatcher.render(renderEntity, 0.0, 0.0, 0.0, 0.0F, 0, pPoseStack, pBuffer, pPackedLight);
                    }
                    if (renderEntity instanceof ItemEntity itemEntity) {
                        pPoseStack.translate(0.5F, 0.25F, 0.5F);
                        BakedModel bakedmodel = this.itemRenderer.getModel(itemEntity.getItem(), renderEntity.level(), null, renderEntity.getId());
                        this.itemRenderer.render(itemEntity.getItem(), ItemDisplayContext.GROUND, false, pPoseStack, pBuffer, pPackedLight, OverlayTexture.NO_OVERLAY, bakedmodel);
                    }
                }
            }
        }
        pPoseStack.popPose();
    }
}
