package com.uraneptus.sullysmod.core.data.server.tags;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SMItemTagsProvider extends ItemTagsProvider {

    public SMItemTagsProvider(DataGenerator generator, BlockTagsProvider provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, provider, SullysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
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
                SMBlocks.JADE_BRICK_SLAB.get().asItem(),
                SMBlocks.SMALL_JADE_BRICK_SLAB.get().asItem(),
                SMBlocks.JADE_TILE_SLAB.get().asItem(),
                SMBlocks.JADE_SHINGLE_SLAB.get().asItem(),
                SMBlocks.RAW_JADE_BRICK_SLAB.get().asItem(),
                SMBlocks.RAW_JADE_TILE_SLAB.get().asItem(),
                SMBlocks.SMOOTH_RAW_JADE_SLAB.get().asItem()
        );
        tag(net.minecraft.tags.ItemTags.STAIRS).add(
                SMBlocks.JADE_BRICK_STAIRS.get().asItem(),
                SMBlocks.SMALL_JADE_BRICK_STAIRS.get().asItem(),
                SMBlocks.JADE_TILE_STAIRS.get().asItem(),
                SMBlocks.JADE_SHINGLE_STAIRS.get().asItem(),
                SMBlocks.RAW_JADE_BRICK_STAIRS.get().asItem(),
                SMBlocks.RAW_JADE_TILE_STAIRS.get().asItem(),
                SMBlocks.SMOOTH_RAW_JADE_STAIRS.get().asItem()
        );

        SullysMod.LOGGER.info("ITEM TAG GENERATION COMPLETE");
    }
}
