package com.uraneptus.sullysmod.common.levelgen;

import com.mojang.serialization.Codec;
import com.uraneptus.sullysmod.core.other.loot.SMBuiltInLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class ArtifactGravelFeature extends Feature<SimpleBlockConfiguration> {

    public ArtifactGravelFeature(Codec<SimpleBlockConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
        SimpleBlockConfiguration simpleblockconfiguration = context.config();
        WorldGenLevel worldgenlevel = context.level();
        BlockPos blockpos = context.origin();
        BlockState blockstate = simpleblockconfiguration.toPlace().getState(context.random(), blockpos);
        if (blockstate.canSurvive(worldgenlevel, blockpos)) {
            if (blockstate.is(Blocks.SUSPICIOUS_GRAVEL)) {
                context.level().getBlockEntity(blockpos, BlockEntityType.BRUSHABLE_BLOCK).ifPresent(brushableBlockEntity -> {
                    brushableBlockEntity.setLootTable(SMBuiltInLootTables.GRAVEL_PETRIFIED_SAPLING_TREE, blockpos.asLong());
                });
                worldgenlevel.setBlock(blockpos, blockstate, 2);
            }
            return true;
        } else {
            return false;
        }
    }
}
