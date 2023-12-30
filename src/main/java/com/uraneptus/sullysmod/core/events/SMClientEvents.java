package com.uraneptus.sullysmod.core.events;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.JadeShieldModel;
import com.uraneptus.sullysmod.client.model.JungleSpiderModel;
import com.uraneptus.sullysmod.client.model.LanternfishModel;
import com.uraneptus.sullysmod.client.model.TortoiseShellModel;
import com.uraneptus.sullysmod.client.particles.RicochetParticle;
import com.uraneptus.sullysmod.client.renderer.entities.*;
import com.uraneptus.sullysmod.common.items.VenomVialItem;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMParticleTypes;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SMClientEvents {

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SMEntityTypes.COPPER_GOLEM.get(), CopperGolemRenderer::new);
        event.registerEntityRenderer(SMEntityTypes.LANTERNFISH.get(), LanternfishRenderer::new);
        event.registerEntityRenderer(SMEntityTypes.TORTOISE.get(), TortoiseRenderer::new);
        event.registerEntityRenderer(SMEntityTypes.TORTOISE_SHELL.get(), TortoiseShellRenderer::new);
        event.registerEntityRenderer(SMEntityTypes.BOULDERING_ZOMBIE.get(), BoulderingZombieRenderer::new);
        event.registerEntityRenderer(SMEntityTypes.JUNGLE_SPIDER.get(), JungleSpiderRenderer::new);
        event.registerEntityRenderer(SMEntityTypes.PIRANHA.get(), PiranhaRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerLocation(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LanternfishModel.LAYER_LOCATION, LanternfishModel::createBodyLayer);
        event.registerLayerDefinition(JadeShieldModel.LAYER_LOCATION, JadeShieldModel::createLayer);
        event.registerLayerDefinition(TortoiseShellModel.LAYER_LOCATION, TortoiseShellModel::createBodyLayer);
        event.registerLayerDefinition(JungleSpiderModel.LAYER_LOCATION, JungleSpiderModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SMParticleTypes.RICOCHET.get(), RicochetParticle.RicochetParticleProvider::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemProperties.register(SMItems.JADE_SHIELD.get(), new ResourceLocation("blocking"), (itemStack, clientWorld, livingEntity, useTime) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);
    }

    @SubscribeEvent
    public static void onItemColouring(RegisterColorHandlersEvent.Item event) {
        event.register(((stack, tint) -> tint == 0 ? -1 : VenomVialItem.getEffectColours(stack, tint)), SMItems.VENOM_VIAL.get());
    }
}
