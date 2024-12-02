package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blocks.utilities.SMSaplingBlock;
import com.uraneptus.sullysmod.core.other.SMFeatureDefinitions;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class PetrifiedSapling extends SMSaplingBlock {

    public PetrifiedSapling(Properties pProperties) {
        super(new AbstractTreeGrower() {
            @Override
            protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
                return SMFeatureDefinitions.CONFIGURED_PETRIFIED_TREE_SMALL;
            }
        }, pProperties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(Blocks.GRAVEL);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pLevel.getBlockState(pPos.below()).is(Blocks.GRAVEL);
    }
}
