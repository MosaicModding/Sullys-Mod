package com.mosaicmodding.sullysmod.registry.datapack;

import com.mosaicmodding.sullysmod.content.petrified.PetrifiedTreeVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.HashMap;
import java.util.Map;

import static com.mosaicmodding.sullysmod.core.SMUtils.path;

public class SMPetrifiedTreeVariants {
    public static final ResourceKey<Registry<PetrifiedTreeVariant>> REGISTRY_KEY = ResourceKey.createRegistryKey(path("petrified_tree_variants"));
    public static Map<ResourceKey<PetrifiedTreeVariant>, PetrifiedTreeVariant> REGISTRIES = new HashMap<>();

    public static final int BIG_LIMIT = 15;
    public static final int SMALL_LIMIT = 2;

    static final ResourceKey<PetrifiedTreeVariant> GROUND0 = register("ground0", false, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> GROUND1 = register("ground1", false, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> GROUND2 = register("ground2", false, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> GROUND_SMALL0 = register("ground_small0", false, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> GROUND_SMALL1 = register("ground_small1", false, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> GROUND_SMALL2 = register("ground_small2", false, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> GROUND_SMALL3 = register("ground_small3", false, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> SMALL0 = register("small0", false, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> SMALL1 = register("small1", false, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> SMALL2 = register("small2", false, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> SMALL3 = register("small3", true, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> MIDDLE0 = register("middle0", true, BIG_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> MIDDLE1 = register("middle1", true, BIG_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> SIDE0 = register("side0", false, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> SIDE1 = register("side1", false, SMALL_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> SIDE2 = register("side2", true, BIG_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> SIDE3 = register("side3", true, BIG_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> HOLLOW = register("hollow", true, BIG_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> BIG0 = register("big0", true, BIG_LIMIT);
    static final ResourceKey<PetrifiedTreeVariant> BIG1 = register("big1", true, BIG_LIMIT);

    private static ResourceKey<PetrifiedTreeVariant> register(String name, boolean allowAmber, int susGravelLimit) {
        ResourceKey<PetrifiedTreeVariant> key = ResourceKey.create(REGISTRY_KEY, path(name));
        var variant = new PetrifiedTreeVariant(path("petrified/" + name), allowAmber, susGravelLimit);
        REGISTRIES.put(key, variant);
        return key;
    }
}
