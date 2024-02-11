package com.uraneptus.sullysmod.common.levelgen;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.List;

public class PetrifiedTreeGravelDecorator extends AlterGroundDecorator {

    public PetrifiedTreeGravelDecorator() {
        super(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(Blocks.GRAVEL.defaultBlockState(), 5)
                .add(Blocks.SUSPICIOUS_GRAVEL.defaultBlockState(), 3))
        );
    }

    @Override
    public void place(TreeDecorator.Context pContext) {
        List<BlockPos> list = Lists.newArrayList();
        List<BlockPos> list1 = pContext.roots();
        List<BlockPos> list2 = pContext.logs();
        if (list1.isEmpty()) {
            list.addAll(list2);
        } else if (!list2.isEmpty() && list1.get(0).getY() == list2.get(0).getY()) {
            list.addAll(list2);
            list.addAll(list1);
        } else {
            list.addAll(list1);
        }

        if (!list.isEmpty()) {
            int i = list.get(0).getY();
            list.stream().filter((p_69310_) -> p_69310_.getY() == i).forEach((p_225978_) -> {
                this.placeCircle(pContext, p_225978_.west().north());
                this.placeCircle(pContext, p_225978_.east(2).north());
                this.placeCircle(pContext, p_225978_.west().south(2));
                this.placeCircle(pContext, p_225978_.east(2).south(2));

                for(int j = 0; j < 5; ++j) {
                    int k = pContext.random().nextInt(64);
                    int l = k % 8;
                    int i1 = k / 8;
                    if (l == 0 || l == 7 || i1 == 0 || i1 == 7) {
                        this.placeCircle(pContext, p_225978_.offset(-3 + l, 0, -3 + i1));
                    }
                }

            });
        }
    }

    private void placeCircle(TreeDecorator.Context pContext, BlockPos pPos) {
        for(int i = -2; i <= 2; ++i) {
            for(int j = -2; j <= 2; ++j) {
                if (Math.abs(i) != 2 || Math.abs(j) != 2) {
                    this.placeBlockAt(pContext, pPos.offset(i, 0, j));
                }
            }
        }

    }

    private void placeBlockAt(TreeDecorator.Context pContext, BlockPos pPos) {
        for(int i = 2; i >= -3; --i) {
            BlockPos blockpos = pPos.above(i);
            if (Feature.isGrassOrDirt(pContext.level(), blockpos)) {
                BlockState state = this.provider.getState(pContext.random(), pPos);
                pContext.level().getBlockEntity(pPos, BlockEntityType.BRUSHABLE_BLOCK).ifPresent(brushableBlockEntity -> {
                    brushableBlockEntity.setLootTable(BuiltInLootTables.END_CITY_TREASURE, pPos.asLong());
                });
                pContext.setBlock(blockpos, ForgeEventFactory.alterGround(pContext.level(), pContext.random(), blockpos, state));

                break;
            }

            if (!pContext.isAir(blockpos) && i < 0) {
                break;
            }
        }

    }
}
