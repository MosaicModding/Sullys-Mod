package com.uraneptus.sullysmod.core.data.server.loot;

import com.google.common.collect.ImmutableSet;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SMEntityLoot extends EntityLootSubProvider {
    private static final Set<EntityType<?>> SPECIAL_LOOT_TYPES = ImmutableSet.of(SMEntityTypes.COPPER_GOLEM.get());

    protected SMEntityLoot() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ForgeRegistries.ENTITY_TYPES.getValues().stream().filter(entityType -> ForgeRegistries.ENTITY_TYPES.getKey(entityType) != null && SullysMod.MOD_ID.equals(ForgeRegistries.ENTITY_TYPES.getKey(entityType).getNamespace()));
    }

    @Override
    protected boolean canHaveLootTable(EntityType<?> entitytype) {
        return !SPECIAL_LOOT_TYPES.contains(entitytype) && entitytype.getCategory() == MobCategory.MISC;
    }

    @Override
    public void generate() {
        this.add(SMEntityTypes.LANTERNFISH.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(SMItems.LANTERNFISH.get()).apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.BONE_MEAL)).when(LootItemRandomChanceCondition.randomChance(0.05F))));
        this.add(SMEntityTypes.COPPER_GOLEM.get(), LootTable.lootTable());
        this.add(SMEntityTypes.TORTOISE.get(), LootTable.lootTable());
    }
}
