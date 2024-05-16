package com.uraneptus.sullysmod.core.registry;

import com.google.common.base.Suppliers;
import com.teamabnormals.blueprint.core.api.BlockSetTypeRegistryHelper;
import com.teamabnormals.blueprint.core.api.WoodTypeRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.function.Supplier;

public class SMBlocksetTypes {
    public static final Supplier<BlockSetType> PETRIFIED_BLOCKSET = Suppliers.memoize(() -> createBlocksetType("petrified", true, SMSounds.PETRIFIED_WOOD, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));
    public static final Supplier<BlockSetType> COPPER_BLOCKSET = Suppliers.memoize(() -> createBlocksetType("copper", true, SoundType.COPPER, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SMSounds.COPPER_BUTTON_CLICK_OFF.get(), SMSounds.COPPER_BUTTON_CLICK_ON.get()));

    public static final Supplier<WoodType> PETRIFIED_WOOD_TYPE = Suppliers.memoize(() -> createDefaultWoodType(PETRIFIED_BLOCKSET));

    public static BlockSetType createBlocksetType(String name, boolean canOpenByHand, SoundType soundType, SoundEvent doorClose, SoundEvent doorOpen, SoundEvent trapdoorClose, SoundEvent trapdoorOpen, SoundEvent pressurePlateClickOff, SoundEvent pressurePlateClickOn, SoundEvent buttonClickOff, SoundEvent buttonClickOn) {
        return BlockSetTypeRegistryHelper.register(new BlockSetType(SullysMod.modPrefix(name).toString(), canOpenByHand, soundType, doorClose, doorOpen, trapdoorClose, trapdoorOpen, pressurePlateClickOff, pressurePlateClickOn, buttonClickOff, buttonClickOn));
    }

    public static WoodType createDefaultWoodType(Supplier<BlockSetType> blockSetType) {
        return createWoodType(blockSetType, SoundType.HANGING_SIGN, SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN);
    }

    public static WoodType createWoodType(Supplier<BlockSetType> blockSetType, SoundType hangingSignSoundType, SoundEvent fenceGateClose, SoundEvent fenceGateOpen) {
        return WoodTypeRegistryHelper.registerWoodType(new WoodType(blockSetType.get().name(), blockSetType.get(), blockSetType.get().soundType(), hangingSignSoundType, fenceGateClose, fenceGateOpen));
    }
}
