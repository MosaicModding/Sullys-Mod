package com.mosaicmodding.sullysmod.platform;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface SMPlatHelper {
    Block getPickaxeStrippableResult(Block original);
    Rarity getAncientRarity();
}
