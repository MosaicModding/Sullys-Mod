package com.uraneptus.sullysmod.common.blocks.utilities;

import com.uraneptus.sullysmod.common.SMToggleable;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.List;

public class SMButtonBlock extends ButtonBlock implements SMToggleable {

    public SMButtonBlock(Properties pProperties, BlockSetType pType, int pTicksToStayPressed, boolean pArrowsCanPress) {
        super(pProperties, pType, pTicksToStayPressed, pArrowsCanPress);
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
