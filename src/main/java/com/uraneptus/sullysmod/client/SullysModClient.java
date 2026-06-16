package com.uraneptus.sullysmod.client;

import com.uraneptus.sullysmod.SullysMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = SullysMod.MOD_ID, dist = Dist.CLIENT) //todo maybe don't reference the common entrypoint here?
public class SullysModClient {

    public SullysModClient(IEventBus bus, ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
