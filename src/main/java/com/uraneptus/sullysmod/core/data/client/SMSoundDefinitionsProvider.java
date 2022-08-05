package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

import java.util.function.Supplier;

public class SMSoundDefinitionsProvider extends SoundDefinitionsProvider {

    public SMSoundDefinitionsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, SullysMod.MOD_ID, helper);
    }

    //TODO make the amount of sounds generated
    @Override
    public void registerSounds() {

        //Music Discs
        addMusicDiscSound(SMSounds.MUSIC_DISC_SCOUR, "scour");

        //Block Sounds
        addBlockSound(SMSounds.POLISH_JADE,
                sound(SullysMod.modPrefix("block/grindstone/jade_polish0")),
                sound(SullysMod.modPrefix("block/grindstone/jade_polish1"))
        );

        addBlockSound(SMSounds.JADE_RICOCHET,
                sound(new ResourceLocation("block/end_portal/eyeplace1")),
                sound(new ResourceLocation("block/end_portal/eyeplace2")),
                sound(new ResourceLocation("block/end_portal/eyeplace3"))
        );

        addBlockSound(SMSounds.FLINGER_FLINGS,
                sound(new ResourceLocation("random/bow"))
        );

        //Entity Sounds
        addEntitySound(SMSounds.TORTOISE_AMBIENT,
                sound(new ResourceLocation("mob/turtle/idle1")).volume(0.8F),
                sound(new ResourceLocation("mob/turtle/idle2")).volume(0.7F),
                sound(new ResourceLocation("mob/turtle/idle3")).volume(0.8F)
        );

        addEntitySound(SMSounds.TORTOISE_DEATH,
                sound(new ResourceLocation("mob/turtle/death1")),
                sound(new ResourceLocation("mob/turtle/death2")),
                sound(new ResourceLocation("mob/turtle/death3"))
        );

        addEntitySound(SMSounds.TORTOISE_HURT,
                sound(new ResourceLocation("mob/turtle/hurt1")),
                sound(new ResourceLocation("mob/turtle/hurt2")),
                sound(new ResourceLocation("mob/turtle/hurt3")),
                sound(new ResourceLocation("mob/turtle/hurt4")),
                sound(new ResourceLocation("mob/turtle/hurt5"))
        );

        addEntitySound(SMSounds.TORTOISE_HIDE,
                sound(new ResourceLocation("entity/shulker/close1")),
                sound(new ResourceLocation("entity/shulker/close2")),
                sound(new ResourceLocation("entity/shulker/close3")),
                sound(new ResourceLocation("entity/shulker/close4")),
                sound(new ResourceLocation("entity/shulker/close5"))
        );

        addEntitySound(SMSounds.TORTOISE_EMERGE,
                sound(new ResourceLocation("entity/shulker/open1")),
                sound(new ResourceLocation("entity/shulker/open2")),
                sound(new ResourceLocation("entity/shulker/open3")),
                sound(new ResourceLocation("entity/shulker/open4")),
                sound(new ResourceLocation("entity/shulker/open5"))
        );

        addEntitySound(SMSounds.TORTOISE_HURT_HIDDEN,
                sound(new ResourceLocation("entity/shulker/hurt_closed1")),
                sound(new ResourceLocation("entity/shulker/hurt_closed2")),
                sound(new ResourceLocation("entity/shulker/hurt_closed3")),
                sound(new ResourceLocation("entity/shulker/hurt_closed4")),
                sound(new ResourceLocation("entity/shulker/hurt_closed5"))
        );

        addEntitySound(SMSounds.BABY_TORTOISE_HURT,
                sound(new ResourceLocation("mob/turtle/baby/hurt1")),
                sound(new ResourceLocation("mob/turtle/baby/hurt2"))
        );

        addEntitySound(SMSounds.BABY_TORTOISE_DEATH,
                sound(new ResourceLocation("mob/turtle/baby/death1")),
                sound(new ResourceLocation("mob/turtle/baby/death2"))
        );

        addEntitySound(SMSounds.TORTOISE_LAY_EGG,
                sound(new ResourceLocation("mob/turtle/egg/drop_egg1")),
                sound(new ResourceLocation("mob/turtle/egg/drop_egg2"))
        );

        addEntitySound(SMSounds.TORTOISE_EGG_BREAK,
                sound(new ResourceLocation("mob/turtle/egg/egg_break1")),
                sound(new ResourceLocation("mob/turtle/egg/egg_break2"))
        );

        addEntitySound(SMSounds.TORTOISE_EGG_CRACK,
                sound(new ResourceLocation("mob/turtle/egg/egg_crack1")),
                sound(new ResourceLocation("mob/turtle/egg/egg_crack2")),
                sound(new ResourceLocation("mob/turtle/egg/egg_crack3")),
                sound(new ResourceLocation("mob/turtle/egg/egg_crack4")),
                sound(new ResourceLocation("mob/turtle/egg/egg_crack5"))
        );

        addEntitySound(SMSounds.TORTOISE_EGG_HATCH,
                sound(new ResourceLocation("mob/turtle/baby/egg_hatched1")),
                sound(new ResourceLocation("mob/turtle/baby/egg_hatched2")),
                sound(new ResourceLocation("mob/turtle/baby/egg_hatched3"))
        );

        addEntitySound(SMSounds.LANTERNFISH_FLOP,
                sound(new ResourceLocation("entity/fish/flop1")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop2")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop3")).volume(0.3),
                sound(new ResourceLocation("entity/fish/flop4")).volume(0.3)
        );

        addEntitySound(SMSounds.LANTERNFISH_DEATH,
                sound(new ResourceLocation("entity/fish/hurt1")),
                sound(new ResourceLocation("entity/fish/hurt2")),
                sound(new ResourceLocation("entity/fish/hurt3")),
                sound(new ResourceLocation("entity/fish/hurt4"))
        );

        addEntitySound(SMSounds.LANTERNFISH_HURT,
                sound(new ResourceLocation("entity/fish/hurt1")),
                sound(new ResourceLocation("entity/fish/hurt2")),
                sound(new ResourceLocation("entity/fish/hurt3")),
                sound(new ResourceLocation("entity/fish/hurt4"))
        );
    }

    private void addBlockSound(Supplier<SoundEvent> soundEvent, SoundDefinition.Sound... sounds) {
        this.add(soundEvent.get(), SoundDefinition.definition().subtitle("subtitles." + soundEvent.get().getLocation().getPath()).with(sounds));
    }

    private void addEntitySound(Supplier<SoundEvent> soundEvent, SoundDefinition.Sound... sounds) {
        this.add(soundEvent.get(), SoundDefinition.definition().subtitle("subtitles." + soundEvent.get().getLocation().getPath()).with(sounds));
    }

    private void addMusicDiscSound(Supplier<SoundEvent> soundEvent, String name) {
        this.add(soundEvent.get(), SoundDefinition.definition().with(sound(SullysMod.modPrefix("records/" + name)).stream()));
    }
}
