package com.uraneptus.sullysmod.data.server.loot;

import com.uraneptus.sullysmod.core.other.loot.SMBuiltInLootTables;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

import static com.uraneptus.sullysmod.core.other.loot.SMLootConditions.*;

public class SMArchaeologyLoot implements LootTableSubProvider {
    public static final int TRASH_WEIGHT_BONUS = 6;
    public static final int ARTIFACT_COMMON_WEIGHT = 50;
    public static final int ARTIFACT_UNCOMMON_WEIGHT = 40;
    public static final int ARTIFACT_RARE_WEIGHT = 30;
    public static final int ARTIFACT_VERY_RARE_WEIGHT = 20;
    public static final int ARTIFACT_EXTREMELY_RARE_WEIGHT = 10;

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> pOutput) {
        pOutput.accept(SMBuiltInLootTables.GRAVEL_PETRIFIED_SAPLING_TREE, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(SMBlocks.AMBER.get())).add(LootItem.lootTableItem(SMBlocks.PETRIFIED_SAPLING.get())).add(LootItem.lootTableItem(Items.STICK)).add(LootItem.lootTableItem(Items.COAL))));
        pOutput.accept(SMBuiltInLootTables.GRAVEL_BIG_PETRIFIED_TREE, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.DIAMOND)).add(LootItem.lootTableItem(SMBlocks.PETRIFIED_SAPLING.get())).add(LootItem.lootTableItem(Items.STICK)).add(LootItem.lootTableItem(Items.COAL)).add(LootItem.lootTableItem(Items.BONE)).add(LootItem.lootTableItem(Items.EMERALD)).add(LootItem.lootTableItem(Items.MUSIC_DISC_RELIC)).add(LootItem.lootTableItem(SMItems.AMBER_ENCASED_BUG.get())))); //At some point ancient gliding pet egg
        pOutput.accept(SMBuiltInLootTables.OVERWORLD_ARTIFACTS, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                //Common
                .add(LootItem.lootTableItem(Items.COAL).setWeight(ARTIFACT_COMMON_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.STICK).setWeight(ARTIFACT_COMMON_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_NORMAL_CAVE))
                .add(LootItem.lootTableItem(Items.BONE).setWeight(ARTIFACT_COMMON_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.STRING).setWeight(ARTIFACT_COMMON_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(SMItems.PRIMITIVE_RING.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.RUSTY_TOOLS.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.BROKEN_VASE.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.BROKEN_BOWL.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.PRIMITIVE_KNIFE.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.PETRIFIED_COOKIE.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.ARROWHEAD.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.LOST_BAG.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.LOST_JOURNAL.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.TORN_CLOTH.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.BROKEN_BOTTLE.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                //Uncommon
                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(ARTIFACT_UNCOMMON_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_NORMAL_CAVE))
                .add(LootItem.lootTableItem(Items.DEAD_BUSH).setWeight(ARTIFACT_UNCOMMON_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_NORMAL_CAVE))
                .add(LootItem.lootTableItem(Items.ARROW).setWeight(ARTIFACT_UNCOMMON_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.GUNPOWDER).setWeight(ARTIFACT_UNCOMMON_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.STICK).setWeight(ARTIFACT_UNCOMMON_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_DEEPSLATE_CAVE))
                .add(LootItem.lootTableItem(SMItems.COPPER_COG.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT).when(biomeConditionCheck(Biomes.BADLANDS)))
                .add(LootItem.lootTableItem(SMItems.DRIED_CYAN_FLOWER.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT).when(biomeConditionCheck(Biomes.FLOWER_FOREST)))
                .add(LootItem.lootTableItem(SMItems.DRIED_RED_FLOWER.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT).when(biomeConditionCheck(Biomes.FLOWER_FOREST)))
                .add(LootItem.lootTableItem(SMItems.MINERS_HELMET.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT).when(biomeConditionCheck(Biomes.BADLANDS)))
                .add(LootItem.lootTableItem(SMItems.LOST_RECIPE_BOOK.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT))
                //Rare
                .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(ARTIFACT_RARE_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.GLOW_BERRIES).setWeight(ARTIFACT_RARE_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.STONE_PICKAXE).setWeight(ARTIFACT_RARE_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(ARTIFACT_RARE_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_DEEPSLATE_CAVE))
                .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(ARTIFACT_RARE_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_DEEPSLATE_CAVE))
                .add(LootItem.lootTableItem(SMItems.JADE_AMULET.get()).setWeight(ARTIFACT_RARE_WEIGHT).when(isJungle()))
                .add(LootItem.lootTableItem(SMItems.COPPER_COG.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.DEATH_WHISTLE.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.DRIED_RED_FLOWER.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.DRIED_CYAN_FLOWER.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.METALLIC_SKULL.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.FOSSILISED_SHELLS.get()).setWeight(ARTIFACT_RARE_WEIGHT).when(biomeConditionCheck(Biomes.DESERT)))
                .add(LootItem.lootTableItem(SMItems.FOSSILISED_FISH.get()).setWeight(ARTIFACT_RARE_WEIGHT).when(biomeConditionCheck(Biomes.DESERT)))
                .add(LootItem.lootTableItem(SMItems.TORN_MANUSCRIPT.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.SMALL_GEODE.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.MINERS_HELMET.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.EMERALD_EARRING.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.FROG_IDOL.get()).setWeight(ARTIFACT_RARE_WEIGHT).when(isSwamp()))
                //Very Rare
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_RELIC).setWeight(ARTIFACT_VERY_RARE_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.EXPLORER_POTTERY_SHERD).setWeight(ARTIFACT_VERY_RARE_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.IRON_PICKAXE).setWeight(ARTIFACT_VERY_RARE_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_DEEPSLATE_CAVE))
                .add(LootItem.lootTableItem(Items.SKELETON_SKULL).setWeight(ARTIFACT_VERY_RARE_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_DEEPSLATE_CAVE))
                .add(LootItem.lootTableItem(SMItems.SMALL_DENTED_HELMET.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.LOST_CROWN.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.OMINOUS_TABLET.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.MOON_TABLET.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.AMBER_ENCASED_BUG.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.FOSSILISED_SHELLS.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.FOSSILISED_BONE.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.FOSSILISED_FOOTSTEP.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.FOSSILISED_FISH.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.LOST_SKETCHBOOK.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.GOLDEN_BELT_BUCKLE.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.DEEPSLATE_VASE.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.GOLDEN_GOBLET.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMBlocks.LONG_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(isSwamp()))
                .add(LootItem.lootTableItem(SMBlocks.WIDE_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(isSnowy().or(isForrest())))
                .add(LootItem.lootTableItem(SMBlocks.HORNED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(biomeConditionCheck(Biomes.DESERT)))
                .add(LootItem.lootTableItem(SMBlocks.TINY_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(biomeConditionCheck(Biomes.OLD_GROWTH_SPRUCE_TAIGA)))
                .add(LootItem.lootTableItem(SMBlocks.FLATBILLED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(biomeConditionCheck(Biomes.PLAINS)))
                .add(LootItem.lootTableItem(SMBlocks.CRESTED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(isMountain()))
                .add(LootItem.lootTableItem(SMBlocks.GIGANTIC_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(biomeConditionCheck(Biomes.WARM_OCEAN)))
                .add(LootItem.lootTableItem(SMBlocks.CRACKED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(biomeConditionCheck(Biomes.COLD_OCEAN).or(biomeConditionCheck(Biomes.DEEP_COLD_OCEAN))))
                //Extremely Rare
                .add(LootItem.lootTableItem(SMItems.STONE_IDOL.get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.RED_CAP.get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.MYSTERIOUS_PLATE.get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMItems.FAMILIAR_CUBE.get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMBlocks.LONG_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMBlocks.WIDE_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMBlocks.HORNED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMBlocks.TINY_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMBlocks.FLATBILLED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMBlocks.CRESTED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMBlocks.GIGANTIC_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMBlocks.CRACKED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
        ));
    }
}
