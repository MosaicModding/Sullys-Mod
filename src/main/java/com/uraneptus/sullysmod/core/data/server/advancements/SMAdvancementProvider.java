package com.uraneptus.sullysmod.core.data.server.advancements;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public class SMAdvancementProvider extends ForgeAdvancementProvider {
    public SMAdvancementProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelperIn) {
        super(packOutput, registries, fileHelperIn, List.of(new SMAdventureAdvancements()));
    }

}
