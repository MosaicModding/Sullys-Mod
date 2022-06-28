package com.uraneptus.sullysmod.common.blocks;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class WeatheringCopperButtonBlock extends CopperButtonBlock implements WeatheringCopper {
    private final WeatheringCopper.WeatherState weatherState;

    public WeatheringCopperButtonBlock(WeatheringCopper.WeatherState weatherState, Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }

    public static Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> {
        return ImmutableBiMap.<Block, Block>builder()
                .put(SMBlocks.COPPER_BUTTON.get(), SMBlocks.EXPOSED_COPPER_BUTTON.get())
                .put(SMBlocks.EXPOSED_COPPER_BUTTON.get(), SMBlocks.WEATHERED_COPPER_BUTTON.get())
                .put(SMBlocks.WEATHERED_COPPER_BUTTON.get(), SMBlocks.OXIDIZED_COPPER_BUTTON.get())
                .build();
    });

    public static final Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> {
        return NEXT_BY_BLOCK.get().inverse();
    });

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.onRandomTick(state, level, pos, random);
    }

    @Override
    public Optional<BlockState> getNext(BlockState state) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(state.getBlock())).map((block) -> {
            return block.withPropertiesOf(state);
        });
    }

    public static Optional<BlockState> getPrevious(BlockState state) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(state.getBlock())).map((block) -> block.withPropertiesOf(state));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(state.getBlock())).isPresent();
    }

    @Override
    protected SoundEvent getSound(boolean pushed) {
        return pushed ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
    }

    @Override
    public WeatherState getAge() {
        return weatherState;
    }

    //Item Filler
    private static final Supplier<Item> POLISHED_BLACKSTONE_BUTTON = Blocks.POLISHED_BLACKSTONE_BUTTON::asItem;
    private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(POLISHED_BLACKSTONE_BUTTON);

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> item) {
        FILLER.fillItem(this.asItem(), tab, item);
    }
}
