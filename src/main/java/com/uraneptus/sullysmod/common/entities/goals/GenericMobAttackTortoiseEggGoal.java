package com.uraneptus.sullysmod.common.entities.goals;

import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RemoveBlockGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class GenericMobAttackTortoiseEggGoal extends RemoveBlockGoal {
    public GenericMobAttackTortoiseEggGoal(PathfinderMob p_34344_, double p_34345_, int p_34346_) {
        super(SMBlocks.TORTOISE_EGG.get(), p_34344_, p_34345_, p_34346_);
    }

    public void playDestroyProgressSound(LevelAccessor pLevel, BlockPos pPos) {
        pLevel.playSound(null, pPos, SoundEvents.ZOMBIE_DESTROY_EGG, SoundSource.HOSTILE, 0.5F, 0.9F + pLevel.getRandom().nextFloat() * 0.2F);
    }

    public void playBreakSound(Level pLevel, BlockPos pPos) {
        pLevel.playSound(null, pPos, SMSounds.TORTOISE_EGG_BREAK.get(), SoundSource.BLOCKS, 0.7F, 0.9F + pLevel.random.nextFloat() * 0.2F);
    }

    public double acceptedDistance() {
        return 1.14D;
    }
}

