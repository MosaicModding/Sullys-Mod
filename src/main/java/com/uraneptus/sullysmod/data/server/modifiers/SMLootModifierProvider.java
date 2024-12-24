package com.uraneptus.sullysmod.data.server.modifiers;

import com.teamabnormals.blueprint.common.loot.modification.LootModifierProvider;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolEntriesModifier;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.SMFeatures;
import com.uraneptus.sullysmod.core.other.loot.SMFeatureLootItemCondition;
import com.uraneptus.sullysmod.core.other.loot.SMLootConditions;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings({"unused", "SameParameterValue"})
public class SMLootModifierProvider extends LootModifierProvider {

    public SMLootModifierProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(SullysMod.MOD_ID, packOutput, lookupProvider);
    }

    @Override
    protected void registerEntries(HolderLookup.Provider provider) {
        this.entry("lanternfish_to_fishing").selects(BuiltInLootTables.FISHING_FISH).addModifier(basicModifier(createLootEntryWithCondition(SMItems.LANTERNFISH.get(), 55, 1, 1, SMLootConditions.ON_LANTERNFISH_HEIGHT, SMFeatures.LANTERNFISH)));
        this.entry("piranha_to_fishing").selects(BuiltInLootTables.FISHING_FISH).addModifier(basicModifier(createLootEntryWithCondition(SMItems.PIRANHA.get(), 40, 1, 1, SMLootConditions.getPiranhaBiomes(), SMFeatures.PIRANHA)));
        this.entry("music_disc_scour_to_jungle_temple").selects(BuiltInLootTables.JUNGLE_TEMPLE).addModifier(basicModifier(createMusicDiscLootEntry(SMItems.MUSIC_DISC_SCOUR.get(), 4, SMFeatures.JADE)));
        this.entry("music_disc_sunken_past_to_elder_guardian").selects(EntityType.ELDER_GUARDIAN.getDefaultLootTable()).addModifier(basicModifier(1, createMusicDiscLootEntry(SMItems.MUSIC_DISC_SUNKEN_PAST.get(), 2)));
        this.entry("jade_smithing_template_to_jungle_temple").selects(BuiltInLootTables.JUNGLE_TEMPLE).addModifier(basicModifier(createLootEntry(SMItems.JADE_UPGRADE_SMITHING_TEMPLATE.get(), 4, 1, 2, SMFeatures.JADE)));
        this.entry("throwing_knife_to_shipwreck_supply").selects(BuiltInLootTables.SHIPWRECK_SUPPLY).addModifier(basicModifier(createLootEntry(SMItems.THROWING_KNIFE.get(), 3, 2, 5, SMFeatures.PIRANHA)));
        this.entry("throwing_knife_to_simple_dungeon").selects(BuiltInLootTables.SIMPLE_DUNGEON).addModifier(basicModifier(createLootEntry(SMItems.THROWING_KNIFE.get(), 15, 3, 7, SMFeatures.PIRANHA)));
        this.entry("throwing_knife_to_jungle_temple_dispenser").selects(BuiltInLootTables.JUNGLE_TEMPLE_DISPENSER).addModifier(basicModifier(createLootEntry(SMItems.THROWING_KNIFE.get(), 10, 2, 7, SMFeatures.PIRANHA)));
        this.entry("throwing_knife_to_underwater_ruin_big").selects(BuiltInLootTables.UNDERWATER_RUIN_BIG).addModifier(basicModifier(createLootEntry(SMItems.THROWING_KNIFE.get(), 5, 1, 6, SMFeatures.PIRANHA)));
        this.entry("throwing_knife_to_underwater_ruin_small").selects(BuiltInLootTables.UNDERWATER_RUIN_SMALL).addModifier(basicModifier(createLootEntry(SMItems.THROWING_KNIFE.get(), 5, 1, 5, SMFeatures.PIRANHA)));
        this.entry("throwing_knife_to_buried_treasure").selects(BuiltInLootTables.BURIED_TREASURE).addModifier(basicModifier(createLootEntry(SMItems.THROWING_KNIFE.get(), 2, 1, 7, SMFeatures.PIRANHA)));
        this.entry("throwing_knife_to_desert_pyramid").selects(BuiltInLootTables.DESERT_PYRAMID).addModifier(basicModifier(createLootEntry(SMItems.THROWING_KNIFE.get(), 20, 1, 8, SMFeatures.PIRANHA)));
        /*
        this.entry("bug_meat_to_spider").selects(new ResourceLocation("entities/spider")).addModifier(basicModifier(createBugMeatEntry(0.2F)));
        this.entry("bug_meat_to_cave_spider").selects(new ResourceLocation("entities/cave_spider")).addModifier(basicModifier(createBugMeatEntry(0.2F)));
        this.entry("bug_meat_to_silverfish").selects(new ResourceLocation("entities/silverfish")).addModifier(basicModifier(createBugMeatEntry(0.05F)));
        this.entry("bug_meat_to_endermite").selects(new ResourceLocation("entities/endermite")).addModifier(basicModifier(createBugMeatEntry(0.05F)));
        this.entry("bug_meat_to_bee").selects(new ResourceLocation("entities/bee")).addModifier(basicModifier(createBugMeatEntry(0.05F)));

         */
    }

    private static LootPoolEntriesModifier basicModifier(LootPoolEntryContainer... containers) {
        return new LootPoolEntriesModifier(false, 0, containers);
    }

    private static LootPoolEntriesModifier basicModifier(int index, LootPoolEntryContainer... containers) {
        return new LootPoolEntriesModifier(false, index, containers);
    }

    private static LootPoolEntryContainer createLootEntryWithCondition(ItemLike item, int weight, int min, int max, LootItemCondition.Builder condition) {
        return LootItem.lootTableItem(item).setWeight(weight).when(condition).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).build();
    }

    private static LootPoolEntryContainer createLootEntry(ItemLike item, int weight, int min, int max) {
        return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).build();
    }

    private static LootPoolEntryContainer createLootEntryWithCondition(ItemLike item, int weight, int min, int max, LootItemCondition.Builder condition, SMFeatures... features) {
        return LootItem.lootTableItem(item).setWeight(weight).when(SMFeatureLootItemCondition.modFeatureCondition(List.of(features))).when(condition).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).build();
    }

    private static LootPoolEntryContainer createLootEntry(ItemLike item, int weight, int min, int max, SMFeatures... features) {
        return LootItem.lootTableItem(item).setWeight(weight).when(SMFeatureLootItemCondition.modFeatureCondition(List.of(features))).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).build();
    }

    private static LootPoolEntryContainer createMusicDiscLootEntry(ItemLike item, int weight) {
        return LootItem.lootTableItem(item).setWeight(weight).build();
    }

    private static LootPoolEntryContainer createMusicDiscLootEntry(ItemLike item, int weight, SMFeatures... features) {
        return LootItem.lootTableItem(item).setWeight(weight).when(SMFeatureLootItemCondition.modFeatureCondition(List.of(features))).build();
    }

    private static LootPoolEntryContainer createBugMeatEntry(float pChance) {
        return LootItem.lootTableItem(SMItems.BUG_MEAT.get()).when(SMFeatureLootItemCondition.modFeatureCondition(List.of(SMFeatures.BUG_MEAT))).when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(pChance, 1.2F)).build();
    }
}
