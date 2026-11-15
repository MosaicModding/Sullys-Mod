package com.mosaicmodding.sullysmod.platform;

import com.mosaicmodding.sullysmod.registry.SMBlocks;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class SMPlatHelperFabricImpl implements SMPlatHelper {

    //This is not compat friendly. On neo we have datamaps for compat.
    // Might be useful to expose an API endpoint for this feature on fabric at some point.
    @Override
    public Block getPickaxeStrippableResult(Block original) {
        Map<Block, Block> pickaxeStrippables = new HashMap<>();

        SMBlocks.addStrippableBlocks(((input, stripped, usingPickaxe) -> {
            if (!usingPickaxe) return;
            pickaxeStrippables.put(input.get(), stripped.get());
        }));

        return pickaxeStrippables.getOrDefault(original, original);
    }
}
