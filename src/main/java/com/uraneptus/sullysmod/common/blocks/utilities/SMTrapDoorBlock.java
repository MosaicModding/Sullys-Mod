package com.uraneptus.sullysmod.common.blocks.utilities;

import com.uraneptus.sullysmod.common.SMToggleable;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.List;

public class SMTrapDoorBlock extends TrapDoorBlock implements SMToggleable {

    public SMTrapDoorBlock(Properties pProperties, BlockSetType pType) {
        super(pProperties, pType);
    }

    @Override
    public List<SMFeatures> getFeature() {
        return List.of();
    }

    @Override
    public Item getItem() {
        return this.asItem();
    }
}
