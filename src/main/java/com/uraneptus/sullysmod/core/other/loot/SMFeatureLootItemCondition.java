package com.uraneptus.sullysmod.core.other.loot;

import com.google.gson.*;
import com.uraneptus.sullysmod.common.recipes.SMFeatureRecipeCondition;
import com.uraneptus.sullysmod.core.SMFeatures;
import com.uraneptus.sullysmod.core.registry.SMLootItemConditions;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.ArrayList;
import java.util.List;

public class SMFeatureLootItemCondition implements LootItemCondition {
    private final List<SMFeatures> condition;

    public SMFeatureLootItemCondition(List<SMFeatures> condition) {
        this.condition = condition;
    }

    public static LootItemCondition.Builder modFeatureCondition(List<SMFeatures> features) {
        return () -> new SMFeatureLootItemCondition(features);
    }

    @Override
    public LootItemConditionType getType() {
        return SMLootItemConditions.MOD_FEATURE_CONDITION_TYPE.get();
    }

    @Override
    public boolean test(LootContext lootContext) {
        return condition.stream().allMatch(SMFeatures::isEnabled);
    }

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<SMFeatureLootItemCondition> {

        public void serialize(JsonObject obj, SMFeatureLootItemCondition itemCondition, JsonSerializationContext context) {
            JsonArray values = new JsonArray();
            for (SMFeatures features : itemCondition.condition) {
                values.add(features.getSerializedName());
            }
            obj.add("values", values);
        }


        public SMFeatureLootItemCondition deserialize(JsonObject jsonObject, JsonDeserializationContext context) {
            List<SMFeatures> features = new ArrayList<>();
            for (JsonElement element : jsonObject.getAsJsonArray("values")) {
                SMFeatures feature = SMFeatures.byName(element.getAsString());
                features.add(feature);
            }
            return new SMFeatureLootItemCondition(features);
        }
    }
}
