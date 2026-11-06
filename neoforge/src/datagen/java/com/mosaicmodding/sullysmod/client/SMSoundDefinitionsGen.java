package com.mosaicmodding.sullysmod.client;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.registry.SMSoundEvents;
import com.mosaicmodding.tessera.datagen.sound.TesseraSoundEventSystem;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class SMSoundDefinitionsGen extends TesseraSoundEventSystem {

    public SMSoundDefinitionsGen(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, SullysMod.ID, existingFileHelper);
    }

    @Override
    public void registerSounds() {
        add(SMSoundEvents.BOULDERING_ZOMBIE_AMBIENT, s -> s.with(allSounds(mc("mob/zombie/say"))));
        add(SMSoundEvents.BOULDERING_ZOMBIE_HURT, s -> s.with(allSounds(mc("mob/zombie/hurt"))));
        add(SMSoundEvents.BOULDERING_ZOMBIE_DEATH, s -> s.with(sound(mc("mob/zombie/death"))));

        add(SMSoundEvents.LANTERNFISH_FLOP, s -> s.with(allSounds(mc("entity/fish/flop"))));
        add(SMSoundEvents.LANTERNFISH_HURT, s -> s.with(allSounds(mc("entity/fish/hurt"))));
        add(SMSoundEvents.LANTERNFISH_DEATH, s -> s.with(allSounds(mc("entity/fish/hurt"))));
    }

    private String mc(String path) {
        return "minecraft:" + path;
    }
}
