package com.mosaicmodding.sullysmod.core;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.tessera.TesseraCore;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.HashMap;

public class SMConstants {

    public static class Textures {

    }

    public static class Text {
        public static HashMap<String, String> TRANSLATABLES = new HashMap<>();

        public static final Component MOD_TAB = createTranslatable("itemGroup.sullysmod.sullysmod_tab", "Sully's Mod");
        public static final Component ARTIFACTS_TAB = createTranslatable("itemGroup.sullysmod.artifact_tab", "Ancient Artifacts");

        public static MutableComponent createTranslatable(String translatable, String translation) {
            if (TesseraCore.PLAT_HELPER.isDuringDatagen()) {
                TRANSLATABLES.put(translatable, translation);
            }
            return Component.translatable(translatable);
        }

        public static MutableComponent createModTranslatable(String translatable, String translation) {
            return createTranslatable(SullysMod.ID + "." + translatable, translation);
        }

        public static void init() {}
    }
}
