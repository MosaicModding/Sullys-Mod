package com.uraneptus.sullysmod.core.events;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SMBlockEvents {

    @SubscribeEvent
    public static void onBlockPlacedEvent(BlockEvent.EntityPlaceEvent event) {

    }

}
