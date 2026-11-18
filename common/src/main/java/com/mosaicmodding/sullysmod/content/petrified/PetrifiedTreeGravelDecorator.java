package com.mosaicmodding.sullysmod.content.petrified;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mosaicmodding.sullysmod.registry.SMBuiltInLootTables;
import com.mosaicmodding.sullysmod.registry.SMTreeDecoratorTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PetrifiedTreeGravelDecorator extends TreeDecorator {
    public static final MapCodec<PetrifiedTreeGravelDecorator> CODEC = MapCodec.unit(PetrifiedTreeGravelDecorator::new);
    public final BlockStateProvider provider;

    public PetrifiedTreeGravelDecorator() {
        this.provider = new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(Blocks.GRAVEL.defaultBlockState(), 75)
                .add(Blocks.SUSPICIOUS_GRAVEL.defaultBlockState(), 25));
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return SMTreeDecoratorTypes.GRAVEL_DECORATOR.get();
    }

    public void place(TreeDecorator.Context pContext) {
        List<BlockPos> placementPositions = getPlacementPositions(pContext);

        if (!placementPositions.isEmpty()) {
            int baseY = placementPositions.getFirst().getY();
            placementPositions.stream()
                    .filter((position) -> position.getY() == baseY)
                    .forEach((basePosition) -> {
                        // Place circles in cardinal directions
                        this.placeCircle(pContext, basePosition.west().north());
                        this.placeCircle(pContext, basePosition.east().north());
                        this.placeCircle(pContext, basePosition.west().south());
                        this.placeCircle(pContext, basePosition.east().south());

                        // Random placement in 8x8 grid
                        for(int attempt = 0; attempt < 1; ++attempt) {
                            int randomIndex = pContext.random().nextInt(64);
                            int gridX = randomIndex % 8;
                            int gridZ = randomIndex / 8;
                            if (gridX == 0 || gridX == 7 || gridZ == 0 || gridZ == 7) {
                                this.placeCircle(pContext, basePosition.offset(-3 + gridX, 0, -3 + gridZ));
                            }
                        }
                    });
        }
    }

    private static @NotNull List<BlockPos> getPlacementPositions(Context pContext) {
        List<BlockPos> placementPositions = new ArrayList<>();
        List<BlockPos> rootPositions = pContext.roots();
        List<BlockPos> logPositions = pContext.logs();

        if (rootPositions.isEmpty()) {
            placementPositions.addAll(logPositions);
        } else if (!logPositions.isEmpty() && rootPositions.getFirst().getY() == logPositions.getFirst().getY()) {
            placementPositions.addAll(logPositions);
            placementPositions.addAll(rootPositions);
        } else {
            placementPositions.addAll(rootPositions);
        }
        return placementPositions;
    }

    private void placeCircle(TreeDecorator.Context pContext, BlockPos centerPos) {
        for(int xOffset = -2; xOffset <= 2; ++xOffset) {
            for(int zOffset = -2; zOffset <= 2; ++zOffset) {
                if (Math.abs(xOffset) != 2 || Math.abs(zOffset) != 2) {
                    this.placeBlockAt(pContext, centerPos.offset(xOffset, 0, zOffset));
                }
            }
        }
    }

    private void placeBlockAt(TreeDecorator.Context pContext, BlockPos targetPos) {
        for(int yOffset = 2; yOffset >= -3; --yOffset) {
            BlockPos checkPos = targetPos.above(yOffset);
            if (Feature.isGrassOrDirt(pContext.level(), checkPos)) {
                BlockState gravelState = this.provider.getState(pContext.random(), checkPos);
                //TODO call alter ground event here again for both loaders
                pContext.setBlock(checkPos, gravelState/*ForgeEventFactory.alterGround(pContext.level(), pContext.random(), checkPos, gravelState)*/);
                pContext.level().getBlockEntity(checkPos, BlockEntityType.BRUSHABLE_BLOCK).ifPresent(brushableBlockEntity -> {
                    brushableBlockEntity.setLootTable(SMBuiltInLootTables.GRAVEL_PETRIFIED_SAPLING_TREE, checkPos.asLong());
                });
                break;
            }
            if (!pContext.isAir(checkPos) && yOffset < 0) {
                break;
            }
        }
    }
}
