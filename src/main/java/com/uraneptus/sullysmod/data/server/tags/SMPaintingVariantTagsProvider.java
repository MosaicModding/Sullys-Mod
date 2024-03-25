package com.uraneptus.sullysmod.data.server.tags;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMPaintingVariants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SMPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

    public SMPaintingVariantTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, SullysMod.MOD_ID, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider pProvider) {
        SMPaintingVariants.PAINTINGS.getEntries().forEach(painting -> tag(PaintingVariantTags.PLACEABLE).add(painting.getKey()));
    }
}
