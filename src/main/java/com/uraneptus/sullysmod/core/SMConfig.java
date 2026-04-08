package com.uraneptus.sullysmod.core;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID)
public class SMConfig {

    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_DYNAMIC_VELOCITY;
    public static final ForgeConfigSpec.ConfigValue<Float> ZOMBIE_IN_DEEPSLATE_REPLACEMENT_RATE;
    public static final ForgeConfigSpec.ConfigValue<Float> SPIDER_IN_JUNGLE_REPLACEMENT_RATE;
    public static final ForgeConfigSpec.ConfigValue<Float> SKELETON_IN_DEEPSLATE_REPLACEMENT_RATE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_POLISHABLE_TOOLTIP;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_MOUNTAIN_CALLS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_MYSTERIOUS_EYES;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_WOLF_CARNIVORE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_JADE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_PETRIFIED_WOOD;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_AMBER;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_TORTOISE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_LANTERNFISH;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_PIRANHA;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_BOULDERING_ZOMBIE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_JUNGLE_SPIDER;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_ITEM_STAND;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_ARTIFACTS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_UNLUCK_POTION;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_RESISTANCE_POTION;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_COPPER_BUTTONS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_GEM_LANTERNS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_GRINDSTONE_POLISHING;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_BUG_MEAT;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_MAULED;

    public static final ForgeConfigSpec CLIENT;
    public static final ForgeConfigSpec COMMON;

    static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        //Client
        ENABLE_POLISHABLE_TOOLTIP = CLIENT_BUILDER.comment("Enables the 'Polishable' tooltip on polishable items").define("Enable Polishable Tooltip", true);


        //COMMON
        COMMON_BUILDER.comment("Mod Feature Selection").push("feature_selection");
        ENABLE_JADE = COMMON_BUILDER.comment("Enables all Jade features").define("jade", true);
        ENABLE_PETRIFIED_WOOD = COMMON_BUILDER.comment("Enables all petrified wood features").define("petrified_wood", true);
        ENABLE_AMBER = COMMON_BUILDER.comment("Enables all amber features (if disabled, petrified trees will also be disabled)").define("amber", true);
        ENABLE_TORTOISE = COMMON_BUILDER.comment("Enables all tortoise features").define("tortoise", true);
        ENABLE_LANTERNFISH = COMMON_BUILDER.comment("Enables all lanternfish features").define("lanternfish", true);
        ENABLE_PIRANHA = COMMON_BUILDER.comment("Enables all piranha features").define("piranha", true);
        ENABLE_BOULDERING_ZOMBIE = COMMON_BUILDER.comment("Enables all bouldering zombie features").define("bouldering_zombie", true);
        ENABLE_MAULED = COMMON_BUILDER.comment("Enables all features regarding the mauled").define("mauled", true);
        ENABLE_JUNGLE_SPIDER = COMMON_BUILDER.comment("Enables all jungle spider features").define("jungle_spider", true);
        ENABLE_ITEM_STAND = COMMON_BUILDER.comment("Enables the item stand").define("item_stand", true);
        ENABLE_ARTIFACTS = COMMON_BUILDER.comment("Enables all artifact features").define("artifacts", true);
        COMMON_BUILDER.comment("Mod Potions").push("potions");
        ENABLE_UNLUCK_POTION = COMMON_BUILDER.comment("Enables the bad luck potion").define("unluck_potion", true);
        ENABLE_RESISTANCE_POTION = COMMON_BUILDER.comment("Enables the resistance potion").define("resistance_potion", true);
        COMMON_BUILDER.pop();
        ENABLE_COPPER_BUTTONS = COMMON_BUILDER.comment("Enables copper buttons").define("copper_buttons", true);
        ENABLE_GEM_LANTERNS = COMMON_BUILDER.comment("Enables gem lanterns").define("gem_lanterns", true);
        ENABLE_GRINDSTONE_POLISHING = COMMON_BUILDER.comment("Enables grindstone polishing").define("grindstone_polishing", true);
        ENABLE_BUG_MEAT = COMMON_BUILDER.comment("Enables bug meat").define("bug_meat", true);
        COMMON_BUILDER.comment("Ambient effects").push("ambient");
        ENABLE_MOUNTAIN_CALLS = COMMON_BUILDER.comment("Enables the Mountain call ambient sounds").define("enable_mountain_calls", true);
        ENABLE_MYSTERIOUS_EYES = COMMON_BUILDER.comment("Enables the mysterious eyes in the void").define("enable_mysterious_eyes", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Other Settings").push("other_settings");
        ENABLE_DYNAMIC_VELOCITY = COMMON_BUILDER.comment("If the velocity of projectiles bounced off of a Jade block should be based on its previous velocity instead of a static value. [Warning: Experimental] (default = false)").define("Dynamic ricochet velocity", false);

        COMMON_BUILDER.comment("Vanilla Spawn Modifications").push("vanilla_spawn_modifications");
        ZOMBIE_IN_DEEPSLATE_REPLACEMENT_RATE = COMMON_BUILDER.comment("Sets a percentage for how many vanilla zombies should be replaced by bouldering zombies in deepslate levels.").define("zombie_deepslate_replacement_rate", 1.0F);
        SPIDER_IN_JUNGLE_REPLACEMENT_RATE = COMMON_BUILDER.comment("Sets a percentage for how many vanilla spiders should be replaced by jungle spiders in jungle biomes.").define("spider_jungle_replacement_rate", 0.0F);
        SKELETON_IN_DEEPSLATE_REPLACEMENT_RATE = COMMON_BUILDER.comment("Sets a percentage for how many vanilla skeletons should be replaced by the mauled in deepslate levels.").define("skeleton_deepslate_replacement_rate", 0.2F);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Experimental Entity Modifications").push("experimental_entity_modifications");
        ENABLE_WOLF_CARNIVORE = COMMON_BUILDER.comment("Enables wolves consuming meat when killing an entity, similar to what the piranha does").define("Enable wolves consuming meat", false);
        COMMON_BUILDER.pop();

        CLIENT = CLIENT_BUILDER.build();
        COMMON = COMMON_BUILDER.build();
    }
}
