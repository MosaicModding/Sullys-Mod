package com.mosaicmodding.sullysmod.core.biome_modifiers;

import com.mojang.serialization.MapCodec;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.registry.SMBiomeModifiers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeGenerationSettingsBuilder;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

import java.util.List;

public record OptionalAddFeatureModifier(HolderSet<Biome> biomes, HolderSet<PlacedFeature> features, GenerationStep.Decoration step, List<String> required_mod_features) implements BiomeModifier {

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (this.required_mod_features.stream().allMatch(SMCommonConfig.HOLDER::isFeatureEnabled)) {
            if (phase == Phase.ADD && this.biomes.contains(biome)) {
                BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
                this.features.forEach((holder) -> generationSettings.addFeature(this.step, holder));
            }
        }

    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return SMBiomeModifiers.OPTIONAL_ADD_FEATURE.get();
    }
}
