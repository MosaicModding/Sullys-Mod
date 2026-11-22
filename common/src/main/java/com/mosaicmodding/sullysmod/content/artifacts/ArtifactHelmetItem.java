package com.mosaicmodding.sullysmod.content.artifacts;

import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.context.UseOnContext;

public class ArtifactHelmetItem extends ArmorItem {
    public ArtifactHelmetItem(RegSupplier<ArmorMaterial> material, Properties pProperties) {
        super(material, Type.HELMET, pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        /*todo
        if (pContext.getLevel().getBlockState(pContext.getClickedPos()).is(SMBlocks.ITEM_STAND.get())) {
            return InteractionResult.PASS;
        }

         */
        return super.useOn(pContext);
    }
}
