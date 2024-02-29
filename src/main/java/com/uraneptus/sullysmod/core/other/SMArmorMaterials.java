package com.uraneptus.sullysmod.core.other;

import com.teamabnormals.blueprint.core.api.BlueprintArmorMaterial;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class SMArmorMaterials {
    public static final BlueprintArmorMaterial MINERS_HELMET = new BlueprintArmorMaterial(
            SullysMod.modPrefix("miners_helmet"),
            10,
            new int[]{0, 0, 0, 0},
            0,
            () -> SoundEvents.ARMOR_EQUIP_LEATHER, //TODO custom sound event
            0.0F, 0.0F,
            () -> Ingredient.of(ItemStack.EMPTY)
    );

    public static final BlueprintArmorMaterial SMALL_DENTED_HELMET = new BlueprintArmorMaterial(
            SullysMod.modPrefix("small_dented_helmet"),
            10,
            new int[]{0, 0, 0, 0},
            0,
            () -> SoundEvents.ARMOR_EQUIP_IRON, //TODO custom sound event
            0.0F, 0.0F,
            () -> Ingredient.of(ItemStack.EMPTY)
    );

    public static final BlueprintArmorMaterial LOST_CROWN = new BlueprintArmorMaterial(
            SullysMod.modPrefix("lost_crown"),
            10,
            new int[]{0, 0, 0, 0},
            0,
            () -> SoundEvents.ARMOR_EQUIP_GOLD, //TODO custom sound event
            0.0F, 0.0F,
            () -> Ingredient.of(ItemStack.EMPTY)
    );
}
