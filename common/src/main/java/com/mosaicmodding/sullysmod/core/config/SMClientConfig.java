package com.mosaicmodding.sullysmod.core.config;

import com.mosaicmodding.sullysmod.SullysMod;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ModConfigHolder;

import java.util.function.Supplier;

public class SMClientConfig {

    public static final ModConfigHolder HOLDER;
    public static final Supplier<Boolean> USE_DEDICATED_MOD_TAB;
    public static final Supplier<Boolean> ENABLE_MOUNTAIN_CALLS;
    public static final Supplier<Boolean> ENABLE_MYSTERIOUS_EYES;
    public static final Supplier<Boolean> ENABLE_POLISHABLE_TOOLTIP;

    static {
        ConfigBuilder builder = ConfigBuilder.create(SullysMod.ID, ConfigType.CLIENT);

        builder.push("general");
        USE_DEDICATED_MOD_TAB = builder.worldReload().comment("When enabled, all mod items except for artifacts are added to a dedicated sully's mod creative tab").define("dedicated_mod_tab", true);
        ENABLE_POLISHABLE_TOOLTIP = builder.comment("Enables the 'Polishable' tooltip on polishable items").define("enable_polishable_tooltip", true);
        ENABLE_MOUNTAIN_CALLS = builder.comment("Enables the Mountain call ambient sounds").define("enable_mountain_calls", true);
        ENABLE_MYSTERIOUS_EYES = builder.comment("Enables the mysterious eyes in the void").define("enable_mysterious_eyes", true);

        builder.disableLangExport();
        HOLDER = builder.build(true);
        HOLDER.forceLoad();
    }

    public static void init() {}
}
