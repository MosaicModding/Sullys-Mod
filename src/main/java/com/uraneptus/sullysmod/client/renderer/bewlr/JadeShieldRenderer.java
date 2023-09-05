package com.uraneptus.sullysmod.client.renderer.bewlr;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.JadeShieldModel;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class JadeShieldRenderer extends BlockEntityWithoutLevelRenderer {
    public static final Material JADE_SHIELD_TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, SullysMod.modPrefix("entity/jade_shield/jade_shield"));
    public final JadeShieldModel model;

    public JadeShieldRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
        this.model = new JadeShieldModel(pEntityModelSet.bakeLayer(JadeShieldModel.LAYER_LOCATION));
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemDisplayContext pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        Item item = pStack.getItem();

        if (item == SMItems.JADE_SHIELD.get()) {
            pPoseStack.pushPose();
            pPoseStack.scale(1.0F, -1.0F, -1.0F);
            Material material = JADE_SHIELD_TEXTURE;
            VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(material.atlasLocation()), true, pStack.hasFoil()));

            this.model.handle().render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            this.model.plate().render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

            pPoseStack.popPose();
        }
    }
}
