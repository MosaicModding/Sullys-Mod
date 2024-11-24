package com.uraneptus.sullysmod.common;

import com.uraneptus.sullysmod.common.items.SMBlockItem;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;

import java.util.List;

public interface SMToggleable extends FeatureElement {
    List<SMFeatures> getFeature();
    Item getItem();

    default boolean isEnabled(FeatureFlagSet pEnabledFeatures) {
        if (getItem() instanceof SMBlockItem smBlockItem) {
            return smBlockItem.getFeatures().stream().allMatch(SMFeatures::isEnabled);
        } else {
            return getFeature().stream().allMatch(SMFeatures::isEnabled);
        }
    }
}
