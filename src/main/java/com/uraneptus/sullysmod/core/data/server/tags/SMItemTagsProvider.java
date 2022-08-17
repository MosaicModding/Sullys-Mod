package com.uraneptus.sullysmod.core.data.server.tags;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SMItemTagsProvider extends ItemTagsProvider {

    public SMItemTagsProvider(DataGenerator generator, BlockTagsProvider provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, provider, SullysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        //Mod Tags
        tag(SMItemTags.TORTOISE_FOOD).add(
                Items.MELON_SLICE,
                Items.GLOW_BERRIES,
                Items.SWEET_BERRIES,
                Items.APPLE
        );

        //Minecraft Tags
        tag(net.minecraft.tags.ItemTags.BUTTONS).add(
                SMBlocks.COPPER_BUTTON.get().asItem(),
                SMBlocks.EXPOSED_COPPER_BUTTON.get().asItem(),
                SMBlocks.WEATHERED_COPPER_BUTTON.get().asItem(),
                SMBlocks.OXIDIZED_COPPER_BUTTON.get().asItem(),
                SMBlocks.WAXED_COPPER_BUTTON.get().asItem(),
                SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get().asItem(),
                SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get().asItem(),
                SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get().asItem()
        );
        tag(net.minecraft.tags.ItemTags.SLABS).add(
                SMBlocks.POLISHED_JADE_BRICK_SLAB.get().asItem(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get().asItem(),
                SMBlocks.POLISHED_JADE_TILE_SLAB.get().asItem(),
                SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get().asItem(),
                SMBlocks.ROUGH_JADE_BRICK_SLAB.get().asItem(),
                SMBlocks.ROUGH_JADE_TILE_SLAB.get().asItem(),
                SMBlocks.SMOOTHED_ROUGH_JADE_SLAB.get().asItem()
        );
        tag(net.minecraft.tags.ItemTags.STAIRS).add(
                SMBlocks.POLISHED_JADE_BRICK_STAIRS.get().asItem(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get().asItem(),
                SMBlocks.POLISHED_JADE_TILE_STAIRS.get().asItem(),
                SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get().asItem(),
                SMBlocks.ROUGH_JADE_BRICK_STAIRS.get().asItem(),
                SMBlocks.ROUGH_JADE_TILE_STAIRS.get().asItem(),
                SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS.get().asItem()
        );

        //Forge Tags
        tag(SMItemTags.RAW_FISH).add(
                SMItems.RAW_LANTERNFISH.get()
        );
        tag(SMItemTags.COOKED_LANTERNFISH).add(
                SMItems.COOKED_LANTERNFISH.get()
        );

        //Quark Tags
        tag(SMItemTags.VERTICAL_SLABS).add(
                SMBlocks.POLISHED_SMALL_JADE_BRICK_VERTICAL_SLAB.get().asItem(),
                SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB.get().asItem(),
                SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB.get().asItem(),
                SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB.get().asItem(),
                SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB.get().asItem(),
                SMBlocks.SMOOTH_ROUGH_JADE_VERTICAL_SLAB.get().asItem(),
                SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB.get().asItem()
        );

        SullysMod.LOGGER.info("ITEM TAG GENERATION COMPLETE");
    }
}
