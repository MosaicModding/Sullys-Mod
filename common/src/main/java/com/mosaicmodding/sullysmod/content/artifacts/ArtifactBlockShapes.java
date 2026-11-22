package com.mosaicmodding.sullysmod.content.artifacts;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.HashMap;
import java.util.Map;

public class ArtifactBlockShapes {

    public static VoxelShape BOWL() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.25, 0.25, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0, 0.1875, 0.8125, 0.25, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.1875, 0.75, 0.25, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.75, 0.75, 0.25, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.0625, 0.75), BooleanOp.OR);
        return shape;
    }

    public static VoxelShape CUP() {
        return Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.3125, 0.6875);
    }

    public static VoxelShape VASE() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.375, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.375, 0.375, 0.625, 0.6875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.6875, 0.3125, 0.6875, 0.875, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.375, 0.375, 0.625, 0.6875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.6875, 0.3125, 0.6875, 0.875, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.375, 0.8125), BooleanOp.OR);
        return shape;
    }

    public static VoxelShape GOLDEN_IDOL() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.15625, 0, 0.15625, 0.84375, 0.25, 0.84375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.34375, 0.25, 0.34375, 0.65625, 0.5625, 0.65625), BooleanOp.OR);
        return shape;
    }

    public static VoxelShape GOLDEN_GOBLET() {
        return Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.6875, 0.6875);
    }

    public static VoxelShape FAMILIAR_CUBE() {
        return Shapes.box(0.125, 0, 0.125, 0.875, 0.75, 0.875);
    }

    public static Map<Direction, VoxelShape> STONE_IDOL() {
        return setShapeForDirection(
                Shapes.box(0.25, 0, 0.25, 0.75, 0.5625, 0.69),
                Shapes.box(0.3125, 0, 0.25, 0.75, 0.5625, 0.75),
                Shapes.box(0.25, 0, 0.25, 0.69, 0.5625, 0.75),
                Shapes.box(0.25, 0, 0.3125, 0.75, 0.5625, 0.75)
        );
    }

    public static Map<Direction, VoxelShape> FROG_IDOL() {
        VoxelShape north = Shapes.empty();
        north = Shapes.join(north, Shapes.box(0.5625, 0.375, 0.3125, 0.75, 0.4375, 0.5), BooleanOp.OR);
        north = Shapes.join(north, Shapes.box(0.25, 0.375, 0.3125, 0.4375, 0.4375, 0.5), BooleanOp.OR);
        north = Shapes.join(north, Shapes.box(0.25, 0, 0.25, 0.75, 0.375, 0.8125), BooleanOp.OR);
        north = Shapes.join(north, Shapes.box(0.1875, 0, 0.1875, 0.375, 0.1875, 0.4375), BooleanOp.OR);
        north = Shapes.join(north, Shapes.box(0.625, 0, 0.1875, 0.8125, 0.1875, 0.4375), BooleanOp.OR);
        north = Shapes.join(north, Shapes.box(0.1875, 0, 0.5625, 0.4375, 0.25, 0.875), BooleanOp.OR);
        north = Shapes.join(north, Shapes.box(0.5625, 0, 0.5625, 0.8125, 0.25, 0.875), BooleanOp.OR);

        VoxelShape south = Shapes.empty();
        south = Shapes.join(south, Shapes.box(0.25, 0.375, 0.5, 0.4375, 0.4375, 0.6875), BooleanOp.OR);
        south = Shapes.join(south, Shapes.box(0.5625, 0.375, 0.5, 0.75, 0.4375, 0.6875), BooleanOp.OR);
        south = Shapes.join(south, Shapes.box(0.25, 0, 0.1875, 0.75, 0.375, 0.75), BooleanOp.OR);
        south = Shapes.join(south, Shapes.box(0.625, 0, 0.5625, 0.8125, 0.1875, 0.8125), BooleanOp.OR);
        south = Shapes.join(south, Shapes.box(0.1875, 0, 0.5625, 0.375, 0.1875, 0.8125), BooleanOp.OR);
        south = Shapes.join(south, Shapes.box(0.5625, 0, 0.125, 0.8125, 0.25, 0.4375), BooleanOp.OR);
        south = Shapes.join(south, Shapes.box(0.1875, 0, 0.125, 0.4375, 0.25, 0.4375), BooleanOp.OR);

        VoxelShape east = Shapes.empty();
        east = Shapes.join(east, Shapes.box(0.5, 0.375, 0.5625, 0.6875, 0.4375, 0.75), BooleanOp.OR);
        east = Shapes.join(east, Shapes.box(0.5, 0.375, 0.25, 0.6875, 0.4375, 0.4375), BooleanOp.OR);
        east = Shapes.join(east, Shapes.box(0.1875, 0, 0.25, 0.75, 0.375, 0.75), BooleanOp.OR);
        east = Shapes.join(east, Shapes.box(0.5625, 0, 0.1875, 0.8125, 0.1875, 0.375), BooleanOp.OR);
        east = Shapes.join(east, Shapes.box(0.5625, 0, 0.625, 0.8125, 0.1875, 0.8125), BooleanOp.OR);
        east = Shapes.join(east, Shapes.box(0.125, 0, 0.1875, 0.4375, 0.25, 0.4375), BooleanOp.OR);
        east = Shapes.join(east, Shapes.box(0.125, 0, 0.5625, 0.4375, 0.25, 0.8125), BooleanOp.OR);

        VoxelShape west = Shapes.empty();
        west = Shapes.join(west, Shapes.box(0.3125, 0.375, 0.25, 0.5, 0.4375, 0.4375), BooleanOp.OR);
        west = Shapes.join(west, Shapes.box(0.3125, 0.375, 0.5625, 0.5, 0.4375, 0.75), BooleanOp.OR);
        west = Shapes.join(west, Shapes.box(0.25, 0, 0.25, 0.8125, 0.375, 0.75), BooleanOp.OR);
        west = Shapes.join(west, Shapes.box(0.1875, 0, 0.625, 0.4375, 0.1875, 0.8125), BooleanOp.OR);
        west = Shapes.join(west, Shapes.box(0.1875, 0, 0.1875, 0.4375, 0.1875, 0.375), BooleanOp.OR);
        west = Shapes.join(west, Shapes.box(0.5625, 0, 0.5625, 0.875, 0.25, 0.8125), BooleanOp.OR);
        west = Shapes.join(west, Shapes.box(0.5625, 0, 0.1875, 0.875, 0.25, 0.4375), BooleanOp.OR);

        return setShapeForDirection(south, west, north, east);
    }

    /**
     * Insert shapes for each direction. The order is: S-W-N-E-U-D <br>
     * South will be used as default for the rest when applied in the block class.
     */
    private static Map<Direction, VoxelShape> setShapeForDirection(VoxelShape... shapes) {
        var map = new HashMap<Direction, VoxelShape>();
        int size = shapes.length;
        for (int i = 0; i < size; i++) {
            if (i == 4) {
                map.put(Direction.UP, shapes[i]);
                continue;
            }
            if (i == 5) {
                map.put(Direction.DOWN, shapes[i]);
                break;
            }
            var dir = Direction.from2DDataValue(i);
            map.put(dir, shapes[i]);
        }
        return map;
    }
}
