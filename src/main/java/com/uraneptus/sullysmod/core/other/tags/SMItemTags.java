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
}
