package com.uraneptus.sullysmod.core.events;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, value = Dist.CLIENT)
public class SMClientForgeEvents {
    static final Set<UUID> DEV_UUIDS = Set.of(
            UUID.fromString("6c175d10-198a-49f9-8e2b-c74f1f0178f3"), //Sully
            UUID.fromString("fddaefa0-31d2-4acf-9cd2-4711d0e5e5d5"), //Uraneptus
            UUID.fromString("3fd1d511-62d6-4e18-a28d-3e3d4fd93620"), //Keke
            UUID.fromString("1fb0ecbd-2000-4f00-aa65-002c1ccedef7") //Stelle
    );

    @SubscribeEvent
    public static void onEvent(RenderPlayerEvent.Post event) {
        Player player = event.getEntity();
        UUID uuid = player.getGameProfile().getId();
        if (DEV_UUIDS.contains(uuid)) {
            ResourceLocation cape = SullysMod.modPrefix("textures/entity/mosaic_modding_cape.png");
            if (player instanceof AbstractClientPlayer clientPlayer && clientPlayer.playerInfo != null) {
                if (clientPlayer.isCapeLoaded() && clientPlayer.getCloakTextureLocation() == null) {
                    Map<MinecraftProfileTexture.Type, ResourceLocation> playerTextures = clientPlayer.playerInfo.textureLocations;
                    playerTextures.put(MinecraftProfileTexture.Type.CAPE, cape);
                    playerTextures.put(MinecraftProfileTexture.Type.ELYTRA, cape);
                }
            }
        }
    }
}
