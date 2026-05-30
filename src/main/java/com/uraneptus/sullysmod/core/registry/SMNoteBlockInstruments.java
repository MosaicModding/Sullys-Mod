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
        registerAncientSkullInstrument(SMArtifacts.CRESTED_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_CRESTED_SKULL);
        registerAncientSkullInstrument(SMArtifacts.CRACKED_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_CRACKED_SKULL);
        registerAncientSkullInstrument(SMArtifacts.FLATBILLED_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_FLATBILLED_SKULL);
        registerAncientSkullInstrument(SMArtifacts.GIGANTIC_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_GIGANTIC_SKULL);
        registerAncientSkullInstrument(SMArtifacts.HORNED_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_HORNED_SKULL);
        registerAncientSkullInstrument(SMArtifacts.LONG_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_LONG_SKULL);
        registerAncientSkullInstrument(SMArtifacts.TINY_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_TINY_SKULL);
        registerAncientSkullInstrument(SMArtifacts.WIDE_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_WIDE_SKULL);
        registerAncientSkullInstrument(SMArtifacts.RIBBED_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_RIBBED_SKULL);
        registerAncientSkullInstrument(SMArtifacts.UNICORN_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_UNICORN_SKULL);
        registerAncientSkullInstrument(SMArtifacts.SNOUTED_ANCIENT_SKULL, SMSounds.NOTE_BLOCK_SNOUTED_SKULL);
    }

    public static void registerAncientSkullInstrument(Pair<RegistryObject<Block>, RegistryObject<Block>> skull, Supplier<SoundEvent> soundEvent) {
        Block block = skull.getFirst().get();
        String name = SMTextUtil.convertSkullTypeToString(((AncientSkullBlock)block).getAncientType());
        DataUtil.registerNoteBlockInstrument(new DataUtil.CustomNoteBlockInstrument(name + "_skull", blockSource -> blockSource.getBlockState().is(block), soundEvent.get(), true));
    }
}
