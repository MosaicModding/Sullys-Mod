package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.levelgen.PetrifiedTreeVariant;

public class SMPetrifiedTreeVariants {
    public static final int BIG_LIMIT = 15;
    public static final int SMALL_LIMIT = 2;

    public static void init() {}

    public static final PetrifiedTreeVariant GROUND0 = create("ground0", false, SMALL_LIMIT);
    public static final PetrifiedTreeVariant GROUND1 = create("ground1", false, SMALL_LIMIT);
    public static final PetrifiedTreeVariant GROUND2 = create("ground2", false, SMALL_LIMIT);
    public static final PetrifiedTreeVariant GROUND_SMALL0 = create("ground_small0", false, SMALL_LIMIT);
    public static final PetrifiedTreeVariant GROUND_SMALL1 = create("ground_small1", false, SMALL_LIMIT);
    public static final PetrifiedTreeVariant GROUND_SMALL2 = create("ground_small2", false, SMALL_LIMIT);
    public static final PetrifiedTreeVariant GROUND_SMALL3 = create("ground_small3", false, SMALL_LIMIT);
    public static final PetrifiedTreeVariant SMALL0 = create("small0", false, SMALL_LIMIT);
    public static final PetrifiedTreeVariant SMALL1 = create("small1", false, SMALL_LIMIT);
    public static final PetrifiedTreeVariant SMALL2 = create("small2", false, SMALL_LIMIT);
    public static final PetrifiedTreeVariant SMALL3 = create("small3", true, SMALL_LIMIT);
    public static final PetrifiedTreeVariant MIDDLE0 = create("middle0", true, BIG_LIMIT);
    public static final PetrifiedTreeVariant MIDDLE1 = create("middle1", true, BIG_LIMIT);
    public static final PetrifiedTreeVariant SIDE0 = create("side0", false, SMALL_LIMIT);
    public static final PetrifiedTreeVariant SIDE1 = create("side1", false, SMALL_LIMIT);
    public static final PetrifiedTreeVariant SIDE2 = create("side2", true, BIG_LIMIT);
    public static final PetrifiedTreeVariant SIDE3 = create("side3", true, BIG_LIMIT);
    public static final PetrifiedTreeVariant HOLLOW = create("hollow", true, BIG_LIMIT);
    public static final PetrifiedTreeVariant BIG0 = create("big0", true, BIG_LIMIT);
    public static final PetrifiedTreeVariant BIG1 = create("big1", true, BIG_LIMIT);

    public static PetrifiedTreeVariant create(String name, boolean allowAmber, int susGravelLimit) {
        return new PetrifiedTreeVariant(SullysMod.modPrefix("petrified/" + name), allowAmber, susGravelLimit);
    }
}
