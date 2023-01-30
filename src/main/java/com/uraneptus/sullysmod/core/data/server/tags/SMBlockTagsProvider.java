package com.uraneptus.sullysmod.core.data.server.tags;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SMBlockTagsProvider extends BlockTagsProvider {

    public SMBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, SullysMod.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {
        //Minecraft Tags
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                SMBlocks.JADE_ORE.get(),
                SMBlocks.DEEPSLATE_JADE_ORE.get(),
                SMBlocks.ROUGH_JADE_BLOCK.get(),
                SMBlocks.ROUGH_JADE_BRICKS.get(),
                SMBlocks.SMOOTHED_ROUGH_JADE.get(),
                SMBlocks.ROUGH_JADE_TILES.get(),
                SMBlocks.POLISHED_JADE_BLOCK.get(),
                SMBlocks.POLISHED_JADE_BRICKS.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICKS.get(),
                SMBlocks.POLISHED_JADE_SHINGLES.get(),
                SMBlocks.POLISHED_JADE_TILES.get(),
                SMBlocks.POLISHED_CHISELED_JADE.get(),
                SMBlocks.JADE_TOTEM.get(),
                SMBlocks.JADE_FLINGER_TOTEM.get(),
                SMBlocks.POLISHED_JADE_PILLAR.get(),
                SMBlocks.POLISHED_JADE_BRICK_STAIRS.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get(),
                SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get(),
                SMBlocks.POLISHED_JADE_TILE_STAIRS.get(),
                SMBlocks.POLISHED_JADE_BRICK_SLAB.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get(),
                SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get(),
                SMBlocks.POLISHED_JADE_TILE_SLAB.get(),
                SMBlocks.ROUGH_JADE_BRICK_STAIRS.get(),
                SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS.get(),
                SMBlocks.ROUGH_JADE_TILE_STAIRS.get(),
                SMBlocks.ROUGH_JADE_BRICK_SLAB.get(),
                SMBlocks.SMOOTHED_ROUGH_JADE_SLAB.get(),
                SMBlocks.ROUGH_JADE_TILE_SLAB.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_VERTICAL_SLAB.get(),
                SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB.get(),
                SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB.get(),
                SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB.get(),
                SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB.get(),
                SMBlocks.SMOOTHED_ROUGH_JADE_VERTICAL_SLAB.get(),
                SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB.get()
        );
        tag(BlockTags.NEEDS_IRON_TOOL).add(
                SMBlocks.JADE_ORE.get(),
                SMBlocks.DEEPSLATE_JADE_ORE.get(),
                SMBlocks.ROUGH_JADE_BLOCK.get(),
                SMBlocks.ROUGH_JADE_BRICKS.get(),
                SMBlocks.SMOOTHED_ROUGH_JADE.get(),
                SMBlocks.ROUGH_JADE_TILES.get(),
                SMBlocks.POLISHED_JADE_BLOCK.get(),
                SMBlocks.POLISHED_JADE_BRICKS.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICKS.get(),
                SMBlocks.POLISHED_JADE_SHINGLES.get(),
                SMBlocks.POLISHED_JADE_TILES.get(),
                SMBlocks.POLISHED_CHISELED_JADE.get(),
                SMBlocks.JADE_TOTEM.get(),
                SMBlocks.JADE_FLINGER_TOTEM.get(),
                SMBlocks.POLISHED_JADE_PILLAR.get(),
                SMBlocks.POLISHED_JADE_BRICK_STAIRS.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get(),
                SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get(),
                SMBlocks.POLISHED_JADE_TILE_STAIRS.get(),
                SMBlocks.POLISHED_JADE_BRICK_SLAB.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get(),
                SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get(),
                SMBlocks.POLISHED_JADE_TILE_SLAB.get(),
                SMBlocks.ROUGH_JADE_BRICK_STAIRS.get(),
                SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS.get(),
                SMBlocks.ROUGH_JADE_TILE_STAIRS.get(),
                SMBlocks.ROUGH_JADE_BRICK_SLAB.get(),
                SMBlocks.SMOOTHED_ROUGH_JADE_SLAB.get(),
                SMBlocks.ROUGH_JADE_TILE_SLAB.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_VERTICAL_SLAB.get(),
                SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB.get(),
                SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB.get(),
                SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB.get(),
                SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB.get(),
                SMBlocks.SMOOTHED_ROUGH_JADE_VERTICAL_SLAB.get(),
                SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB.get()
        );
        tag(BlockTags.BUTTONS).add(
                SMBlocks.COPPER_BUTTON.get(),
                SMBlocks.EXPOSED_COPPER_BUTTON.get(),
                SMBlocks.WEATHERED_COPPER_BUTTON.get(),
                SMBlocks.OXIDIZED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get()
        );
        tag(BlockTags.SLABS).add(
                SMBlocks.POLISHED_JADE_BRICK_SLAB.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get(),
                SMBlocks.POLISHED_JADE_TILE_SLAB.get(),
                SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get(),
                SMBlocks.ROUGH_JADE_BRICK_SLAB.get(),
                SMBlocks.ROUGH_JADE_TILE_SLAB.get(),
                SMBlocks.SMOOTHED_ROUGH_JADE_SLAB.get()
        );
        tag(BlockTags.STAIRS).add(
                SMBlocks.POLISHED_JADE_BRICK_STAIRS.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get(),
                SMBlocks.POLISHED_JADE_TILE_STAIRS.get(),
                SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get(),
                SMBlocks.ROUGH_JADE_BRICK_STAIRS.get(),
                SMBlocks.ROUGH_JADE_TILE_STAIRS.get(),
                SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS.get()
        );

        //Our Tags
        tag(SMBlockTags.PROJECTILES_BOUNCE_ON).add(
                SMBlocks.POLISHED_JADE_BLOCK.get(),
                SMBlocks.POLISHED_JADE_BRICKS.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICKS.get(),
                SMBlocks.POLISHED_JADE_SHINGLES.get(),
                SMBlocks.POLISHED_JADE_TILES.get(),
                SMBlocks.POLISHED_CHISELED_JADE.get(),
                SMBlocks.JADE_TOTEM.get(),
                SMBlocks.POLISHED_JADE_PILLAR.get(),
                SMBlocks.POLISHED_JADE_BRICK_STAIRS.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get(),
                SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get(),
                SMBlocks.POLISHED_JADE_TILE_STAIRS.get(),
                SMBlocks.POLISHED_JADE_BRICK_SLAB.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get(),
                SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get(),
                SMBlocks.POLISHED_JADE_TILE_SLAB.get(),
                SMBlocks.JADE_FLINGER_TOTEM.get(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_VERTICAL_SLAB.get(),
                SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB.get(),
                SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB.get(),
                SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB.get()
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
        tag(Tags.Blocks.STORAGE_BLOCKS).add(
                SMBlocks.ROUGH_JADE_BLOCK.get(),
                SMBlocks.POLISHED_JADE_BLOCK.get()
        );
        tag(Tags.Blocks.ORES).addTag(
                SMBlockTags.JADE_ORES
        );
        tag(SMBlockTags.JADE_ORES).add(
                SMBlocks.JADE_ORE.get(),
                SMBlocks.DEEPSLATE_JADE_ORE.get()
        );
        tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(
                SMBlocks.JADE_ORE.get()
        );
        tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(
                SMBlocks.DEEPSLATE_JADE_ORE.get()
        );

        //Quark Tags
        tag(SMBlockTags.VERTICAL_SLABS).add(
                SMBlocks.POLISHED_SMALL_JADE_BRICK_VERTICAL_SLAB.get(),
                SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB.get(),
                SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB.get(),
                SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB.get(),
                SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB.get(),
                SMBlocks.SMOOTHED_ROUGH_JADE_VERTICAL_SLAB.get(),
                SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB.get()
        );
    }
}
