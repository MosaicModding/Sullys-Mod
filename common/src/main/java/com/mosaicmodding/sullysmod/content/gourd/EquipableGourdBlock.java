package com.mosaicmodding.sullysmod.content.gourd;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

public class EquipableGourdBlock extends CarvedGourdBlock implements Equipable {
    public static final MapCodec<EquipableGourdBlock> CODEC = simpleCodec(EquipableGourdBlock::new);

    public EquipableGourdBlock(Properties properties) {
        super(properties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }
}
