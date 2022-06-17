package com.uraneptus.sullysmod.core;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID)
public class SMConfig {
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_TORTOISES;
    public static ForgeConfigSpec.ConfigValue<Integer> TORTOISE_SPAWN_WEIGHT;

    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_LANTERNFISH;
    public static ForgeConfigSpec.ConfigValue<Integer> LANTERNFISH_SPAWN_WEIGHT;

    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_JADE;

    public static ForgeConfigSpec COMMON;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        //Common
        COMMON_BUILDER.comment("Mobs").push("mobs");
        COMMON_BUILDER.comment("Tortoise").push("tortoise");
        ENABLE_TORTOISES = COMMON_BUILDER.comment("If Tortoises will spawn naturally in the world. (default = true)").define("Tortoises spawn naturally", true);
        TORTOISE_SPAWN_WEIGHT = COMMON_BUILDER.comment("The weight of which Tortoises will spawn. (default = 5").defineInRange("Tortoise spawn weight", 5, 1, 100);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Lanternfish").push("lanternfish");
        ENABLE_LANTERNFISH = COMMON_BUILDER.comment("If Lanternfish will spawn naturally in the world. (default = true)").define("Lanternfish spawn naturally", true);
        LANTERNFISH_SPAWN_WEIGHT = COMMON_BUILDER.comment("The weight of which Lanternfish will spawn. (default = 5)").defineInRange("Lanternfish spawn weight", 5, 1, 100);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Worldgen").push("worldgen");
        COMMON_BUILDER.comment("Jade").push("jade");
        ENABLE_JADE = COMMON_BUILDER.comment("If Jade will generate naturally in the world. (default = true)").define("Jade generates naturally", true);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.pop();

        COMMON = COMMON_BUILDER.build();
    }
}
