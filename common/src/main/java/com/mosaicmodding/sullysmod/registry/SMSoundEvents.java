package com.mosaicmodding.sullysmod.registry;

import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.sounds.SoundEvent;

import static com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils.path;

public class SMSoundEvents {

    public static final RegSupplier<SoundEvent> BOULDERING_ZOMBIE_AMBIENT = RegHelper.registerSound(path("entity.bouldering_zombie.ambient"));
    public static final RegSupplier<SoundEvent> BOULDERING_ZOMBIE_HURT = RegHelper.registerSound(path("entity.bouldering_zombie.hurt"));
    public static final RegSupplier<SoundEvent> BOULDERING_ZOMBIE_DEATH = RegHelper.registerSound(path("entity.bouldering_zombie.death"));

    public static final RegSupplier<SoundEvent> LANTERNFISH_FLOP = RegHelper.registerSound(path("entity.lanternfish.flop"));
    public static final RegSupplier<SoundEvent> LANTERNFISH_HURT = RegHelper.registerSound(path("entity.lanternfish.hurt"));
    public static final RegSupplier<SoundEvent> LANTERNFISH_DEATH = RegHelper.registerSound(path("entity.lanternfish.death"));

    public static void init() {}
}
