package com.uraneptus.sullysmod.common.blocks;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class JadeFlingerTotem extends SMDirectionalBlock {
    public JadeFlingerTotem(Properties properties) {
        super(properties);
    }

    private static final Supplier<Item> DROPPER = Blocks.DROPPER::asItem;
    private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(DROPPER);

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> item) {
        FILLER.fillItem(this.asItem(), tab, item);
    }
}