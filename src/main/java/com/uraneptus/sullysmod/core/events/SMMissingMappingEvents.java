package com.uraneptus.sullysmod.core.events;

import com.google.common.collect.ImmutableMap;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;

import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID)
public class SMMissingMappingEvents {

    @SubscribeEvent
    public static void fixMissingBlockMappings(MissingMappingsEvent event) {
        Map<ResourceLocation, Supplier<Block>> blocksMap = (new ImmutableMap.Builder<ResourceLocation, Supplier<Block>>())
                .put(SullysMod.modPrefix("raw_jade_block"), SMBlocks.ROUGH_JADE_BLOCK)
                .put(SullysMod.modPrefix("raw_jade_bricks"), SMBlocks.ROUGH_JADE_BRICKS)
                .put(SullysMod.modPrefix("smooth_raw_jade"), SMBlocks.SMOOTHED_ROUGH_JADE)
                .put(SullysMod.modPrefix("smooth_rough_jade"), SMBlocks.SMOOTHED_ROUGH_JADE)
                .put(SullysMod.modPrefix("raw_jade_tiles"), SMBlocks.ROUGH_JADE_TILES)
                .put(SullysMod.modPrefix("raw_jade_brick_stairs"), SMBlocks.ROUGH_JADE_BRICK_STAIRS)
                .put(SullysMod.modPrefix("smooth_raw_jade_stairs"), SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS)
                .put(SullysMod.modPrefix("smooth_rough_jade_stairs"), SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS)
                .put(SullysMod.modPrefix("raw_jade_tile_stairs"), SMBlocks.ROUGH_JADE_TILE_STAIRS)
                .put(SullysMod.modPrefix("raw_jade_brick_slab"), SMBlocks.ROUGH_JADE_BRICK_SLAB)
                .put(SullysMod.modPrefix("smooth_raw_jade_slab"), SMBlocks.SMOOTHED_ROUGH_JADE_SLAB)
                .put(SullysMod.modPrefix("smooth_rough_jade_slab"), SMBlocks.SMOOTHED_ROUGH_JADE_SLAB)
                .put(SullysMod.modPrefix("raw_jade_tile_slab"), SMBlocks.ROUGH_JADE_TILE_SLAB)
                .put(SullysMod.modPrefix("jade_block"), SMBlocks.POLISHED_JADE_BLOCK)
                .put(SullysMod.modPrefix("jade_bricks"), SMBlocks.POLISHED_JADE_BRICKS)
                .put(SullysMod.modPrefix("small_jade_bricks"), SMBlocks.POLISHED_SMALL_JADE_BRICKS)
                .put(SullysMod.modPrefix("small_polished_jade_bricks"), SMBlocks.POLISHED_SMALL_JADE_BRICKS)
                .put(SullysMod.modPrefix("jade_shingles"), SMBlocks.POLISHED_JADE_SHINGLES)
                .put(SullysMod.modPrefix("jade_tiles"), SMBlocks.POLISHED_JADE_TILES)
                .put(SullysMod.modPrefix("chiseled_jade"), SMBlocks.POLISHED_CHISELED_JADE)
                .put(SullysMod.modPrefix("jade_pillar"), SMBlocks.POLISHED_JADE_PILLAR)
                .put(SullysMod.modPrefix("jade_brick_stairs"), SMBlocks.POLISHED_JADE_BRICK_STAIRS)
                .put(SullysMod.modPrefix("small_jade_brick_stairs"), SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS)
                .put(SullysMod.modPrefix("small_polished_jade_brick_stairs"), SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS)
                .put(SullysMod.modPrefix("jade_shingle_stairs"), SMBlocks.POLISHED_JADE_SHINGLE_STAIRS)
                .put(SullysMod.modPrefix("jade_tile_stairs"), SMBlocks.POLISHED_JADE_TILE_STAIRS)
                .put(SullysMod.modPrefix("jade_brick_slab"), SMBlocks.POLISHED_JADE_BRICK_SLAB)
                .put(SullysMod.modPrefix("small_polished_jade_brick_slab"), SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB)
                .put(SullysMod.modPrefix("jade_shingle_slab"), SMBlocks.POLISHED_JADE_SHINGLE_SLAB)
                .put(SullysMod.modPrefix("jade_tile_slab"), SMBlocks.POLISHED_JADE_TILE_SLAB)
                .put(SullysMod.modPrefix("smooth_rough_jade_vertical_slab"), SMBlocks.SMOOTHED_ROUGH_JADE_VERTICAL_SLAB)
                .put(SullysMod.modPrefix("small_polished_jade_brick_vertical_slab"), SMBlocks.POLISHED_SMALL_JADE_BRICK_VERTICAL_SLAB)
                .build();

        Map<ResourceLocation, Supplier<Item>> itemsMap = (new ImmutableMap.Builder<ResourceLocation, Supplier<Item>>())
                .put(SullysMod.modPrefix("raw_jade"), SMItems.ROUGH_JADE)
                .put(SullysMod.modPrefix("jade"), SMItems.POLISHED_JADE)
                .put(SullysMod.modPrefix("raw_lanternfish"), SMItems.LANTERNFISH)
                .build();

        for (MissingMappingsEvent.Mapping<Block> mapping : event.getMappings(ForgeRegistries.Keys.BLOCKS, SullysMod.MOD_ID)) {
            Supplier<Block> blockSupplier = blocksMap.get(mapping.getKey());
            if (blockSupplier != null) {
                Block block = blockSupplier.get();
                mapping.remap(block);

            }
        }

        for (MissingMappingsEvent.Mapping<Item> mapping : event.getMappings(ForgeRegistries.Keys.ITEMS, SullysMod.MOD_ID)) {
            Supplier<Item> itemSupplier = itemsMap.get(mapping.getKey());
            if (itemSupplier != null) {
                Item item = itemSupplier.get();
                mapping.remap(item);

            }
        }
    }
}
