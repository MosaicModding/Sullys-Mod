package com.uraneptus.sullysmod.common.blocks.utilities;

import com.uraneptus.sullysmod.common.SMToggleable;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.List;
import java.util.function.Supplier;

public class SMLiquidBlock extends LiquidBlock implements SMToggleable {
    private final List<SMFeatures> features;

    public SMLiquidBlock(Supplier<? extends FlowingFluid> pFluid, Properties pProperties, SMFeatures... features) {
        super(pFluid, pProperties);
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
