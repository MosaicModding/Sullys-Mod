package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.loot.SMFeatureLootItemCondition;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SMLootItemConditions {
    public static final DeferredRegister<LootItemConditionType> ITEM_CONDITIONS = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, SullysMod.MOD_ID);

    public static final RegistryObject<LootItemConditionType> MOD_FEATURE_CONDITION_TYPE = ITEM_CONDITIONS.register("mod_feature_flag", () -> new LootItemConditionType(new SMFeatureLootItemCondition.Serializer()));

}
