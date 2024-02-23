package com.uraneptus.sullysmod.core.registry.util;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;

public class SMBlockSubRegistryHelper extends BlockSubRegistryHelper {
    public SMBlockSubRegistryHelper(RegistryHelper parent) {
        super(parent);
    }

    //Edited version of the base method that uses the SoundType provided from the WoodType instead of the hardcoded WoodType
    @Override
    public Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> createSignBlock(String name, WoodType woodType, MapColor color) {
        RegistryObject<BlueprintStandingSignBlock> standing = this.deferredRegister.register(name + "_sign", () -> new BlueprintStandingSignBlock(Block.Properties.of().mapColor(color).forceSolidOn().instrument(NoteBlockInstrument.BASS).ignitedByLava().noCollission().strength(1.0F).sound(woodType.soundType()), woodType));
        RegistryObject<BlueprintWallSignBlock> wall = this.deferredRegister.register(name + "_wall_sign", () -> new BlueprintWallSignBlock(Block.Properties.of().mapColor(color).forceSolidOn().instrument(NoteBlockInstrument.BASS).ignitedByLava().noCollission().strength(1.0F).sound(woodType.soundType()).lootFrom(standing), woodType));
        this.itemRegister.register(name + "_sign", () -> new SignItem(new Item.Properties().stacksTo(16), standing.get(), wall.get()));
        return Pair.of(standing, wall);
    }

    @Override
    public Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> createHangingSignBlock(String name, WoodType woodType, MapColor color) {
        RegistryObject<BlueprintCeilingHangingSignBlock> ceiling = this.deferredRegister.register(name + "_hanging_sign", () -> new BlueprintCeilingHangingSignBlock(Block.Properties.of().mapColor(color).forceSolidOn().instrument(NoteBlockInstrument.BASS).ignitedByLava().noCollission().strength(1.0F).sound(woodType.hangingSignSoundType()), woodType));
        RegistryObject<BlueprintWallHangingSignBlock> wall = this.deferredRegister.register(name + "_wall_hanging_sign", () -> new BlueprintWallHangingSignBlock(Block.Properties.of().mapColor(color).forceSolidOn().instrument(NoteBlockInstrument.BASS).ignitedByLava().noCollission().strength(1.0F).sound(woodType.hangingSignSoundType()).lootFrom(ceiling), woodType));
        this.itemRegister.register(name + "_hanging_sign", () -> new HangingSignItem(ceiling.get(), wall.get(), new Item.Properties().stacksTo(16)));
        return Pair.of(ceiling, wall);    }
}
