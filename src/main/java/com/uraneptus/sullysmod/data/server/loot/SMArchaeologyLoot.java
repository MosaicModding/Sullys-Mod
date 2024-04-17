package com.uraneptus.sullysmod.data.server.loot;

import com.uraneptus.sullysmod.core.other.loot.SMBuiltInLootTables;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

public class SMArchaeologyLoot implements LootTableSubProvider {

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> pOutput) {
        pOutput.accept(SMBuiltInLootTables.GRAVEL_PETRIFIED_SAPLING_TREE, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(SMBlocks.AMBER.get())).add(LootItem.lootTableItem(SMBlocks.PETRIFIED_SAPLING.get())).add(LootItem.lootTableItem(Items.STICK)).add(LootItem.lootTableItem(Items.COAL))));
        pOutput.accept(SMBuiltInLootTables.GRAVEL_BIG_PETRIFIED_TREE, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.DIAMOND)).add(LootItem.lootTableItem(SMBlocks.PETRIFIED_SAPLING.get())).add(LootItem.lootTableItem(Items.STICK)).add(LootItem.lootTableItem(Items.COAL)).add(LootItem.lootTableItem(Items.BONE)).add(LootItem.lootTableItem(Items.EMERALD)).add(LootItem.lootTableItem(Items.MUSIC_DISC_RELIC)).add(LootItem.lootTableItem(SMItems.AMBER_ENCASED_BUG.get())))); //At some point ancient gliding pet egg
    }
}
