package com.uraneptus.sullysmod.core.data.server.datapack_registries;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.data.SMDatagenUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;
import java.util.function.Supplier;

public class SMPlacedFeatures {
    public static void create(BootstapContext<PlacedFeature> context) {
        var jade = "jade_ore";
        register(context, jade, () -> addOreFeature(context, jade, -16, 112, 16));
    }

    private static PlacedFeature addOreFeature(BootstapContext<PlacedFeature> context, String name, int minHeight, int maxHeight, int count) {
        return addFeaturePlacement(context, name, HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)), CountPlacement.of(count), InSquarePlacement.spread(), BiomeFilter.biome());
    }

    private static PlacedFeature addFeaturePlacement(BootstapContext<PlacedFeature> context, String name, PlacementModifier... placementModifiers) {
        return new PlacedFeature(SMDatagenUtil.getConfigHolder(name, context.lookup(Registries.CONFIGURED_FEATURE)), List.of(placementModifiers));
    }

    private static void register(BootstapContext<PlacedFeature> context, String name, Supplier<? extends PlacedFeature> feature) {
        context.register(ResourceKey.create(Registries.PLACED_FEATURE, SullysMod.modPrefix(name)), feature.get());
    }
}