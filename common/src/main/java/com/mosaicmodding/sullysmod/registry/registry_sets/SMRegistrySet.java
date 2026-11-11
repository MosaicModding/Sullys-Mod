package com.mosaicmodding.sullysmod.registry.registry_sets;

public class SMRegistrySet {
    public final String id;
    public final String feature;

    public SMRegistrySet(String id, String feature) {
        this.id = id;
        this.feature = feature;
    }

    public String name(String name) {
        return name(name, "%s");
    }

    public String name(String name, String regex) {
        return name.replace(regex, id);
    }
}
