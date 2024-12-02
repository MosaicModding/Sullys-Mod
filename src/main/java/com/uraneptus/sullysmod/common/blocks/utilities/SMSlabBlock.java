package com.uraneptus.sullysmod.common.blocks.utilities;

import com.uraneptus.sullysmod.common.SMToggleable;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SlabBlock;

import java.util.List;

public class SMSlabBlock extends SlabBlock implements SMToggleable {

    public SMSlabBlock(Properties pProperties) {
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
