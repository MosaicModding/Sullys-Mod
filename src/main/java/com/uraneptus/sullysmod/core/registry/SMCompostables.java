package com.uraneptus.sullysmod.core.registry;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class SMCompostables {

    public static void registerCompostables() {
        register(SMArtifacts.DRIED_CYAN_FLOWER.get(), 0.65F);
        register(SMArtifacts.DRIED_RED_FLOWER.get(), 0.65F);
        register(SMArtifacts.TORN_MANUSCRIPT.get(), 0.3F);
        register(SMArtifacts.CAVE_CARROT.get(), 0.65F);
        register(SMArtifacts.SOAKED_BOOK.get(), 0.4F);
        register(SMArtifacts.PETRIFIED_COOKIE.get(), 0.1F);
    }

    private static void register(ItemLike item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
    }
}
