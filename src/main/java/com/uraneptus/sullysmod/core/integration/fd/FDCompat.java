package com.uraneptus.sullysmod.core.integration.fd;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.registry.ModCreativeTabs;
import vectorwing.farmersdelight.common.registry.ModItems;

public class FDCompat {
    public static final String MOD_ID = "farmersdelight";
    public static boolean IS_LOADED;
    public static ResourceKey<CreativeModeTab> FDTAB;

    public static void register() {
        IS_LOADED = ModList.get().isLoaded(FDCompat.MOD_ID);
        FDTAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(MOD_ID, MOD_ID));
    }

    public static Item getFDItem(String id) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(MOD_ID, id));
    }
}
