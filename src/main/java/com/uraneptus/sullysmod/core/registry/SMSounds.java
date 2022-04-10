package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMSounds {
    public static final SoundSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getSoundSubHelper();

    public static final RegistryObject<SoundEvent> POLISH_JADE = HELPER.createSoundEvent("block.grindstone.polish_jade");
}
