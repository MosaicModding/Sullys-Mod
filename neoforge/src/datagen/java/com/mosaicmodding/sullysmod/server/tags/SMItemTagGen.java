package com.mosaicmodding.sullysmod.server.tags;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.core.SMUtils;
import com.mosaicmodding.tessera.datagen.providers.tag.TesseraBlockTagsSystem;
import com.mosaicmodding.tessera.datagen.providers.tag.TesseraItemTagsSystem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

public class SMItemTagGen extends TesseraItemTagsSystem {

    public SMItemTagGen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, TesseraBlockTagsSystem blockTagsSystem, ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, blockTagsSystem, SullysMod.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        var blocks = new HashSet<>(SMUtils.getAllModBlocks());
        addTagsFromBlockProperties(blocks);
        addCopies();
    }
}
