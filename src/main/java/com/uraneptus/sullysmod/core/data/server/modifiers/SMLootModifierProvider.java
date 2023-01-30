package com.uraneptus.sullysmod.core.data.server.modifiers;

import com.teamabnormals.blueprint.common.loot.modification.LootModifierProvider;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolEntriesModifier;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.SMLootConditions;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Collections;

@SuppressWarnings({"unused", "SameParameterValue"})
public class SMLootModifierProvider extends LootModifierProvider {

    public SMLootModifierProvider(DataGenerator gen) {
        super(gen, SullysMod.MOD_ID);
    }

    @Override
    protected void registerEntries() {
        this.entry("add_lanternfish_to_fishing").selects(BuiltInLootTables.FISHING_FISH).addModifier(new LootPoolEntriesModifier(false, 0, Collections.singletonList(createLootEntryWithCondition(SMItems.LANTERNFISH.get(), 55, 1, 1, SMLootConditions.ON_LANTERNFISH_HEIGHT))));
        this.entry("add_music_disc_scour_to_jungle_temple").selects(BuiltInLootTables.JUNGLE_TEMPLE).addModifier(new LootPoolEntriesModifier(false, 0, Collections.singletonList(createMusicDiscLootEntry(SMItems.MUSIC_DISC_SCOUR.get(), 4))));

    }

    private static LootPoolEntryContainer createLootEntryWithCondition(ItemLike item, int weight, int min, int max, LootItemCondition.Builder condition) {
        return LootItem.lootTableItem(item).setWeight(weight).when(condition).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).build();
    }

    private static LootPoolEntryContainer createLootEntry(ItemLike item, int weight, int min, int max) {
        return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).build();
    }

    private static LootPoolEntryContainer createMusicDiscLootEntry(ItemLike item, int weight) {
        return LootItem.lootTableItem(item).setWeight(weight).build();
    }
}
