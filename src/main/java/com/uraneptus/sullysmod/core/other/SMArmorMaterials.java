package com.uraneptus.sullysmod.core.other;

import com.teamabnormals.blueprint.core.api.BlueprintArmorMaterial;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
//MOVE to registry package
//BREAKING CHANGE use vanilla's ArmorMaterial in 1.21
public class SMArmorMaterials {
    public static final BlueprintArmorMaterial MINERS_HELMET = new BlueprintArmorMaterial(
            SullysMod.modPrefix("miners_helmet"),
            9,
            new int[]{1, 0, 0, 0},
            0,
            SMSounds.EQUIP_MINERS_HELMET,
            0.0F, 0.0F,
            () -> Ingredient.of(ItemStack.EMPTY)
    );

    public static final BlueprintArmorMaterial SMALL_DENTED_HELMET = new BlueprintArmorMaterial(
            SullysMod.modPrefix("small_dented_helmet"),
            9,
            new int[]{1, 0, 0, 0},
            0,
            SMSounds.EQUIP_SMALL_DENTED_HELMET,
            0.0F, 0.0F,
            () -> Ingredient.of(ItemStack.EMPTY)
    );

    public static final BlueprintArmorMaterial LOST_CROWN = new BlueprintArmorMaterial(
            SullysMod.modPrefix("lost_crown"),
            9,
            new int[]{1, 0, 0, 0},
            0,
            SMSounds.EQUIP_LOST_CROWN,
            0.0F, 0.0F,
            () -> Ingredient.of(ItemStack.EMPTY)
    );
}
