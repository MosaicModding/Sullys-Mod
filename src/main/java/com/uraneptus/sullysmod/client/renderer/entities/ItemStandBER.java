package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.ItemStandBlock;
import com.uraneptus.sullysmod.common.blocks.ItemStandBlockEntity;
import com.uraneptus.sullysmod.common.items.ArtifactHelmetItem;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

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
        ItemStack displayItem = pBlockEntity.getDisplayItem();
        if (displayItem.isEmpty()) return;

        Component component = Component.translatable(pBlockEntity.getDisplayItem().getHoverName().getString());
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.56F, 0.5F);
        this.renderNameTag(pBlockEntity, component, pPoseStack, pBuffer, pPackedLight);

        float yRot = pBlockEntity.getBlockState().getValue(ItemStandBlock.FACING).getClockWise().toYRot();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(-yRot));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(90F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(90F));

        if (displayItem.getItem() instanceof ArmorItem armorItem) {
            pPoseStack.pushPose();
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            EquipmentSlot slot = armorItem.getEquipmentSlot();
            HumanoidModel<?> humanoidModel = new HumanoidArmorModel<>(mc.getEntityModels().bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR));
            this.setPartVisibility(humanoidModel, slot);
            Model model = IClientItemExtensions.of(displayItem).getGenericArmorModel(player, displayItem, slot, humanoidModel);

            pPoseStack.mulPose(Axis.YP.rotationDegrees(-180));
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-90));
            pPoseStack.translate(0.0, -0.5, 0.0);
            if (armorItem instanceof DyeableLeatherItem dyeableLeatherItem) {
                int i = dyeableLeatherItem.getColor(displayItem);
                float f = (float)(i >> 16 & 255) / 255.0F;
                float f1 = (float)(i >> 8 & 255) / 255.0F;
                float f2 = (float)(i & 255) / 255.0F;
                this.renderModel(armorItem, pPoseStack, pBuffer, pPackedLight, model, f, f1, f2, "1");
                this.renderModel(armorItem, pPoseStack, pBuffer, pPackedLight, model, 1.0F, 1.0F, 1.0F, "1_overlay");
            }
            this.renderModel(armorItem, pPoseStack, pBuffer, pPackedLight, model, 1.0F, 1.0F, 1.0F, "1");
            pPoseStack.popPose();
        } else {
            pPoseStack.translate(0.0F, -0.130F, 0.0F);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-55));
            pPoseStack.mulPose(Axis.YP.rotationDegrees(-180));
            this.itemRenderer.renderStatic(pBlockEntity.getDisplayItem(), ItemDisplayContext.GROUND, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), pBlockEntity.saveWithId().getId());
        }
        pPoseStack.popPose();
    }

    private void setPartVisibility(HumanoidModel<?> pModel, EquipmentSlot pSlot) {
        pModel.setAllVisible(false);
        switch (pSlot) {
            case HEAD:
                pModel.head.visible = true;
                pModel.hat.visible = true;
                break;
            case CHEST:
                pModel.body.visible = true;
                pModel.rightArm.visible = true;
                pModel.leftArm.visible = true;
                break;
            case LEGS:
                pModel.body.visible = true;
                pModel.rightLeg.visible = true;
                pModel.leftLeg.visible = true;
                break;
            case FEET:
                pModel.rightLeg.visible = true;
                pModel.leftLeg.visible = true;
        }
    }

    private void renderModel(ArmorItem armorItem, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, Model pModel, float pRed, float pGreen, float pBlue, String suffix) {
        String name = ForgeRegistries.ITEMS.getKey(armorItem).getPath();
        String namespace = ForgeRegistries.ITEMS.getKey(armorItem).getNamespace();
        ResourceLocation armorLocation = "minecraft".equals(namespace) ? new ResourceLocation("textures/models/armor/" + armorItem.getMaterial().getName() + "_layer_" + suffix + ".png") : SullysMod.modPrefix("textures/models/armor/" + name + "_layer_" + suffix + ".png");
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.armorCutoutNoCull(armorLocation));
        pModel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, pRed, pGreen, pBlue, 1.0F);
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
