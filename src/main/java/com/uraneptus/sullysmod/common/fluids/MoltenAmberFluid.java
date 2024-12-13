package com.uraneptus.sullysmod.common.fluids;

import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMFluidTypes;
import com.uraneptus.sullysmod.core.registry.SMFluids;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;

//TODO clean up more. I only did the bare minimum for now
public class MoltenAmberFluid extends ForgeFlowingFluid {
    public static final float MIN_LEVEL_CUTOFF = 0.44444445F;

    protected MoltenAmberFluid(Properties properties) {
        super(properties);
    }

    @Override
    public FluidType getFluidType() {
        return SMFluidTypes.MOLTEN_AMBER_FLUID_TYPE.get();
    }

    public Fluid getFlowing() {
        return SMFluids.FLOWING_MOLTEN_AMBER.get();
    }

    public Fluid getSource() {
        return SMFluids.SOURCE_MOLTEN_AMBER.get();
    }

    public Item getBucket() {
        return SMItems.MOLTEN_AMBER_BUCKET.get();
    }

    public void randomTick(Level pLevel, BlockPos pPos, FluidState pState, RandomSource pRandom) {
        int k = 2;
        if (pState.isSource()) {
            k = 15;
        }
        if (pRandom.nextInt(k) == 0) {
            boolean cooldown = true;
            if (pLevel.dimension() == Level.NETHER) {
                cooldown = false;
            } else {
                for (BlockPos pos : BlockPos.betweenClosed(pPos.offset(-1, -1, -1), pPos.offset(1, 1, 1))) {
                    if (pLevel.getBlockState(pos).is(SMBlockTags.MELTS_AMBER) && !pLevel.getBlockState(pos).is(SMBlocks.MOLTEN_AMBER_BLOCK.get())) {
                        cooldown = false;
                        break;
                    }
                }
            }
            if (cooldown) {
                if (pLevel.getBlockState(pPos).getBlock() instanceof LiquidBlock) {
                    pLevel.setBlock(pPos, ForgeEventFactory.fireFluidPlaceBlockEvent(pLevel, pPos, pPos, SMBlocks.AMBER.get().defaultBlockState()), 3);
                }
            }
        }
    }

    public int getSlopeFindDistance(LevelReader pLevel) {
        return pLevel.dimensionType().ultraWarm() ? 4 : 2;
    }

    @Override
    public @NotNull BlockState createLegacyBlock(FluidState pState) {
        return SMBlocks.MOLTEN_AMBER_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(pState));
    }

    @Override
    public boolean isSource(FluidState fluidState) {
        return false;
    }

    @Override
    public boolean isSame(Fluid pFluid) {
        return pFluid == SMFluids.SOURCE_MOLTEN_AMBER.get() || pFluid == SMFluids.FLOWING_MOLTEN_AMBER.get();
    }

    @Override
    public int getDropOff(LevelReader pLevel) {
        return pLevel.dimensionType().ultraWarm() ? 1 : 2;
    }

    @Override
    public boolean canBeReplacedWith(FluidState pFluidState, BlockGetter pBlockReader, BlockPos pPos, Fluid pFluid, Direction pDirection) {
        return pFluidState.getHeight(pBlockReader, pPos) >= 0.44444445F && pFluid.is(FluidTags.WATER);
    }

    @Override
    public int getTickDelay(LevelReader pLevel) {
        return pLevel.dimensionType().ultraWarm() ? 10 : 30;
    }

    @Override
    public int getSpreadDelay(Level pLevel, BlockPos pPos, FluidState pCurrentState, FluidState pNewState) {
        int i = this.getTickDelay(pLevel);
        if (!pCurrentState.isEmpty() && !pNewState.isEmpty() && !(Boolean)pCurrentState.getValue(FALLING) && !(Boolean)pNewState.getValue(FALLING) && pNewState.getHeight(pLevel, pPos) > pCurrentState.getHeight(pLevel, pPos) && pLevel.getRandom().nextInt(4) != 0) {
            i *= 4;
        }
        return i;
    }

    @Override
    public int getAmount(FluidState fluidState) {
        return fluidState.getValue(LEVEL);
    }

    protected boolean canConvertToSource(Level pLevel) {
        return pLevel.getGameRules().getBoolean(GameRules.RULE_LAVA_SOURCE_CONVERSION);
    }

    @Override
    protected void spreadTo(LevelAccessor pLevel, BlockPos pPos, BlockState pBlockState, Direction pDirection, FluidState pFluidState) {
        if (pDirection == Direction.DOWN) {
            FluidState fluidstate = pLevel.getFluidState(pPos);
            if (fluidstate.is(FluidTags.WATER)) {
                if (pBlockState.getBlock() instanceof LiquidBlock) {
                    pLevel.setBlock(pPos, ForgeEventFactory.fireFluidPlaceBlockEvent(pLevel, pPos, pPos, SMBlocks.AMBER.get().defaultBlockState()), 3);
                }

                return;
            }
        }

        super.spreadTo(pLevel, pPos, pBlockState, pDirection, pFluidState);
    }

    protected boolean isRandomlyTicking() {
        return true;
    }

    protected float getExplosionResistance() {
        return 100.0F;
    }

    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL_LAVA);
    }

    public static class Source extends MoltenAmberFluid {

        public Source(Properties properties) {
            super(properties);
        }

        public int getAmount(FluidState pState) {
            return 8;
        }

        public boolean isSource(FluidState pState) {
            return true;
        }
    }

    public static class Flowing extends MoltenAmberFluid {

        public Flowing(Properties properties) {
            super(properties);
        }

        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> pBuilder) {
            super.createFluidStateDefinition(pBuilder);
            pBuilder.add(LEVEL);
        }

        public int getAmount(FluidState pState) {
            return pState.getValue(LEVEL);
        }

    }
}
