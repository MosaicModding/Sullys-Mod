package com.uraneptus.sullysmod.common.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

import java.util.ArrayList;
import java.util.List;

public class SMFeatureRecipeCondition implements ICondition {
    private static final ResourceLocation ID = SullysMod.modPrefix("mod_features");
    private final List<SMFeatures> condition;

    public SMFeatureRecipeCondition(List<SMFeatures> condition) {
        this.condition = condition;
    }

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test(IContext context) {
        return condition.stream().allMatch(SMFeatures::isEnabled);
    }

    public static class Serializer implements IConditionSerializer<SMFeatureRecipeCondition> {
        private final ResourceLocation location;

        public Serializer() {
            this.location = SullysMod.modPrefix("mod_features");
        }

        @Override
        public void write(JsonObject json, SMFeatureRecipeCondition value) {
            JsonArray values = new JsonArray();
            for (SMFeatures feature : value.condition) {
                values.add(feature.getSerializedName());
            }
            json.add("values", values);
        }

        @Override
        public SMFeatureRecipeCondition read(JsonObject json) {
            List<SMFeatures> features = new ArrayList<>();
            for (JsonElement element : json.getAsJsonArray("values")) {
                SMFeatures feature = SMFeatures.byName(element.getAsString());
                features.add(feature);
            }
            return new SMFeatureRecipeCondition(features);
        }

        @Override
        public ResourceLocation getID() {
            return SMFeatureRecipeCondition.ID;
        }
    }
}
