package com.uraneptus.sullysmod.common.blocks.utilities;

import com.uraneptus.sullysmod.common.SMToggleable;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.RotatedPillarBlock;

import java.util.List;

public class SMRotatedPillarBlock extends RotatedPillarBlock implements SMToggleable {

    public SMRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
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
