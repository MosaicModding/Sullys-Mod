package com.uraneptus.sullysmod.core.data.server.tags;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SMBlockTagsProvider extends BlockTagsProvider {

    public SMBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, SullysMod.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {
        //Minecraft Tags
        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(
                SMBlocks.JADE_ORE.get(),
                SMBlocks.DEEPSLATE_JADE_ORE.get(),
                SMBlocks.RAW_JADE_BLOCK.get(),
                SMBlocks.RAW_JADE_BRICKS.get(),
                SMBlocks.SMOOTH_RAW_JADE.get(),
                SMBlocks.RAW_JADE_TILES.get(),
                SMBlocks.JADE_BLOCK.get(),
                SMBlocks.JADE_BRICKS.get(),
                SMBlocks.SMALL_JADE_BRICKS.get(),
                SMBlocks.JADE_SHINGLES.get(),
                SMBlocks.JADE_TILES.get(),
                SMBlocks.CHISELED_JADE.get(),
                SMBlocks.JADE_TOTEM.get(),
                SMBlocks.JADE_FLINGER_TOTEM.get(),
                SMBlocks.JADE_PILLAR.get(),
                SMBlocks.JADE_BRICK_STAIRS.get(),
                SMBlocks.SMALL_JADE_BRICK_STAIRS.get(),
                SMBlocks.JADE_SHINGLE_STAIRS.get(),
                SMBlocks.JADE_TILE_STAIRS.get(),
                SMBlocks.JADE_BRICK_SLAB.get(),
                SMBlocks.SMALL_JADE_BRICK_SLAB.get(),
                SMBlocks.JADE_SHINGLE_SLAB.get(),
                SMBlocks.JADE_TILE_SLAB.get(),
                SMBlocks.RAW_JADE_BRICK_STAIRS.get(),
                SMBlocks.SMOOTH_RAW_JADE_STAIRS.get(),
                SMBlocks.RAW_JADE_TILE_STAIRS.get(),
                SMBlocks.RAW_JADE_BRICK_SLAB.get(),
                SMBlocks.SMOOTH_RAW_JADE_SLAB.get(),
                SMBlocks.RAW_JADE_TILE_SLAB.get()
        );
        tag(net.minecraft.tags.BlockTags.NEEDS_IRON_TOOL).add(
                SMBlocks.JADE_ORE.get(),
                SMBlocks.DEEPSLATE_JADE_ORE.get(),
                SMBlocks.RAW_JADE_BLOCK.get(),
                SMBlocks.RAW_JADE_BRICKS.get(),
                SMBlocks.SMOOTH_RAW_JADE.get(),
                SMBlocks.RAW_JADE_TILES.get(),
                SMBlocks.JADE_BLOCK.get(),
                SMBlocks.JADE_BRICKS.get(),
                SMBlocks.SMALL_JADE_BRICKS.get(),
                SMBlocks.JADE_SHINGLES.get(),
                SMBlocks.JADE_TILES.get(),
                SMBlocks.CHISELED_JADE.get(),
                SMBlocks.JADE_TOTEM.get(),
                SMBlocks.JADE_FLINGER_TOTEM.get(),
                SMBlocks.JADE_PILLAR.get(),
                SMBlocks.JADE_BRICK_STAIRS.get(),
                SMBlocks.SMALL_JADE_BRICK_STAIRS.get(),
                SMBlocks.JADE_SHINGLE_STAIRS.get(),
                SMBlocks.JADE_TILE_STAIRS.get(),
                SMBlocks.JADE_BRICK_SLAB.get(),
                SMBlocks.SMALL_JADE_BRICK_SLAB.get(),
                SMBlocks.JADE_SHINGLE_SLAB.get(),
                SMBlocks.JADE_TILE_SLAB.get(),
                SMBlocks.RAW_JADE_BRICK_STAIRS.get(),
                SMBlocks.SMOOTH_RAW_JADE_STAIRS.get(),
                SMBlocks.RAW_JADE_TILE_STAIRS.get(),
                SMBlocks.RAW_JADE_BRICK_SLAB.get(),
                SMBlocks.SMOOTH_RAW_JADE_SLAB.get(),
                SMBlocks.RAW_JADE_TILE_SLAB.get()
        );
        tag(net.minecraft.tags.BlockTags.BUTTONS).add(
                SMBlocks.COPPER_BUTTON.get(),
                SMBlocks.EXPOSED_COPPER_BUTTON.get(),
                SMBlocks.WEATHERED_COPPER_BUTTON.get(),
                SMBlocks.OXIDIZED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get()
        );
        tag(net.minecraft.tags.BlockTags.SLABS).add(
                SMBlocks.JADE_BRICK_SLAB.get(),
                SMBlocks.SMALL_JADE_BRICK_SLAB.get(),
                SMBlocks.JADE_TILE_SLAB.get(),
                SMBlocks.JADE_SHINGLE_SLAB.get(),
                SMBlocks.RAW_JADE_BRICK_SLAB.get(),
                SMBlocks.RAW_JADE_TILE_SLAB.get(),
                SMBlocks.SMOOTH_RAW_JADE_SLAB.get()
        );
        tag(net.minecraft.tags.BlockTags.STAIRS).add(
                SMBlocks.JADE_BRICK_STAIRS.get(),
                SMBlocks.SMALL_JADE_BRICK_STAIRS.get(),
                SMBlocks.JADE_TILE_STAIRS.get(),
                SMBlocks.JADE_SHINGLE_STAIRS.get(),
                SMBlocks.RAW_JADE_BRICK_STAIRS.get(),
                SMBlocks.RAW_JADE_TILE_STAIRS.get(),
                SMBlocks.SMOOTH_RAW_JADE_STAIRS.get()
        );

        //Mod Tags
        tag(SMBlockTags.PROJECTILES_BOUNCE_ON).add(
                SMBlocks.JADE_BLOCK.get(),
                SMBlocks.JADE_BRICKS.get(),
                SMBlocks.SMALL_JADE_BRICKS.get(),
                SMBlocks.JADE_SHINGLES.get(),
                SMBlocks.JADE_TILES.get(),
                SMBlocks.CHISELED_JADE.get(),
                SMBlocks.JADE_TOTEM.get(),
                SMBlocks.JADE_PILLAR.get(),
                SMBlocks.JADE_BRICK_STAIRS.get(),
                SMBlocks.SMALL_JADE_BRICK_STAIRS.get(),
                SMBlocks.JADE_SHINGLE_STAIRS.get(),
                SMBlocks.JADE_TILE_STAIRS.get(),
                SMBlocks.JADE_BRICK_SLAB.get(),
                SMBlocks.SMALL_JADE_BRICK_SLAB.get(),
                SMBlocks.JADE_SHINGLE_SLAB.get(),
                SMBlocks.JADE_TILE_SLAB.get()
        );

        //Forge Tags
        tag(SMBlockTags.WAXABLE_COPPER_BLOCKS).add(
                SMBlocks.COPPER_BUTTON.get(),
                SMBlocks.EXPOSED_COPPER_BUTTON.get(),
                SMBlocks.WEATHERED_COPPER_BUTTON.get(),
                SMBlocks.OXIDIZED_COPPER_BUTTON.get()
        );
        tag(SMBlockTags.WAXED_COPPER_BLOCKS).add(
                SMBlocks.WAXED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get()
        );

        SullysMod.LOGGER.info("BLOCK TAG GENERATION COMPLETE");
    }
}
