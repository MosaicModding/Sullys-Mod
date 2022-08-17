package com.uraneptus.sullysmod.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class SMItemTags {
    //Our Tags
    public static final TagKey<Item> TORTOISE_FOOD = TagUtil.itemTag(SullysMod.MOD_ID, "tortoise_food");

    //Forge Tags
    public static final TagKey<Item> AXE_ITEMS = TagUtil.itemTag("forge", "tools/axes");
    public static final TagKey<Item> RAW_FISH = TagUtil.itemTag("forge", "raw_fishes");
    public static final TagKey<Item> COOKED_LANTERNFISH = TagUtil.itemTag("forge", "cooked_fishes/lanternfish");
    public static final TagKey<Item> STORAGE_BLOCKS = TagUtil.itemTag("forge", "storage_blocks");
    public static final TagKey<Item> ORES = TagUtil.itemTag("forge", "ores");
    public static final TagKey<Item> JADE_ORES = TagUtil.itemTag("forge", "ores/jade");
    public static final TagKey<Item> ORES_IN_STONE = TagUtil.itemTag("forge", "ores_in_ground/stone");
    public static final TagKey<Item> ORES_IN_DEEPSLATE = TagUtil.itemTag("forge", "ores_in_ground/deepslate");

    //Quark Tags
    public static final TagKey<Item> VERTICAL_SLABS = TagUtil.itemTag("quark", "vertical_slabs");
}
