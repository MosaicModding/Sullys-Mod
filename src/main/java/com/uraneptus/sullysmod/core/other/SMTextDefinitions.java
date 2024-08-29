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
    private static final Style POLISHING_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(8355711)).withItalic(true);
    public static final Style ARTIFACT_DESC_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(6644320)).withItalic(true);
    public static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    public static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    public static final String ZOMBIE_PACK_NAME = "zombie_retextures";
    public static final Component ZOMBIE_PACK_DISPLAY_NAME = Component.literal("Sully's Mod " + SMTextUtil.createTranslation(ZOMBIE_PACK_NAME));
    public static Component POLISHABLE;
    public static Component JEI_POLISHING_TITLE;
    public static Component JEI_POLISHING_INFO;
    public static Component JADE_UPGRADE;
    public static Component JADE_UPGRADE_APPLIES_TO;
    public static Component JADE_UPGRADE_INGREDIENTS;
    public static Component JADE_UPGRADE_BASE_SLOT_DESCRIPTION;
    public static Component JADE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION;
    public static Component ARTIFACT_TAB_TITLE;
    public static Component WHEN_THROWN_TOOLTIP;
    public static Component THROWING_KNIFE_DAMAGE;
    public static Component THROWING_KNIFE_AIR_DAMAGE;

    //ADVANCEMENTS
    public static final String ADVENTURE_PATH = "advancements.adventure.";
    public static final String JADE_GRINDSET_ADV_NAME = "jade_grindset";
    public static final String POLISH_JADE_ADV_NAME = "polish_jade";
    public static final String SHELL_HIT_ADV_NAME = "tortoise_shell_hit";
    public static final String SHELL_HIT_RAVAGER_ADV_NAME = "tortoise_shell_hit_ravager";
    public static final String FILL_VIAL_JUNGLE_SPIDER_ADV_NAME = "glass_vial_filled_jungle_spider";
    public static final String ITEM_STAND_ADV_NAME = "item_stand_crafted";
    public static final String FIRST_ARTIFACT_ADV_NAME = "first_artifact";
    public static final String LOST_CROWN_ADV_NAME = "find_lost_crown";
    public static final String BIBLIOPHILE_ADV_NAME = "bibliophile";
    public static final String FIRST_SKULL_ADV_NAME = "first_skull";
    public static final String ALL_SKULLS_ADV_NAME = "all_skulls";
    public static final String DRIED_FLOWERS_ADV_NAME = "dried_flowers";
    public static final String FIND_TABLETS_ADV_NAME = "find_tablets";
    public static final String ALL_FOSSILS_ADV_NAME = "all_fossils";
    public static final String ALL_ARTIFACTS_ADV_NAME = "all_artifacts";
    public static Pair<Component, Component> JADE_GRINDSET_ADV;
    public static Pair<Component, Component> POLISH_JADE_ADV;
    public static Pair<Component, Component> SHELL_HIT_ADV;
    public static Pair<Component, Component> SHELL_HIT_RAVAGER_ADV;
    public static Pair<Component, Component> FILL_VIAL_JUNGLE_SPIDER_ADV;
    public static Pair<Component, Component> ITEM_STAND_ADV;
    public static Pair<Component, Component> FIRST_ARTIFACT_ADV;
    public static Pair<Component, Component> LOST_CROWN_ADV;
    public static Pair<Component, Component> BIBLIOPHILE_ADV;
    public static Pair<Component, Component> FIRST_SKULL_ADV;
    public static Pair<Component, Component> ALL_SKULLS_ADV;
    public static Pair<Component, Component> DRIED_FLOWERS_ADV;
    public static Pair<Component, Component> FIND_TABLETS_ADV;
    public static Pair<Component, Component> ALL_FOSSILS_ADV;
    public static Pair<Component, Component> ALL_ARTIFACTS_ADV;

    public static void init() {
        POLISHABLE = SMTextUtil.addSMTranslatable("polishing.tooltip", "Polishable at grindstone").setStyle(POLISHING_STYLE);
        JEI_POLISHING_TITLE = SMTextUtil.addSMTranslatable("jei." + GrindstonePolishingRecipe.NAME, "Polishing");
        JEI_POLISHING_INFO = SMTextUtil.addSMTranslatable("jei." + GrindstonePolishingRecipe.NAME + ".info", "Right click to polish");
        JADE_UPGRADE = SMTextUtil.addTranslatable(Util.makeDescriptionId("upgrade", new ResourceLocation("jade_upgrade")), "Jade Upgrade").withStyle(TITLE_FORMAT);
        JADE_UPGRADE_APPLIES_TO = SMTextUtil.addTranslatable(Util.makeDescriptionId("item", jadeUpgradeString("applies_to")), "Shield, Diamond Horse Armor").withStyle(DESCRIPTION_FORMAT);
        JADE_UPGRADE_INGREDIENTS = SMTextUtil.addTranslatable(Util.makeDescriptionId("item", jadeUpgradeString("ingredients")), "Polished Jade").withStyle(DESCRIPTION_FORMAT);
        JADE_UPGRADE_BASE_SLOT_DESCRIPTION = SMTextUtil.addTranslatable(Util.makeDescriptionId("item", jadeUpgradeString("base_slot_description")), "Add Shield/Horse Armor");
        JADE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = SMTextUtil.addTranslatable(Util.makeDescriptionId("item", jadeUpgradeString("additions_slot_description")), "Add Polished Jade");
        JADE_GRINDSET_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + JADE_GRINDSET_ADV_NAME, SMTextUtil.createTranslation(JADE_GRINDSET_ADV_NAME), "Acquire Rough Jade from within a jungle biome");
        POLISH_JADE_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + POLISH_JADE_ADV_NAME, "Sparkles Like New!", "Polish Rough Jade using a Grindstone");
        SHELL_HIT_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + SHELL_HIT_ADV_NAME, "Get Bonked!", "Hit an Entity with a Tortoise Shell");
        SHELL_HIT_RAVAGER_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + SHELL_HIT_RAVAGER_ADV_NAME, "Bully the Bullies", "Hit a Ravager with a Tortoise Shell");
        FILL_VIAL_JUNGLE_SPIDER_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + FILL_VIAL_JUNGLE_SPIDER_ADV_NAME, "Jungle Fever", "Harvest a Jungle Spider's venom using a Glass Vial");
        ARTIFACT_TAB_TITLE = SMTextUtil.addSMTranslatable("itemGroup.artifacts", "Ancient Artifacts");
        ITEM_STAND_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + ITEM_STAND_ADV_NAME, "The start of a collection", "Craft an Item Stand");
        FIRST_ARTIFACT_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + FIRST_ARTIFACT_ADV_NAME, "A blast from the past", "Find your first artifact in suspicious gravel");
        LOST_CROWN_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + LOST_CROWN_ADV_NAME, "Back when he ruled the land...", "Find the lost crown artifact");
        BIBLIOPHILE_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + BIBLIOPHILE_ADV_NAME, "Bibliophile", "Find all ancient writings");
        FIRST_SKULL_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + FIRST_SKULL_ADV_NAME, "No brainer", "Find your first ancient skull");
        ALL_SKULLS_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + ALL_SKULLS_ADV_NAME, "Walking with... heavy skulls!", "Find all ancient skulls");
        DRIED_FLOWERS_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + DRIED_FLOWERS_ADV_NAME, "A sentimental bouquet!", "Find both dried flowers");
        FIND_TABLETS_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + FIND_TABLETS_ADV_NAME, "The boogeyman", "Find both ancient tablets");
        ALL_FOSSILS_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + ALL_FOSSILS_ADV_NAME, "Medusa's been busy", "Find all fossils");
        ALL_ARTIFACTS_ADV = SMTextUtil.addAdvancementTranslatables(ADVENTURE_PATH + ALL_ARTIFACTS_ADV_NAME, "A grand collection", "Find all artifacts");
        WHEN_THROWN_TOOLTIP = SMTextUtil.addSMTranslatable("item.modifiers.thrown", "When Thrown:").withStyle(ChatFormatting.GRAY);
        THROWING_KNIFE_DAMAGE = SMTextUtil.addSMTranslatable("item.throwing_knife.damage", " 2 Attack Damage").withStyle(ChatFormatting.DARK_GREEN);
        THROWING_KNIFE_AIR_DAMAGE = SMTextUtil.addSMTranslatable("item.throwing_knife.damage.air", " 2 Airborne Bonus").withStyle(ChatFormatting.GOLD);

    }

    private static ResourceLocation jadeUpgradeString(String suffix) {
        return new ResourceLocation("smithing_template.jade_upgrade." + suffix);
    }

}
