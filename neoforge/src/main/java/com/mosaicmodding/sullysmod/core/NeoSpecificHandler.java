package com.mosaicmodding.sullysmod.core;

import com.mosaicmodding.sullysmod.registry.SMBlocks;
import com.mosaicmodding.sullysmod.registry.registry_sets.WoodBlockRegistrySet;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;

//TODO probably rename or move. I couldn't think of a good place to put the flower pot handler
public class NeoSpecificHandler {

    public static void addPlantsToPot() {
        FlowerPotBlock flowerPot = (FlowerPotBlock) Blocks.FLOWER_POT;
        SMBlocks.REG_SETS.forEach(set -> {
            if (set instanceof WoodBlockRegistrySet woodSet) {
                flowerPot.addPlant(woodSet.getSaplingBlock().getId(), woodSet.getPottedSaplingBlock());
            }
        });
    }
}
