package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.uraneptus.sullysmod.common.blocks.ItemStandBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import org.joml.Matrix4f;

public class ItemStandBER implements BlockEntityRenderer<ItemStandBlockEntity> {
    private final ItemRenderer itemRenderer;
    private final EntityRenderDispatcher entityRenderer;
    private final Font font;

    public ItemStandBER(BlockEntityRendererProvider.Context pContext) {
        this.itemRenderer = pContext.getItemRenderer();
        this.entityRenderer = pContext.getEntityRenderer();
        this.font = pContext.getFont();
    }

    @Override
    public void render(ItemStandBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        if (!pBlockEntity.getDisplayItem().isEmpty()) {
            Component component = Component.translatable(pBlockEntity.getDisplayItem().getDescriptionId()).withStyle(Style.EMPTY);
            pPoseStack.pushPose();
            //This code is for item models only
            pPoseStack.translate(0.5F, 0.56F, 0.5F);
            this.renderNameTag(pBlockEntity, component, pPoseStack, pBuffer, pPackedLight);
            float f = pBlockEntity.getBlockState().getValue(LecternBlock.FACING).getClockWise().toYRot();
            pPoseStack.mulPose(Axis.YP.rotationDegrees(-f));
            pPoseStack.mulPose(Axis.XP.rotationDegrees(90F));
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(90F));
            pPoseStack.translate(0.0F, -0.130F, 0.0F);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-55));
            pPoseStack.mulPose(Axis.YP.rotationDegrees(-180));
            pPoseStack.scale(1.3F, 1.3F, 1.3F);
            //
            this.itemRenderer.renderStatic(pBlockEntity.getDisplayItem(), ItemDisplayContext.GROUND, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), pBlockEntity.saveWithId().getId());
            pPoseStack.popPose();
        }
    }

    protected void renderNameTag(ItemStandBlockEntity pBlockEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
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
                float f1 = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);

                int j = (int)(f1 * 255.0F) << 24;
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
