package com.uraneptus.sullysmod.data.server.loot;

import com.uraneptus.sullysmod.core.SMFeatures;
import com.uraneptus.sullysmod.core.other.loot.SMBuiltInLootTables;
import com.uraneptus.sullysmod.core.other.loot.SMFeatureLootItemCondition;
import com.uraneptus.sullysmod.core.registry.SMArtifacts;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;
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
        pOutput.accept(SMBuiltInLootTables.GRAVEL_BIG_PETRIFIED_TREE, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMBlocks.PETRIFIED_SAPLING.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(Items.STICK).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(Items.COAL).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(Items.BONE).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(Items.EMERALD).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_RELIC).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.AMBER_ENCASED_BUG.get()).setWeight(ARTIFACT_COMMON_WEIGHT).when(SMFeatureLootItemCondition.modFeatureCondition(List.of(SMFeatures.AMBER))))
                .add(LootItem.lootTableItem(SMArtifacts.RIBBED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
        )); //At some point ancient gliding pet egg
        pOutput.accept(SMBuiltInLootTables.OVERWORLD_ARTIFACTS, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                //Common
                .add(LootItem.lootTableItem(Items.COAL).setWeight(ARTIFACT_COMMON_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.STICK).setWeight(ARTIFACT_COMMON_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_NORMAL_CAVE))
                .add(LootItem.lootTableItem(Items.BONE).setWeight(ARTIFACT_COMMON_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.STRING).setWeight(ARTIFACT_COMMON_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(SMArtifacts.PRIMITIVE_RING.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.RUSTY_TOOLS.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.BROKEN_VASE.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.BROKEN_BOWL.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.PRIMITIVE_KNIFE.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.PETRIFIED_COOKIE.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.ARROWHEAD.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.LOST_BAG.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.LOST_JOURNAL.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.TORN_CLOTH.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.BROKEN_BOTTLE.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.BROKEN_CUP.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.CAVE_CARROT.get()).setWeight(ARTIFACT_COMMON_WEIGHT)).when(belowY(45))
                .add(LootItem.lootTableItem(SMArtifacts.COPPER_SPOON.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.PRIMITIVE_NECKLACE.get()).setWeight(ARTIFACT_COMMON_WEIGHT))
                //Uncommon
                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(ARTIFACT_UNCOMMON_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_NORMAL_CAVE))
                .add(LootItem.lootTableItem(Items.DEAD_BUSH).setWeight(ARTIFACT_UNCOMMON_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_NORMAL_CAVE))
                .add(LootItem.lootTableItem(Items.ARROW).setWeight(ARTIFACT_UNCOMMON_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.GUNPOWDER).setWeight(ARTIFACT_UNCOMMON_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.STICK).setWeight(ARTIFACT_UNCOMMON_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_DEEPSLATE_CAVE))
                .add(LootItem.lootTableItem(SMArtifacts.COPPER_COG.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT).when(biomeConditionCheck(Biomes.BADLANDS)))
                .add(LootItem.lootTableItem(SMArtifacts.DRIED_CYAN_FLOWER.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT).when(biomeConditionCheck(Biomes.FLOWER_FOREST)))
                .add(LootItem.lootTableItem(SMArtifacts.DRIED_RED_FLOWER.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT).when(biomeConditionCheck(Biomes.FLOWER_FOREST)))
                .add(LootItem.lootTableItem(SMArtifacts.MINERS_HELMET.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT).when(biomeConditionCheck(Biomes.BADLANDS)))
                .add(LootItem.lootTableItem(SMArtifacts.LOST_RECIPE_BOOK.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.BROKEN_MUG.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.LOST_SHOE.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.PETRIFIED_PILLBUG.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT)).when(IS_DEEPSLATE_CAVE)
                .add(LootItem.lootTableItem(SMArtifacts.SOAKED_BOOK.get()).setWeight(ARTIFACT_UNCOMMON_WEIGHT))
                //Rare
                .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(ARTIFACT_RARE_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.GLOW_BERRIES).setWeight(ARTIFACT_RARE_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.STONE_PICKAXE).setWeight(ARTIFACT_RARE_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.NAME_TAG).setWeight(ARTIFACT_RARE_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(ARTIFACT_RARE_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_DEEPSLATE_CAVE))
                .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(ARTIFACT_RARE_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_DEEPSLATE_CAVE))
                .add(LootItem.lootTableItem(SMArtifacts.JADE_AMULET.get()).setWeight(ARTIFACT_RARE_WEIGHT).when(isJungle()))
                .add(LootItem.lootTableItem(SMArtifacts.COPPER_COG.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.DEATH_WHISTLE.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.DRIED_RED_FLOWER.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.DRIED_CYAN_FLOWER.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.METALLIC_SKULL.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.FOSSILISED_SHELLS.get()).setWeight(ARTIFACT_RARE_WEIGHT).when(biomeConditionCheck(Biomes.DESERT)))
                .add(LootItem.lootTableItem(SMArtifacts.FOSSILISED_FISH.get()).setWeight(ARTIFACT_RARE_WEIGHT).when(biomeConditionCheck(Biomes.DESERT)))
                .add(LootItem.lootTableItem(SMArtifacts.TORN_MANUSCRIPT.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.SMALL_GEODE.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.MINERS_HELMET.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.EMERALD_EARRING.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.FROG_IDOL.get()).setWeight(ARTIFACT_RARE_WEIGHT).when(isSwamp()))
                .add(LootItem.lootTableItem(SMArtifacts.JADE_RING.get()).setWeight(ARTIFACT_RARE_WEIGHT)).when(isJungle())
                .add(LootItem.lootTableItem(SMArtifacts.LOST_BESTIARY.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.LOST_PICTURE_BOOK.get()).setWeight(ARTIFACT_RARE_WEIGHT)).when(IS_DEEPSLATE_CAVE)
                .add(LootItem.lootTableItem(SMArtifacts.SNAPPED_PAINTBRUSH.get()).setWeight(ARTIFACT_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.STRANGE_FUR.get()).setWeight(ARTIFACT_RARE_WEIGHT)).when(isMountain())
                //Very Rare
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_RELIC).setWeight(ARTIFACT_VERY_RARE_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.EXPLORER_POTTERY_SHERD).setWeight(ARTIFACT_VERY_RARE_WEIGHT + TRASH_WEIGHT_BONUS))
                .add(LootItem.lootTableItem(Items.IRON_PICKAXE).setWeight(ARTIFACT_VERY_RARE_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_DEEPSLATE_CAVE))
                .add(LootItem.lootTableItem(Items.SKELETON_SKULL).setWeight(ARTIFACT_VERY_RARE_WEIGHT + TRASH_WEIGHT_BONUS).when(IS_DEEPSLATE_CAVE))
                .add(LootItem.lootTableItem(SMArtifacts.SMALL_DENTED_HELMET.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.LOST_CROWN.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.OMINOUS_TABLET.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.MOON_TABLET.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.AMBER_ENCASED_BUG.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.FOSSILISED_SHELLS.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.FOSSILISED_BONE.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.FOSSILISED_FOOTSTEP.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.FOSSILISED_FISH.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.LOST_SKETCHBOOK.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.GOLDEN_BELT_BUCKLE.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.DEEPSLATE_VASE.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.GOLDEN_GOBLET.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.LONG_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(isSwamp()))
                .add(LootItem.lootTableItem(SMArtifacts.WIDE_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(isSnowy().or(isForrest())))
                .add(LootItem.lootTableItem(SMArtifacts.HORNED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(biomeConditionCheck(Biomes.DESERT)))
                .add(LootItem.lootTableItem(SMArtifacts.TINY_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(biomeConditionCheck(Biomes.OLD_GROWTH_SPRUCE_TAIGA)))
                .add(LootItem.lootTableItem(SMArtifacts.FLATBILLED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(biomeConditionCheck(Biomes.PLAINS)))
                .add(LootItem.lootTableItem(SMArtifacts.SNOUTED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(biomeConditionCheck(Biomes.PLAINS)))
                .add(LootItem.lootTableItem(SMArtifacts.CRESTED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(isMountain()))
                .add(LootItem.lootTableItem(SMArtifacts.GIGANTIC_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(biomeConditionCheck(Biomes.WARM_OCEAN)))
                .add(LootItem.lootTableItem(SMArtifacts.CRACKED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT).when(biomeConditionCheck(Biomes.COLD_OCEAN).or(biomeConditionCheck(Biomes.DEEP_COLD_OCEAN))))
                .add(LootItem.lootTableItem(SMArtifacts.BROKEN_FANCY_DAGGER.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.DARK_TABLET.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.EYE_TABLET.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.FOSSILISED_BEAK.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.GLOOMY_TABLET.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.SMALL_DIAMOND_GEODE.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT)).when(belowY(-20))
                .add(LootItem.lootTableItem(SMArtifacts.SMALL_EMERALD_GEODE.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT)).when(isMountain().and(belowY(30)))
                .add(LootItem.lootTableItem(SMArtifacts.STONE_MASK.get()).setWeight(ARTIFACT_VERY_RARE_WEIGHT))
                //Extremely Rare
                .add(LootItem.lootTableItem(SMArtifacts.STONE_IDOL.get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.RED_CAP.get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.MYSTERIOUS_PLATE.get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.FAMILIAR_CUBE.get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.ANCIENT_RELIC.get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.GOLDEN_IDOL.get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.LONG_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.WIDE_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.HORNED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.TINY_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.FLATBILLED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.CRESTED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.GIGANTIC_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.CRACKED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.RIBBED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
                .add(LootItem.lootTableItem(SMArtifacts.SNOUTED_ANCIENT_SKULL.getFirst().get()).setWeight(ARTIFACT_EXTREMELY_RARE_WEIGHT))
        ));
    }
}
