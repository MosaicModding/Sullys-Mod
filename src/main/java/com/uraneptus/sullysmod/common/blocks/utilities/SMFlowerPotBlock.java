package com.uraneptus.sullysmod.common.blocks.utilities;

import com.uraneptus.sullysmod.common.SMToggleable;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class SMFlowerPotBlock extends FlowerPotBlock implements SMToggleable {
    private final List<SMFeatures> features;

    public SMFlowerPotBlock(@Nullable Supplier<FlowerPotBlock> emptyPot, Supplier<? extends Block> pContent, Properties properties, SMFeatures... features) {
        super(emptyPot, pContent, properties);
        this.features = List.of(features);
    }

    @Override
    public List<SMFeatures> getFeature() {
        return features;
    }

    @Override
    public Item getItem() {
        return this.asItem();
    }
}
