package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.SMConfig;
import com.uraneptus.sullysmod.core.SMFeatureSelection;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SMCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SullysMod.MOD_ID);

    public static final Supplier<CreativeModeTab> SULLYSMOD_TAB = TABS.register("sullysmod_tab", () -> CreativeModeTab.builder()
            .title(SMTextDefinitions.SULLYSMOD_TAB_TITLE)
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> SMItems.TORTOISE_SHELL.get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> SMItems.ITEMS.getEntries().stream().filter(o -> !SMArtifacts.ARTIFACT_DESC_MAP.contains(o) && !o.get().equals(SMBlocks.AMBER_SOLID.get().asItem())).forEach(item -> {
                if (SMConfig.USE_CUSTOM_TAB.get()) {
                    output.accept(item.get());
                }
            }))
            .build()
    );

    public static final Supplier<CreativeModeTab> ARTIFACT_TAB = TABS.register("artifact_tab", () -> CreativeModeTab.builder()
            .title(SMTextDefinitions.ARTIFACT_TAB_TITLE)
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> SMArtifacts.BROKEN_VASE.get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> SMArtifacts.ARTIFACT_DESC_MAP.forEach(item -> {
                if (SMFeatureSelection.isEnabled(SMFeatureSelection.ARTIFACTS)) {
                    if (item.get() == SMArtifacts.UNICORN_ANCIENT_SKULL.getFirst().get().asItem()) {
                        if (ModList.get().isLoaded("sullysmod_additions") && ModList.get().isLoaded("twilightforest")) {
                            output.accept(item.get());
                        }
                    } else {
                        output.accept(item.get());
                    }
                }
            }))
            .build()
    );
}
