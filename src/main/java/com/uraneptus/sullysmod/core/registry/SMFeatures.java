package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.levelgen.AmberBlobFeature;
import com.uraneptus.sullysmod.common.levelgen.ArtifactGravelFeature;
import com.uraneptus.sullysmod.common.levelgen.PetrifiedTreeFeature;
import com.uraneptus.sullysmod.common.levelgen.configs.PetrifiedTreeConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = SullysMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
//todo Rename to SMWorldgenFeatures
public class SMFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, SullysMod.MOD_ID);

    public static final Supplier<Feature<PetrifiedTreeConfig>> PETRIFIED_TREE = FEATURES.register("petrified_tree", () -> new PetrifiedTreeFeature(PetrifiedTreeConfig.CODEC));
    public static final Supplier<ArtifactGravelFeature> ARTIFACT_GRAVEL = FEATURES.register("artifact_gravel", () -> new ArtifactGravelFeature(SimpleBlockConfiguration.CODEC));
    public static final Supplier<AmberBlobFeature> AMBER_BLOB = FEATURES.register("amber_blob", () -> new AmberBlobFeature(NoneFeatureConfiguration.CODEC));


}
