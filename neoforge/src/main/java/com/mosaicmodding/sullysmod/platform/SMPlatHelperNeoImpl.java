package com.mosaicmodding.sullysmod.platform;

import com.mosaicmodding.sullysmod.registry.SMDataMaps;
import net.minecraft.world.level.block.Block;

public class SMPlatHelperNeoImpl implements SMPlatHelper{

    @Override
    @SuppressWarnings("deprecation")
    public Block getPickaxeStrippableResult(Block original) {
        var strippable = original.builtInRegistryHolder().getData(SMDataMaps.PICKAXE_STRIPPABLES);
        if (strippable != null) {
            return strippable.strippedBlock();
        }
        return original;
    }
}
