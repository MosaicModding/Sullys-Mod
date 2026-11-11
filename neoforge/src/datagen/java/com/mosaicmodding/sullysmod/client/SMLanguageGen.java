package com.mosaicmodding.sullysmod.client;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.core.SMConstants;
import com.mosaicmodding.sullysmod.core.config.SMClientConfig;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils;
import com.mosaicmodding.tessera.datagen.providers.TesseraDefaultLangProvider;
import net.minecraft.data.PackOutput;

import java.util.Map;

public class SMLanguageGen extends TesseraDefaultLangProvider {

    public SMLanguageGen(PackOutput output) {
        super(output, SullysMod.ID);
    }

    @Override
    protected void addTranslations() {
        forItems(SMRegUtils.getAllModItemsNoBlocks());
        forBlocks(SMRegUtils.getAllModBlocks(), this::modifyBlockNames);
        forEntities(SMRegUtils.getAllModEntityTypes());
        forMobEffects(SMRegUtils.getAllModMobEffects()); //TODO custom names for effects

        forStringMap(SMCommonConfig.HOLDER.getMissingTranslations());
        forStringMap(SMClientConfig.HOLDER.getMissingTranslations());
        if (!SMConstants.Text.TRANSLATABLES.isEmpty()) {
            forStringMap(SMConstants.Text.TRANSLATABLES);
        }


        //todo make this more convenient. probably through tessera
        add("subtitles.entity.bouldering_zombie.ambient", "Bouldering Zombie groans");
        add("subtitles.entity.bouldering_zombie.hurt", "Bouldering Zombie hurts");
        add("subtitles.entity.bouldering_zombie.death", "Bouldering Zombie dies");
    }

    protected void forStringMap(Map<String, String> map) {
        map.forEach(this::add);
    }

    private String modifyBlockNames(String name) {
        if ((!name.endsWith("_bricks"))) {
            if (name.contains("bricks")) {
                name = name.replaceFirst("bricks", "brick");
            }
        }
        if (name.endsWith("_carved")) {
            name = "carved_" + name.replaceFirst("_carved", "");
        }

        return name;
    }

    /* todo nah
    protected <I extends SoundEvent> void forSounds(List<Supplier<I>> soundSupplier) {
        soundSupplier.forEach(s -> {
            var modId = s.get().getLocation().getNamespace();
            var soundPath = s.get().getLocation().getPath();
            String name = correctSoundName(soundPath).replaceAll("_", " ");
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            add(modId + ".subtitle." + soundPath, name);
        });
    }

    public String correctSoundName(String name) {
        if ((name.endsWith("_step"))) {
            return "footsteps";
        }
        if ((name.endsWith("_place"))) {
            return "block_placed";
        }
        if ((name.endsWith("_break"))) {
            return "block_broken";
        }
        if ((name.endsWith("_hit"))) {
            return "block_breaking";
        }
        return name;
    }

     */

}
