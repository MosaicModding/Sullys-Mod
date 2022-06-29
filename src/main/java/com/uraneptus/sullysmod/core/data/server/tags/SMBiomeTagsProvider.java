package com.uraneptus.sullysmod.core.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBiomeTags;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.tags.SMBiomeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SMBiomeTagsProvider extends BiomeTagsProvider {
    public SMBiomeTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, SullysMod.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags() {
        //Our Tags
        tag(SMBiomeTags.JADE_GENERATES_IN).addTag(BiomeTags.IS_JUNGLE);
        tag(SMBiomeTags.TORTOISES_SPAWN_IN).addTag(BiomeTags.IS_SAVANNA).addTag(BiomeTags.IS_JUNGLE).add(
                Biomes.BIRCH_FOREST,
                Biomes.OLD_GROWTH_BIRCH_FOREST
        );
        tag(SMBiomeTags.LANTERNFISH_SPAWN_IN).addTag(BlueprintBiomeTags.IS_OVERWORLD);
    }
}
