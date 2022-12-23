package com.uraneptus.sullysmod.core.integration.fd;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;

public class FDCompat {
    public static final String MOD_ID = "farmersdelight";
    public static boolean IS_LOADED;

    public static void register() {
        IS_LOADED = ModList.get().isLoaded(FDCompat.MOD_ID);

    }

    public static class FDLoaded {
        public static final Item COOKED_RICE = ModItems.COOKED_RICE.get();

        public static CreativeModeTab fdTab() {
            return FarmersDelight.CREATIVE_TAB;
        }

    }

}
