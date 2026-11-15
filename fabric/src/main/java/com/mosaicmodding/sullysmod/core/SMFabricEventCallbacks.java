package com.mosaicmodding.sullysmod.core;

import com.mosaicmodding.sullysmod.content.pickaxe_stripping.PickaxeStrippingHandler;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class SMFabricEventCallbacks {

    public static void init() {
        UseBlockCallback.EVENT.register((PickaxeStrippingHandler::handleInteraction));
    }
}
