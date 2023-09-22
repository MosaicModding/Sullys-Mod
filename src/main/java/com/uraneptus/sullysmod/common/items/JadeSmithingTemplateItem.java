package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class JadeSmithingTemplateItem extends SmithingTemplateItem {
    private static final Component JADE_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation("jade_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component JADE_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.jade_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component JADE_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.jade_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component JADE_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.jade_upgrade.base_slot_description")));
    private static final Component JADE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.jade_upgrade.additions_slot_description")));
    private static final ResourceLocation EMPTY_SLOT_JADE = SullysMod.modPrefix("item/empty_slot_jade");
    private static final ResourceLocation EMPTY_SLOT_SHIELD = new ResourceLocation("item/empty_armor_slot_shield");

    public JadeSmithingTemplateItem() {
        super(JADE_UPGRADE_APPLIES_TO, JADE_UPGRADE_INGREDIENTS, JADE_UPGRADE, JADE_UPGRADE_BASE_SLOT_DESCRIPTION, JADE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, List.of(EMPTY_SLOT_SHIELD), List.of(EMPTY_SLOT_JADE));
    }
}
