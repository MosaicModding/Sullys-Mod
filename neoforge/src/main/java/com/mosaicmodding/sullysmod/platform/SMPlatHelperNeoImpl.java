package com.mosaicmodding.sullysmod.platform;

import com.mosaicmodding.sullysmod.registry.SMDataMaps;
import com.mosaicmodding.sullysmod.registry.SMRaritiesNeo;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class SMPlatHelperNeoImpl implements SMPlatHelper {

    @Override
    @SuppressWarnings("deprecation")
    public Block getPickaxeStrippableResult(Block original) {
        var strippable = original.builtInRegistryHolder().getData(SMDataMaps.PICKAXE_STRIPPABLES);
        if (strippable != null) {
            return strippable.strippedBlock();
        }
        return original;
    }

    @Override
    public Rarity getAncientRarity() {
        return SMRaritiesNeo.ANCIENT_RARITY_PROXY.getValue();
    }
}
