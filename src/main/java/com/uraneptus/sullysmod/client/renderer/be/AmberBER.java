package com.uraneptus.sullysmod.client.renderer.be;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.uraneptus.sullysmod.client.renderer.entities.layer.StuckInAmberLayer;
import com.uraneptus.sullysmod.common.blockentities.AmberBE;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class AmberBER implements BlockEntityRenderer<AmberBE> {
    private final EntityRenderDispatcher entityRenderer;

    public AmberBER(BlockEntityRendererProvider.Context pContext) {
        this.entityRenderer = pContext.getEntityRenderer();
    }

    @Override
    public void render(AmberBE amberBlockEntity, float pPartialTick, @NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

        Level level = amberBlockEntity.getLevel();
        BlockPos pos = amberBlockEntity.getBlockPos();

        pPoseStack.pushPose();
        if (level != null) {
            CompoundTag compoundtag = amberBlockEntity.getEntityStuck();
            if (!compoundtag.isEmpty()) {
                AmberBE.removeIgnoredNBT(compoundtag);
                Entity renderEntity = EntityType.loadEntityRecursive(compoundtag, level, Function.identity());

                if (renderEntity instanceof LivingEntity livingEntity) {
                    if (entityRenderer.getRenderer(livingEntity) instanceof LivingEntityRenderer<? extends LivingEntity, ?> livingEntityRenderer) {
                        pPoseStack.translate(0.5F, 0.0F, 0.5F);
                        pPoseStack.mulPose(Axis.YP.rotationDegrees(-livingEntity.getRotationVector().y));

                        livingEntityRenderer.addLayer(new StuckInAmberLayer(livingEntityRenderer));
                        entityRenderer.getRenderer(livingEntity).render(livingEntity, 0.0F, 0, pPoseStack, pBuffer, pPackedLight);
                    }







                    //USE THIS IF NO LAYER
                    //this.entityRenderer.setRenderShadow(false);
                    //this.entityRenderer.render(renderEntity, 0.0,  0.0,  0.0, 0.0F, 0, pPoseStack, pBuffer, pPackedLight);

                }
            }
        }
        pPoseStack.popPose();
    }
}
