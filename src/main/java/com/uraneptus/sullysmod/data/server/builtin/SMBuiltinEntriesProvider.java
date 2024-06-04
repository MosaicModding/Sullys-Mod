package com.uraneptus.sullysmod.data.server.builtin;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class SMBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder SET_BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, SMConfiguredFeaturesProvider::create)
            .add(Registries.PLACED_FEATURE, SMPlacedFeaturesProvider::create)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, SMBiomeModifiersProvider::create)
            .add(Registries.DAMAGE_TYPE, SMDamageTypesProvider::create);

    public SMBuiltinEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, SET_BUILDER, Set.of(SullysMod.MOD_ID));
    }
}
