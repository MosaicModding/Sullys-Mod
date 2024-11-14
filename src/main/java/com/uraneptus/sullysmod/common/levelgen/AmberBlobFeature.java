package com.uraneptus.sullysmod.common.levelgen;

import com.mojang.serialization.Codec;
import com.uraneptus.sullysmod.common.blockentities.AmberBE;
import com.uraneptus.sullysmod.common.levelgen.configs.AmberBlobConfig;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraftforge.registries.ForgeRegistries;

public class AmberBlobFeature extends Feature<AmberBlobConfig> {

    public AmberBlobFeature(Codec<AmberBlobConfig> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<AmberBlobConfig> context) {
        BlockPos blockpos = context.origin();
        WorldGenLevel worldgenlevel = context.level();
        RandomSource randomsource = context.random();
        AmberBlobConfig config = context.config();
        BlockState amber = SMBlocks.AMBER.get().defaultBlockState();

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
                        worldgenlevel.setBlock(blockpos1, amber, 3);
                        if (randomsource.nextInt(15) == 0) {
                            BlockEntity be = worldgenlevel.getBlockEntity(blockpos1);
                            if (be instanceof AmberBE amberBE) {
                                EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(config.possibleEntities().get(randomsource.nextInt(config.possibleEntities().size())));
                                if (entityType != null) {
                                    //TODO rotate entity
                                    amberBE.storeTypeForGeneration(entityType);
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
