package com.uraneptus.sullysmod.core;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID)
public class SMConfig {

    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_DYNAMIC_VELOCITY;
    public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_DEEPSLATE_ZOMBIE_SPAWNS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_SPIDER_IN_JUNGLE_SPAWNS;
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
        ENABLE_JUNGLE_SPIDER = COMMON_BUILDER.comment("Enables all jungle spider features").define("jungle_spider", true);
        ENABLE_ITEM_STAND = COMMON_BUILDER.comment("Enables the item stand").define("item_stand", true);
        ENABLE_ARTIFACTS = COMMON_BUILDER.comment("Enables all artifact features").define("artifacts", true);
        COMMON_BUILDER.comment("Mod Potions").push("potions");
        ENABLE_UNLUCK_POTION = COMMON_BUILDER.comment("Enables the bad luck potion").define("unluck_potion", false);
        ENABLE_RESISTANCE_POTION = COMMON_BUILDER.comment("Enables the resistance potion").define("resistance_potion", false);
        COMMON_BUILDER.pop();
        ENABLE_COPPER_BUTTONS = COMMON_BUILDER.comment("Enables copper buttons").define("copper_buttons", true);
        ENABLE_GEM_LANTERNS = COMMON_BUILDER.comment("Enables gem lanterns").define("gem_lanterns", false);
        ENABLE_GRINDSTONE_POLISHING = COMMON_BUILDER.comment("Enables grindstone polishing").define("grindstone_polishing", true);
        COMMON_BUILDER.comment("Ambient effects").push("ambient");
        ENABLE_MOUNTAIN_CALLS = COMMON_BUILDER.comment("Enables the Mountain call ambient sounds").define("enable_mountain_calls", true);
        ENABLE_MYSTERIOUS_EYES = COMMON_BUILDER.comment("Enables the mysterious eyes in the void").define("enable_mysterious_eyes", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Other Settings").push("other_settings");
        ENABLE_DYNAMIC_VELOCITY = COMMON_BUILDER.comment("If the velocity of projectiles bounced off of a Jade block should be based on its previous velocity instead of a static value. [Warning: Experimental] (default = false)").define("Dynamic ricochet velocity", false);

        COMMON_BUILDER.comment("Vanilla Spawn Modifications").push("vanilla_spawn_modifications");
        DISABLE_DEEPSLATE_ZOMBIE_SPAWNS = COMMON_BUILDER.comment("Disables zombie spawning in deepslate levels. This is done to prevent too many zombies spawning in this area, since the Bouldering Zombie spawns there exclusively").define("Disable deepslate zombie spawning", true);
        DISABLE_SPIDER_IN_JUNGLE_SPAWNS = COMMON_BUILDER.comment("Disables spider spawning in jungle biomes. This is done to prevent too many spiders spawning in this area, since the Jungle Spider spawns there exclusively").define("Disable spider spawning in jungles", false);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Experimental Entity Modifications").push("experimental_entity_modifications");
        ENABLE_WOLF_CARNIVORE = COMMON_BUILDER.comment("Enables wolves consuming meat when killing an entity, similar to what the piranha does").define("Enable wolves consuming meat", false);
        COMMON_BUILDER.pop();

        CLIENT = CLIENT_BUILDER.build();
        COMMON = COMMON_BUILDER.build();
    }
}
