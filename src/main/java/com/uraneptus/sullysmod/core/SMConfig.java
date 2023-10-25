package com.uraneptus.sullysmod.core;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID)
public class SMConfig {
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_DYNAMIC_VELOCITY;
    public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_DEEPSLATE_ZOMBIE_SPAWNS;

    public static final ForgeConfigSpec SERVER;
    public static final ForgeConfigSpec CLIENT;
    public static final ForgeConfigSpec COMMON;

    static {
        ForgeConfigSpec.Builder SERVER_BULDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        //Client

        //COMMON
        COMMON_BUILDER.comment("Vanilla Spawn Modifications").push("vanilla_spawn_modifications");
        DISABLE_DEEPSLATE_ZOMBIE_SPAWNS = COMMON_BUILDER.comment("Disables zombie spawning in deepslate levels. This is done to prevent too many zombies spawning in this area, since the Bouldering Zombie spawns there exclusively").define("Disable deepslate zombie spawning", true);
        COMMON_BUILDER.pop();

        //TODO test if this can be done common side
        //Server
        SERVER_BULDER.comment("Blocks").push("blocks");
        SERVER_BULDER.comment("Jade").push("jade");
        ENABLE_DYNAMIC_VELOCITY = SERVER_BULDER.comment("If the velocity of projectiles bounced off of a Jade block should be based on its previous velocity instead of a static value. [Warning: Experimental] (default = false)").define("Dynamic ricochet velocity", false);
        SERVER_BULDER.pop();
        SERVER_BULDER.pop();

        SERVER = SERVER_BULDER.build();
        CLIENT = CLIENT_BUILDER.build();
        COMMON = COMMON_BUILDER.build();
    }
}
