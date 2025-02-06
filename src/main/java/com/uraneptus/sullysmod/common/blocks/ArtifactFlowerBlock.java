package com.uraneptus.sullysmod.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class ArtifactFlowerBlock extends FlowerBlock {

    public ArtifactFlowerBlock(Properties pProperties) {
        this(() -> MobEffects.CONFUSION, 5, pProperties);
    }

    public ArtifactFlowerBlock(Supplier<MobEffect> effectSupplier, int pEffectDuration, Properties pProperties) {
        super(effectSupplier, pEffectDuration, pProperties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return true;
    }
}
