package com.mosaicmodding.sullysmod.registry.registry_sets;

import net.mehvahdjukaar.moonlight.api.platform.TabAdderHelper;

public abstract class AbstractBlockRegistrySet {
    public final String id;
    public final String feature;

    public AbstractBlockRegistrySet(String id, String feature) {
        this.id = id;
        this.feature = feature;
    }

    public String name(String name) {
        return name(name, "%s");
    }

    public String name(String name, String regex) {
        return name.replace(regex, id);
    }

    public BlockItemTagBundle createTag(String tag) {
        return new BlockItemTagBundle(id + "_" + tag);
    }

    public abstract void addToTab(TabAdderHelper helper);
}
