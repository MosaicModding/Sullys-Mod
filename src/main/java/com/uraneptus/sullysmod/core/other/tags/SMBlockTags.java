package com.uraneptus.sullysmod.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class SMBlockTags {
    //Our Tags
    public static final TagKey<Block> PROJECTILES_BOUNCE_ON = TagUtil.blockTag(SullysMod.MOD_ID, "projectiles_bounce_on");
    public static final TagKey<Block> PETRIFIED_LOGS = TagUtil.blockTag(SullysMod.MOD_ID, "petrified_logs");

    public static final TagKey<Block> MELTS_AMBER = TagUtil.blockTag(SullysMod.MOD_ID, "melts_amber");

    //Forge Tags
    public static final TagKey<Block> WAXABLE_COPPER_BLOCKS = TagUtil.blockTag("forge", "waxable_copper_blocks");
    public static final TagKey<Block> WAXED_COPPER_BLOCKS = TagUtil.blockTag("forge", "waxed_copper_blocks");
    public static final TagKey<Block> JADE_ORES = TagUtil.blockTag("forge", "ores/jade");

}
