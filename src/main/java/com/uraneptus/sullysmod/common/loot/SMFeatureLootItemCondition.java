package com.uraneptus.sullysmod.common.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.uraneptus.sullysmod.core.SMFeatureSelection;
import com.uraneptus.sullysmod.core.registry.SMLootItemConditions;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.List;

public record SMFeatureLootItemCondition(List<SMFeatureSelection> condition) implements LootItemCondition {
    public static final MapCodec<SMFeatureLootItemCondition> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
                SMFeatureSelection.CODEC.listOf().fieldOf("condition").forGetter(SMFeatureLootItemCondition::condition))
                .apply(instance, SMFeatureLootItemCondition::new)
    );


    public static LootItemCondition.Builder modFeatureCondition(List<SMFeatureSelection> features) {
        return () -> new SMFeatureLootItemCondition(features);
    }

    @Override
    public LootItemConditionType getType() {
        return SMLootItemConditions.MOD_FEATURE_CONDITION_TYPE.get();
    }

    @Override
    public boolean test(LootContext lootContext) {
        return condition.stream().allMatch(SMFeatureSelection::isEnabled);
    }
}
