package com.uraneptus.sullysmod.client.renderer.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.ChameleonModel;
import com.uraneptus.sullysmod.common.entities.Chameleon;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraft.client.renderer.entity.layers.WolfCollarLayer;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeableLeatherItem;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ChameleonRenderer extends GeoEntityRenderer<Chameleon> {

    public static final ResourceLocation STANDARD = SullysMod.modPrefix("textures/entity/chameleon/chameleon.png");
    public static final ResourceLocation TINTABLE = SullysMod.modPrefix("textures/entity/chameleon/tintable.png");
    public ChameleonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ChameleonModel());
    }

    @Override
    public RenderType getRenderType(Chameleon animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityCutoutNoCull(TINTABLE);
    }

    @Override
    public void renderEarly(Chameleon animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);

        if (animatable.isBaby()) {
            stackIn.scale(0.75F, 0.75F, 0.75F);
            this.shadowRadius *= 0.3F;
        }
    }

    @Override
    public Color getRenderColor(Chameleon animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight) {
        int r = (animatable.getCurrentColour() & 16711680) >> 16;
        int g = (animatable.getCurrentColour() & '\uff00') >> 8;
        int b = (animatable.getCurrentColour() & 255);

        Color rawColor = Color.ofRGB(r, g, b);

        return rawColor.brighter(1.2);
    }
}
