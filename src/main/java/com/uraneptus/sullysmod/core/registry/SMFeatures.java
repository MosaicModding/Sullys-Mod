package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.levelgen.PetrifiedTreeConfig;
import com.uraneptus.sullysmod.common.levelgen.PetrifiedTreeFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, SullysMod.MOD_ID);

    public static final RegistryObject<Feature<PetrifiedTreeConfig>> PETRIFIED_TREE = FEATURES.register("petrified_tree", () -> new PetrifiedTreeFeature(PetrifiedTreeConfig.CODEC));


}
