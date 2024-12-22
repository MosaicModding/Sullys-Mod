package com.uraneptus.sullysmod.core.events;

import com.google.common.collect.ImmutableMap;
import com.teamabnormals.blueprint.core.util.TradeUtil;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.SMFeatures;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;

import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID)
@SuppressWarnings("unused")
public class SMCommonForgeEvents {
    public static boolean SEND_BLOCK_REMOVAL_NOTIFY;

    @SubscribeEvent
    public static void onVWandererTradeEvent(WandererTradesEvent event) {
        if (SMFeatures.isEnabled(SMFeatures.ARTIFACTS)) {
            SMItems.TRADES.forEach((item, price) -> TradeUtil.addWandererTrades(event, new TradeUtil.BlueprintTrade(item.get(), 1, Items.EMERALD, price, 3, 15)));
        }
    }

    @SubscribeEvent
    public static void missingMappingsEvent(MissingMappingsEvent event) {
        //TODO this can possibly all be removed in 1.21
        Map<ResourceLocation, Supplier<Block>> blocksMap = (new ImmutableMap.Builder<ResourceLocation, Supplier<Block>>())
                .put(SullysMod.modPrefix("polished_jade_block"), SMBlocks.JADE_BLOCK)
                .put(SullysMod.modPrefix("polished_small_jade_bricks"), SMBlocks.JADE_BRICKS)
                .put(SullysMod.modPrefix("polished_small_jade_brick_slab"), SMBlocks.JADE_BRICK_SLAB)
                .put(SullysMod.modPrefix("polished_small_jade_brick_stairs"), SMBlocks.JADE_BRICK_STAIRS)
                .put(SullysMod.modPrefix("polished_chiseled_jade"), SMBlocks.CHISELED_JADE)
                .put(SullysMod.modPrefix("polished_jade_pillar"), SMBlocks.JADE_PILLAR)
                //Removed
                .put(SullysMod.modPrefix("smoothed_rough_jade"), SMBlocks.ROUGH_JADE_BRICKS)
                .put(SullysMod.modPrefix("rough_jade_tiles"), SMBlocks.ROUGH_JADE_BRICKS)
                .put(SullysMod.modPrefix("polished_jade_bricks"), SMBlocks.JADE_BRICKS)
                .put(SullysMod.modPrefix("polished_jade_brick_slab"), SMBlocks.JADE_BRICK_SLAB)
                .put(SullysMod.modPrefix("polished_jade_brick_stairs"), SMBlocks.JADE_BRICK_STAIRS)
                .put(SullysMod.modPrefix("polished_jade_shingles"), SMBlocks.JADE_BRICKS)
                .put(SullysMod.modPrefix("polished_jade_tiles"), SMBlocks.JADE_BRICKS)
                .put(SullysMod.modPrefix("smoothed_rough_jade_stairs"), SMBlocks.ROUGH_JADE_BRICK_STAIRS)
                .put(SullysMod.modPrefix("rough_jade_tile_stairs"), SMBlocks.ROUGH_JADE_BRICK_STAIRS)
                .put(SullysMod.modPrefix("polished_jade_shingle_stairs"), SMBlocks.JADE_BRICK_STAIRS)
                .put(SullysMod.modPrefix("polished_jade_tile_stairs"), SMBlocks.JADE_BRICK_STAIRS)
                .put(SullysMod.modPrefix("rough_jade_tile_slab"), SMBlocks.ROUGH_JADE_BRICK_SLAB)
                .put(SullysMod.modPrefix("smoothed_rough_jade_slab"), SMBlocks.ROUGH_JADE_BRICK_SLAB)
                .put(SullysMod.modPrefix("polished_jade_shingle_slab"), SMBlocks.JADE_BRICK_SLAB)
                .put(SullysMod.modPrefix("polished_jade_tile_slab"), SMBlocks.JADE_BRICK_SLAB)
                .build();

        Map<ResourceLocation, Supplier<Item>> itemsMap = (new ImmutableMap.Builder<ResourceLocation, Supplier<Item>>())
                .put(SullysMod.modPrefix("polished_jade"), SMItems.JADE)
                .put(SullysMod.modPrefix("polished_jade_block"), () -> SMBlocks.JADE_BLOCK.get().asItem())
                .put(SullysMod.modPrefix("polished_small_jade_bricks"), () -> SMBlocks.JADE_BRICKS.get().asItem())
                .put(SullysMod.modPrefix("polished_small_jade_brick_slab"), () -> SMBlocks.JADE_BRICK_SLAB.get().asItem())
                .put(SullysMod.modPrefix("polished_small_jade_brick_stairs"), () -> SMBlocks.JADE_BRICK_STAIRS.get().asItem())
                .put(SullysMod.modPrefix("polished_chiseled_jade"), () -> SMBlocks.CHISELED_JADE.get().asItem())
                .put(SullysMod.modPrefix("polished_jade_pillar"), () -> SMBlocks.JADE_PILLAR.get().asItem())
                //Removed
                .put(SullysMod.modPrefix("smoothed_rough_jade"), () -> SMBlocks.ROUGH_JADE_BRICKS.get().asItem())
                .put(SullysMod.modPrefix("rough_jade_tiles"), () -> SMBlocks.ROUGH_JADE_BRICKS.get().asItem())
                .put(SullysMod.modPrefix("polished_jade_bricks"), () -> SMBlocks.JADE_BRICKS.get().asItem())
                .put(SullysMod.modPrefix("polished_jade_brick_slab"), () -> SMBlocks.JADE_BRICK_SLAB.get().asItem())
                .put(SullysMod.modPrefix("polished_jade_brick_stairs"), () -> SMBlocks.JADE_BRICK_STAIRS.get().asItem())
                .put(SullysMod.modPrefix("polished_jade_shingles"), () -> SMBlocks.JADE_BRICKS.get().asItem())
                .put(SullysMod.modPrefix("polished_jade_tiles"), () -> SMBlocks.JADE_BRICKS.get().asItem())
                .put(SullysMod.modPrefix("smoothed_rough_jade_stairs"), () -> SMBlocks.ROUGH_JADE_BRICK_STAIRS.get().asItem())
                .put(SullysMod.modPrefix("rough_jade_tile_stairs"), () -> SMBlocks.ROUGH_JADE_BRICK_STAIRS.get().asItem())
                .put(SullysMod.modPrefix("polished_jade_shingle_stairs"), () -> SMBlocks.JADE_BRICK_STAIRS.get().asItem())
                .put(SullysMod.modPrefix("polished_jade_tile_stairs"), () -> SMBlocks.JADE_BRICK_STAIRS.get().asItem())
                .put(SullysMod.modPrefix("rough_jade_tile_slab"), () -> SMBlocks.ROUGH_JADE_BRICK_SLAB.get().asItem())
                .put(SullysMod.modPrefix("smoothed_rough_jade_slab"), () -> SMBlocks.ROUGH_JADE_BRICK_SLAB.get().asItem())
                .put(SullysMod.modPrefix("polished_jade_shingle_slab"), () -> SMBlocks.JADE_BRICK_SLAB.get().asItem())
                .put(SullysMod.modPrefix("polished_jade_tile_slab"), () -> SMBlocks.JADE_BRICK_SLAB.get().asItem())
                .build();

        for (MissingMappingsEvent.Mapping<Block> mapping : event.getMappings(ForgeRegistries.Keys.BLOCKS, SullysMod.MOD_ID)) {
            if (!SEND_BLOCK_REMOVAL_NOTIFY) {
                SEND_BLOCK_REMOVAL_NOTIFY = true;
            }
            Supplier<Block> blockSupplier = blocksMap.get(mapping.getKey());
            if (blockSupplier != null) {
                Block block = blockSupplier.get();
                mapping.remap(block);
            }
        }

        for (MissingMappingsEvent.Mapping<Item> mapping : event.getMappings(ForgeRegistries.Keys.ITEMS, SullysMod.MOD_ID)) {
            if (!SEND_BLOCK_REMOVAL_NOTIFY) {
                SEND_BLOCK_REMOVAL_NOTIFY = true;
            }
            Supplier<Item> itemSupplier = itemsMap.get(mapping.getKey());
            if (itemSupplier != null) {
                Item item = itemSupplier.get();
                mapping.remap(item);
            }
        }
    }
}
