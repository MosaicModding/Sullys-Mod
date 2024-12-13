package com.uraneptus.sullysmod.core.events;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.SMFeatures;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.uraneptus.sullysmod.core.registry.SMBlocks.*;
import static com.uraneptus.sullysmod.core.registry.SMItems.*;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreativeTabAdditions {

    @SubscribeEvent
    public static void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        if (tab == CreativeModeTabs.INGREDIENTS) {
            addAfter(event, List.of(SMFeatures.JADE), Items.COPPER_INGOT, JADE);
            addAfter(event, List.of(SMFeatures.JADE), Items.RAW_COPPER, ROUGH_JADE);
            addAfter(event, List.of(SMFeatures.JADE), Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, JADE_UPGRADE_SMITHING_TEMPLATE);
            addAfter(event, List.of(SMFeatures.TORTOISE), Items.SCUTE, TORTOISE_SCUTE);
            addAfter(event, List.of(SMFeatures.PIRANHA), Items.BONE, PIRANHA_TOOTH);
            addAfter(event, Items.GLASS_BOTTLE, GLASS_VIAL);
        }

        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            addAfter(event, List.of(SMFeatures.AMBER), Items.MILK_BUCKET, MOLTEN_AMBER_BUCKET);
            addAfter(event, List.of(SMFeatures.TORTOISE), Items.SADDLE, TORTOISE_SHELL);
            addAfter(event, List.of(SMFeatures.LANTERNFISH), Items.COD_BUCKET, LANTERNFISH_BUCKET);
            addAfter(event, List.of(SMFeatures.PIRANHA), Items.COD_BUCKET, PIRANHA_BUCKET);
            addAfter(event, List.of(SMFeatures.JADE), Items.MUSIC_DISC_RELIC, MUSIC_DISC_SCOUR);
            addAfter(event, Items.MUSIC_DISC_RELIC, MUSIC_DISC_SUNKEN_PAST);
        }

        if (tab == CreativeModeTabs.FOOD_AND_DRINKS) {
            addAfter(event, List.of(SMFeatures.LANTERNFISH), Items.COOKED_COD, LANTERNFISH, COOKED_LANTERNFISH);
            addAfter(event, List.of(SMFeatures.PIRANHA), Items.COOKED_COD, PIRANHA, COOKED_PIRANHA);
        }

        if (tab == CreativeModeTabs.COMBAT) {
            addAfter(event, List.of(SMFeatures.JADE), Items.SHIELD, JADE_SHIELD);
            addAfter(event, List.of(SMFeatures.JADE), Items.DIAMOND_HORSE_ARMOR, JADE_HORSE_ARMOR);
            addAfter(event, List.of(SMFeatures.PIRANHA), Items.TRIDENT, THROWING_KNIFE);
        }

        if (tab == CreativeModeTabs.SPAWN_EGGS) { //Sorted alphabetically
            addAfter(event, List.of(SMFeatures.TORTOISE), Items.TADPOLE_SPAWN_EGG, TORTOISE_SPAWN_EGG);
            addAfter(event, List.of(SMFeatures.BOULDERING_ZOMBIE), Items.BLAZE_SPAWN_EGG, BOULDERING_ZOMBIE_SPAWN_EGG);
            addAfter(event, List.of(SMFeatures.JUNGLE_SPIDER), Items.IRON_GOLEM_SPAWN_EGG, JUNGLE_SPIDER_SPAWN_EGG);
            addAfter(event, List.of(SMFeatures.LANTERNFISH), Items.IRON_GOLEM_SPAWN_EGG, LANTERNFISH_SPAWN_EGG);
            addAfter(event, List.of(SMFeatures.PIRANHA), Items.PILLAGER_SPAWN_EGG, PIRANHA_SPAWN_EGG);
        }

        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            addAfter(event, List.of(SMFeatures.JADE), Items.DEEPSLATE_COPPER_ORE, JADE_ORE, DEEPSLATE_JADE_ORE);
            addAfter(event, List.of(SMFeatures.JADE), Items.RAW_COPPER_BLOCK, ROUGH_JADE_BLOCK);
            addAfter(event, List.of(SMFeatures.TORTOISE), Items.TURTLE_EGG, TORTOISE_EGG);
            addAfter(event, List.of(SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER), Items.CHERRY_LOG, PETRIFIED_LOG);
            addAfter(event, List.of(SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER), Items.CHERRY_SAPLING, PETRIFIED_SAPLING);
            addAfter(event, List.of(SMFeatures.AMBER), Items.COBWEB, AMBER, ROUGH_AMBER);
        }

        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            addAfter(event, List.of(SMFeatures.JADE), Items.WAXED_OXIDIZED_CUT_COPPER_SLAB,
                    ROUGH_JADE_BLOCK, ROUGH_JADE_BRICKS, ROUGH_JADE_BRICK_STAIRS, ROUGH_JADE_BRICK_SLAB, ROUGH_JADE_BRICK_WALL,
                    JADE_BLOCK, JADE_BRICKS, JADE_BRICK_STAIRS, JADE_BRICK_SLAB, JADE_BRICK_WALL, JADE_PILLAR, CHISELED_JADE, JADE_TOTEM
            );
            addAfter(event, List.of(SMFeatures.JADE, SMFeatures.GEM_LANTERNS), Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, JADE_LANTERN);
            addAfter(event, List.of(SMFeatures.AMBER), Items.WAXED_OXIDIZED_CUT_COPPER_SLAB,
                    ROUGH_AMBER, CHISELED_AMBER, AMBER_PILLAR, AMBER_BRICKS, AMBER_BRICK_STAIRS, AMBER_BRICK_SLAB, AMBER_BRICK_WALL
            );
            addAfter(event, List.of(SMFeatures.AMBER, SMFeatures.GEM_LANTERNS), Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, AMBER_LANTERN);
            addAfter(event, List.of(SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER), Items.CHERRY_BUTTON,
                    PETRIFIED_LOG, PETRIFIED_WOOD, STRIPPED_PETRIFIED_LOG, STRIPPED_PETRIFIED_WOOD, PETRIFIED_PLANKS, PETRIFIED_STAIRS,
                    PETRIFIED_SLAB, PETRIFIED_FENCE, PETRIFIED_FENCE_GATE, PETRIFIED_DOOR, PETRIFIED_TRAPDOOR, PETRIFIED_PRESSURE_PLATE, PETRIFIED_BUTTON
            );
            addAfter(event, List.of(SMFeatures.GEM_LANTERNS), Items.DIAMOND_BLOCK, DIAMOND_LANTERN);
            addAfter(event, List.of(SMFeatures.GEM_LANTERNS), Items.EMERALD_BLOCK, EMERALD_LANTERN);
            addAfter(event, List.of(SMFeatures.GEM_LANTERNS), Items.LAPIS_BLOCK, LAPIS_LANTERN);
            addAfter(event, List.of(SMFeatures.GEM_LANTERNS), Items.AMETHYST_BLOCK, AMETHYST_LANTERN);
            addAfter(event, List.of(SMFeatures.GEM_LANTERNS), Items.QUARTZ_BLOCK, QUARTZ_LANTERN);
        }

        if (tab == CreativeModeTabs.REDSTONE_BLOCKS) {
            addAfter(event, List.of(SMFeatures.JADE), Items.DROPPER, JADE_FLINGER_TOTEM);
            addAfter(event, List.of(SMFeatures.COPPER_BUTTONS), Items.STONE_BUTTON, COPPER_BUTTON, EXPOSED_COPPER_BUTTON, WEATHERED_COPPER_BUTTON,
                    OXIDIZED_COPPER_BUTTON, WAXED_COPPER_BUTTON, WAXED_EXPOSED_COPPER_BUTTON, WAXED_WEATHERED_COPPER_BUTTON, WAXED_OXIDIZED_COPPER_BUTTON
            );
        }

        if (tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            addAfter(event, List.of(SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER), Items.CHERRY_HANGING_SIGN, PETRIFIED_SIGN.getFirst(), PETRIFIED_HANGING_SIGN.getFirst());
            addAfter(event, List.of(SMFeatures.AMBER), Items.INFESTED_DEEPSLATE, AMBER);
            addAfter(event, List.of(SMFeatures.ITEM_STAND), Items.ARMOR_STAND, ITEM_STAND);
            addAfter(event, List.of(SMFeatures.GEM_LANTERNS, SMFeatures.AMBER), Items.PEARLESCENT_FROGLIGHT, AMBER_LANTERN);
            addAfter(event, List.of(SMFeatures.GEM_LANTERNS, SMFeatures.JADE), Items.PEARLESCENT_FROGLIGHT, JADE_LANTERN);
            addAfter(event, List.of(SMFeatures.GEM_LANTERNS), Items.PEARLESCENT_FROGLIGHT, DIAMOND_LANTERN, EMERALD_LANTERN, LAPIS_LANTERN, AMETHYST_LANTERN, QUARTZ_LANTERN);
        }
    }

    @SafeVarargs
    private static void addBefore(BuildCreativeModeTabContentsEvent event, List<SMFeatures> features, Item before, Supplier<? extends ItemLike>... itemsToAdd) {
        if (features.stream().allMatch(SMFeatures::isEnabled)) {
            addBefore(event, before, itemsToAdd);
        }
    }

    @SafeVarargs
    private static void addBefore(BuildCreativeModeTabContentsEvent event, Item before, Supplier<? extends ItemLike>... itemsToAdd) {
        for (Supplier<? extends ItemLike> item : itemsToAdd) {
            event.getEntries().putBefore(before.getDefaultInstance(), item.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    @SafeVarargs
    private static void addAfter(BuildCreativeModeTabContentsEvent event, List<SMFeatures> features, Item before, Supplier<? extends ItemLike>... itemsToAdd) {
        if (features.stream().allMatch(SMFeatures::isEnabled)) {
            addAfter(event, before, itemsToAdd);
        }
    }

    @SafeVarargs
    private static void addAfter(BuildCreativeModeTabContentsEvent event, Item before, Supplier<? extends ItemLike>... itemsToAdd) {
        List<Supplier<? extends ItemLike>> list = new ArrayList<>(List.of(itemsToAdd));
        Collections.reverse(list);
        for (Supplier<? extends ItemLike> item : list) {
            event.getEntries().putAfter(before.getDefaultInstance(), item.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
