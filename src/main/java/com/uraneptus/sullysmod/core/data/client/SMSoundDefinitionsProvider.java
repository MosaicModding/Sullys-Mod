package com.uraneptus.sullysmod.core.data.client;

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

        //Item Sounds
        addbasicSound(SMSounds.VIAL_SHATTERS,
                sound("random/glass1").pitch(1.3F),
                sound("random/glass2").pitch(1.3F),
                sound("random/glass3").pitch(1.3F)
        );
        addbasicSound(SMSounds.VIAL_FILLS,
                sound("item/bottle/fill1").pitch(1.3F),
                sound("item/bottle/fill2").pitch(1.3F),
                sound("item/bottle/fill3").pitch(1.3F),
                sound("item/bottle/fill4").pitch(1.3F)
        );

        //Block Sounds
        addbasicSound(SMSounds.POLISH_JADE,
                sound(SullysMod.modPrefix("block/grindstone/jade_polish0")),
                sound(SullysMod.modPrefix("block/grindstone/jade_polish1"))
        );

        addbasicSound(SMSounds.JADE_RICOCHET,
                sound("block/end_portal/eyeplace1"),
                sound("block/end_portal/eyeplace2"),
                sound("block/end_portal/eyeplace3")
        );

        addbasicSound(SMSounds.FLINGER_FLINGS,
                sound(SullysMod.modPrefix("block/flinger_totem/flinger_fling1")),
                sound(SullysMod.modPrefix("block/flinger_totem/flinger_fling3")),
                sound(SullysMod.modPrefix("block/flinger_totem/flinger_fling3"))
        );

        addbasicSound(SMSounds.FLINGER_INPUT_HONEY,
                sound(SullysMod.modPrefix("block/flinger_totem/flinger_input_honey"))
        );

        addbasicSound(SMSounds.FLINGER_ADD_HONEY,
                sound("item/honeycomb/wax_on1"),
                sound("item/honeycomb/wax_on2"),
                sound("item/honeycomb/wax_on3")
        );

        addbasicSound(SMSounds.FLINGER_REDUCE_HONEY,
                sound("block/beehive/shear")
        );

        //Entity Sounds
        addbasicSound(SMSounds.TORTOISE_AMBIENT,
                sound("mob/turtle/idle1").volume(0.8F),
                sound("mob/turtle/idle2").volume(0.7F),
                sound("mob/turtle/idle3").volume(0.8F)
        );

        addbasicSound(SMSounds.TORTOISE_DEATH,
                sound("mob/turtle/death1"),
                sound("mob/turtle/death2"),
                sound("mob/turtle/death3")
        );

        addbasicSound(SMSounds.TORTOISE_HURT,
                sound("mob/turtle/hurt1"),
                sound("mob/turtle/hurt2"),
                sound("mob/turtle/hurt3"),
                sound("mob/turtle/hurt4"),
                sound("mob/turtle/hurt5")
        );

        addbasicSound(SMSounds.TORTOISE_HIDE,
                sound(new ResourceLocation("entity/shulker/close1")),
                sound(new ResourceLocation("entity/shulker/close2")),
                sound(new ResourceLocation("entity/shulker/close3")),
                sound(new ResourceLocation("entity/shulker/close4")),
                sound(new ResourceLocation("entity/shulker/close5"))
        );

        addbasicSound(SMSounds.TORTOISE_EMERGE,
                sound(new ResourceLocation("entity/shulker/open1")),
                sound(new ResourceLocation("entity/shulker/open2")),
                sound(new ResourceLocation("entity/shulker/open3")),
                sound(new ResourceLocation("entity/shulker/open4")),
                sound(new ResourceLocation("entity/shulker/open5"))
        );

        addbasicSound(SMSounds.TORTOISE_HURT_HIDDEN,
                sound(new ResourceLocation("entity/shulker/hurt_closed1")),
                sound(new ResourceLocation("entity/shulker/hurt_closed2")),
                sound(new ResourceLocation("entity/shulker/hurt_closed3")),
                sound(new ResourceLocation("entity/shulker/hurt_closed4")),
                sound(new ResourceLocation("entity/shulker/hurt_closed5"))
        );

        addbasicSound(SMSounds.BABY_TORTOISE_HURT,
                sound(new ResourceLocation("mob/turtle/baby/hurt1")),
                sound(new ResourceLocation("mob/turtle/baby/hurt2"))
        );

        addbasicSound(SMSounds.BABY_TORTOISE_DEATH,
                sound(new ResourceLocation("mob/turtle/baby/death1")),
                sound(new ResourceLocation("mob/turtle/baby/death2"))
        );

        addbasicSound(SMSounds.TORTOISE_LAY_EGG,
                sound(new ResourceLocation("mob/turtle/egg/drop_egg1")),
                sound(new ResourceLocation("mob/turtle/egg/drop_egg2"))
        );

        addbasicSound(SMSounds.TORTOISE_EGG_BREAK,
                sound(new ResourceLocation("mob/turtle/egg/egg_break1")),
                sound(new ResourceLocation("mob/turtle/egg/egg_break2"))
        );

        addbasicSound(SMSounds.TORTOISE_EGG_CRACK,
                sound(new ResourceLocation("mob/turtle/egg/egg_crack1")),
                sound(new ResourceLocation("mob/turtle/egg/egg_crack2")),
                sound(new ResourceLocation("mob/turtle/egg/egg_crack3")),
                sound(new ResourceLocation("mob/turtle/egg/egg_crack4")),
                sound(new ResourceLocation("mob/turtle/egg/egg_crack5"))
        );

        addbasicSound(SMSounds.TORTOISE_EGG_HATCH,
                sound(new ResourceLocation("mob/turtle/baby/egg_hatched1")),
                sound(new ResourceLocation("mob/turtle/baby/egg_hatched2")),
                sound(new ResourceLocation("mob/turtle/baby/egg_hatched3"))
        );

        addbasicSound(SMSounds.TORTOISE_SHELL_PLACE,
                sound(new ResourceLocation("random/bow")));

        addbasicSound(SMSounds.LANTERNFISH_FLOP,
                sound(new ResourceLocation("entity/fish/flop1")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop2")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop3")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop4")).volume(0.3)
        );

        addbasicSound(SMSounds.LANTERNFISH_DEATH,
                sound(new ResourceLocation("entity/fish/hurt1")),
                sound(new ResourceLocation("entity/fish/hurt2")),
                sound(new ResourceLocation("entity/fish/hurt3")),
                sound(new ResourceLocation("entity/fish/hurt4"))
        );

        addbasicSound(SMSounds.LANTERNFISH_HURT,
                sound(new ResourceLocation("entity/fish/hurt1")),
                sound(new ResourceLocation("entity/fish/hurt2")),
                sound(new ResourceLocation("entity/fish/hurt3")),
                sound(new ResourceLocation("entity/fish/hurt4"))
        );

        addbasicSound(SMSounds.PIRANHA_FLOP,
                sound(new ResourceLocation("entity/fish/flop1")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop2")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop3")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop4")).volume(0.3)
        );

        addbasicSound(SMSounds.PIRANHA_DEATH,
                sound(new ResourceLocation("entity/fish/hurt1")),
                sound(new ResourceLocation("entity/fish/hurt2")),
                sound(new ResourceLocation("entity/fish/hurt3")),
                sound(new ResourceLocation("entity/fish/hurt4"))
        );

        addbasicSound(SMSounds.PIRANHA_HURT,
                sound(new ResourceLocation("entity/fish/hurt1")),
                sound(new ResourceLocation("entity/fish/hurt2")),
                sound(new ResourceLocation("entity/fish/hurt3")),
                sound(new ResourceLocation("entity/fish/hurt4"))
        );

        addbasicSound(SMSounds.BOULDERING_ZOMBIE_AMBIENT,
                sound(new ResourceLocation("mob/zombie/say1")),
                sound(new ResourceLocation("mob/zombie/say2")),
                sound(new ResourceLocation("mob/zombie/say3"))
        );

        addbasicSound(SMSounds.BOULDERING_ZOMBIE_HURT,
                sound(new ResourceLocation("mob/zombie/hurt1")),
                sound(new ResourceLocation("mob/zombie/hurt2"))
        );

        addbasicSound(SMSounds.BOULDERING_ZOMBIE_DEATH,
                sound(new ResourceLocation("mob/zombie/death"))
        );
    }

    private void addbasicSound(Supplier<SoundEvent> soundEvent, SoundDefinition.Sound... sounds) {
        this.add(soundEvent.get(), SoundDefinition.definition().subtitle("subtitles." + soundEvent.get().getLocation().getPath()).with(sounds));
    }

    private void addMusicDiscSound(Supplier<SoundEvent> soundEvent, String name) {
        this.add(soundEvent.get(), SoundDefinition.definition().with(sound(SullysMod.modPrefix("records/" + name)).stream()));
    }
}
