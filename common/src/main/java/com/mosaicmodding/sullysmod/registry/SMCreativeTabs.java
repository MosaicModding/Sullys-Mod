package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.core.SMConstants;
import com.mosaicmodding.sullysmod.core.config.SMClientConfig;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.platform.TabAdderHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

import static com.mosaicmodding.sullysmod.registry.SMBlocks.*;
import static com.mosaicmodding.sullysmod.registry.SMItems.*;
import static com.mosaicmodding.sullysmod.registry.SMRegUtils.path;

public class SMCreativeTabs {

    public static final RegSupplier<CreativeModeTab> CONTENT_TAB = RegHelper.registerCreativeModeTab(path(SullysMod.ID), builder ->
        builder.title(SMConstants.Text.MOD_TAB).icon(() -> CARVED_MINIMU_GOURD.get().asItem().getDefaultInstance()).build()
    );

    public static void registerItemsToTabs(RegHelper.ItemToTabEvent event) {
        var tabAdder = new TabAdderHelper(event, SMCommonConfig.HOLDER).intoSingleTab(SMClientConfig.USE_DEDICATED_MOD_TAB.get() ? CONTENT_TAB.getKey() : null);

        tabAdder.after(Items.BLAZE_SPAWN_EGG, CreativeModeTabs.SPAWN_EGGS, SMCommonConfig.BOULDERING_ZOMBIE.getSecond(), BOULDERING_ZOMBIE_SPAWN_EGG);
        tabAdder.after(Items.IRON_GOLEM_SPAWN_EGG, CreativeModeTabs.SPAWN_EGGS, SMCommonConfig.LANTERNFISH.getSecond(), LANTERNFISH_SPAWN_EGG);

        tabAdder.after(Items.MILK_BUCKET, CreativeModeTabs.TOOLS_AND_UTILITIES, SMCommonConfig.AMBER_BLOCKS.getSecond(), MOLTEN_AMBER_BUCKET);
        tabAdder.after(Items.MILK_BUCKET, CreativeModeTabs.TOOLS_AND_UTILITIES, SMCommonConfig.LANTERNFISH.getSecond(), LANTERNFISH_BUCKET);

        tabAdder.after(Items.CHORUS_FRUIT, CreativeModeTabs.FOOD_AND_DRINKS, SMCommonConfig.GOURDS.getSecond(), MINIMU_SLICE, GIGANTO_SLICE);
        tabAdder.after(Items.COD, CreativeModeTabs.FOOD_AND_DRINKS, SMCommonConfig.LANTERNFISH.getSecond(), LANTERNFISH, COOKED_LANTERNFISH);

        tabAdder.add(CreativeModeTabs.NATURAL_BLOCKS, SMCommonConfig.GOURDS.getSecond(), GIGANTO_GOURD, CARVED_GIGANTO_GOURD, GIGANTO_GOURD_LANTERN, MINIMU_GOURD, CARVED_MINIMU_GOURD, MINIMU_GOURD_LANTERN);
        tabAdder.add(CreativeModeTabs.BUILDING_BLOCKS, SMCommonConfig.AMBER_BLOCKS.getSecond(), ROUGH_AMBER, CHISELED_AMBER, AMBER_PILLAR, AMBER_BRICKS, AMBER_BRICK_STAIRS, AMBER_BRICK_SLAB, AMBER_BRICK_WALL);
    }

    public static void init() {
        RegHelper.addItemsToTabsRegistration(SMCreativeTabs::registerItemsToTabs);
    }
}
