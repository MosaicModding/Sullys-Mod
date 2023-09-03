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

    //Removed this because it's unlikely there still mapping errors
    @SubscribeEvent
    public static void fixMissingBlockMappings(MissingMappingsEvent event) {
        Map<ResourceLocation, Supplier<Block>> blocksMap = (new ImmutableMap.Builder<ResourceLocation, Supplier<Block>>())
                .build();

        Map<ResourceLocation, Supplier<Item>> itemsMap = (new ImmutableMap.Builder<ResourceLocation, Supplier<Item>>())
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
