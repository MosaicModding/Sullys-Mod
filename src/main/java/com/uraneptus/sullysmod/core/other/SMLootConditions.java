package com.uraneptus.sullysmod.core.other;

import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class SMLootConditions {
    public static final LootItemCondition.Builder ON_LANTERNFISH_HEIGHT = LocationCheck.checkLocation(LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.between(-57.0D, 16.0D)));

}
