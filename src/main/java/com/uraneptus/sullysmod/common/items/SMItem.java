package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.common.SMToggleable;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.item.Item;

import java.util.List;

public class SMItem extends Item implements SMToggleable {
    private final List<SMFeatures> features;

    public SMItem(Properties pProperties, SMFeatures... features) {
        super(pProperties);
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
