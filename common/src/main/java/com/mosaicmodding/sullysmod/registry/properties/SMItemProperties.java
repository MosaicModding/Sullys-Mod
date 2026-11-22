package com.mosaicmodding.sullysmod.registry.properties;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class SMItemProperties {
    public static Item.Properties DEFAULT() {
        return new Item.Properties();
    }

    public static Item.Properties SINGLE_STACK() {
        return new Item.Properties().stacksTo(1);
    }

    public static Item.Properties FOOD(FoodProperties props) {
        return new Item.Properties().food(props);
    }

    public static Item.Properties ARTIFACT() {
        return new Item.Properties()/*.rarity(SMArtifacts.ANCIENT)*/;
    }

    public static Item.Properties ARTIFACT_DURABILITY(int maxDamage) {
        return new Item.Properties()/*.rarity(SMArtifacts.ANCIENT)*/.durability(maxDamage);
    }

}
