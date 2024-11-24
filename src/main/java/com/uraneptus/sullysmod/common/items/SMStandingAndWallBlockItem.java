package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.common.SMToggleable;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class SMStandingAndWallBlockItem extends StandingAndWallBlockItem implements SMToggleable {
    private final List<SMFeatures> features;

    public SMStandingAndWallBlockItem(Block pBlock, Block pWallBlock, Properties pProperties, Direction pAttachmentDirection, SMFeatures... features) {
        super(pBlock, pWallBlock, pProperties, pAttachmentDirection);
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
