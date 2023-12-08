package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.SMTextDefinitions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class JadeSmithingTemplateItem extends SmithingTemplateItem {
    private static final ResourceLocation EMPTY_SLOT_JADE = SullysMod.modPrefix("item/empty_slot_jade");
    private static final ResourceLocation EMPTY_SLOT_HORSE_ARMOR = SullysMod.modPrefix("item/empty_slot_horse_armor");
    private static final ResourceLocation EMPTY_SLOT_SHIELD = new ResourceLocation("item/empty_armor_slot_shield");

    public JadeSmithingTemplateItem() {
        super(SMTextDefinitions.JADE_UPGRADE_APPLIES_TO, SMTextDefinitions.JADE_UPGRADE_INGREDIENTS, SMTextDefinitions.JADE_UPGRADE, SMTextDefinitions.JADE_UPGRADE_BASE_SLOT_DESCRIPTION, SMTextDefinitions.JADE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, List.of(EMPTY_SLOT_SHIELD, EMPTY_SLOT_HORSE_ARMOR), List.of(EMPTY_SLOT_JADE));
    }
}
