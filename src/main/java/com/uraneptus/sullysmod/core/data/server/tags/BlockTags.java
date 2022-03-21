package com.uraneptus.sullysmod.core.data.server.tags;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.SMBlockTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class BlockTags extends BlockTagsProvider {

    public BlockTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
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
                SMBlocks.RAW_JADE_TILE.get(),
                SMBlocks.RAW_JADE_TILES.get(),
                SMBlocks.JADE_BRICKS.get(),
                SMBlocks.SMALL_JADE_BRICKS.get(),
                SMBlocks.JADE_SHINGLES.get(),
                SMBlocks.JADE_TILES.get(),
                SMBlocks.CHISELED_JADE.get(),
                SMBlocks.JADE_TOTEM.get(),
                SMBlocks.JADE_PILLAR.get(),
                SMBlocks.JADE_BRICKS_STAIRS.get(),
                SMBlocks.SMALL_JADE_BRICKS_STAIRS.get(),
                SMBlocks.JADE_SHINGLES_STAIRS.get(),
                SMBlocks.JADE_TILES_STAIRS.get(),
                SMBlocks.JADE_BRICKS_SLAB.get(),
                SMBlocks.SMALL_JADE_BRICKS_SLAB.get(),
                SMBlocks.JADE_SHINGLES_SLAB.get(),
                SMBlocks.JADE_TILES_SLAB.get()
        );
        tag(net.minecraft.tags.BlockTags.NEEDS_IRON_TOOL).add(
                SMBlocks.JADE_ORE.get(),
                SMBlocks.DEEPSLATE_JADE_ORE.get(),
                SMBlocks.RAW_JADE_BLOCK.get(),
                SMBlocks.RAW_JADE_BRICKS.get(),
                SMBlocks.RAW_JADE_TILE.get(),
                SMBlocks.RAW_JADE_TILES.get(),
                SMBlocks.JADE_BRICKS.get(),
                SMBlocks.SMALL_JADE_BRICKS.get(),
                SMBlocks.JADE_SHINGLES.get(),
                SMBlocks.JADE_TILES.get(),
                SMBlocks.CHISELED_JADE.get(),
                SMBlocks.JADE_TOTEM.get(),
                SMBlocks.JADE_PILLAR.get(),
                SMBlocks.JADE_BRICKS_STAIRS.get(),
                SMBlocks.SMALL_JADE_BRICKS_STAIRS.get(),
                SMBlocks.JADE_SHINGLES_STAIRS.get(),
                SMBlocks.JADE_TILES_STAIRS.get(),
                SMBlocks.JADE_BRICKS_SLAB.get(),
                SMBlocks.SMALL_JADE_BRICKS_SLAB.get(),
                SMBlocks.JADE_SHINGLES_SLAB.get(),
                SMBlocks.JADE_TILES_SLAB.get()
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

        //Mod Tags
        tag(SMBlockTags.PROJECTILES_BOUNCE_ON).add(
                SMBlocks.JADE_BRICKS.get(),
                SMBlocks.SMALL_JADE_BRICKS.get(),
                SMBlocks.JADE_SHINGLES.get(),
                SMBlocks.JADE_TILES.get(),
                SMBlocks.CHISELED_JADE.get(),
                SMBlocks.JADE_TOTEM.get(),
                SMBlocks.JADE_PILLAR.get(),
                SMBlocks.JADE_BRICKS_STAIRS.get(),
                SMBlocks.SMALL_JADE_BRICKS_STAIRS.get(),
                SMBlocks.JADE_SHINGLES_STAIRS.get(),
                SMBlocks.JADE_TILES_STAIRS.get(),
                SMBlocks.JADE_BRICKS_SLAB.get(),
                SMBlocks.SMALL_JADE_BRICKS_SLAB.get(),
                SMBlocks.JADE_SHINGLES_SLAB.get(),
                SMBlocks.JADE_TILES_SLAB.get()
        );

        SullysMod.LOGGER.info("BLOCK TAG GENERATION COMPLETE");
    }


}
