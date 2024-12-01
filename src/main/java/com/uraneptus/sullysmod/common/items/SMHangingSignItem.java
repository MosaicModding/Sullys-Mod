package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.common.SMToggleable;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class SMHangingSignItem extends HangingSignItem implements SMToggleable {
    private final List<SMFeatures> features;

    public SMHangingSignItem(Block pBlock, Block pWallBlock, SMFeatures... features) {
        super(pBlock, pWallBlock, new Item.Properties().stacksTo(16));
        this.features = List.of(features);
    }

    @Override
    public List<SMFeatures> getFeature() {
        return features;
    }

    @Override
    public Item getItem() {
        return this;
    }
}
