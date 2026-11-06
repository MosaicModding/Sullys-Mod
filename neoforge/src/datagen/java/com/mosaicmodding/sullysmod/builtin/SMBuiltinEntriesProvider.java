package com.mosaicmodding.sullysmod.builtin;

import com.mosaicmodding.sullysmod.SullysMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class SMBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder SET_BUILDER = new RegistrySetBuilder()
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, SMBiomeModifiersProvider::create);

    public SMBuiltinEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, SET_BUILDER, Set.of(SullysMod.ID));
    }
}
