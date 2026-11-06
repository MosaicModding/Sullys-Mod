package com.mosaicmodding.sullysmod.tags;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.registry.SMTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class SMBiomeTagGen extends BiomeTagsProvider {
    public SMBiomeTagGen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper fileHelper) {
        super(packOutput, pProvider, SullysMod.ID, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(SMTags.Biomes.BOULDERING_ZOMBIE_SPAWN_IN)
                .addTag(BiomeTags.IS_OCEAN)
                .addTag(BiomeTags.IS_FOREST)
                .addTag(BiomeTags.IS_SAVANNA)
                .addTag(BiomeTags.IS_MOUNTAIN)
                .addTag(BiomeTags.IS_BADLANDS)
                .addTag(BiomeTags.IS_JUNGLE)
                .addTag(BiomeTags.IS_BEACH)
                .addTag(BiomeTags.IS_RIVER)
                .addTag(BiomeTags.IS_HILL)
                .addTag(BiomeTags.IS_TAIGA)
                .addTag(Tags.Biomes.IS_SNOWY)
                .addTag(Tags.Biomes.IS_PLAINS)
                .addTag(Tags.Biomes.IS_SANDY)
                .addTag(Tags.Biomes.IS_SWAMP)
                .add(Biomes.LUSH_CAVES)
                .add(Biomes.DRIPSTONE_CAVES);
    }
}
