package com.uraneptus.sullysmod.common.blocks;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings({"deprecation", "unused"})
public class WeatheringCopperButtonBlock extends CopperButtonBlock implements WeatheringCopper {
    private final WeatheringCopper.WeatherState weatherState;
    public static Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(SMBlocks.COPPER_BUTTON.get(), SMBlocks.EXPOSED_COPPER_BUTTON.get())
            .put(SMBlocks.EXPOSED_COPPER_BUTTON.get(), SMBlocks.WEATHERED_COPPER_BUTTON.get())
            .put(SMBlocks.WEATHERED_COPPER_BUTTON.get(), SMBlocks.OXIDIZED_COPPER_BUTTON.get())
            .build());

    public static final Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

    public WeatheringCopperButtonBlock(Properties pProperties, BlockSetType pType, int pTicksToStayPressed, boolean pArrowsCanPress, WeatherState weatherState) {
        super(pProperties, pType, pTicksToStayPressed, pArrowsCanPress);
        this.weatherState = weatherState;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.onRandomTick(state, level, pos, random);
    }

    @Override
    public Optional<BlockState> getNext(BlockState state) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(state.getBlock())).map((block) -> block.withPropertiesOf(state));
    }

    public static Optional<BlockState> getPrevious(BlockState state) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(state.getBlock())).map((block) -> block.withPropertiesOf(state));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(state.getBlock())).isPresent();
    }

    @Override
    public WeatherState getAge() {
        return weatherState;
    }
}
