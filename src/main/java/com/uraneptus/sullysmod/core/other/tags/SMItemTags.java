package com.uraneptus.sullysmod.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SMItemTags {
    //Our Tags
    public static final TagKey<Item> TORTOISE_FOOD = TagUtil.itemTag(SullysMod.MOD_ID, "tortoise_food");
    public static final TagKey<Item> CHAMELEON_FOOD = TagUtil.itemTag(SullysMod.MOD_ID,  "chameleon_food");

    //Forge Tags
    public static final TagKey<Item> AXE_ITEMS = TagUtil.itemTag("forge", "tools/axes");
    public static final TagKey<Item> RAW_FISHES = TagUtil.itemTag("forge", "raw_fishes");
    public static final TagKey<Item> RAW_LANTERNFISH = TagUtil.itemTag("forge", "raw_fishes/lanternfish");
    public static final TagKey<Item> COOKED_FISHES = TagUtil.itemTag("forge", "cooked_fishes");
    public static final TagKey<Item> COOKED_LANTERNFISH = TagUtil.itemTag("forge", "cooked_fishes/lanternfish");
    public static final TagKey<Item> JADE_ORES = TagUtil.itemTag("forge", "ores/jade");

    //Quark Tags
    public static final TagKey<Item> VERTICAL_SLABS = TagUtil.itemTag("quark", "vertical_slabs");
}
