package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.common.SMToggleable;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class SMSignItem extends SignItem implements SMToggleable {
    private final List<SMFeatures> features;

    public SMSignItem(Block pStandingBlock, Block pWallBlock, SMFeatures... features) {
        super(new Item.Properties().stacksTo(16), pStandingBlock, pWallBlock);
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
