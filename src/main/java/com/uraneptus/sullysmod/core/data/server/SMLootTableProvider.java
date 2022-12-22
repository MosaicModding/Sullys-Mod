package com.uraneptus.sullysmod.core.data.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SMLootTableProvider extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTables = ImmutableList.of(Pair.of(SMBlockLoot::new, LootContextParamSets.BLOCK), Pair.of(SMEntityLoot::new, LootContextParamSets.ENTITY));

    public SMLootTableProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return this.lootTables;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
    }

    private static class SMBlockLoot extends BlockLoot {
        @Override
        protected void addTables() {
            //Jade Ores
            this.add(SMBlocks.JADE_ORE.get(), SMBlockLoot::createJadeOreDrops);
            this.add(SMBlocks.DEEPSLATE_JADE_ORE.get(), SMBlockLoot::createJadeOreDrops);

            //Raw Jade
            this.dropSelf(SMBlocks.ROUGH_JADE_BLOCK.get());
            this.dropSelf(SMBlocks.ROUGH_JADE_BRICKS.get());
            this.dropSelf(SMBlocks.ROUGH_JADE_TILES.get());
            this.dropSelf(SMBlocks.SMOOTHED_ROUGH_JADE.get());
            this.dropSelf(SMBlocks.ROUGH_JADE_BRICK_STAIRS.get());
            this.dropSelf(SMBlocks.ROUGH_JADE_TILE_STAIRS.get());
            this.dropSelf(SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS.get());
            this.add(SMBlocks.ROUGH_JADE_BRICK_SLAB.get(), BlockLoot::createSlabItemTable);
            this.add(SMBlocks.ROUGH_JADE_TILE_SLAB.get(), BlockLoot::createSlabItemTable);
            this.add(SMBlocks.SMOOTHED_ROUGH_JADE_SLAB.get(), BlockLoot::createSlabItemTable);
            this.add(SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB.get(), BlockLoot::createSlabItemTable);
            this.add(SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB.get(), BlockLoot::createSlabItemTable);
            this.add(SMBlocks.SMOOTHED_ROUGH_JADE_VERTICAL_SLAB.get(), BlockLoot::createSlabItemTable);

            //Jade
            this.dropSelf(SMBlocks.POLISHED_JADE_BLOCK.get());
            this.dropSelf(SMBlocks.POLISHED_JADE_BRICKS.get());
            this.dropSelf(SMBlocks.POLISHED_SMALL_JADE_BRICKS.get());
            this.dropSelf(SMBlocks.POLISHED_JADE_TILES.get());
            this.dropSelf(SMBlocks.POLISHED_JADE_SHINGLES.get());
            this.dropSelf(SMBlocks.POLISHED_CHISELED_JADE.get());
            this.dropSelf(SMBlocks.POLISHED_JADE_PILLAR.get());
            this.dropSelf(SMBlocks.JADE_TOTEM.get());
            this.dropSelf(SMBlocks.JADE_FLINGER_TOTEM.get());
            this.dropSelf(SMBlocks.POLISHED_JADE_BRICK_STAIRS.get());
            this.dropSelf(SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get());
            this.dropSelf(SMBlocks.POLISHED_JADE_TILE_STAIRS.get());
            this.dropSelf(SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get());
            this.add(SMBlocks.POLISHED_JADE_BRICK_SLAB.get(), BlockLoot::createSlabItemTable);
            this.add(SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get(), BlockLoot::createSlabItemTable);
            this.add(SMBlocks.POLISHED_JADE_TILE_SLAB.get(), BlockLoot::createSlabItemTable);
            this.add(SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get(), BlockLoot::createSlabItemTable);
            this.add(SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB.get(), BlockLoot::createSlabItemTable);
            this.add(SMBlocks.POLISHED_SMALL_JADE_BRICK_VERTICAL_SLAB.get(), BlockLoot::createSlabItemTable);
            this.add(SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB.get(), BlockLoot::createSlabItemTable);
            this.add(SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB.get(), BlockLoot::createSlabItemTable);

            //Copper Buttons
            this.dropSelf(SMBlocks.COPPER_BUTTON.get());
            this.dropSelf(SMBlocks.EXPOSED_COPPER_BUTTON.get());
            this.dropSelf(SMBlocks.WEATHERED_COPPER_BUTTON.get());
            this.dropSelf(SMBlocks.OXIDIZED_COPPER_BUTTON.get());
            this.dropSelf(SMBlocks.WAXED_COPPER_BUTTON.get());
            this.dropSelf(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get());
            this.dropSelf(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get());
            this.dropSelf(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get());

            //Tortoise Egg
            this.dropWhenSilkTouch(SMBlocks.TORTOISE_EGG.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return SMBlocks.HELPER.getDeferredRegister().getEntries().stream().map(RegistryObject::get)::iterator;
        }

        protected static LootTable.Builder createJadeOreDrops(Block block) {
            return createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(SMItems.ROUGH_JADE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
        }
    }

    private static class SMEntityLoot extends EntityLoot {
        private static final Set<EntityType<?>> SPECIAL_LOOT_TYPES = ImmutableSet.of(SMEntityTypes.COPPER_GOLEM.get());

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return ForgeRegistries.ENTITY_TYPES.getValues().stream().filter(entityType -> ForgeRegistries.ENTITY_TYPES.getKey(entityType) != null && SullysMod.MOD_ID.equals(ForgeRegistries.ENTITY_TYPES.getKey(entityType).getNamespace())).collect(Collectors.toSet());
        }

        @Override
        protected boolean isNonLiving(EntityType<?> entitytype) {
            return !SPECIAL_LOOT_TYPES.contains(entitytype) && entitytype.getCategory() == MobCategory.MISC;
        }

        @Override
        protected void addTables() {
            this.add(SMEntityTypes.LANTERNFISH.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(SMItems.LANTERNFISH.get()).apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.BONE_MEAL)).when(LootItemRandomChanceCondition.randomChance(0.05F))));
            this.add(SMEntityTypes.COPPER_GOLEM.get(), LootTable.lootTable());
            this.add(SMEntityTypes.TORTOISE.get(), LootTable.lootTable());
            this.add(SMEntityTypes.RASCAL.get(), LootTable.lootTable());
            this.add(SMEntityTypes.CHAMELEON.get(), LootTable.lootTable());

            SullysMod.LOGGER.info("ENTITY LOOT TABLE GENERATION COMPLETE");
        }
    }
}
