package com.uraneptus.sullysmod.common.item;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;

import java.util.function.Supplier;

public class SMRecordItem extends RecordItem {
    private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> Items.MUSIC_DISC_OTHERSIDE);

    public SMRecordItem(int comparatorValue, Supplier<SoundEvent> soundSupplier, Properties builder) {
        super(comparatorValue, soundSupplier, builder);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        FILLER.fillItem(this, group, items);
    }
}