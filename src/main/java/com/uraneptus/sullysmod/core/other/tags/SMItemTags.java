package com.uraneptus.sullysmod.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SMItemTags {
    //Our Tags
    public static final TagKey<Item> TORTOISE_FOOD = TagUtil.itemTag(SullysMod.MOD_ID, "tortoise_food");
    public static final TagKey<Item> CARNIVORE_CONSUMABLES = TagUtil.itemTag(SullysMod.MOD_ID, "carnivore_consumables");
    public static final TagKey<Item> PETRIFIED_LOGS = TagUtil.itemTag(SullysMod.MOD_ID, "petrified_logs");
    public static final TagKey<Item> ARTIFACTS = TagUtil.itemTag(SullysMod.MOD_ID, "artifacts");
    public static final TagKey<Item> ANCIENT_SKULLS = TagUtil.itemTag(SullysMod.MOD_ID, "artifacts/ancient_skulls");

    //Forge Tags
    public static final TagKey<Item> AXE_ITEMS = TagUtil.itemTag("forge", "tools/axes");
    public static final TagKey<Item> KNIFES = TagUtil.itemTag("forge", "tools/knives");
    public static final TagKey<Item> RAW_FISHES = TagUtil.itemTag("forge", "raw_fishes");
    public static final TagKey<Item> RAW_LANTERNFISH = TagUtil.itemTag("forge", "raw_fishes/lanternfish");
    public static final TagKey<Item> RAW_PIRANHA = TagUtil.itemTag("forge", "raw_fishes/piranha");
    public static final TagKey<Item> COOKED_FISHES = TagUtil.itemTag("forge", "cooked_fishes");
    public static final TagKey<Item> COOKED_LANTERNFISH = TagUtil.itemTag("forge", "cooked_fishes/lanternfish");
    public static final TagKey<Item> COOKED_PIRANHA = TagUtil.itemTag("forge", "cooked_fishes/piranha");
    public static final TagKey<Item> JADE_ORES = TagUtil.itemTag("forge", "ores/jade");
    public static final TagKey<Item> JADE_GEM = TagUtil.itemTag("forge", "gems/jade");
    public static final TagKey<Item> RAW_MATERIALS_JADE = TagUtil.itemTag("forge", "raw_materials/jade");
    public static final TagKey<Item> CRAFTING_TABLES = TagUtil.itemTag("forge", "crafting_tables");
    public static final TagKey<Item> JUKEBOXES = TagUtil.itemTag("forge", "jukeboxes");

}
