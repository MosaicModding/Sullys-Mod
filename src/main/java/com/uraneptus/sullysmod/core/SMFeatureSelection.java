package com.uraneptus.sullysmod.core;

public class SMFeatureSelection {
    //TODO I do this fully once all features are implemented for 3.2
    public static final String JADE = "jade";
    public static final String PETRIFIED_WOOD = "petrified_wood";
    public static final String AMBER = "amber"; //depends on petrified wood
    public static final String TORTOISE = "tortoise";
    public static final String LANTERNFISH = "lanternfish";
    public static final String PIRANHA = "piranha";
    //throwing knife
    public static final String BOULDERING_ZOMBIE = "bouldering_zombie";
    public static final String JUNGLE_SPIDER = "jungle_spider";
    public static final String ARTIFACTS = "artifacts";
    //ancient skulls?
    public static final String UNLUCK_POTION = "unluck_potion";
    public static final String COPPER_BUTTONS = "copper_buttons";
    public static final String GEM_LANTERNS = "gem_lanterns";
    public static final String GRINDSTONE_POLISHING = "grindstone_polishing";

    public static boolean isEnabled(String key) {
        return switch (key) {
            case GEM_LANTERNS -> SMConfig.ENABLE_GEM_LANTERNS.get();
            default -> false;
        };
    }
}
