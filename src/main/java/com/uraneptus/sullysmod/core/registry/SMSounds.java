package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.common.util.DeferredSoundType;

import java.util.function.Supplier;


public class SMSounds {
    public static final SoundSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getSoundSubHelper();

    //Music Discs
    public static final Supplier<SoundEvent> MUSIC_DISC_SCOUR = HELPER.createSoundEvent("music_disc.scour");
    public static final Supplier<SoundEvent> MUSIC_DISC_SUNKEN_PAST = HELPER.createSoundEvent("music_disc.sunken_past");

    //Item Sounds
    public static final Supplier<SoundEvent> POLISH_JADE = HELPER.createSoundEvent("block.grindstone.polish_jade");
    public static final Supplier<SoundEvent> VIAL_SHATTERS = HELPER.createSoundEvent("item.vial.shatter");
    public static final Supplier<SoundEvent> VIAL_FILLS = HELPER.createSoundEvent("item.vial.fill");
    public static final Supplier<SoundEvent> THROWING_KNIFE_HIT = HELPER.createSoundEvent("item.throwing_knife.hit");
    public static final Supplier<SoundEvent> THROWING_KNIFE_HIT_GROUND = HELPER.createSoundEvent("item.throwing_knife.hit_ground");
    public static final Supplier<SoundEvent> THROWING_KNIFE_THROW = HELPER.createSoundEvent("item.throwing_knife.throw");
    public static final Supplier<SoundEvent> BROKEN_BOTTLE_SHATTERS = HELPER.createSoundEvent("item.broken_bottle.shatter");
    public static final Supplier<SoundEvent> JADE_SHIELD_RICOCHET = HELPER.createSoundEvent("item.jade_shield.ricochet");

    //Block Sounds
    public static final Supplier<SoundEvent> JADE_RICOCHET = HELPER.createSoundEvent("block.jade.ricochet");
    public static final Supplier<SoundEvent> FLINGER_FLINGS = HELPER.createSoundEvent("block.flinger_totem.shoot");
    public static final Supplier<SoundEvent> FLINGER_PLACE = HELPER.createSoundEvent("block.flinger_totem.place");
    public static final Supplier<SoundEvent> FLINGER_BREAK = HELPER.createSoundEvent("block.flinger_totem.break");
    public static final Supplier<SoundEvent> FLINGER_INPUT_HONEY = HELPER.createSoundEvent("block.flinger_totem.input_honey");
    public static final Supplier<SoundEvent> FLINGER_ADD_HONEY = HELPER.createSoundEvent("block.flinger_totem.add_honey");
    public static final Supplier<SoundEvent> FLINGER_REDUCE_HONEY = HELPER.createSoundEvent("block.flinger_totem.reduce_honey");
    public static final Supplier<SoundEvent> PETRIFIED_WOOD_BREAK = HELPER.createSoundEvent("block.petrified_wood.break");
    public static final Supplier<SoundEvent> PETRIFIED_WOOD_FALL = HELPER.createSoundEvent("block.petrified_wood.fall");
    public static final Supplier<SoundEvent> PETRIFIED_WOOD_HIT = HELPER.createSoundEvent("block.petrified_wood.hit");
    public static final Supplier<SoundEvent> PETRIFIED_WOOD_PLACE = HELPER.createSoundEvent("block.petrified_wood.place");
    public static final Supplier<SoundEvent> PETRIFIED_WOOD_STEP = HELPER.createSoundEvent("block.petrified_wood.step");

    public static final Supplier<SoundEvent> JADE_BREAK = HELPER.createSoundEvent("block.jade.break");
    public static final Supplier<SoundEvent> JADE_FALL = HELPER.createSoundEvent("block.jade.fall");
    public static final Supplier<SoundEvent> JADE_HIT = HELPER.createSoundEvent("block.jade.hit");
    public static final Supplier<SoundEvent> JADE_PLACE = HELPER.createSoundEvent("block.jade.place");
    public static final Supplier<SoundEvent> JADE_STEP = HELPER.createSoundEvent("block.jade.step");

    public static final Supplier<SoundEvent> ROUGH_JADE_BREAK = HELPER.createSoundEvent("block.rough_jade.break");
    public static final Supplier<SoundEvent> ROUGH_JADE_FALL = HELPER.createSoundEvent("block.rough_jade.fall");
    public static final Supplier<SoundEvent> ROUGH_JADE_HIT = HELPER.createSoundEvent("block.rough_jade.hit");
    public static final Supplier<SoundEvent> ROUGH_JADE_PLACE = HELPER.createSoundEvent("block.rough_jade.place");
    public static final Supplier<SoundEvent> ROUGH_JADE_STEP = HELPER.createSoundEvent("block.rough_jade.step");

    public static final Supplier<SoundEvent> JADE_ORE_BREAK = HELPER.createSoundEvent("block.jade_ore.break");
    public static final Supplier<SoundEvent> DEEPSLATE_JADE_ORE_BREAK = HELPER.createSoundEvent("block.deepslate_jade_ore.break");

    public static final Supplier<SoundEvent> COPPER_BUTTON_CLICK_OFF = HELPER.createSoundEvent("block.copper_button.click_off");
    public static final Supplier<SoundEvent> COPPER_BUTTON_CLICK_ON = HELPER.createSoundEvent("block.copper_button.click_on");
    public static final Supplier<SoundEvent> AMBER_DRIP = HELPER.createSoundEvent("block.amber.drip");

    //Note Block Instruments
    public static final Supplier<SoundEvent> NOTE_BLOCK_CRESTED_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.crested");
    public static final Supplier<SoundEvent> NOTE_BLOCK_CRACKED_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.cracked");
    public static final Supplier<SoundEvent> NOTE_BLOCK_FLATBILLED_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.flatbilled");
    public static final Supplier<SoundEvent> NOTE_BLOCK_GIGANTIC_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.gigantic");
    public static final Supplier<SoundEvent> NOTE_BLOCK_HORNED_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.horned");
    public static final Supplier<SoundEvent> NOTE_BLOCK_LONG_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.long");
    public static final Supplier<SoundEvent> NOTE_BLOCK_TINY_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.tiny");
    public static final Supplier<SoundEvent> NOTE_BLOCK_WIDE_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.wide");
    public static final Supplier<SoundEvent> NOTE_BLOCK_RIBBED_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.ribbed");
    public static final Supplier<SoundEvent> NOTE_BLOCK_UNICORN_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.unicorn");
    public static final Supplier<SoundEvent> NOTE_BLOCK_SNOUTED_SKULL = HELPER.createSoundEvent("block.note_block.ancient_skull.snouted");

    //Entity Sounds
    public static final Supplier<SoundEvent> TORTOISE_HURT = HELPER.createSoundEvent("entity.tortoise.hurt");
    public static final Supplier<SoundEvent> BABY_TORTOISE_HURT = HELPER.createSoundEvent("entity.tortoise.hurt_baby");
    public static final Supplier<SoundEvent> TORTOISE_DEATH = HELPER.createSoundEvent("entity.tortoise.death");
    public static final Supplier<SoundEvent> BABY_TORTOISE_DEATH = HELPER.createSoundEvent("entity.tortoise.death_baby");
    public static final Supplier<SoundEvent> TORTOISE_AMBIENT = HELPER.createSoundEvent("entity.tortoise.ambient");
    public static final Supplier<SoundEvent> TORTOISE_HIDE = HELPER.createSoundEvent("entity.tortoise.hide");
    public static final Supplier<SoundEvent> TORTOISE_EMERGE = HELPER.createSoundEvent("entity.tortoise.emerge");
    public static final Supplier<SoundEvent> TORTOISE_HURT_HIDDEN = HELPER.createSoundEvent("entity.tortoise.hurt.hidden");
    public static final Supplier<SoundEvent> TORTOISE_LAY_EGG = HELPER.createSoundEvent("entity.tortoise.lay_egg");
    public static final Supplier<SoundEvent> TORTOISE_EGG_BREAK = HELPER.createSoundEvent("entity.tortoise.egg_break");
    public static final Supplier<SoundEvent> TORTOISE_EGG_CRACK = HELPER.createSoundEvent("entity.tortoise.egg_crack");
    public static final Supplier<SoundEvent> TORTOISE_EGG_HATCH = HELPER.createSoundEvent("entity.tortoise.egg_hatch");

    public static final Supplier<SoundEvent> TORTOISE_SHELL_PLACE = HELPER.createSoundEvent("entity.tortoise_shell.place");

    public static final Supplier<SoundEvent> LANTERNFISH_FLOP = HELPER.createSoundEvent("entity.lanternfish.flop");
    public static final Supplier<SoundEvent> LANTERNFISH_HURT = HELPER.createSoundEvent("entity.lanternfish.hurt");
    public static final Supplier<SoundEvent> LANTERNFISH_DEATH = HELPER.createSoundEvent("entity.lanternfish.death");
    public static final Supplier<SoundEvent> PIRANHA_FLOP = HELPER.createSoundEvent("entity.piranha.flop");
    public static final Supplier<SoundEvent> PIRANHA_HURT = HELPER.createSoundEvent("entity.piranha.hurt");
    public static final Supplier<SoundEvent> PIRANHA_DEATH = HELPER.createSoundEvent("entity.piranha.death");

    public static final Supplier<SoundEvent> BOULDERING_ZOMBIE_AMBIENT = HELPER.createSoundEvent("entity.bouldering_zombie.ambient");
    public static final Supplier<SoundEvent> BOULDERING_ZOMBIE_HURT = HELPER.createSoundEvent("entity.bouldering_zombie.hurt");
    public static final Supplier<SoundEvent> BOULDERING_ZOMBIE_DEATH = HELPER.createSoundEvent("entity.bouldering_zombie.death");

    public static final Supplier<SoundEvent> MAULED_AMBIENT = HELPER.createSoundEvent("entity.mauled.ambient");
    public static final Supplier<SoundEvent> MAULED_HURT = HELPER.createSoundEvent("entity.mauled.hurt");
    public static final Supplier<SoundEvent> MAULED_DEATH = HELPER.createSoundEvent("entity.mauled.death");
    public static final Supplier<SoundEvent> MAULED_STEP = HELPER.createSoundEvent("entity.mauled.step");
    public static final Supplier<SoundEvent> MAULED_SHED = HELPER.createSoundEvent("entity.mauled.shed");

    //Equip sounds
    public static final Holder<SoundEvent> EQUIP_MINERS_HELMET = HELPER.createSoundEvent("item.armor.equip_miners_helmet");
    public static final Holder<SoundEvent> EQUIP_SMALL_DENTED_HELMET = HELPER.createSoundEvent("item.armor.equip_small_dented_helmet");
    public static final Holder<SoundEvent> EQUIP_LOST_CROWN = HELPER.createSoundEvent("item.armor.equip_lost_crown");
    public static final Holder<SoundEvent> EQUIP_STONE_MASK = HELPER.createSoundEvent("item.armor.equip_stone_mask");

    //Ambient sounds
    public static final Supplier<SoundEvent> MOUNTAIN_CALLS = HELPER.createSoundEvent("ambient.mountain.calls");

    //SoundTypes
    public static final SoundType PETRIFIED_WOOD = new DeferredSoundType(1.0F, 1.0F, PETRIFIED_WOOD_BREAK, PETRIFIED_WOOD_STEP, PETRIFIED_WOOD_PLACE, PETRIFIED_WOOD_HIT, PETRIFIED_WOOD_FALL);
    public static final SoundType JADE = new DeferredSoundType(1.0F, 1.0F, JADE_BREAK, JADE_STEP, JADE_PLACE, JADE_HIT, JADE_FALL);
    public static final SoundType FLINGER_TOTEM = new DeferredSoundType(1.0F, 1.0F, FLINGER_BREAK, JADE_STEP, FLINGER_PLACE, JADE_HIT, JADE_FALL);
    public static final SoundType ROUGH_JADE = new DeferredSoundType(1.0F, 1.0F, ROUGH_JADE_BREAK, ROUGH_JADE_STEP, ROUGH_JADE_PLACE, ROUGH_JADE_HIT, ROUGH_JADE_FALL);
    public static final SoundType JADE_ORE = new DeferredSoundType(1.0F, 1.0F,  JADE_ORE_BREAK, () -> SoundEvents.STONE_STEP, () -> SoundEvents.STONE_PLACE, () -> SoundEvents.STONE_HIT, () -> SoundEvents.STONE_FALL);
    public static final SoundType DEEPSLATE_JADE_ORE = new DeferredSoundType(1.0F, 1.0F,  DEEPSLATE_JADE_ORE_BREAK, () -> SoundEvents.DEEPSLATE_STEP, () -> SoundEvents.DEEPSLATE_PLACE, () -> SoundEvents.DEEPSLATE_HIT, () -> SoundEvents.DEEPSLATE_FALL);
}
