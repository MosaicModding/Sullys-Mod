package com.uraneptus.sullysmod.core.events;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.SMConfig;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

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
    static final Set<UUID> CONTRIBUTORS = Set.of(
            UUID.fromString("a69fc0f5-028a-4bee-9102-26ff3764017e"), //Sully alt
            UUID.fromString("4d659277-fbcc-4fd8-9e80-c8e4c73d10b5"), //Sunken past artist
            UUID.fromString("4378df24-8433-4b5c-b865-bf635b003ebb") //Farcr
    );

    @SubscribeEvent
    public static void onEvent(RenderPlayerEvent.Post event) {
        Player player = event.getEntity();
        UUID uuid = player.getGameProfile().getId();

        if ((CONTRIBUTORS.contains(uuid) || devPlayerOrIDE(uuid)) && SMConfig.ENABLE_CONTRIBUTOR_CAPE.get()) {
            ResourceLocation cape = devPlayerOrIDE(uuid) ? SullysMod.modPrefix("textures/entity/mosaic_modding_cape.png") :
                    SullysMod.modPrefix("textures/entity/contributor_cape.png");

            if (player instanceof AbstractClientPlayer clientPlayer && clientPlayer.playerInfo != null) {
                if (clientPlayer.isCapeLoaded()) {
                    Map<MinecraftProfileTexture.Type, ResourceLocation> playerTextures = clientPlayer.playerInfo.textureLocations;
                    playerTextures.put(MinecraftProfileTexture.Type.CAPE, cape);
                    playerTextures.put(MinecraftProfileTexture.Type.ELYTRA, cape);
                }
            }
        }
    }

    private static boolean devPlayerOrIDE(UUID uuid) {
        return DEV_UUIDS.contains(uuid) || !FMLEnvironment.production;
    }
}
