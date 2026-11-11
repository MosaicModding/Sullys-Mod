package com.mosaicmodding.sullysmod.loot;

import com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils;
import com.mosaicmodding.tessera.datagen.providers.loot.TesseraBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static com.mosaicmodding.sullysmod.registry.SMBlocks.*;

public class SMBlockLoot extends TesseraBlockLootSubProvider {

    protected SMBlockLoot(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected List<Supplier<Block>> modBlockEntries() {
        return SMRegUtils.getAllModBlocks();
    }

    @Override
    protected void generate() {
        this.dropOther(AMBER.get(), ROUGH_AMBER.get());
        this.dropOther(AMBER_SOLID.get(), ROUGH_AMBER.get());
        this.dropOther(AMBER_CAULDRON.get(), Blocks.CAULDRON);

        this.addLootFromBlockProperties();
    }
}
