package com.uraneptus.sullysmod.core.events;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderBlockScreenEffectEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Matrix4f;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, value = Dist.CLIENT)
public class SMClientForgeEvents {

    @SubscribeEvent
    public static void onRenderBlockScreenEffectEvent(RenderBlockScreenEffectEvent event) {
        if (event.getOverlayType() == RenderBlockScreenEffectEvent.OverlayType.BLOCK) {
            if (event.getBlockState().is(SMBlocks.AMBER.get())) {
                event.setCanceled(true);
                Minecraft mc = Minecraft.getInstance();
                TextureAtlasSprite texture = mc.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(SullysMod.modPrefix("block/amber"));
                renderTransparentTexture(texture, event.getPoseStack());
            }
        }
    }

    private static void renderTransparentTexture(TextureAtlasSprite pTexture, PoseStack pPoseStack) {
        RenderSystem.setShaderTexture(0, pTexture.atlasLocation());
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.enableBlend();
        float f6 = pTexture.getU0();
        float f7 = pTexture.getU1();
        float f8 = pTexture.getV0();
        float f9 = pTexture.getV1();
        Matrix4f matrix4f = pPoseStack.last().pose();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
        bufferbuilder.vertex(matrix4f, -1.0F, -1.0F, -0.5F).color(0.4F, 0.4F, 0.4F, 1.0F).uv(f7, f9).endVertex();
        bufferbuilder.vertex(matrix4f, 1.0F, -1.0F, -0.5F).color(0.4F, 0.4F, 0.4F, 1.0F).uv(f6, f9).endVertex();
        bufferbuilder.vertex(matrix4f, 1.0F, 1.0F, -0.5F).color(0.4F, 0.4F, 0.4F, 1.0F).uv(f6, f8).endVertex();
        bufferbuilder.vertex(matrix4f, -1.0F, 1.0F, -0.5F).color(0.4F, 0.4F, 0.4F, 1.0F).uv(f7, f8).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
        RenderSystem.disableBlend();
    }

    static int refresh = 0;
    static PostChain effect;

    @SubscribeEvent
    public static void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;
        if (mc.level == null) return;

        GameRenderer renderer = mc.gameRenderer;

        if (player.getItemBySlot(EquipmentSlot.HEAD).is(SMItems.STONE_MASK.get()) && mc.options.getCameraType().isFirstPerson()) {
            if (refresh == 0 || renderer.currentEffect() != effect || !renderer.effectActive) {
                refresh = 1;
            }

        } else {
            if (refresh >= 1) {
                renderer.shutdownEffect();
                refresh = 0;
            }
        }

        if (refresh == 1) {
            ResourceLocation resourceLocation = SullysMod.modPrefix("shaders/post/gray.json");
            renderer.loadEffect(resourceLocation);
            effect = renderer.currentEffect();
            refresh = 2;
        }
    }
}
