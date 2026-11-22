package com.mosaicmodding.sullysmod.registry.datapack;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import static com.mosaicmodding.sullysmod.core.SMUtils.path;

public class SMTags {

    public static class Items {
        public static final TagKey<Item> TOOLS_SHEAR = commonTag("tools/shear");
        public static final TagKey<Item> ARTIFACTS = modTag("artifacts");

        public static TagKey<Item> modTag(String id) {
            return createModTag(Registries.ITEM, id);
        }

        static TagKey<Item> commonTag(String id) {
            return createOtherTag(Registries.ITEM, "c", id);
        }
    }

    public static class Blocks {
        public static final TagKey<Block> MELTS_AMBER = modTag("melts_amber");
        public static final TagKey<Block> IS_PETRIFIED = modTag("is_petrified");

        public static TagKey<Block> modTag(String id) {
            return createModTag(Registries.BLOCK, id);
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> BOULDERING_ZOMBIE_SPAWN_IN = modTag("bouldering_zombie_spawn_in");
        public static final TagKey<Biome> LANTERNFISH_SPAWN_IN = modTag("lanternfish_spawn_in");
        public static final TagKey<Biome> TORTOISES_SPAWN_IN = modTag("tortoises_spawn_in");
        public static final TagKey<Biome> PIRANHA_SPAWN_IN = modTag("piranha_spawn_in");
        public static final TagKey<Biome> MAULED_SPAWN_IN = modTag("mauled_spawn_in");
        public static final TagKey<Biome> JUNGLE_SPIDER_SPAWN_IN = modTag("jungle_spider_spawn_in");

        public static final TagKey<Biome> PETRIFIED_TREE_GENERATES_IN = modTag("petrified_tree_generates_in");
        public static final TagKey<Biome> ARTIFACT_GRAVEL_GENERATES_IN = modTag("artifact_gravel_generates_in");
        public static final TagKey<Biome> JADE_GENERATES_IN = modTag("jade_generates_in");

        public static final TagKey<Biome> SNOWY_MOUNTAINS = modTag("snowy_mountains");

        static TagKey<Biome> modTag(String id) {
            return createModTag(Registries.BIOME, id);
        }
    }

    public static class Entities {
        public static final TagKey<EntityType<?>> SPAWN_IN_AMBER = modTag("spawn_in_amber");

        public static TagKey<EntityType<?>> modTag(String id) {
            return createModTag(Registries.ENTITY_TYPE, id);
        }

        static TagKey<EntityType<?>> commonTag(String id) {
            return createOtherTag(Registries.ENTITY_TYPE, "c", id);
        }
    }

    public static <T> TagKey<T> createModTag(ResourceKey<Registry<T>> resourceKey, String id) {
        return TagKey.create(resourceKey, path(id));
    }

    public static <T> TagKey<T> createOtherTag(ResourceKey<Registry<T>> resourceKey, String namespace, String id) {
        return TagKey.create(resourceKey, ResourceLocation.fromNamespaceAndPath(namespace, id));
    }
}
