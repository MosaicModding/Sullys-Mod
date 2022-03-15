package com.uraneptus.sullysmod.core.data.server.tags;

import com.uraneptus.sullysmod.SullysMod;
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
                SMBlocks.JADE_PILLAR.get()
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
                SMBlocks.JADE_PILLAR.get()
        );

        SullysMod.LOGGER.info("BLOCK TAG GENERATION COMPLETE");
    }


}
