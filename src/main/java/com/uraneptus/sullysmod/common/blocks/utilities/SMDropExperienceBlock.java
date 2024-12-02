package com.uraneptus.sullysmod.common.blocks.utilities;

import com.uraneptus.sullysmod.common.SMToggleable;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DropExperienceBlock;

import java.util.List;

public class SMDropExperienceBlock extends DropExperienceBlock implements SMToggleable {

    public SMDropExperienceBlock(Properties pProperties) {
        super(pProperties);
    }

    public SMDropExperienceBlock(Properties pProperties, IntProvider pXpRange) {
        super(pProperties, pXpRange);
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
