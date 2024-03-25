package com.uraneptus.sullysmod.data.server.tags;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unchecked")
public class SMItemTagsProvider extends ItemTagsProvider {

    public SMItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> pProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, pProvider, blockProvider, SullysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //Mod Tags
        tag(SMItemTags.TORTOISE_FOOD).add(
                Items.MELON_SLICE,
                Items.GLOW_BERRIES,
                Items.SWEET_BERRIES,
                Items.APPLE
        );

        tag(SMItemTags.CARNIVORE_CONSUMABLES).add(
                Items.MUTTON,
                Items.PORKCHOP,
                Items.BEEF,
                Items.CHICKEN
        );
        tag(SMItemTags.PETRIFIED_LOGS).add(
                SMBlocks.PETRIFIED_LOG.get().asItem(),
                SMBlocks.STRIPPED_PETRIFIED_LOG.get().asItem(),
                SMBlocks.PETRIFIED_WOOD.get().asItem(),
                SMBlocks.STRIPPED_PETRIFIED_WOOD.get().asItem()
        );
        SMItems.ARTIFACTS.forEach((item, desc) -> tag(SMItemTags.ARTIFACTS).add(item.get()));

        //Minecraft Tags
        tag(ItemTags.BUTTONS).add(
                SMBlocks.COPPER_BUTTON.get().asItem(),
                SMBlocks.EXPOSED_COPPER_BUTTON.get().asItem(),
                SMBlocks.WEATHERED_COPPER_BUTTON.get().asItem(),
                SMBlocks.OXIDIZED_COPPER_BUTTON.get().asItem(),
                SMBlocks.WAXED_COPPER_BUTTON.get().asItem(),
                SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get().asItem(),
                SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get().asItem(),
                SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get().asItem()
        );
        tag(ItemTags.SLABS).add(
                SMBlocks.POLISHED_JADE_BRICK_SLAB.get().asItem(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get().asItem(),
                SMBlocks.POLISHED_JADE_TILE_SLAB.get().asItem(),
                SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get().asItem(),
                SMBlocks.ROUGH_JADE_BRICK_SLAB.get().asItem(),
                SMBlocks.ROUGH_JADE_TILE_SLAB.get().asItem(),
                SMBlocks.SMOOTHED_ROUGH_JADE_SLAB.get().asItem(),
                SMBlocks.PETRIFIED_SLAB.get().asItem()
        );
        tag(ItemTags.STAIRS).add(
                SMBlocks.POLISHED_JADE_BRICK_STAIRS.get().asItem(),
                SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get().asItem(),
                SMBlocks.POLISHED_JADE_TILE_STAIRS.get().asItem(),
                SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get().asItem(),
                SMBlocks.ROUGH_JADE_BRICK_STAIRS.get().asItem(),
                SMBlocks.ROUGH_JADE_TILE_STAIRS.get().asItem(),
                SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS.get().asItem()
        );
        tag(ItemTags.FISHES).add(
                SMItems.LANTERNFISH.get(),
                SMItems.COOKED_LANTERNFISH.get(),
                SMItems.PIRANHA.get(),
                SMItems.COOKED_PIRANHA.get()
        );
        tag(ItemTags.MUSIC_DISCS).add(
                SMItems.MUSIC_DISC_SCOUR.get(),
                SMItems.MUSIC_DISC_SUNKEN_PAST.get()
        );
        tag(ItemTags.NON_FLAMMABLE_WOOD)
                .addTag(SMItemTags.PETRIFIED_LOGS)
                .add(
                        SMBlocks.PETRIFIED_FENCE.get().asItem(),
                        SMBlocks.PETRIFIED_FENCE_GATE.get().asItem(),
                        SMBlocks.PETRIFIED_SLAB.get().asItem(),
                        SMBlocks.PETRIFIED_STAIRS.get().asItem(),
                        SMBlocks.PETRIFIED_BUTTON.get().asItem(),
                        SMBlocks.PETRIFIED_DOOR.get().asItem(),
                        SMBlocks.PETRIFIED_PRESSURE_PLATE.get().asItem(),
                        SMBlocks.PETRIFIED_TRAPDOOR.get().asItem(),
                        SMBlocks.PETRIFIED_SIGN.getFirst().get().asItem(),
                        SMBlocks.PETRIFIED_HANGING_SIGN.getFirst().get().asItem()
                );
        tag(ItemTags.FENCE_GATES).add(SMBlocks.PETRIFIED_FENCE_GATE.get().asItem());
        tag(ItemTags.PLANKS).add(SMBlocks.PETRIFIED_PLANKS.get().asItem());
        tag(ItemTags.WOODEN_FENCES).add(SMBlocks.PETRIFIED_FENCE.get().asItem());
        tag(ItemTags.WOODEN_STAIRS).add(SMBlocks.PETRIFIED_STAIRS.get().asItem());
        tag(ItemTags.WOODEN_SLABS).add(SMBlocks.PETRIFIED_SLAB.get().asItem());
        tag(ItemTags.SIGNS).add(SMBlocks.PETRIFIED_SIGN.getFirst().get().asItem());
        tag(ItemTags.HANGING_SIGNS).add(SMBlocks.PETRIFIED_SIGN.getFirst().get().asItem());
        tag(ItemTags.WOODEN_DOORS).add(SMBlocks.PETRIFIED_DOOR.get().asItem());
        tag(ItemTags.WOODEN_TRAPDOORS).add(SMBlocks.PETRIFIED_TRAPDOOR.get().asItem());
        tag(ItemTags.WOODEN_BUTTONS).add(SMBlocks.PETRIFIED_BUTTON.get().asItem());
        tag(ItemTags.WOODEN_PRESSURE_PLATES).add(SMBlocks.PETRIFIED_PRESSURE_PLATE.get().asItem());
        tag(ItemTags.LOGS).addTag(SMItemTags.PETRIFIED_LOGS);
        tag(ItemTags.SAPLINGS).add(SMBlocks.PETRIFIED_SAPLING.get().asItem());
        tag(ItemTags.STONE_TOOL_MATERIALS).addTag(SMItemTags.PETRIFIED_LOGS);


        //Forge Tags
        tag(SMItemTags.CRAFTING_TABLES).add(Items.CRAFTING_TABLE);
        tag(SMItemTags.JUKEBOXES).add(Items.JUKEBOX);
        tag(SMItemTags.RAW_LANTERNFISH).add(
                SMItems.LANTERNFISH.get()
        );
        tag(SMItemTags.RAW_PIRANHA).add(
                SMItems.PIRANHA.get()
        );
        tag(SMItemTags.RAW_FISHES).addTags(
                SMItemTags.RAW_LANTERNFISH,
                SMItemTags.RAW_PIRANHA
        );
        tag(SMItemTags.COOKED_LANTERNFISH).add(
                SMItems.COOKED_LANTERNFISH.get()
        );
        tag(SMItemTags.COOKED_PIRANHA).add(
                SMItems.COOKED_PIRANHA.get()
        );
        tag(SMItemTags.COOKED_FISHES).addTags(
                SMItemTags.COOKED_LANTERNFISH,
                SMItemTags.COOKED_PIRANHA
        );
        tag(Tags.Items.STORAGE_BLOCKS).add(
                SMBlocks.ROUGH_JADE_BLOCK.get().asItem(),
                SMBlocks.POLISHED_JADE_BLOCK.get().asItem()
        );
        tag(Tags.Items.ORES).addTag(
                SMItemTags.JADE_ORES
        );
        tag(SMItemTags.JADE_ORES).add(
                SMBlocks.JADE_ORE.get().asItem(),
                SMBlocks.DEEPSLATE_JADE_ORE.get().asItem()
        );
        tag(Tags.Items.ORES_IN_GROUND_STONE).add(
                SMBlocks.JADE_ORE.get().asItem()
        );
        tag(Tags.Items.ORES_IN_GROUND_DEEPSLATE).add(
                SMBlocks.DEEPSLATE_JADE_ORE.get().asItem()
        );
        tag(Tags.Items.TOOLS_SHIELDS).add(
                SMItems.JADE_SHIELD.get()
        );
        tag(Tags.Items.FENCES_WOODEN).add(SMBlocks.PETRIFIED_FENCE.get().asItem());
        tag(Tags.Items.FENCE_GATES_WOODEN).add(SMBlocks.PETRIFIED_FENCE_GATE.get().asItem());
        //tag(Tags.Items.COBBLESTONE_NORMAL).addTag(SMItemTags.PETRIFIED_LOGS);
    }
}
