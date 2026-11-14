package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.mixin.WoodTypeAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class SMWoodTypes {
    public static final WoodType PETRIFIED = WoodTypeAccessor.register(new WoodType("sullysmod:petrified", SMBlockSetTypes.PETRIFIED,
            SMBlockSetTypes.PETRIFIED.soundType(), SoundType.HANGING_SIGN, SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN)); //TODO add our own sound types & sound events
}
