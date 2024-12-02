package com.uraneptus.sullysmod.common.blocks.utilities;

import com.uraneptus.sullysmod.common.SMToggleable;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.List;

public class SMFenceGateBlock extends FenceGateBlock implements SMToggleable {

    public SMFenceGateBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    public SMFenceGateBlock(Properties props, SoundEvent openSound, SoundEvent closeSound) {
        super(props, openSound, closeSound);
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
