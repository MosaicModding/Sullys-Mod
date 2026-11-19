package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.content.amber.AmberBlobFeature;
import com.mosaicmodding.sullysmod.content.petrified.PetrifiedTreeConfig;
import com.mosaicmodding.sullysmod.content.petrified.PetrifiedTreeFeature;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;

import static com.mosaicmodding.sullysmod.core.SMUtils.path;

public class SMWorldgenFeatures {

    public static final RegSupplier<PetrifiedTreeFeature> PETRIFIED_TREE = RegHelper.registerFeature(path("petrified_tree"), () -> new PetrifiedTreeFeature(PetrifiedTreeConfig.CODEC));
    public static final RegSupplier<AmberBlobFeature> AMBER_BLOB = RegHelper.registerFeature(path("amber_blob"), AmberBlobFeature::new);

    public static void init() {

    }
}
