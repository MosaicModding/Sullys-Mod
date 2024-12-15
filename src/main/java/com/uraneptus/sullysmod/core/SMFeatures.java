package com.uraneptus.sullysmod.core;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum SMFeatures implements StringRepresentable {
    JADE,
    PETRIFIED_WOOD,
    AMBER,
    TORTOISE,
    LANTERNFISH,
    PIRANHA,
    BOULDERING_ZOMBIE,
    JUNGLE_SPIDER,
    ITEM_STAND,
    ARTIFACTS,
    UNLUCK_POTION,
    RESISTANCE_POTION,
    COPPER_BUTTONS,
    GEM_LANTERNS,
    GRINDSTONE_POLISHING;

    public static final StringRepresentable.EnumCodec<SMFeatures> CODEC = StringRepresentable.fromEnum(SMFeatures::values);
    private static final Map<String, SMFeatures> BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(SMFeatures::getSerializedName, mobCategory -> mobCategory));

    public static boolean isEnabled(SMFeatures feature) {
        return switch (feature) {
            case JADE -> SMConfig.ENABLE_JADE.get();
            case PETRIFIED_WOOD -> SMConfig.ENABLE_PETRIFIED_WOOD.get();
            case AMBER -> SMConfig.ENABLE_AMBER.get();
            case TORTOISE -> SMConfig.ENABLE_TORTOISE.get();
            case LANTERNFISH -> SMConfig.ENABLE_LANTERNFISH.get();
            case PIRANHA -> SMConfig.ENABLE_PIRANHA.get();
            case BOULDERING_ZOMBIE -> SMConfig.ENABLE_BOULDERING_ZOMBIE.get();
            case JUNGLE_SPIDER -> SMConfig.ENABLE_JUNGLE_SPIDER.get();
            case ITEM_STAND -> SMConfig.ENABLE_ITEM_STAND.get();
            case ARTIFACTS -> SMConfig.ENABLE_ARTIFACTS.get();
            case UNLUCK_POTION -> SMConfig.ENABLE_UNLUCK_POTION.get();
            case RESISTANCE_POTION -> SMConfig.ENABLE_RESISTANCE_POTION.get();
            case COPPER_BUTTONS -> SMConfig.ENABLE_COPPER_BUTTONS.get();
            case GEM_LANTERNS -> SMConfig.ENABLE_GEM_LANTERNS.get();
            case GRINDSTONE_POLISHING -> SMConfig.ENABLE_GRINDSTONE_POLISHING.get();
        };
    }

    public static boolean isEnabled(String name) {
        return isEnabled(SMFeatures.valueOf(name));
    }

    public static SMFeatures byName(String name) {
        return BY_NAME.get(name);
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name().toLowerCase();
    }
}
