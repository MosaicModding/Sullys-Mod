package com.mosaicmodding.sullysmod.events;

import com.mosaicmodding.sullysmod.content.pickaxe_stripping.PickaxeStrippingHandler;
import com.mosaicmodding.sullysmod.registry.SMDataMaps;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber
public class SMNeoRuntimeEvents {

    @SubscribeEvent
    private static void rightClickBlockEvent(PlayerInteractEvent.RightClickBlock event) {
        var pickaxeStrippingResult = PickaxeStrippingHandler.handleInteraction(event.getEntity(), event.getLevel(), event.getHand(), event.getHitVec());
        if (pickaxeStrippingResult.consumesAction()) {
            event.setCancellationResult(pickaxeStrippingResult);
            event.setCanceled(true);
        }
    }
}
