package com.uraneptus.sullysmod.core.data.server.loot;

import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class SMBlockLoot extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = Set.of();

    protected SMBlockLoot() {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return SMBlocks.HELPER.getDeferredRegister().getEntries().stream().map(RegistryObject::get)::iterator;
    }

    @Override
    protected void generate() {
        //Jade Ores
        this.add(SMBlocks.JADE_ORE.get(), this::createJadeOreDrops);
        this.add(SMBlocks.DEEPSLATE_JADE_ORE.get(), this::createJadeOreDrops);

        //Raw Jade
        this.dropSelf(SMBlocks.ROUGH_JADE_BLOCK.get());
        this.dropSelf(SMBlocks.ROUGH_JADE_BRICKS.get());
        this.dropSelf(SMBlocks.ROUGH_JADE_TILES.get());
        this.dropSelf(SMBlocks.SMOOTHED_ROUGH_JADE.get());
        this.dropSelf(SMBlocks.ROUGH_JADE_BRICK_STAIRS.get());
        this.dropSelf(SMBlocks.ROUGH_JADE_TILE_STAIRS.get());
        this.dropSelf(SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS.get());
        createSlab(SMBlocks.ROUGH_JADE_BRICK_SLAB.get());
        createSlab(SMBlocks.ROUGH_JADE_TILE_SLAB.get());
        createSlab(SMBlocks.SMOOTHED_ROUGH_JADE_SLAB.get());

        //Jade
        this.dropSelf(SMBlocks.POLISHED_JADE_BLOCK.get());
        this.dropSelf(SMBlocks.POLISHED_JADE_BRICKS.get());
        this.dropSelf(SMBlocks.POLISHED_SMALL_JADE_BRICKS.get());
        this.dropSelf(SMBlocks.POLISHED_JADE_TILES.get());
        this.dropSelf(SMBlocks.POLISHED_JADE_SHINGLES.get());
        this.dropSelf(SMBlocks.POLISHED_CHISELED_JADE.get());
        this.dropSelf(SMBlocks.POLISHED_JADE_PILLAR.get());
        this.dropSelf(SMBlocks.JADE_TOTEM.get());
        this.dropSelf(SMBlocks.JADE_FLINGER_TOTEM.get());
        this.dropSelf(SMBlocks.POLISHED_JADE_BRICK_STAIRS.get());
        this.dropSelf(SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get());
        this.dropSelf(SMBlocks.POLISHED_JADE_TILE_STAIRS.get());
        this.dropSelf(SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get());
        createSlab(SMBlocks.POLISHED_JADE_BRICK_SLAB.get());
        createSlab(SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get());
        createSlab(SMBlocks.POLISHED_JADE_TILE_SLAB.get());
        createSlab(SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get());

        //Copper Buttons
        this.dropSelf(SMBlocks.COPPER_BUTTON.get());
        this.dropSelf(SMBlocks.EXPOSED_COPPER_BUTTON.get());
        this.dropSelf(SMBlocks.WEATHERED_COPPER_BUTTON.get());
        this.dropSelf(SMBlocks.OXIDIZED_COPPER_BUTTON.get());
        this.dropSelf(SMBlocks.WAXED_COPPER_BUTTON.get());
        this.dropSelf(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get());
        this.dropSelf(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get());
        this.dropSelf(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get());

        //Tortoise Egg
        this.dropWhenSilkTouch(SMBlocks.TORTOISE_EGG.get());

        //Amber
        this.dropSelf(SMBlocks.AMBER.get());
        this.dropSelf(SMBlocks.AMBER_BRICKS.get());
        this.dropSelf(SMBlocks.AMBER_BRICK_SLAB.get());
        this.dropSelf(SMBlocks.AMBER_BRICK_STAIRS.get());
        this.dropSelf(SMBlocks.AMBER_BRICK_WALL.get());

        //Petrified Wood
        this.dropSelf(SMBlocks.PETRIFIED_PLANKS.get());
        this.dropSelf(SMBlocks.PETRIFIED_LOG.get());
        this.dropSelf(SMBlocks.STRIPPED_PETRIFIED_LOG.get());
        this.dropSelf(SMBlocks.PETRIFIED_WOOD.get());
        this.dropSelf(SMBlocks.STRIPPED_PETRIFIED_WOOD.get());
        this.dropSelf(SMBlocks.PETRIFIED_PRESSURE_PLATE.get());
        this.dropSelf(SMBlocks.PETRIFIED_TRAPDOOR.get());
        this.dropSelf(SMBlocks.PETRIFIED_STAIRS.get());
        createSlab(SMBlocks.PETRIFIED_SLAB.get());
        this.dropSelf(SMBlocks.PETRIFIED_BUTTON.get());
        this.dropSelf(SMBlocks.PETRIFIED_FENCE_GATE.get());
        this.dropSelf(SMBlocks.PETRIFIED_FENCE.get());
        this.dropSelf(SMBlocks.PETRIFIED_SIGN.getFirst().get());
        this.dropSelf(SMBlocks.PETRIFIED_SIGN.getSecond().get());
        this.dropSelf(SMBlocks.PETRIFIED_HANGING_SIGN.getFirst().get());
        this.dropSelf(SMBlocks.PETRIFIED_HANGING_SIGN.getSecond().get());
        createDoor(SMBlocks.PETRIFIED_DOOR.get());
    }

    protected LootTable.Builder createJadeOreDrops(Block block) {
        return createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(SMItems.ROUGH_JADE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected void createSlab(Block block) {
        add(block, createSlabItemTable(block));
    }

    protected void createDoor(Block block) {
        add(block, createDoorTable(block));
    }
}
