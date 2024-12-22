package com.uraneptus.sullysmod.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.uraneptus.sullysmod.common.blocks.AncientSkullBlock;
import com.uraneptus.sullysmod.core.other.SMTextUtil;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class SMNoteBlockInstruments {

    public static void register() {
        registerAncientSkullInstrument(SMBlocks.CRESTED_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_CRESTED_SKULL);
        registerAncientSkullInstrument(SMBlocks.CRACKED_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_CRACKED_SKULL);
        registerAncientSkullInstrument(SMBlocks.FLATBILLED_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_FLATBILLED_SKULL);
        registerAncientSkullInstrument(SMBlocks.GIGANTIC_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_GIGANTIC_SKULL);
        registerAncientSkullInstrument(SMBlocks.HORNED_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_HORNED_SKULL);
        registerAncientSkullInstrument(SMBlocks.LONG_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_LONG_SKULL);
        registerAncientSkullInstrument(SMBlocks.TINY_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_TINY_SKULL);
        registerAncientSkullInstrument(SMBlocks.WIDE_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_WIDE_SKULL);
        registerAncientSkullInstrument(SMBlocks.RIBBED_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_RIBBED_SKULL);
        registerAncientSkullInstrument(SMBlocks.UNICORN_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_UNICORN_SKULL);
    }

    //BREAKING CHANGE implement own way of registering this.
    public static void registerAncientSkullInstrument(Pair<RegistryObject<Block>, RegistryObject<Block>> skull, Supplier<SoundEvent> soundEvent) {
        Block block = skull.getFirst().get();
        String name = SMTextUtil.convertSkullTypeToString(((AncientSkullBlock)block).getAncientType());
        DataUtil.registerNoteBlockInstrument(new DataUtil.CustomNoteBlockInstrument(name + "_skull", blockSource -> blockSource.getBlockState().is(block), soundEvent.get(), true));
    }
}
