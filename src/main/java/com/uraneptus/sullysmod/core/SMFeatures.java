package com.uraneptus.sullysmod.core;

public enum SMFeatures {
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
    COPPER_BUTTONS,
    GEM_LANTERNS,
    GRINDSTONE_POLISHING;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

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
            case COPPER_BUTTONS -> SMConfig.ENABLE_COPPER_BUTTONS.get();
            case GEM_LANTERNS -> SMConfig.ENABLE_GEM_LANTERNS.get();
            case GRINDSTONE_POLISHING -> SMConfig.ENABLE_GRINDSTONE_POLISHING.get();
        };
    }
}
