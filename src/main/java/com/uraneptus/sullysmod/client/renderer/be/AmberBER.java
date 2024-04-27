package com.uraneptus.sullysmod.client.renderer.be;

import com.mojang.blaze3d.vertex.PoseStack;
import com.uraneptus.sullysmod.common.blockentities.AmberBE;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
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

        if (level != null) {
            CompoundTag compoundtag = amberBlockEntity.getEntityStuck();
            if (!compoundtag.isEmpty()) {
                AmberBE.removeIgnoredNBT(compoundtag);
                Entity renderEntity = EntityType.loadEntityRecursive(compoundtag, level, Function.identity());
                System.out.println(renderEntity);
                if (renderEntity != null) {
                    pPoseStack.pushPose();
                    this.entityRenderer.render(renderEntity, renderEntity.getX(), renderEntity.getY(), renderEntity.getZ(), 0.0F, pPartialTick, pPoseStack, pBuffer, pPackedLight);
                    //System.out.println(" amber if entity renderer?");
                    pPoseStack.popPose();
                }
            }
        }
    }
}
