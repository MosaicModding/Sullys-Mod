package com.uraneptus.sullysmod.core.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintEntityTypeTags;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SMEntityTagsProvider extends EntityTypeTagsProvider {
    public SMEntityTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, SullysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlueprintEntityTypeTags.FISHES).add(SMEntityTypes.LANTERNFISH.get());

        SullysMod.LOGGER.info("ENTITY TAG GENERATION COMPLETE");
    }
}
