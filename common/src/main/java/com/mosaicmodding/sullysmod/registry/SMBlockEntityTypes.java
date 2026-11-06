package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.content.amber.AmberBE;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static com.mosaicmodding.sullysmod.registry.SMRegUtils.path;

public class SMBlockEntityTypes {
    public static final RegSupplier<BlockEntityType<AmberBE>> AMBER = RegHelper.registerBlockEntityType(path("amber"), () -> PlatHelper.newBlockEntityType(AmberBE::new, SMBlocks.AMBER_SOLID.get()));

    public static void init() {}
}
