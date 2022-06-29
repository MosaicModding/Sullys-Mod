package com.uraneptus.sullysmod.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class SMBiomeTags {
    public static final TagKey<Biome> JADE_GENERATES_IN = TagUtil.biomeTag(SullysMod.MOD_ID, "jade_generates_in");
    public static final TagKey<Biome> TORTOISES_SPAWN_IN = TagUtil.biomeTag(SullysMod.MOD_ID, "tortoises_spawn_in");
    public static final TagKey<Biome> LANTERNFISH_SPAWN_IN = TagUtil.biomeTag(SullysMod.MOD_ID, "lanternfish_spawn_in");
}
