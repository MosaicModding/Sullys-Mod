package com.uraneptus.sullysmod.core;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID)
public class SMConfig {
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_DYNAMIC_VELOCITY;

    public static final ForgeConfigSpec SERVER;
    //public static final ForgeConfigSpec CLIENT;

    static {
        ForgeConfigSpec.Builder SERVER_BULDER = new ForgeConfigSpec.Builder();
        //ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        //Client

        //Server
        SERVER_BULDER.comment("Blocks").push("blocks");
        SERVER_BULDER.comment("Jade").push("jade");
        ENABLE_DYNAMIC_VELOCITY = SERVER_BULDER.comment("If the velocity of projectiles bounced off of a Jade block should be based on its previous velocity instead of a static value. [Warning: Experimental] (default = false)").define("Dynamic ricochet velocity", false);
        SERVER_BULDER.pop();
        SERVER_BULDER.pop();

        SERVER = SERVER_BULDER.build();
        //CLIENT = CLIENT_BUILDER.build();
    }
}
