package com.uraneptus.sullysmod.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class SMEntityTags {
    //Mod Tags
    public static final TagKey<EntityType<?>> SCARES_TORTOISES = TagUtil.entityTypeTag(SullysMod.MOD_ID, "scares_tortoises");
    public static final TagKey<EntityType<?>> CANNOT_BOUNCE = TagUtil.entityTypeTag(SullysMod.MOD_ID, "cannot_bounce");
    public static final TagKey<EntityType<?>> CANNOT_BE_FLUNG = TagUtil.entityTypeTag(SullysMod.MOD_ID, "cannot_be_flung");
}