package com.mosaicmodding.sullysmod.client;

import com.mosaicmodding.sullysmod.client.registry.SMClientRegistration;
import com.mosaicmodding.sullysmod.core.config.SMClientConfig;
import net.mehvahdjukaar.moonlight.api.client.gui.ModCatalogAPI;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.List;

public class SullysModClient {

    public static void init() {
        SMClientConfig.init();
        SMClientRegistration.init();
        ModCatalogAPI.register(MM_NAME, MM_MODS);
    }

    //Might be a little overkill lmao
    private static final Component MM_NAME = Component.literal("M").withStyle(ChatFormatting.RED)
            .append(Component.literal("o").withStyle(ChatFormatting.YELLOW))
            .append(Component.literal("s").withStyle(ChatFormatting.GOLD))
            .append(Component.literal("a").withStyle(ChatFormatting.GREEN))
            .append(Component.literal("i").withStyle(ChatFormatting.AQUA))
            .append(Component.literal("c").withStyle(ChatFormatting.LIGHT_PURPLE))
            .append(Component.literal(" M").withStyle(ChatFormatting.RED))
            .append(Component.literal("o").withStyle(ChatFormatting.YELLOW))
            .append(Component.literal("d").withStyle(ChatFormatting.GOLD))
            .append(Component.literal("d").withStyle(ChatFormatting.GREEN))
            .append(Component.literal("i").withStyle(ChatFormatting.AQUA))
            .append(Component.literal("n").withStyle(ChatFormatting.LIGHT_PURPLE))
            .append(Component.literal("g").withStyle(ChatFormatting.RED));

    //TODO put this on the internet and fetch it
    private static final List<ModCatalogAPI.Entry> MM_MODS = List.of(
            new ModCatalogAPI.Entry(
                    "sullysmod",
                    "Sully's Mod",
                    "A highly configurable mod adding unique features that extend the game while keeping a vanilla vibe",
                    "https://media.forgecdn.net/avatars/thumbnails/765/170/256/256/638100907177769604.png",
                    "https://www.curseforge.com/minecraft/mc-mods/sullys-mod",
                    "https://modrinth.com/mod/sullysmod"
            ),
            new ModCatalogAPI.Entry(
                    "painters_inc",
                    "Painters Inc",
                    "A painting mod collab between several artists",
                    "https://media.forgecdn.net/avatars/thumbnails/1911/779/256/256/639192865576601536.png",
                    "https://www.curseforge.com/minecraft/mc-mods/paintersinc",
                    "https://modrinth.com/mod/paintersinc"
            )
    );
}
