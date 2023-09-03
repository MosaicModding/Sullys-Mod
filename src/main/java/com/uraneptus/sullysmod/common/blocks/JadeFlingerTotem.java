package com.uraneptus.sullysmod.common.blocks;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public class JadeFlingerTotem extends SMDirectionalBlock {
    public JadeFlingerTotem(Properties properties) {
        super(properties);
    }

    private static final Supplier<Item> DROPPER = Blocks.DROPPER::asItem;
}