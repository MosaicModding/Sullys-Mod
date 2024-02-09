package com.uraneptus.sullysmod.common.blocks.grower;

import com.uraneptus.sullysmod.core.data.server.SMDatapackBuiltinEntriesProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class PetrifiedTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return SMDatapackBuiltinEntriesProvider.CONFIGURED_PETRIFIED_TREE;
    }
}
