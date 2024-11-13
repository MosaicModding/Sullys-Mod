package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMSounds {
    public static final SoundSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getSoundSubHelper();

    //Music Discs
    public static final RegistryObject<SoundEvent> MUSIC_DISC_SCOUR = HELPER.createSoundEvent("music_disc.scour");
    public static final RegistryObject<SoundEvent> MUSIC_DISC_SUNKEN_PAST = HELPER.createSoundEvent("music_disc.sunken_past");

    //Item Sounds
    public static final RegistryObject<SoundEvent> POLISH_JADE = HELPER.createSoundEvent("block.grindstone.polish_jade");
    public static final RegistryObject<SoundEvent> VIAL_SHATTERS = HELPER.createSoundEvent("item.vial.shatter");
    public static final RegistryObject<SoundEvent> VIAL_FILLS = HELPER.createSoundEvent("item.vial.fill");
    public static final RegistryObject<SoundEvent> THROWING_KNIFE_HIT = HELPER.createSoundEvent("item.throwing_knife.hit");
    public static final RegistryObject<SoundEvent> THROWING_KNIFE_HIT_GROUND = HELPER.createSoundEvent("item.throwing_knife.hit_ground");
    public static final RegistryObject<SoundEvent> THROWING_KNIFE_THROW = HELPER.createSoundEvent("item.throwing_knife.throw");
    public static final RegistryObject<SoundEvent> BROKEN_BOTTLE_SHATTERS = HELPER.createSoundEvent("item.broken_bottle.shatter");

    //Block Sounds
    public static final RegistryObject<SoundEvent> JADE_RICOCHET = HELPER.createSoundEvent("block.jade.ricochet");
    public static final RegistryObject<SoundEvent> FLINGER_FLINGS = HELPER.createSoundEvent("block.flinger_totem.shoot");
    public static final RegistryObject<SoundEvent> FLINGER_INPUT_HONEY = HELPER.createSoundEvent("block.flinger_totem.input_honey");
    public static final RegistryObject<SoundEvent> FLINGER_ADD_HONEY = HELPER.createSoundEvent("block.flinger_totem.add_honey");
    public static final RegistryObject<SoundEvent> FLINGER_REDUCE_HONEY = HELPER.createSoundEvent("block.flinger_totem.reduce_honey");
    public static final RegistryObject<SoundEvent> PETRIFIED_WOOD_BREAK = HELPER.createSoundEvent("block.petrified_wood.break");
    public static final RegistryObject<SoundEvent> PETRIFIED_WOOD_FALL = HELPER.createSoundEvent("block.petrified_wood.fall");
    public static final RegistryObject<SoundEvent> PETRIFIED_WOOD_HIT = HELPER.createSoundEvent("block.petrified_wood.hit");
    public static final RegistryObject<SoundEvent> PETRIFIED_WOOD_PLACE = HELPER.createSoundEvent("block.petrified_wood.place");
    public static final RegistryObject<SoundEvent> PETRIFIED_WOOD_STEP = HELPER.createSoundEvent("block.petrified_wood.step");
    public static final RegistryObject<SoundEvent> COPPER_BUTTON_CLICK_OFF = HELPER.createSoundEvent("block.copper_button.click_off");
    public static final RegistryObject<SoundEvent> COPPER_BUTTON_CLICK_ON = HELPER.createSoundEvent("block.copper_button.click_on");
    public static final RegistryObject<SoundEvent> AMBER_DRIP = HELPER.createSoundEvent("block.amber.drip");

    //Note Block Instruments
    public static final RegistryObject<SoundEvent> NOTE_BLOCK_CRESTED_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.crested");
    public static final RegistryObject<SoundEvent> NOTE_BLOCK_CRACKED_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.cracked");
    public static final RegistryObject<SoundEvent> NOTE_BLOCK_FLATBILLED_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.flatbilled");
    public static final RegistryObject<SoundEvent> NOTE_BLOCK_GIGANTIC_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.gigantic");
    public static final RegistryObject<SoundEvent> NOTE_BLOCK_HORNED_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.horned");
    public static final RegistryObject<SoundEvent> NOTE_BLOCK_LONG_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.long");
    public static final RegistryObject<SoundEvent> NOTE_BLOCK_TINY_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.tiny");
    public static final RegistryObject<SoundEvent> NOTE_BLOCK_WIDE_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.wide");
    public static final RegistryObject<SoundEvent> NOTE_BLOCK_RIBBED_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.ribbed");
    public static final RegistryObject<SoundEvent> NOTE_BLOCK_UNICORN_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.unicorn");

    //Entity Sounds
    public static final RegistryObject<SoundEvent> TORTOISE_HURT = HELPER.createSoundEvent("entity.tortoise.hurt");
    public static final RegistryObject<SoundEvent> BABY_TORTOISE_HURT = HELPER.createSoundEvent("entity.tortoise.hurt_baby");
    public static final RegistryObject<SoundEvent> TORTOISE_DEATH = HELPER.createSoundEvent("entity.tortoise.death");
    public static final RegistryObject<SoundEvent> BABY_TORTOISE_DEATH = HELPER.createSoundEvent("entity.tortoise.death_baby");
    public static final RegistryObject<SoundEvent> TORTOISE_AMBIENT = HELPER.createSoundEvent("entity.tortoise.ambient");
    public static final RegistryObject<SoundEvent> TORTOISE_HIDE = HELPER.createSoundEvent("entity.tortoise.hide");
    public static final RegistryObject<SoundEvent> TORTOISE_EMERGE = HELPER.createSoundEvent("entity.tortoise.emerge");
    public static final RegistryObject<SoundEvent> TORTOISE_HURT_HIDDEN = HELPER.createSoundEvent("entity.tortoise.hurt.hidden");
    public static final RegistryObject<SoundEvent> TORTOISE_LAY_EGG = HELPER.createSoundEvent("entity.tortoise.lay_egg");
    public static final RegistryObject<SoundEvent> TORTOISE_EGG_BREAK = HELPER.createSoundEvent("entity.tortoise.egg_break");
    public static final RegistryObject<SoundEvent> TORTOISE_EGG_CRACK = HELPER.createSoundEvent("entity.tortoise.egg_crack");
    public static final RegistryObject<SoundEvent> TORTOISE_EGG_HATCH = HELPER.createSoundEvent("entity.tortoise.egg_hatch");

    public static final RegistryObject<SoundEvent> TORTOISE_SHELL_PLACE = HELPER.createSoundEvent("entity.tortoise_shell.place");

    public static final RegistryObject<SoundEvent> LANTERNFISH_FLOP = HELPER.createSoundEvent("entity.lanternfish.flop");
    public static final RegistryObject<SoundEvent> LANTERNFISH_HURT = HELPER.createSoundEvent("entity.lanternfish.hurt");
    public static final RegistryObject<SoundEvent> LANTERNFISH_DEATH = HELPER.createSoundEvent("entity.lanternfish.death");
    public static final RegistryObject<SoundEvent> PIRANHA_FLOP = HELPER.createSoundEvent("entity.piranha.flop");
    public static final RegistryObject<SoundEvent> PIRANHA_HURT = HELPER.createSoundEvent("entity.piranha.hurt");
    public static final RegistryObject<SoundEvent> PIRANHA_DEATH = HELPER.createSoundEvent("entity.piranha.death");

    public static final RegistryObject<SoundEvent> BOULDERING_ZOMBIE_AMBIENT = HELPER.createSoundEvent("entity.bouldering_zombie.ambient");
    public static final RegistryObject<SoundEvent> BOULDERING_ZOMBIE_HURT = HELPER.createSoundEvent("entity.bouldering_zombie.hurt");
    public static final RegistryObject<SoundEvent> BOULDERING_ZOMBIE_DEATH = HELPER.createSoundEvent("entity.bouldering_zombie.death");

    //Equip sounds
    public static final RegistryObject<SoundEvent> EQUIP_MINERS_HELMET = HELPER.createSoundEvent("item.armor.equip_miners_helmet");
    public static final RegistryObject<SoundEvent> EQUIP_SMALL_DENTED_HELMET = HELPER.createSoundEvent("item.armor.equip_small_dented_helmet");
    public static final RegistryObject<SoundEvent> EQUIP_LOST_CROWN = HELPER.createSoundEvent("item.armor.equip_lost_crown");

    //Ambient sounds
    public static final RegistryObject<SoundEvent> MOUNTAIN_CALLS = HELPER.createSoundEvent("ambient.mountain.calls");

    //SoundTypes
    public static final SoundType PETRIFIED_WOOD = new ForgeSoundType(1.0F, 1.0F, PETRIFIED_WOOD_BREAK, PETRIFIED_WOOD_STEP, PETRIFIED_WOOD_PLACE, PETRIFIED_WOOD_HIT, PETRIFIED_WOOD_FALL);
}
