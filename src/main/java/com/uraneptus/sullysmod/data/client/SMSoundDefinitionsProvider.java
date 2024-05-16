package com.uraneptus.sullysmod.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public class SMSoundDefinitionsProvider extends SoundDefinitionsProvider {

    public SMSoundDefinitionsProvider(PackOutput packOutput, ExistingFileHelper helper) {
        super(packOutput, SullysMod.MOD_ID, helper);
    }

    //TODO: make the amount of sounds generated
    @Override
    public void registerSounds() {

        //Music Discs
        addMusicDiscSound(SMSounds.MUSIC_DISC_SCOUR, "scour");
        addMusicDiscSound(SMSounds.MUSIC_DISC_SUNKEN_PAST, "sunken_past");

        //Item Sounds
        this.addBasicSound(SMSounds.VIAL_SHATTERS,
                sound("random/glass1").pitch(1.3F),
                sound("random/glass2").pitch(1.3F),
                sound("random/glass3").pitch(1.3F)
        );
        this.addBasicSound(SMSounds.VIAL_FILLS,
                sound("item/bottle/fill1").pitch(1.3F),
                sound("item/bottle/fill2").pitch(1.3F),
                sound("item/bottle/fill3").pitch(1.3F),
                sound("item/bottle/fill4").pitch(1.3F)
        );
        this.addBasicSound(SMSounds.THROWING_KNIFE_HIT,
                sound(SullysMod.modPrefix("item/throwing_knife/hit1")),
                sound(SullysMod.modPrefix("item/throwing_knife/hit2")),
                sound(SullysMod.modPrefix("item/throwing_knife/hit3"))
        );
        this.addBasicSound(SMSounds.THROWING_KNIFE_HIT_GROUND,
                sound(SullysMod.modPrefix("item/throwing_knife/hit1")),
                sound(SullysMod.modPrefix("item/throwing_knife/hit2")),
                sound(SullysMod.modPrefix("item/throwing_knife/hit3"))
        );
        this.addBasicSound(SMSounds.THROWING_KNIFE_THROW,
                sound(SullysMod.modPrefix("item/throwing_knife/throw1")),
                sound(SullysMod.modPrefix("item/throwing_knife/throw2"))
        );

        //Block Sounds
        this.addBasicSound(SMSounds.POLISH_JADE,
                sound(SullysMod.modPrefix("block/grindstone/jade_polish0")),
                sound(SullysMod.modPrefix("block/grindstone/jade_polish1"))
        );

        this.addBasicSound(SMSounds.JADE_RICOCHET,
                sound("block/end_portal/eyeplace1"),
                sound("block/end_portal/eyeplace2"),
                sound("block/end_portal/eyeplace3")
        );

        this.addBasicSound(SMSounds.FLINGER_FLINGS,
                sound(SullysMod.modPrefix("block/flinger_totem/flinger_fling1")),
                sound(SullysMod.modPrefix("block/flinger_totem/flinger_fling3")),
                sound(SullysMod.modPrefix("block/flinger_totem/flinger_fling3"))
        );

        this.addBasicSound(SMSounds.FLINGER_INPUT_HONEY,
                sound(SullysMod.modPrefix("block/flinger_totem/flinger_input_honey"))
        );

        this.addBasicSound(SMSounds.FLINGER_ADD_HONEY,
                sound("item/honeycomb/wax_on1"),
                sound("item/honeycomb/wax_on2"),
                sound("item/honeycomb/wax_on3")
        );

        this.addBasicSound(SMSounds.FLINGER_REDUCE_HONEY,
                sound("block/beehive/shear")
        );

        this.addBasicSound(SMSounds.PETRIFIED_WOOD_BREAK, "block.generic.break",
                sound(SullysMod.modPrefix("block/petrified_wood/break1")),
                sound(SullysMod.modPrefix("block/petrified_wood/break2")),
                sound(SullysMod.modPrefix("block/petrified_wood/break3")),
                sound(SullysMod.modPrefix("block/petrified_wood/break4"))
        );

        this.addBasicSound(SMSounds.PETRIFIED_WOOD_FALL, "block.generic.fall",
                sound(SullysMod.modPrefix("block/petrified_wood/step1")),
                sound(SullysMod.modPrefix("block/petrified_wood/step2")),
                sound(SullysMod.modPrefix("block/petrified_wood/step3")),
                sound(SullysMod.modPrefix("block/petrified_wood/step4")),
                sound(SullysMod.modPrefix("block/petrified_wood/step5")),
                sound(SullysMod.modPrefix("block/petrified_wood/step6"))
        );

        this.addBasicSound(SMSounds.PETRIFIED_WOOD_HIT, "block.generic.hit",
                sound(SullysMod.modPrefix("block/petrified_wood/step1")),
                sound(SullysMod.modPrefix("block/petrified_wood/step2")),
                sound(SullysMod.modPrefix("block/petrified_wood/step3")),
                sound(SullysMod.modPrefix("block/petrified_wood/step4")),
                sound(SullysMod.modPrefix("block/petrified_wood/step5")),
                sound(SullysMod.modPrefix("block/petrified_wood/step6"))
        );

        this.addBasicSound(SMSounds.PETRIFIED_WOOD_PLACE, "block.generic.place",
                sound(SullysMod.modPrefix("block/petrified_wood/place1")),
                sound(SullysMod.modPrefix("block/petrified_wood/place2")),
                sound(SullysMod.modPrefix("block/petrified_wood/place3")),
                sound(SullysMod.modPrefix("block/petrified_wood/place4"))
        );

        this.addBasicSound(SMSounds.PETRIFIED_WOOD_STEP, "block.generic.footsteps",
                sound(SullysMod.modPrefix("block/petrified_wood/step1")),
                sound(SullysMod.modPrefix("block/petrified_wood/step2")),
                sound(SullysMod.modPrefix("block/petrified_wood/step3")),
                sound(SullysMod.modPrefix("block/petrified_wood/step4")),
                sound(SullysMod.modPrefix("block/petrified_wood/step5")),
                sound(SullysMod.modPrefix("block/petrified_wood/step6"))
        );

        this.addBasicSound(SMSounds.COPPER_BUTTON_CLICK_OFF, "block.button.click",
                sound(SullysMod.modPrefix("block/copper/copper_click")).pitch(0.5)
        );

        this.addBasicSound(SMSounds.COPPER_BUTTON_CLICK_ON, "block.button.click",
                sound(SullysMod.modPrefix("block/copper/copper_click")).pitch(0.6)
        );

        //Note Block Instruments
        this.addBasicSound(SMSounds.NOTE_BLOCK_CRESTED_SKULL, "block.ancient_skull.crested",
                sound(SullysMod.modPrefix("block/note_block/ancient_skull/crested1")),
                sound(SullysMod.modPrefix("block/note_block/ancient_skull/crested2")),
                sound(SullysMod.modPrefix("block/note_block/ancient_skull/crested3"))
        );

        this.addBasicSound(SMSounds.NOTE_BLOCK_CRACKED_SKULL, "block.ancient_skull.cracked",
                sound("entity.skeleton.ambient", SoundDefinition.SoundType.EVENT)
        );

        this.addBasicSound(SMSounds.NOTE_BLOCK_FLATBILLED_SKULL, "block.ancient_skull.flatbilled",
                sound("entity.skeleton.ambient", SoundDefinition.SoundType.EVENT)
        );

        this.addBasicSound(SMSounds.NOTE_BLOCK_GIGANTIC_SKULL, "block.ancient_skull.gigantic",
                sound("entity.skeleton.ambient", SoundDefinition.SoundType.EVENT)
        );

        this.addBasicSound(SMSounds.NOTE_BLOCK_HORNED_SKULL, "block.ancient_skull.horned",
                sound("entity.skeleton.ambient", SoundDefinition.SoundType.EVENT)
        );

        this.addBasicSound(SMSounds.NOTE_BLOCK_LONG_SKULL, "block.ancient_skull.long",
                sound("entity.skeleton.ambient", SoundDefinition.SoundType.EVENT)
        );

        this.addBasicSound(SMSounds.NOTE_BLOCK_TINY_SKULL, "block.ancient_skull.tiny",
                sound("entity.skeleton.ambient", SoundDefinition.SoundType.EVENT)
        );

        this.addBasicSound(SMSounds.NOTE_BLOCK_WIDE_SKULL, "block.ancient_skull.wide",
                sound("entity.skeleton.ambient", SoundDefinition.SoundType.EVENT)
        );

        //Entity Sounds
        this.addBasicSound(SMSounds.TORTOISE_AMBIENT,
                sound("mob/turtle/idle1").volume(0.8F),
                sound("mob/turtle/idle2").volume(0.7F),
                sound("mob/turtle/idle3").volume(0.8F)
        );

        this.addBasicSound(SMSounds.TORTOISE_DEATH,
                sound("mob/turtle/death1"),
                sound("mob/turtle/death2"),
                sound("mob/turtle/death3")
        );

        this.addBasicSound(SMSounds.TORTOISE_HURT,
                sound("mob/turtle/hurt1"),
                sound("mob/turtle/hurt2"),
                sound("mob/turtle/hurt3"),
                sound("mob/turtle/hurt4"),
                sound("mob/turtle/hurt5")
        );

        this.addBasicSound(SMSounds.TORTOISE_HIDE,
                sound(new ResourceLocation("entity/shulker/close1")),
                sound(new ResourceLocation("entity/shulker/close2")),
                sound(new ResourceLocation("entity/shulker/close3")),
                sound(new ResourceLocation("entity/shulker/close4")),
                sound(new ResourceLocation("entity/shulker/close5"))
        );

        this.addBasicSound(SMSounds.TORTOISE_EMERGE,
                sound(new ResourceLocation("entity/shulker/open1")),
                sound(new ResourceLocation("entity/shulker/open2")),
                sound(new ResourceLocation("entity/shulker/open3")),
                sound(new ResourceLocation("entity/shulker/open4")),
                sound(new ResourceLocation("entity/shulker/open5"))
        );

        this.addBasicSound(SMSounds.TORTOISE_HURT_HIDDEN,
                sound(new ResourceLocation("entity/shulker/hurt_closed1")),
                sound(new ResourceLocation("entity/shulker/hurt_closed2")),
                sound(new ResourceLocation("entity/shulker/hurt_closed3")),
                sound(new ResourceLocation("entity/shulker/hurt_closed4")),
                sound(new ResourceLocation("entity/shulker/hurt_closed5"))
        );

        this.addBasicSound(SMSounds.BABY_TORTOISE_HURT,
                sound(new ResourceLocation("mob/turtle/baby/hurt1")),
                sound(new ResourceLocation("mob/turtle/baby/hurt2"))
        );

        this.addBasicSound(SMSounds.BABY_TORTOISE_DEATH,
                sound(new ResourceLocation("mob/turtle/baby/death1")),
                sound(new ResourceLocation("mob/turtle/baby/death2"))
        );

        this.addBasicSound(SMSounds.TORTOISE_LAY_EGG,
                sound(new ResourceLocation("mob/turtle/egg/drop_egg1")),
                sound(new ResourceLocation("mob/turtle/egg/drop_egg2"))
        );

        this.addBasicSound(SMSounds.TORTOISE_EGG_BREAK,
                sound(new ResourceLocation("mob/turtle/egg/egg_break1")),
                sound(new ResourceLocation("mob/turtle/egg/egg_break2"))
        );

        this.addBasicSound(SMSounds.TORTOISE_EGG_CRACK,
                sound(new ResourceLocation("mob/turtle/egg/egg_crack1")),
                sound(new ResourceLocation("mob/turtle/egg/egg_crack2")),
                sound(new ResourceLocation("mob/turtle/egg/egg_crack3")),
                sound(new ResourceLocation("mob/turtle/egg/egg_crack4")),
                sound(new ResourceLocation("mob/turtle/egg/egg_crack5"))
        );

        this.addBasicSound(SMSounds.TORTOISE_EGG_HATCH,
                sound(new ResourceLocation("mob/turtle/baby/egg_hatched1")),
                sound(new ResourceLocation("mob/turtle/baby/egg_hatched2")),
                sound(new ResourceLocation("mob/turtle/baby/egg_hatched3"))
        );

        this.addBasicSound(SMSounds.TORTOISE_SHELL_PLACE,
                sound(new ResourceLocation("random/bow")));

        this.addBasicSound(SMSounds.LANTERNFISH_FLOP,
                sound(new ResourceLocation("entity/fish/flop1")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop2")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop3")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop4")).volume(0.3)
        );

        this.addBasicSound(SMSounds.LANTERNFISH_DEATH,
                sound(new ResourceLocation("entity/fish/hurt1")),
                sound(new ResourceLocation("entity/fish/hurt2")),
                sound(new ResourceLocation("entity/fish/hurt3")),
                sound(new ResourceLocation("entity/fish/hurt4"))
        );

        this.addBasicSound(SMSounds.LANTERNFISH_HURT,
                sound(new ResourceLocation("entity/fish/hurt1")),
                sound(new ResourceLocation("entity/fish/hurt2")),
                sound(new ResourceLocation("entity/fish/hurt3")),
                sound(new ResourceLocation("entity/fish/hurt4"))
        );

        this.addBasicSound(SMSounds.PIRANHA_FLOP,
                sound(new ResourceLocation("entity/fish/flop1")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop2")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop3")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop4")).volume(0.3)
        );

        this.addBasicSound(SMSounds.PIRANHA_DEATH,
                sound(new ResourceLocation("entity/fish/hurt1")),
                sound(new ResourceLocation("entity/fish/hurt2")),
                sound(new ResourceLocation("entity/fish/hurt3")),
                sound(new ResourceLocation("entity/fish/hurt4"))
        );

        this.addBasicSound(SMSounds.PIRANHA_HURT,
                sound(new ResourceLocation("entity/fish/hurt1")),
                sound(new ResourceLocation("entity/fish/hurt2")),
                sound(new ResourceLocation("entity/fish/hurt3")),
                sound(new ResourceLocation("entity/fish/hurt4"))
        );

        this.addBasicSound(SMSounds.BOULDERING_ZOMBIE_AMBIENT,
                sound(new ResourceLocation("mob/zombie/say1")),
                sound(new ResourceLocation("mob/zombie/say2")),
                sound(new ResourceLocation("mob/zombie/say3"))
        );

        this.addBasicSound(SMSounds.BOULDERING_ZOMBIE_HURT,
                sound(new ResourceLocation("mob/zombie/hurt1")),
                sound(new ResourceLocation("mob/zombie/hurt2"))
        );

        this.addBasicSound(SMSounds.BOULDERING_ZOMBIE_DEATH,
                sound(new ResourceLocation("mob/zombie/death"))
        );
        this.addBasicSound(SMSounds.MOUNTAIN_CALLS, "ambient.mountain.calls",
                sound(SullysMod.modPrefix("ambient/mountain/call1")),
                sound(SullysMod.modPrefix("ambient/mountain/call2")),
                sound(SullysMod.modPrefix("ambient/mountain/call3")),
                sound(SullysMod.modPrefix("ambient/mountain/call4")),
                sound(SullysMod.modPrefix("ambient/mountain/call5"))
        );
    }

    private void addBasicSound(Supplier<SoundEvent> soundEvent, String subtitle, SoundDefinition.Sound... sounds) {
        this.add(soundEvent.get(), SoundDefinition.definition().subtitle("subtitles." + subtitle).with(sounds));
    }

    private void addBasicSound(Supplier<SoundEvent> soundEvent, SoundDefinition.Sound... sounds){
        this.addBasicSound(soundEvent, soundEvent.get().getLocation().getPath(), sounds);
    }

    private void addMusicDiscSound(Supplier<SoundEvent> soundEvent, String name) {
        this.add(soundEvent.get(), SoundDefinition.definition().with(sound(SullysMod.modPrefix("records/" + name)).stream()));
    }
}
