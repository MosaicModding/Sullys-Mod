package com.mosaicmodding.sullysmod.server;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.registry.datapack.SMPetrifiedTreeVariants;
import com.mosaicmodding.sullysmod.server.worldgen.SMConfiguredFeatureGen;
import com.mosaicmodding.sullysmod.server.worldgen.SMPetrifiedTreeVariantGen;
import com.mosaicmodding.sullysmod.server.worldgen.SMPlacedFeatureGen;
import com.mosaicmodding.sullysmod.server.worldgen.SMBiomeModifiersGen;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class SMBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder SET_BUILDER = new RegistrySetBuilder()
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, SMBiomeModifiersGen::add)
            .add(Registries.CONFIGURED_FEATURE, SMConfiguredFeatureGen::add)
            .add(Registries.PLACED_FEATURE, SMPlacedFeatureGen::add)
            .add(SMPetrifiedTreeVariants.REGISTRY_KEY, SMPetrifiedTreeVariantGen::add);

    public SMBuiltinEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, SET_BUILDER, Set.of(SullysMod.ID));
    }
}
