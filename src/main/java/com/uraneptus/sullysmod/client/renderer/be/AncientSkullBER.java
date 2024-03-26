package com.uraneptus.sullysmod.client.renderer.be;


import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.client.model.CrackedAncientSkullModel;
import com.uraneptus.sullysmod.common.blockentities.AncientSkullBE;
import com.uraneptus.sullysmod.common.blocks.AncientSkullBlock;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PiglinHeadModel;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.dragon.DragonHeadModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.core.Direction;
import net.minecraft.core.UUIDUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class AncientSkullBER implements BlockEntityRenderer<AncientSkullBE> {
    private final Map<AncientSkullBlock.Type, SkullModelBase> modelByType;
    public static final Map<AncientSkullBlock.Type, ResourceLocation> SKIN_BY_TYPE = Util.make(Maps.newHashMap(), (p_261388_) -> {
        p_261388_.put(AncientSkullBlock.Types.CRACKED, new ResourceLocation("textures/entity/skeleton/skeleton.png"));
    });

    public static Map<AncientSkullBlock.Type, SkullModelBase> createSkullRenderers(EntityModelSet pEntityModelSet) {
        ImmutableMap.Builder<AncientSkullBlock.Type, SkullModelBase> builder = ImmutableMap.builder();
        builder.put(AncientSkullBlock.Types.CRACKED, new CrackedAncientSkullModel(pEntityModelSet.bakeLayer(CrackedAncientSkullModel.LAYER_LOCATION)));
        //net.minecraftforge.fml.ModLoader.get().postEvent(new net.minecraftforge.client.event.EntityRenderersEvent.CreateSkullModels(builder, pEntityModelSet));
        return builder.build();
    }

    public AncientSkullBER(BlockEntityRendererProvider.Context pContext) {
        this.modelByType = createSkullRenderers(pContext.getModelSet());
    }

    public void render(AncientSkullBE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        float f = pBlockEntity.getAnimation(pPartialTick);
        BlockState blockstate = pBlockEntity.getBlockState();
        boolean flag = blockstate.getBlock() instanceof WallSkullBlock;
        Direction direction = flag ? blockstate.getValue(WallSkullBlock.FACING) : null;
        int i = flag ? RotationSegment.convertToSegment(direction.getOpposite()) : blockstate.getValue(SkullBlock.ROTATION);
        float f1 = RotationSegment.convertToDegrees(i);
        SkullBlock.Type skullblock$type = ((AbstractSkullBlock)blockstate.getBlock()).getType();
        SkullModelBase skullmodelbase = this.modelByType.get(skullblock$type);
        RenderType rendertype = getRenderType(skullblock$type, pBlockEntity.getOwnerProfile());
        renderSkull(direction, f1, f, pPoseStack, pBuffer, pPackedLight, skullmodelbase, rendertype);
    }

    public static void renderSkull(@Nullable Direction pDirection, float pYRot, float pMouthAnimation, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, SkullModelBase pModel, RenderType pRenderType) {
        pPoseStack.pushPose();
        if (pDirection == null) {
            pPoseStack.translate(0.5F, 0.0F, 0.5F);
        } else {
            float f = 0.25F;
            pPoseStack.translate(0.5F - (float)pDirection.getStepX() * 0.25F, 0.25F, 0.5F - (float)pDirection.getStepZ() * 0.25F);
        }

        pPoseStack.scale(-1.0F, -1.0F, 1.0F);
        VertexConsumer vertexconsumer = pBufferSource.getBuffer(pRenderType);
        pModel.setupAnim(pMouthAnimation, pYRot, 0.0F);
        pModel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
    }

    public static RenderType getRenderType(SkullBlock.Type pSkullType, @Nullable GameProfile pGameProfile) {
        ResourceLocation resourcelocation = SKIN_BY_TYPE.get(pSkullType);
        if (pSkullType == SkullBlock.Types.PLAYER && pGameProfile != null) {
            Minecraft minecraft = Minecraft.getInstance();
            Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.getSkinManager().getInsecureSkinInformation(pGameProfile);
            return map.containsKey(MinecraftProfileTexture.Type.SKIN) ? RenderType.entityTranslucent(minecraft.getSkinManager().registerTexture(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN)) : RenderType.entityCutoutNoCull(DefaultPlayerSkin.getDefaultSkin(UUIDUtil.getOrCreatePlayerUUID(pGameProfile)));
        } else {
            return RenderType.entityCutoutNoCullZOffset(resourcelocation);
        }
    }
}