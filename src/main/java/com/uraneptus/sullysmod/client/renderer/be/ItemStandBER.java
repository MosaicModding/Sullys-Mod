package com.uraneptus.sullysmod.client.renderer.be;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.uraneptus.sullysmod.common.blockentities.ItemStandBE;
import com.uraneptus.sullysmod.common.blocks.AncientSkullBlock;
import com.uraneptus.sullysmod.common.blocks.ItemStandBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class ItemStandBER implements BlockEntityRenderer<ItemStandBE> {
    private final ItemRenderer itemRenderer;
    private final EntityRenderDispatcher entityRenderer;
    private final Font font;

    public ItemStandBER(BlockEntityRendererProvider.Context pContext) {
        this.itemRenderer = pContext.getItemRenderer();
        this.entityRenderer = pContext.getEntityRenderer();
        this.font = pContext.getFont();
    }

    @Override
    public void render(ItemStandBE pBlockEntity, float pPartialTick, @NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemStack displayItem = pBlockEntity.getDisplayItem();
        if (displayItem.isEmpty()) return;

        Component component = Component.translatable(displayItem.getHoverName().getString()).withStyle(displayItem.getRarity().getStyleModifier());
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.56F, 0.5F);
        this.renderNameTag(pBlockEntity, component, pPoseStack, pBuffer, pPackedLight);

        float yRot = pBlockEntity.getBlockState().getValue(ItemStandBlock.FACING).getClockWise().toYRot();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(-yRot));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(90F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(90F));

        int xRotDegrees = -55;
        pPoseStack.translate(0.0F, -0.130F, 0.0F);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(xRotDegrees));
        if (displayItem.getItem() instanceof BlockItem blockItem) {
            boolean flatTexture = !itemRenderer.getItemModelShaper().getItemModel(blockItem).isGui3d();
            if (flatTexture || blockItem.getBlock() instanceof AncientSkullBlock) {
                pPoseStack.mulPose(Axis.YP.rotationDegrees(-180));
            }
        } else {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(-180));
        }
        this.itemRenderer.renderStatic(displayItem, ItemDisplayContext.GROUND, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), pBlockEntity.saveWithId().getId());

        pPoseStack.popPose();
    }

    protected void renderNameTag(ItemStandBE pBlockEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        BlockGetter getter = pBlockEntity.getLevel();

        if (player != null && getter != null) {
            BlockHitResult result = getLookedAtBlock(player, getter, pBlockEntity.getBlockPos(), pBlockEntity.getBlockState());
            if (result != null) {
                pPoseStack.pushPose();
                pPoseStack.translate(0.0F, 0.9F, 0.0F);
                pPoseStack.mulPose(this.entityRenderer.cameraOrientation());
                pPoseStack.scale(-0.025F, -0.025F, 0.025F);
                Matrix4f matrix4f = pPoseStack.last().pose();
                float f2 = (float)(-font.width(pDisplayName) / 2);
                font.drawInBatch(pDisplayName, f2, 0, 16763455, false, matrix4f, pBuffer, Font.DisplayMode.NORMAL, 0, pPackedLight);
                pPoseStack.popPose();
            }
        }
    }

    private static BlockHitResult getLookedAtBlock(Player player, BlockGetter getter, BlockPos pos, BlockState state) {
        double distance = 7;
        Vec3 viewVector = player.getViewVector(100);
        Vec3 playerLookingPos = player.getEyePosition();
        Vec3 lookingAtVector = playerLookingPos.add(viewVector.x() * distance, viewVector.y() * distance, viewVector.z() * distance);
        return getter.clipWithInteractionOverride(playerLookingPos, lookingAtVector, pos, state.getShape(getter, pos), state);
    }
}
