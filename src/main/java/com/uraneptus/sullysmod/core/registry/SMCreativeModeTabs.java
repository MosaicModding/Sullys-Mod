package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.SMTextDefinitions;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SMCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SullysMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ARTIFACT_TAB = TABS.register("artifact_tab", () -> CreativeModeTab.builder()
            .title(SMTextDefinitions.ARTIFACT_TAB_TITLE)
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> SMItems.BROKEN_VASE.get().getDefaultInstance())
            .displayItems((parameters, output) -> SMItems.ARTIFACT_DESC_MAP.forEach((item, desc) -> {
                if (item.get() == SMBlocks.UNICORN_ANCIENT_SKULL.getFirst().get().asItem()) {
                    if (ModList.get().isLoaded("sullysmod_additions") && ModList.get().isLoaded("twilightforest")) {
                        output.accept(item.get());
                    }
                } else {
                    output.accept(item.get());
                }

            }))
            .build());
}
