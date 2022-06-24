package com.uraneptus.sullysmod.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SMItemTags {
    //Our Tags
    public static final TagKey<Item> TORTOISE_FOOD = TagUtil.itemTag(SullysMod.MOD_ID, "tortoise_food");

    //Forge Tags
    public static final TagKey<Item> AXE_ITEMS = TagUtil.itemTag("forge", "tools/axes");
    public static final TagKey<Item> RAW_FISH = TagUtil.itemTag("forge", "raw_fishes");
    public static final TagKey<Item> COOKED_LANTERNFISH = TagUtil.itemTag("forge", "cooked_fishes/lanternfish");

    //Quark Tags
    public static final TagKey<Item> VERTICAL_SLABS = TagUtil.itemTag("quark", "vertical_slabs");
}
