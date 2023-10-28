package com.uraneptus.sullysmod.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;

public class SMMobEffectTags {

    public static final TagKey<MobEffect> JUNGLE_SPIDER_BENEFICIAL_EFFECTS = TagUtil.mobEffectTag(SullysMod.MOD_ID, "jungle_spider_beneficial_effects");
    public static final TagKey<MobEffect> JUNGLE_SPIDER_HARMFUL_EFFECTS = TagUtil.mobEffectTag(SullysMod.MOD_ID, "jungle_spider_harmful_effects");
}
