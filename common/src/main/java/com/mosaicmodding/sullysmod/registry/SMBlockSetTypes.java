package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.mixin.BlockSetTypeAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class SMBlockSetTypes {
    public static final BlockSetType PETRIFIED = BlockSetTypeAccessor.register(
            new BlockSetType(
                    "petrified",
                    true,
                    true,
                    false,
                    BlockSetType.PressurePlateSensitivity.EVERYTHING,
                    /*todo SMSounds.PETRIFIED_WOOD*/SoundType.WOOD, SoundEvents.IRON_DOOR_CLOSE,
                    SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE,
                    SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF,
                    SoundEvents.STONE_BUTTON_CLICK_ON));
}
