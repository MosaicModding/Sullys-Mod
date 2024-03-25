package com.uraneptus.sullysmod.client.renderer.be;

import com.mojang.blaze3d.vertex.PoseStack;
import com.uraneptus.sullysmod.common.blockentities.AmberBE;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

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
        if (amberBlockEntity.hasStuckEntity()) {
            CompoundTag compoundtag = amberBlockEntity.getEntityStuck();
            AmberBE.removeIgnoredNBT(compoundtag);
            assert level != null;
            LivingEntity livingEntity = (LivingEntity) EntityType.loadEntityRecursive(compoundtag, level, entity -> entity);
            assert livingEntity != null;
            this.entityRenderer.render(livingEntity, pos.getX(), pos.getY(), pos.getZ(), 0.0F, pPartialTick, pPoseStack, pBuffer, pPackedLight);
        }
    }
}
