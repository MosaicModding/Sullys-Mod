package com.uraneptus.sullysmod.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unchecked")
public class SMBlockTagsProvider extends BlockTagsProvider {

    public SMBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, pProvider, SullysMod.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags(HolderLookup.Provider pProvider) {
        //Minecraft Tags
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                SMBlocks.JADE_ORE.get(),
                SMBlocks.DEEPSLATE_JADE_ORE.get(),
                SMBlocks.ROUGH_JADE_BLOCK.get(),
                SMBlocks.ROUGH_JADE_BRICKS.get(),
                SMBlocks.JADE_BLOCK.get(),
                SMBlocks.JADE_BRICKS.get(),
                SMBlocks.CHISELED_JADE.get(),
                SMBlocks.JADE_TOTEM.get(),
                SMBlocks.JADE_FLINGER_TOTEM.get(),
                SMBlocks.JADE_PILLAR.get(),
                SMBlocks.JADE_BRICK_STAIRS.get(),
                SMBlocks.JADE_BRICK_SLAB.get(),
                SMBlocks.JADE_LANTERN.get(),
                SMBlocks.ROUGH_JADE_BRICK_WALL.get(),
                SMBlocks.JADE_BRICK_WALL.get(),
                SMBlocks.ROUGH_JADE_BRICK_STAIRS.get(),
                SMBlocks.ROUGH_JADE_BRICK_SLAB.get(),
                SMBlocks.PETRIFIED_PLANKS.get(),
                SMBlocks.PETRIFIED_LOG.get(),
                SMBlocks.STRIPPED_PETRIFIED_LOG.get(),
                SMBlocks.PETRIFIED_WOOD.get(),
                SMBlocks.STRIPPED_PETRIFIED_WOOD.get(),
                SMBlocks.PETRIFIED_STAIRS.get(),
                SMBlocks.PETRIFIED_SLAB.get(),
                SMBlocks.PETRIFIED_FENCE_GATE.get(),
                SMBlocks.PETRIFIED_FENCE.get(),
                SMBlocks.PETRIFIED_SIGN.getFirst().get(),
                SMBlocks.PETRIFIED_HANGING_SIGN.getFirst().get(),
                SMBlocks.PETRIFIED_DOOR.get(),
                SMBlocks.AMBER.get(),
                SMBlocks.ROUGH_AMBER.get(),
                SMBlocks.CHISELED_AMBER.get(),
                SMBlocks.AMBER_PILLAR.get(),
                SMBlocks.AMBER_LANTERN.get(),
                SMBlocks.AMBER_BRICKS.get(),
                SMBlocks.AMBER_BRICK_SLAB.get(),
                SMBlocks.AMBER_BRICK_STAIRS.get(),
                SMBlocks.AMBER_BRICK_WALL.get(),
                SMBlocks.ITEM_STAND.get(),
                SMBlocks.DIAMOND_LANTERN.get(),
                SMBlocks.EMERALD_LANTERN.get(),
                SMBlocks.LAPIS_LANTERN.get(),
                SMBlocks.AMETHYST_LANTERN.get(),
                SMBlocks.QUARTZ_LANTERN.get()
        );
        tag(BlockTags.NEEDS_IRON_TOOL).add(
                SMBlocks.JADE_ORE.get(),
                SMBlocks.DEEPSLATE_JADE_ORE.get(),
                SMBlocks.ROUGH_JADE_BLOCK.get(),
                SMBlocks.ROUGH_JADE_BRICKS.get(),
                SMBlocks.JADE_BLOCK.get(),
                SMBlocks.JADE_BRICKS.get(),
                SMBlocks.CHISELED_JADE.get(),
                SMBlocks.JADE_TOTEM.get(),
                SMBlocks.JADE_FLINGER_TOTEM.get(),
                SMBlocks.JADE_PILLAR.get(),
                SMBlocks.JADE_BRICK_STAIRS.get(),
                SMBlocks.JADE_BRICK_SLAB.get(),
                SMBlocks.ROUGH_JADE_BRICK_STAIRS.get(),
                SMBlocks.ROUGH_JADE_BRICK_SLAB.get(),
                SMBlocks.ROUGH_JADE_BRICK_WALL.get(),
                SMBlocks.JADE_BRICK_WALL.get(),
                SMBlocks.DIAMOND_LANTERN.get(),
                SMBlocks.EMERALD_LANTERN.get(),
                SMBlocks.LAPIS_LANTERN.get()
        );
        tag(BlockTags.BUTTONS).add(
                SMBlocks.COPPER_BUTTON.get(),
                SMBlocks.EXPOSED_COPPER_BUTTON.get(),
                SMBlocks.WEATHERED_COPPER_BUTTON.get(),
                SMBlocks.OXIDIZED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get()
        );
        tag(BlockTags.SLABS).add(
                SMBlocks.JADE_BRICK_SLAB.get(),
                SMBlocks.ROUGH_JADE_BRICK_SLAB.get()
        );
        tag(BlockTags.STAIRS).add(
                SMBlocks.JADE_BRICK_STAIRS.get(),
                SMBlocks.ROUGH_JADE_BRICK_STAIRS.get(),
                SMBlocks.AMBER_BRICK_STAIRS.get()
        );
        tag(BlockTags.WALLS).add(
                SMBlocks.AMBER_BRICK_WALL.get(),
                SMBlocks.ROUGH_JADE_BRICK_WALL.get(),
                SMBlocks.JADE_BRICK_WALL.get()
        );
        tag(BlockTags.FENCE_GATES).add(SMBlocks.PETRIFIED_FENCE_GATE.get());
        tag(BlockTags.WOODEN_FENCES).add(SMBlocks.PETRIFIED_FENCE.get());
        tag(BlockTags.STANDING_SIGNS).add(SMBlocks.PETRIFIED_SIGN.getFirst().get());
        tag(BlockTags.WALL_SIGNS).add(SMBlocks.PETRIFIED_SIGN.getSecond().get());
        tag(BlockTags.WOODEN_DOORS).add(SMBlocks.PETRIFIED_DOOR.get());
        tag(BlockTags.WOODEN_TRAPDOORS).add(SMBlocks.PETRIFIED_TRAPDOOR.get());
        tag(BlockTags.WOODEN_BUTTONS).add(SMBlocks.PETRIFIED_BUTTON.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(SMBlocks.PETRIFIED_PRESSURE_PLATE.get());
        tag(BlockTags.CEILING_HANGING_SIGNS).add(SMBlocks.PETRIFIED_HANGING_SIGN.getFirst().get());
        tag(BlockTags.WALL_HANGING_SIGNS).add(SMBlocks.PETRIFIED_HANGING_SIGN.getSecond().get());
        tag(BlockTags.WOODEN_SLABS).add(SMBlocks.PETRIFIED_SLAB.get());
        tag(BlockTags.WOODEN_STAIRS).add(SMBlocks.PETRIFIED_STAIRS.get());
        tag(BlockTags.PLANKS).add(SMBlocks.PETRIFIED_PLANKS.get());
        tag(BlockTags.SAPLINGS).add(SMBlocks.PETRIFIED_SAPLING.get());
        tag(BlockTags.FLOWER_POTS).add(SMBlocks.POTTED_PETRIFIED_SAPLING.get());

        //Our Tags
        tag(SMBlockTags.PROJECTILES_BOUNCE_ON).add(
                SMBlocks.JADE_BLOCK.get(),
                SMBlocks.JADE_BRICKS.get(),
                SMBlocks.CHISELED_JADE.get(),
                SMBlocks.JADE_TOTEM.get(),
                SMBlocks.JADE_PILLAR.get(),
                SMBlocks.JADE_BRICK_STAIRS.get(),
                SMBlocks.JADE_BRICK_SLAB.get(),
                SMBlocks.JADE_FLINGER_TOTEM.get(),
                SMBlocks.JADE_BRICK_WALL.get(),
                SMBlocks.JADE_LANTERN.get()
        );
        tag(SMBlockTags.PETRIFIED_LOGS).add(
                SMBlocks.PETRIFIED_LOG.get(),
                SMBlocks.STRIPPED_PETRIFIED_LOG.get(),
                SMBlocks.PETRIFIED_WOOD.get(),
                SMBlocks.STRIPPED_PETRIFIED_WOOD.get()
        );
        tag(SMBlockTags.MELTS_AMBER).add(
                Blocks.TORCH,
                Blocks.WALL_TORCH,
                Blocks.SOUL_TORCH,
                Blocks.SOUL_WALL_TORCH,
                Blocks.FIRE,
                Blocks.SOUL_FIRE,
                Blocks.LANTERN,
                Blocks.SOUL_LANTERN,
                Blocks.LAVA,
                Blocks.LAVA_CAULDRON,
                Blocks.CAMPFIRE,
                Blocks.SOUL_CAMPFIRE,
                //COPPER BULB
                Blocks.FURNACE,
                Blocks.BLAST_FURNACE,
                Blocks.SMOKER,
                Blocks.MAGMA_BLOCK,
                Blocks.OCHRE_FROGLIGHT,
                Blocks.PEARLESCENT_FROGLIGHT,
                Blocks.VERDANT_FROGLIGHT,
                Blocks.JACK_O_LANTERN/*,
                SMBlocks.MOLTEN_AMBER_BLOCK.get()*/
        ).addTags(BlockTags.CANDLES, BlockTags.CANDLE_CAKES);

        //Forge Tags
        tag(SMBlockTags.WAXABLE_COPPER_BLOCKS).add(
                SMBlocks.COPPER_BUTTON.get(),
                SMBlocks.EXPOSED_COPPER_BUTTON.get(),
                SMBlocks.WEATHERED_COPPER_BUTTON.get(),
                SMBlocks.OXIDIZED_COPPER_BUTTON.get()
        );
        tag(SMBlockTags.WAXED_COPPER_BLOCKS).add(
                SMBlocks.WAXED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(),
                SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get()
        );
        tag(Tags.Blocks.STORAGE_BLOCKS).add(
                SMBlocks.ROUGH_JADE_BLOCK.get(),
                SMBlocks.JADE_BLOCK.get()
        );
        tag(Tags.Blocks.ORES).addTag(
                SMBlockTags.JADE_ORES
        );
        tag(SMBlockTags.JADE_ORES).add(
                SMBlocks.JADE_ORE.get(),
                SMBlocks.DEEPSLATE_JADE_ORE.get()
        );
        tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(
                SMBlocks.JADE_ORE.get()
        );
        tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(
                SMBlocks.DEEPSLATE_JADE_ORE.get()
        );
        tag(Tags.Blocks.FENCES_WOODEN).add(SMBlocks.PETRIFIED_FENCE.get());
        tag(Tags.Blocks.FENCE_GATES_WOODEN).add(SMBlocks.PETRIFIED_FENCE_GATE.get());
        SMBlocks.ANCIENT_SKULLS.forEach(blockSupplier -> {
            tag(BlueprintBlockTags.NOTE_BLOCK_TOP_INSTRUMENTS).add(blockSupplier.get());
        });
    }
}
