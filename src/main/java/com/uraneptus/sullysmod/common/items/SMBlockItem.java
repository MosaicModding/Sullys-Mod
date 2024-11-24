package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class SMBlockItem extends BlockItem {
    private final List<SMFeatures> features;

    public SMBlockItem(Block pBlock, Properties pProperties, SMFeatures... features) {
        super(pBlock, pProperties);
        this.features = List.of(features);
    }

    @Override
    public boolean isEnabled(FeatureFlagSet pEnabledFeatures) {
        return features.stream().allMatch(SMFeatures::isEnabled);
    }

    public List<SMFeatures> getFeatures() {
        return features;
    }
}
