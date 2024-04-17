package com.uraneptus.sullysmod.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import com.uraneptus.sullysmod.client.model.ancient_skulls.BaseAncientSkullModel;
import com.uraneptus.sullysmod.common.blocks.AncientSkullBlock;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SkullBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(CustomHeadLayer.class)
public abstract class CustomHeadLayerMixin {

    @Final
    @Shadow
    private Map<SkullBlock.Type, SkullModelBase> skullModels;

    @Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V", ordinal = 2, shift = At.Shift.AFTER)
    )
    private void sullysmod_onRender(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, LivingEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, CallbackInfo ci) {
        Item item = pLivingEntity.getItemBySlot(EquipmentSlot.HEAD).getItem();
        if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof AncientSkullBlock ancientSkullBlock) {
            SkullBlock.Type skullblock$type = ancientSkullBlock.getType();
            if (this.skullModels.get(skullblock$type) instanceof BaseAncientSkullModel ancientSkullModel) {
                pPoseStack.scale(ancientSkullModel.headRenderScale(), ancientSkullModel.headRenderScale(), ancientSkullModel.headRenderScale());
                pPoseStack.translate(0, ancientSkullModel.headRenderHeight(), 0);
            }
        }
    }

}
