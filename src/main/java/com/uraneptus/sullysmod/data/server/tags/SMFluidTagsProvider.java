package com.uraneptus.sullysmod.data.server.tags;

import com.uraneptus.sullysmod.core.registry.SMFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SMFluidTagsProvider extends FluidTagsProvider {
    public SMFluidTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, provider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //Our Tags
        tag(FluidTags.LAVA).add(SMFluids.SOURCE_MOLTEN_AMBER.get());
        tag(FluidTags.LAVA).add(SMFluids.FLOWING_MOLTEN_AMBER.get());
    }
}
