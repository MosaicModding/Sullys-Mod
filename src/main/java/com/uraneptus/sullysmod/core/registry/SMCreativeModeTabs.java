package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.SMTextDefinitions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SMCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SullysMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ARTIFACT_TAB = TABS.register("artifact_tab", () -> CreativeModeTab.builder()
            .title(SMTextDefinitions.ARTIFACT_TAB_TITLE)
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> SMItems.BROKEN_VASE.get().getDefaultInstance())
            .displayItems((parameters, output) -> SMItems.ARTIFACTS.forEach((item, desc) -> output.accept(item.get())))
            .build());
}
