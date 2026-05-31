package com.uraneptus.sullysmod.common.levelgen.biome_modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.uraneptus.sullysmod.core.SMFeatures;
import com.uraneptus.sullysmod.core.registry.SMBiomeModifiers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeGenerationSettingsBuilder;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

import java.util.List;

public record OptionalAddFeaturesBiomeModifier(HolderSet<Biome> biomes, HolderSet<PlacedFeature> features, GenerationStep.Decoration step, List<SMFeatures> modFeatures) implements BiomeModifier {

    @Override
    public void modify(Holder<Biome> biome, BiomeModifier.Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (modFeatures.stream().allMatch(SMFeatures::isEnabled)) {
            if (phase == Phase.ADD && this.biomes.contains(biome)) {
                BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
                this.features.forEach(holder -> generationSettings.addFeature(this.step, holder));
            }
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return SMBiomeModifiers.ADD_OPTIONAL_FEATURES_BIOME_MODIFIER_TYPE.get();
    }
}
