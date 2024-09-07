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
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_WOLF_CARNIVORE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_CONTRIBUTOR_CAPE;

    public static final ForgeConfigSpec CLIENT;
    public static final ForgeConfigSpec COMMON;

    static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        //Client
        ENABLE_POLISHABLE_TOOLTIP = CLIENT_BUILDER.comment("Enables the 'Polishable' tooltip on polishable items").define("Enable Polishable Tooltip", true);
        ENABLE_CONTRIBUTOR_CAPE = CLIENT_BUILDER.comment("Enables the contributor or dev cape. If you're not a contributor or dev, you can ignore this").define("Enable Cape", true);

        //COMMON
        /*
        COMMON_BUILDER.comment("Mod Feature Selection").push("sm_feature_selection");
        COMMON_BUILDER.comment("Jade Features").push("jade_features");
        JADE_ENABLED = COMMON_BUILDER.comment("Enables all jade features (default = true)").define("Enable jade features", true);
        COMMON_BUILDER.comment("Mod Entities").push("sm_entities");
        COMMON_BUILDER.comment("Amber Features").push("amber_features");
        COMMON_BUILDER.comment("Artifact Features").push("artifact_features");
        COMMON_BUILDER.comment("Other Features").push("other_features");

         */



        COMMON_BUILDER.comment("Vanilla Spawn Modifications").push("vanilla_spawn_modifications");
        DISABLE_DEEPSLATE_ZOMBIE_SPAWNS = COMMON_BUILDER.comment("Disables zombie spawning in deepslate levels. This is done to prevent too many zombies spawning in this area, since the Bouldering Zombie spawns there exclusively").define("Disable deepslate zombie spawning", true);
        DISABLE_SPIDER_IN_JUNGLE_SPAWNS = COMMON_BUILDER.comment("Disables spider spawning in jungle biomes. This is done to prevent too many spiders spawning in this area, since the Jungle Spider spawns there exclusively").define("Disable spider spawning in jungles", false);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Experimental Entity Modifications").push("experimental_entity_modifications");
        ENABLE_WOLF_CARNIVORE = COMMON_BUILDER.comment("Enables wolfes consuming meat when killing an entity, similar to what the piranha does").define("Enable wolves consuming meat", false);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Blocks").push("blocks");
        COMMON_BUILDER.comment("Jade").push("jade");
        ENABLE_DYNAMIC_VELOCITY = COMMON_BUILDER.comment("If the velocity of projectiles bounced off of a Jade block should be based on its previous velocity instead of a static value. [Warning: Experimental] (default = false)").define("Dynamic ricochet velocity", false);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.pop();

        CLIENT = CLIENT_BUILDER.build();
        COMMON = COMMON_BUILDER.build();
    }
}
