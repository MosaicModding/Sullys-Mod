package com.mosaicmodding.sullysmod.core.config;

import com.mojang.datafixers.util.Pair;
import com.mosaicmodding.sullysmod.SullysMod;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ModConfigHolder;

import java.util.function.Function;
import java.util.function.Supplier;

public class SMCommonConfig {

    public static final ModConfigHolder HOLDER;
    public static final Pair<Supplier<Boolean>, String> UNDEAD_VARINATS;
    public static final Pair<Supplier<Boolean>, String> BOULDERING_ZOMBIE;
    public static final Pair<Supplier<Boolean>, String> MAULED;

    public static final Pair<Supplier<Boolean>, String> PETRIFIED_TREES;
    public static final Pair<Supplier<Boolean>, String> AMBER_BLOCKS;
    public static final Pair<Supplier<Boolean>, String> SPAWN_AMBER_BLOBS;
    public static final Pair<Supplier<Boolean>, String> ENTITY_IN_BLOB;

    public static final Pair<Supplier<Boolean>, String> COPPER_BUTTONS;

    public static final Pair<Supplier<Boolean>, String> BUG_MEAT;

    public static final Pair<Supplier<Boolean>, String> ITEM_STAND;

    public static final Pair<Supplier<Boolean>, String> GEM_LANTERNS;

    public static final Pair<Supplier<Boolean>, String> LANTERNFISH;

    public static final Pair<Supplier<Boolean>, String> GRINDSTONE_POLISHING;

    public static final Pair<Supplier<Boolean>, String> GOURDS;

    public static final Pair<Supplier<Boolean>, String> ANCIENT_COLLECTABLES;
    public static final Pair<Supplier<Boolean>, String> ARTIFACT_GRAVEL_SPAWNING;
    public static final Pair<Supplier<Boolean>, String> ARTIFACTS;
    public static final Pair<Supplier<Boolean>, String> ANCIENT_SKULLS;

    public static final Pair<Supplier<Boolean>, String> TORTOISE_FEATURES;
    public static final Pair<Supplier<Boolean>, String> TORTOISE;
    public static final Pair<Supplier<Boolean>, String> TORTOISE_SHELL;
    public static final Pair<Supplier<Boolean>, String> ATTACH_WORKSTATIONS;

    public static final Pair<Supplier<Boolean>, String> PIRANHA_FEATURES;
    public static final Pair<Supplier<Boolean>, String> PIRANHA;
    public static final Pair<Supplier<Boolean>, String> THROWING_KNIFE;

    public static final Pair<Supplier<Boolean>, String> JADE_FEATURES;
    public static final Pair<Supplier<Boolean>, String> ORE_GENERATION;
    public static final Pair<Supplier<Boolean>, String> BLOCKS;
    public static final Pair<Supplier<Boolean>, String> DECORATIONS;
    public static final Pair<Supplier<Boolean>, String> FLINGER_TOTEM;
    public static final Pair<Supplier<Boolean>, String> RICOCHET_MECHANIC;
    public static final Pair<Supplier<Boolean>, String> JADE_SHIELD;
    public static final Pair<Supplier<Boolean>, String> JADE_HORSE_ARMOR;

    public static final Pair<Supplier<Boolean>, String> JUNGLE_SPIDER;

    public static final Supplier<Boolean> CAVE_SPIDER_POISON_COLLECTION;

    public static final Supplier<Double> ZOMBIE_IN_DEEPSLATE_REPLACEMENT_RATE;
    public static final Supplier<Double> SPIDER_IN_JUNGLE_REPLACEMENT_RATE;
    public static final Supplier<Double> SKELETON_IN_DEEPSLATE_REPLACEMENT_RATE;
    public static final Supplier<Boolean> ENABLE_WOLF_CARNIVORE;
    public static final Supplier<Boolean> ENABLE_DYNAMIC_VELOCITY;
    public static final Supplier<Boolean> ENABLE_UNLUCK_POTION;
    public static final Supplier<Boolean> ENABLE_RESISTANCE_POTION;

    /*
      builder::pushFeature -> creates config category (folder) and a corresponding config feature inside it (enabled by default)
      builder::feature -> creates a simple feature no folder (enabled by default);
      <br>
      As of rn, config values need at least one category above them as a root. (This might change in the future)
     */
    static {
        ConfigBuilder builder = ConfigBuilder.create(SullysMod.ID, ConfigType.COMMON_SYNCED);

        builder.push("mod_features");

        UNDEAD_VARINATS = handleNamedFeature("undead_variants", builder::pushFeature);
        BOULDERING_ZOMBIE = handleNamedFeature("bouldering_zombie", builder::feature);
        MAULED = handleNamedFeature("mauled", builder::feature);
        builder.pop();

        PETRIFIED_TREES = handleNamedFeature("petrified_trees", builder::pushFeature);
        AMBER_BLOCKS = handleNamedFeature("amber", name -> builder.comment("Activates all amber blocks and items").feature(name, true));
        SPAWN_AMBER_BLOBS = handleNamedFeature("spawn_amber_blobs", name -> builder.comment("Spawns the occasional amber blobs near the big trees").feature(name, true));
        ENTITY_IN_BLOB = handleNamedFeature("spawn_entity_in_blobs", name -> builder.comment("Spawns the random entities inside the generated amber blobs").feature(name, true));
        builder.pop();

        //TODO check if configs that don't have children, still need to be feature values
        GOURDS = handleNamedFeature("gourds", builder::pushFeature);
        builder.pop();

        COPPER_BUTTONS = handleNamedFeature("copper_buttons", builder::feature);


        BUG_MEAT = handleNamedFeature("bug_meat", builder::feature);


        ITEM_STAND = handleNamedFeature("item_stand", builder::feature);


        GEM_LANTERNS = handleNamedFeature("gem_lanterns", builder::feature);


        LANTERNFISH = handleNamedFeature("lanternfish", name -> builder.worldReload().feature(name));


        GRINDSTONE_POLISHING = handleNamedFeature("grindstone_polishing", builder::feature);


        JUNGLE_SPIDER = handleNamedFeature("jungle_spider", builder::feature);


        ANCIENT_COLLECTABLES = handleNamedFeature("ancient_collectables", builder::pushFeature);
        ARTIFACT_GRAVEL_SPAWNING = handleNamedFeature("artifact_gravel_spawning", builder::feature);
        ARTIFACTS = handleNamedFeature("artifacts", builder::feature);
        ANCIENT_SKULLS = handleNamedFeature("ancient_skulls", builder::feature);
        builder.pop();

        TORTOISE_FEATURES = handleNamedFeature("tortoise_features", builder::pushFeature);
        TORTOISE = handleNamedFeature("tortoise", builder::feature);
        TORTOISE_SHELL = handleNamedFeature("tortoise_shell", builder::feature);
        ATTACH_WORKSTATIONS = handleNamedFeature("attach_workstations", builder::feature);
        builder.pop();

        PIRANHA_FEATURES = handleNamedFeature("piranha_features", builder::pushFeature);
        PIRANHA = handleNamedFeature("piranha", builder::feature);
        THROWING_KNIFE = handleNamedFeature("throwing_knife", builder::feature);
        builder.pop();

        JADE_FEATURES = handleNamedFeature("jade_features", builder::pushFeature);
        ORE_GENERATION = handleNamedFeature("ore_generation", builder::feature);
        BLOCKS = handleNamedFeature("blocks", builder::pushFeature);
        DECORATIONS = handleNamedFeature("decorations", builder::feature);
        FLINGER_TOTEM = handleNamedFeature("flinger_totem", builder::feature);
        builder.pop();
        RICOCHET_MECHANIC = handleNamedFeature("ricochet_mechanic", builder::feature);
        JADE_SHIELD = handleNamedFeature("jade_shield", builder::feature);
        JADE_HORSE_ARMOR = handleNamedFeature("jade_horse_armor", builder::feature);
        builder.pop();
        builder.pop();

        builder.push("misc");
        builder.push("vanilla_spawn_modifications");
        ZOMBIE_IN_DEEPSLATE_REPLACEMENT_RATE = builder.comment("Sets a percentage for how many vanilla zombies should be replaced by bouldering zombies in deepslate levels.").define("zombie_deepslate_replacement_rate", 1.0D, 0.0D, 1.0D);
        SPIDER_IN_JUNGLE_REPLACEMENT_RATE = builder.comment("Sets a percentage for how many vanilla spiders should be replaced by jungle spiders in jungle biomes.").define("spider_jungle_replacement_rate", 0.0D, 0.0D, 1.0D);
        SKELETON_IN_DEEPSLATE_REPLACEMENT_RATE = builder.comment("Sets a percentage for how many vanilla skeletons should be replaced by the mauled in deepslate levels.").define("skeleton_deepslate_replacement_rate", 0.2D, 0.0D, 1.0D);
        builder.pop();
        builder.push("vanilla_potion_additions");
        ENABLE_UNLUCK_POTION = builder.define("unluck_potion", true);
        ENABLE_RESISTANCE_POTION = builder.define("resistance_potion", true);
        builder.pop();
        ENABLE_DYNAMIC_VELOCITY = builder.comment("If the velocity of projectiles bounced off of a Jade block should be based on its previous velocity instead of a static value.").define("dynamic_ricochet_velocity", false);
        ENABLE_WOLF_CARNIVORE = builder.comment("Enables wolves consuming meat when killing an entity, similar to what the piranha does").define("enable_wolf_carnivore", false);
        CAVE_SPIDER_POISON_COLLECTION = builder.comment("Enables the ability to extract poision from cave spiders. Similar to the jungle spider mechanic").define("spider_poison_collection", true);
        builder.pop();

        builder.disableLangExport();
        HOLDER = builder.build(true);
        HOLDER.forceLoad();
    }

    public static Pair<Supplier<Boolean>, String> handleNamedFeature(String name, Function<String, Supplier<Boolean>> func) {
        return Pair.of(func.apply(name), name);
    }

    public static boolean isEnabled(String key) {
        return HOLDER.isFeatureEnabled(key);
    }

    public static void init() {}
}
