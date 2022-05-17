package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMSounds {
    public static final SoundSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getSoundSubHelper();

    //Item Sounds
    public static final RegistryObject<SoundEvent> POLISH_JADE = HELPER.createSoundEvent("block.grindstone.polish_jade");

    //Entity Sounds
    public static final RegistryObject<SoundEvent> TORTOISE_HURT = HELPER.createSoundEvent("entity.tortoise.hurt");
    public static final RegistryObject<SoundEvent> TORTOISE_DEATH = HELPER.createSoundEvent("entity.tortoise.death");
    public static final RegistryObject<SoundEvent> TORTOISE_AMBIENT = HELPER.createSoundEvent("entity.tortoise.ambient");
    public static final RegistryObject<SoundEvent> TORTOISE_HIDE = HELPER.createSoundEvent("entity.tortoise.hide");
    public static final RegistryObject<SoundEvent> TORTOISE_EMERGE = HELPER.createSoundEvent("entity.tortoise.emerge");
    public static final RegistryObject<SoundEvent> TORTOISE_HURT_HIDDEN = HELPER.createSoundEvent("entity.tortoise.hurt.hidden");

    public static final RegistryObject<SoundEvent> LANTERNFISH_FLOP = HELPER.createSoundEvent("entity.lanternfish.flop");
    public static final RegistryObject<SoundEvent> LANTERNFISH_HURT = HELPER.createSoundEvent("entity.lanternfish.hurt");
    public static final RegistryObject<SoundEvent> LANTERNFISH_DEATH = HELPER.createSoundEvent("entity.lanternfish.death");
}
