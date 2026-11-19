package com.mosaicmodding.sullysmod.server.worldgen;

import com.mosaicmodding.sullysmod.content.petrified.PetrifiedTreeVariant;
import com.mosaicmodding.sullysmod.registry.datapack.SMPetrifiedTreeVariants;
import net.minecraft.data.worldgen.BootstrapContext;

public class SMPetrifiedTreeVariantGen {

    public static void add(BootstrapContext<PetrifiedTreeVariant> context) {
        SMPetrifiedTreeVariants.REGISTRIES.forEach(context::register);
    }
}
