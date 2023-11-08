package com.uraneptus.sullysmod.core.other;

import com.uraneptus.sullysmod.common.recipes.GrindstonePolishingRecipe;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

public class SMTextDefinitions {
    private static final Style polishingStyle = Style.EMPTY.withColor(TextColor.fromRgb(8355711)).withItalic(true);
    public static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    public static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    public static final String ZOMBIE_PACK_NAME = "zombie_retextures";
    public static final Component ZOMBIE_PACK_DISPLAY_NAME = Component.literal("Sully's Mod " + TextUtil.createTranslation(ZOMBIE_PACK_NAME));
    public static Component POLISHABLE;
    public static Component JEI_POLISHING_TITLE;
    public static Component JEI_POLISHING_INFO;
    public static Component JADE_UPGRADE;
    public static Component JADE_UPGRADE_APPLIES_TO;
    public static Component JADE_UPGRADE_INGREDIENTS;
    public static Component JADE_UPGRADE_BASE_SLOT_DESCRIPTION;
    public static Component JADE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION;

    //ADVANCEMENTS
    public static final String JADE_GRINDSET_ADV_NAME = "jade_grindset";
    public static final String POLISH_JADE_ADV_NAME = "polish_jade";
    public static Pair<Component, Component> JADE_GRINDSET_ADV;
    public static Pair<Component, Component> POLISH_JADE_ADV;

    public static void init() {
        POLISHABLE = TextUtil.addSMTranslatable("polishing.tooltip", "Polishable at grindstone").setStyle(polishingStyle);
        JEI_POLISHING_TITLE = TextUtil.addSMTranslatable("jei." + GrindstonePolishingRecipe.NAME, "Polishing");
        JEI_POLISHING_INFO = TextUtil.addSMTranslatable("jei." + GrindstonePolishingRecipe.NAME + ".info", "Right click to polish");
        JADE_UPGRADE = TextUtil.addTranslatable(Util.makeDescriptionId("upgrade", new ResourceLocation("jade_upgrade")), "Jade Upgrade").withStyle(TITLE_FORMAT);
        JADE_UPGRADE_APPLIES_TO = TextUtil.addTranslatable(Util.makeDescriptionId("item", jadeUpgradeString("applies_to")), "Shield").withStyle(DESCRIPTION_FORMAT);
        JADE_UPGRADE_INGREDIENTS = TextUtil.addTranslatable(Util.makeDescriptionId("item", jadeUpgradeString("ingredients")), "Polished Jade").withStyle(DESCRIPTION_FORMAT);
        JADE_UPGRADE_BASE_SLOT_DESCRIPTION = TextUtil.addTranslatable(Util.makeDescriptionId("item", jadeUpgradeString("base_slot_description")), "Add shield");
        JADE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = TextUtil.addTranslatable(Util.makeDescriptionId("item", jadeUpgradeString("additions_slot_description")), "Add Polished Jade");
        JADE_GRINDSET_ADV = TextUtil.addAdvancementTranslatables("advancements.adventure." + JADE_GRINDSET_ADV_NAME, TextUtil.createTranslation(JADE_GRINDSET_ADV_NAME), "Acquire Rough Jade from within a jungle biome");
        POLISH_JADE_ADV = TextUtil.addAdvancementTranslatables("advancements.adventure." + POLISH_JADE_ADV_NAME, "Sparkles Like New!", "Polish Rough Jade using a Grindstone");
    }

    private static ResourceLocation jadeUpgradeString(String suffix) {
        return new ResourceLocation("smithing_template.jade_upgrade." + suffix);
    }

}
