package com.mosaicmodding.sullysmod.server.tags;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.registry.SMEntityTypes;
import com.mosaicmodding.sullysmod.registry.datapack.SMTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SMEntityTypeTagGen extends EntityTypeTagsProvider {

    public SMEntityTypeTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, SullysMod.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(EntityTypeTags.ZOMBIES).add(SMEntityTypes.BOULDERING_ZOMBIE.get());
        this.tag(SMTags.Entities.SPAWN_IN_AMBER).add(
                EntityType.BEE,
                EntityType.SPIDER,
                EntityType.CAVE_SPIDER,
                EntityType.SILVERFISH
                //SMEntityTypes.JUNGLE_SPIDER.get()
        );
    }
}
