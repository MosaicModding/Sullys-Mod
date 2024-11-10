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
import net.minecraft.sounds.SoundSource;
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

import javax.annotation.Nullable;
import java.util.Optional;

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


    public void animateTick(Level pLevel, BlockPos pPos, FluidState pState, RandomSource pRandom) {
        BlockPos blockpos = pPos.above();
        if (pLevel.getBlockState(blockpos).isAir() && !pLevel.getBlockState(blockpos).isSolidRender(pLevel, blockpos)) {
            if (pRandom.nextInt(100) == 0) {
                double d0 = (double) pPos.getX() + pRandom.nextDouble();
                double d1 = (double) pPos.getY() + 1.0;
                double d2 = (double) pPos.getZ() + pRandom.nextDouble();
                pLevel.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0, 0.0, 0.0);
                pLevel.playLocalSound(d0, d1, d2, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.2F + pRandom.nextFloat() * 0.2F, 0.9F + pRandom.nextFloat() * 0.15F, false);
            }

            if (pRandom.nextInt(200) == 0) {
                pLevel.playLocalSound((double) pPos.getX(), (double) pPos.getY(), (double) pPos.getZ(), SoundEvents.LAVA_AMBIENT, SoundSource.BLOCKS, 0.2F + pRandom.nextFloat() * 0.2F, 0.9F + pRandom.nextFloat() * 0.15F, false);
            }
        }
    }

    public void randomTick(Level pLevel, BlockPos pPos, FluidState pState, RandomSource pRandom) {
        if (pLevel.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
            int i = pRandom.nextInt(3);
            if (i > 0) {
                BlockPos blockpos = pPos;

                for(int j = 0; j < i; ++j) {
                    blockpos = blockpos.offset(pRandom.nextInt(3) - 1, 1, pRandom.nextInt(3) - 1);
                    if (!pLevel.isLoaded(blockpos)) {
                        return;
                    }

                    BlockState blockstate = pLevel.getBlockState(blockpos);
                    if (blockstate.isAir()) {
                        if (this.hasFlammableNeighbours(pLevel, blockpos)) {
                            pLevel.setBlockAndUpdate(blockpos, ForgeEventFactory.fireFluidPlaceBlockEvent(pLevel, blockpos, pPos, Blocks.FIRE.defaultBlockState()));
                            return;
                        }
                    } else if (blockstate.blocksMotion()) {
                        return;
                    }
                }
            } else {
                for(int k = 0; k < 3; ++k) {
                    BlockPos blockpos1 = pPos.offset(pRandom.nextInt(3) - 1, 0, pRandom.nextInt(3) - 1);
                    if (!pLevel.isLoaded(blockpos1)) {
                        return;
                    }

                    if (pLevel.isEmptyBlock(blockpos1.above()) && this.isFlammable(pLevel, blockpos1, Direction.UP)) {
                        pLevel.setBlockAndUpdate(blockpos1.above(), ForgeEventFactory.fireFluidPlaceBlockEvent(pLevel, blockpos1.above(), pPos, Blocks.FIRE.defaultBlockState()));
                    }
                }
            }
        }
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

    private boolean hasFlammableNeighbours(LevelReader pLevel, BlockPos pPos) {
        Direction[] var3 = Direction.values();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Direction direction = var3[var5];
            if (this.isFlammable(pLevel, pPos.relative(direction), direction.getOpposite())) {
                return true;
            }
        }

        return false;
    }

    /** @deprecated */
    @Deprecated
    private boolean isFlammable(LevelReader pLevel, BlockPos pPos) {
        return pPos.getY() >= pLevel.getMinBuildHeight() && pPos.getY() < pLevel.getMaxBuildHeight() && !pLevel.hasChunkAt(pPos) ? false : pLevel.getBlockState(pPos).ignitedByLava();
    }

    private boolean isFlammable(LevelReader level, BlockPos pos, Direction face) {
        return pos.getY() >= level.getMinBuildHeight() && pos.getY() < level.getMaxBuildHeight() && !level.hasChunkAt(pos) ? false : level.getBlockState(pos).isFlammable(level, pos, face);
    }

    @Nullable
    public ParticleOptions getDripParticle() {
        return ParticleTypes.DRIPPING_LAVA;
    }

    protected void beforeDestroyingBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        this.fizz(pLevel, pPos);
    }

    public int getSlopeFindDistance(LevelReader pLevel) {
        return pLevel.dimensionType().ultraWarm() ? 4 : 2;
    }

    public BlockState createLegacyBlock(FluidState pState) {
        return (BlockState) SMBlocks.MOLTEN_AMBER_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(pState));
    }

    @Override
    public boolean isSource(FluidState fluidState) {
        return false;
    }

    public boolean isSame(Fluid pFluid) {
        return pFluid == SMFluids.SOURCE_MOLTEN_AMBER.get() || pFluid == SMFluids.FLOWING_MOLTEN_AMBER.get();
    }

    public int getDropOff(LevelReader pLevel) {
        return pLevel.dimensionType().ultraWarm() ? 1 : 2;
    }

    public boolean canBeReplacedWith(FluidState pFluidState, BlockGetter pBlockReader, BlockPos pPos, Fluid pFluid, Direction pDirection) {
        return pFluidState.getHeight(pBlockReader, pPos) >= 0.44444445F && pFluid.is(FluidTags.WATER);
    }

    public int getTickDelay(LevelReader pLevel) {
        return pLevel.dimensionType().ultraWarm() ? 10 : 30;
    }

    public int getSpreadDelay(Level pLevel, BlockPos pPos, FluidState pCurrentState, FluidState pNewState) {
        int i = this.getTickDelay(pLevel);
        if (!pCurrentState.isEmpty() && !pNewState.isEmpty() && !(Boolean)pCurrentState.getValue(FALLING) && !(Boolean)pNewState.getValue(FALLING) && pNewState.getHeight(pLevel, pPos) > pCurrentState.getHeight(pLevel, pPos) && pLevel.getRandom().nextInt(4) != 0) {
            i *= 4;
        }

        return i;
    }

    @Override
    public int getAmount(FluidState fluidState) {
        return (Integer)fluidState.getValue(LEVEL);
    }

    private void fizz(LevelAccessor pLevel, BlockPos pPos) {
        pLevel.levelEvent(1501, pPos, 0);
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

                this.fizz(pLevel, pPos);
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
            pBuilder.add(new Property[]{LEVEL});
        }

        public int getAmount(FluidState pState) {
            return (Integer)pState.getValue(LEVEL);
        }

        public boolean isSource(FluidState pState) {
            return false;
        }
    }
}
