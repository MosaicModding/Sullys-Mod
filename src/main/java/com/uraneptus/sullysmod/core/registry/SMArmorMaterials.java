package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;

public class SMArmorMaterials {

    public static final ArmorMaterial JADE = new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 0);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.HELMET, 0);
                map.put(ArmorItem.Type.BODY, 6);
            }),
            9,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(SMItems.JADE),
            List.of(
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(SullysMod.MOD_ID, "jade")
                    )
            ),
            0F,
            0F
    );

    public static final ArmorMaterial MINERS_HELMET = new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 0);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.HELMET, 1);
                map.put(ArmorItem.Type.BODY, 0);
            }),
            9,
            SMSounds.EQUIP_MINERS_HELMET,
            () -> Ingredient.of(ItemStack.EMPTY),
            List.of(
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(SullysMod.MOD_ID, "miners_helmet")
                    )
            ),
            0F,
            0F
    );


    public static final ArmorMaterial SMALL_DENTED_HELMET = new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 0);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.HELMET, 1);
                map.put(ArmorItem.Type.BODY, 0);
            }),
            9,
            SMSounds.EQUIP_SMALL_DENTED_HELMET,
            () -> Ingredient.of(ItemStack.EMPTY),
            List.of(
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(SullysMod.MOD_ID, "small_dented_helmet")
                    )
            ),
            0F,
            0F
    );

    public static final ArmorMaterial LOST_CROWN = new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 0);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.HELMET, 1);
                map.put(ArmorItem.Type.BODY, 0);
            }),
            9,
            SMSounds.EQUIP_LOST_CROWN,
            () -> Ingredient.of(ItemStack.EMPTY),
            List.of(
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(SullysMod.MOD_ID, "lost_crown")
                    )
            ),
            0F,
            0F
    );

    public static final ArmorMaterial STONE_MASK = new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 0);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.HELMET, 1);
                map.put(ArmorItem.Type.BODY, 0);
            }),
            9,
            SMSounds.EQUIP_STONE_MASK,
            () -> Ingredient.of(ItemStack.EMPTY),
            List.of(
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(SullysMod.MOD_ID, "stone_mask")
                    )
            ),
            0F,
            0F
    );
}
