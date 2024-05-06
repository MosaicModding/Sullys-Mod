package com.uraneptus.sullysmod.client.renderer.entities.layer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.LanternfishModel;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.joml.Matrix4f;

public class StuckInAmberLayer <E extends Mob> extends RenderLayer<E, EntityModel<E>> {

    public StuckInAmberLayer(RenderLayerParent<E, EntityModel<E>> entityRenderer) {
        super(entityRenderer);
    }

    //ALL IN HERE IS JUST TO TEST STUFF

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, E pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        VertexConsumer consumer = pBuffer.getBuffer(enchantSwirl());
        this.getParentModel().renderToBuffer(pPoseStack, consumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    protected static final RenderStateShard.TransparencyStateShard ADDITIVE_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });

    protected static final RenderStateShard.TexturingStateShard ENTITY_GLINT_TEXTURING = new RenderStateShard.TexturingStateShard("entity_glint_texturing", () -> {
        setupGlintTexturing(2.5F);
    }, RenderSystem::resetTextureMatrix);

    private static void setupGlintTexturing(float p_110187_) {
        long i = Util.getMillis() * 8L;
        float f = (float) (i % 110000L) / 110000.0F;
        float f1 = (float) (i % 30000L) / 30000.0F;
        Matrix4f matrix4f = (new Matrix4f()).translation(f, -f1, 0.0F);
        matrix4f.rotateZ(-0.17453292F).scale(p_110187_);
        //matrix4f.rotateY(0.17453292F).scale(p_110187_);
        RenderSystem.setTextureMatrix(matrix4f);
    }

    protected static final RenderStateShard.OutputStateShard ITEM_ENTITY_TARGET = new RenderStateShard.OutputStateShard("item_entity_target", () -> {
        if (Minecraft.useShaderTransparency()) {
            Minecraft.getInstance().levelRenderer.getItemEntityTarget().bindWrite(false);
        }

    }, () -> {
        if (Minecraft.useShaderTransparency()) {
            Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
        }

    });

    public static RenderType enchantSwirl() {
        ResourceLocation location = SullysMod.modPrefix("textures/misc/amber_layer.png");
        return RenderType.create("enchant_effect", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true,
                RenderType.CompositeState.builder()
                        .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEnergySwirlShader))
                        .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false))
                        .setTextureState(new RenderStateShard.TextureStateShard(location, false, false))
                        .setTransparencyState(ADDITIVE_TRANSPARENCY)
                        .setCullState(new RenderStateShard.CullStateShard(false))
                        .setDepthTestState(new RenderStateShard.DepthTestStateShard("==", 514))
                        .setTexturingState(ENTITY_GLINT_TEXTURING)
                        .createCompositeState(false));
    }
}
