package com.mosaicmodding.sullysmod.registry;

import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import static com.mosaicmodding.sullysmod.core.SMUtils.path;

public class SMArmorMaterials {

    public static final RegSupplier<ArmorMaterial> MINERS_HELMET = RegHelper.registerArmorMaterial(
            path("miners_helmet"),
            1,
            () -> Ingredient.of(ItemStack.EMPTY),
            0,
            () -> SoundEvents.ARMOR_EQUIP_CHAIN,
            //todo SMSounds.EQUIP_MINERS_HELMET,
            0,
            0
    );

    public static final RegSupplier<ArmorMaterial> SMALL_DENTED_HELMET = RegHelper.registerArmorMaterial(
            path("small_dented_helmet"),
            1,
            () -> Ingredient.of(ItemStack.EMPTY),
            0,
            () -> SoundEvents.ARMOR_EQUIP_CHAIN,
            //todo SMSounds.EQUIP_SMALL_DENTED_HELMET,
            0,
            0
    );

    public static final RegSupplier<ArmorMaterial> LOST_CROWN = RegHelper.registerArmorMaterial(
            path("lost_crown"),
            1,
            () -> Ingredient.of(ItemStack.EMPTY),
            0,
            () -> SoundEvents.ARMOR_EQUIP_CHAIN,
            //todo SMSounds.EQUIP_LOST_CROWN,
            0,
            0
    );

    public static final RegSupplier<ArmorMaterial> STONE_MASK = RegHelper.registerArmorMaterial(
            path("stone_mask"),
            1,
            () -> Ingredient.of(ItemStack.EMPTY),
            0,
            () -> SoundEvents.ARMOR_EQUIP_CHAIN,
            //todo SMSounds.EQUIP_STONE_MASK,
            0,
            0
    );

    public static void init() {}
}
