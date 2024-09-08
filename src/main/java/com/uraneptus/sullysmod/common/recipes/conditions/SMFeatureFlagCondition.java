package com.uraneptus.sullysmod.common.recipes.conditions;

import com.google.gson.JsonObject;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

import java.util.function.Predicate;

public class SMFeatureFlagCondition implements ICondition {
    private static final ResourceLocation ID =SullysMod.modPrefix("flag");
    private final String condition;

    public SMFeatureFlagCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test(IContext context) {
        return Serializer.predicate.test(condition);
    }

    public static class Serializer implements IConditionSerializer<SMFeatureFlagCondition> {
        private static Predicate<String> predicate;

        public Serializer(Predicate<String> predicate) {
            Serializer.predicate = predicate;
        }

        @Override
        public void write(JsonObject json, SMFeatureFlagCondition value) {
            json.addProperty(SMFeatureFlagCondition.ID.getPath(), value.condition);
        }

        @Override
        public SMFeatureFlagCondition read(JsonObject json) {
            return new SMFeatureFlagCondition(json.getAsJsonPrimitive(SMFeatureFlagCondition.ID.getPath()).getAsString());
        }

        @Override
        public ResourceLocation getID() {
            return SMFeatureFlagCondition.ID;
        }
    }
}
