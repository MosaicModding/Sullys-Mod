package com.mosaicmodding.sullysmod.content.amber;

import com.mosaicmodding.sullysmod.registry.SMBlocks;
import com.mosaicmodding.sullysmod.registry.datapack.SMTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class AmberBlobFeature extends Feature<NoneFeatureConfiguration> {

    public AmberBlobFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos blockpos = context.origin();
        WorldGenLevel worldgenlevel = context.level();
        RandomSource randomsource = context.random();
        List<EntityType<?>> possibleEntities = BuiltInRegistries.ENTITY_TYPE.stream().filter(e -> e.is(SMTags.Entities.SPAWN_IN_AMBER)).toList();
        int placedEntities = 0;

        if (blockpos.getY() <= worldgenlevel.getMinBuildHeight() + 3) {
            return false;
        } else {
            for (int l = 0; l < 3; ++l) {
                int i = randomsource.nextInt(3);
                int j = randomsource.nextInt(3);
                int k = randomsource.nextInt(3);
                float f = (float) (i + j + k) * 0.333F + 0.5F;

                for (BlockPos blockpos1 : BlockPos.betweenClosed(blockpos.offset(-i, -j, -k), blockpos.offset(i, j, k))) {
                    if (blockpos1.distSqr(blockpos) <= (double) (f * f)) {
                        BlockState toBePlaced;
                        boolean storeEntity = false;
                        if (placedEntities < 3 && randomsource.nextInt(5) == 0) {
                            toBePlaced = SMBlocks.AMBER_SOLID.get().defaultBlockState();
                            storeEntity = true;
                        } else {
                            toBePlaced = SMBlocks.AMBER.get().defaultBlockState();
                        }

                        worldgenlevel.setBlock(blockpos1, toBePlaced, 3);
                        if (storeEntity) {
                            BlockEntity be = worldgenlevel.getBlockEntity(blockpos1);
                            if (be instanceof AmberBE amberBE) {
                                EntityType<?> entityType = possibleEntities.get(randomsource.nextInt(possibleEntities.size()));
                                if (entityType != null) {
                                    if (amberBE.storeTypeForGeneration(entityType)) {
                                        placedEntities++;
                                    }
                                }
                            }
                        }
                    }
                }
                blockpos = blockpos.offset(-1 + randomsource.nextInt(2), -randomsource.nextInt(2), -1 + randomsource.nextInt(2));
            }
            return true;
        }
    }
}