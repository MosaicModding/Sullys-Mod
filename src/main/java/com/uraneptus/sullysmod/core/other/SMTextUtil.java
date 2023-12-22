package com.uraneptus.sullysmod.core.other;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class SMTextUtil {
    public static Map<String, String> TRANSLATABLES = new HashMap<>();

    public static MutableComponent addTranslatable(String translatable, String translation) {
        TRANSLATABLES.put(translatable, translation);
        return Component.translatable(translatable);
    }

    public static MutableComponent addSMTranslatable(String translatable, String translation) {
        return addTranslatable(SullysMod.MOD_ID + "." + translatable, translation);
    }

    public static Pair<Component, Component> addAdvancementTranslatables(String path, String titleTranslation, String descTranslation) {
        var title = addTranslatable(path + ".title", titleTranslation);
        var desc = addTranslatable(path + ".description", descTranslation);
        return Pair.of(title, desc);
    }

    public static String createTranslation(String path) {
        final StringBuilder builder = new StringBuilder();

        for (String part : path.split("_")) {
            if (!builder.isEmpty()) {
                builder.append(" ");
            }
            builder.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return builder.toString();

        /*
        var translation = "";
        List<String> translationParts = Lists.newArrayList();
        var splitList = path.split("_");
        for (String split : splitList) {
            var capitalized = firstToUpperCase(split);
            translationParts.add(capitalized);
        }
        translation = String.join(" ", translationParts);
        return translation;

         */
    }

    public static String firstToUpperCase(String string) {
        var firstLetter = string.charAt(0);
        return string.replaceFirst(String.valueOf(firstLetter), String.valueOf(firstLetter).toUpperCase());
    }
}
