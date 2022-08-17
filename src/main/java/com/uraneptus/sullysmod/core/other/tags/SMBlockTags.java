package com.uraneptus.sullysmod.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class SMBlockTags {
    //Our Tags
    public static final TagKey<Block> PROJECTILES_BOUNCE_ON = TagUtil.blockTag(SullysMod.MOD_ID, "projectiles_bounce_on");

    //Forge Tags
    public static final TagKey<Block> WAXABLE_COPPER_BLOCKS = TagUtil.blockTag("forge", "waxable_copper_blocks");
    public static final TagKey<Block> WAXED_COPPER_BLOCKS = TagUtil.blockTag("forge", "waxed_copper_blocks");
    public static final TagKey<Block> STORAGE_BLOCKS = TagUtil.blockTag("forge", "storage_blocks");
    public static final TagKey<Block> ORES = TagUtil.blockTag("forge", "ores");
    public static final TagKey<Block> JADE_ORES = TagUtil.blockTag("forge", "ores/jade");
    public static final TagKey<Block> ORES_IN_STONE = TagUtil.blockTag("forge", "ores_in_ground/stone");
    public static final TagKey<Block> ORES_IN_DEEPSLATE = TagUtil.blockTag("forge", "ores_in_ground/deepslate");

    //Quark Tags
    public static final TagKey<Block> VERTICAL_SLABS = TagUtil.blockTag("quark", "vertical_slabs");
}
