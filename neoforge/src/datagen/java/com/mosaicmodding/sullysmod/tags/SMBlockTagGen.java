package com.mosaicmodding.sullysmod.tags;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils;
import com.mosaicmodding.sullysmod.registry.SMTags;
import com.mosaicmodding.tessera.datagen.providers.tag.TesseraBlockTagsSystem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unchecked")
public class SMBlockTagGen extends TesseraBlockTagsSystem {
    public SMBlockTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SullysMod.ID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Sully's Mod Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        var blocks = new HashSet<>(SMRegUtils.getAllModBlocks());

        tag(SMTags.Blocks.MELTS_AMBER).add(
                Blocks.TORCH,
                Blocks.WALL_TORCH,
                Blocks.SOUL_TORCH,
                Blocks.SOUL_WALL_TORCH,
                Blocks.FIRE,
                Blocks.SOUL_FIRE,
                Blocks.LANTERN,
                Blocks.SOUL_LANTERN,
                Blocks.LAVA,
                Blocks.LAVA_CAULDRON,
                Blocks.CAMPFIRE,
                Blocks.SOUL_CAMPFIRE,
                Blocks.COPPER_BULB,
                Blocks.FURNACE,
                Blocks.BLAST_FURNACE,
                Blocks.SMOKER,
                Blocks.MAGMA_BLOCK,
                Blocks.OCHRE_FROGLIGHT,
                Blocks.PEARLESCENT_FROGLIGHT,
                Blocks.VERDANT_FROGLIGHT,
                Blocks.JACK_O_LANTERN
        ).addTags(BlockTags.CANDLES, BlockTags.CANDLE_CAKES);

        addTagsFromBlockProperties(blocks);
    }
}
