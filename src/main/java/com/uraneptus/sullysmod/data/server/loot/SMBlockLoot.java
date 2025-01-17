package com.uraneptus.sullysmod.data.server.loot;

import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
        return SMBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    @Override
    protected void generate() {
        //Jade Ores
        this.add(SMBlocks.JADE_ORE.get(), this::createJadeOreDrops);
        this.add(SMBlocks.DEEPSLATE_JADE_ORE.get(), this::createJadeOreDrops);

        //Raw Jade
        this.dropSelf(SMBlocks.ROUGH_JADE_BLOCK.get());
        this.dropSelf(SMBlocks.ROUGH_JADE_BRICKS.get());
        this.dropSelf(SMBlocks.ROUGH_JADE_BRICK_STAIRS.get());
        createSlab(SMBlocks.ROUGH_JADE_BRICK_SLAB.get());

        //Jade
        this.dropSelf(SMBlocks.JADE_BLOCK.get());
        this.dropSelf(SMBlocks.JADE_BRICKS.get());
        this.dropSelf(SMBlocks.CHISELED_JADE.get());
        this.dropSelf(SMBlocks.JADE_PILLAR.get());
        this.dropSelf(SMBlocks.JADE_TOTEM.get());
        this.dropSelf(SMBlocks.JADE_FLINGER_TOTEM.get());
        this.dropSelf(SMBlocks.JADE_BRICK_STAIRS.get());
        this.dropSelf(SMBlocks.ROUGH_JADE_BRICK_WALL.get());
        this.dropSelf(SMBlocks.JADE_BRICK_WALL.get());
        createSlab(SMBlocks.JADE_BRICK_SLAB.get());

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
        this.add(SMBlocks.AMBER.get(), createSingleItemTableWithSilkTouch(SMBlocks.AMBER.get(), SMBlocks.ROUGH_AMBER.get()));
        this.dropSelf(SMBlocks.ROUGH_AMBER.get());
        this.dropSelf(SMBlocks.CHISELED_AMBER.get());
        this.dropSelf(SMBlocks.AMBER_PILLAR.get());
        this.dropSelf(SMBlocks.AMBER_LANTERN.get());
        this.dropSelf(SMBlocks.JADE_LANTERN.get());
        this.dropSelf(SMBlocks.DIAMOND_LANTERN.get());
        this.dropSelf(SMBlocks.EMERALD_LANTERN.get());
        this.dropSelf(SMBlocks.LAPIS_LANTERN.get());
        this.dropSelf(SMBlocks.AMETHYST_LANTERN.get());
        this.dropSelf(SMBlocks.QUARTZ_LANTERN.get());
        this.dropSelf(SMBlocks.AMBER_BRICKS.get());
        createSlab(SMBlocks.AMBER_BRICK_SLAB.get());
        this.dropSelf(SMBlocks.AMBER_BRICK_STAIRS.get());
        this.dropSelf(SMBlocks.AMBER_BRICK_WALL.get());
        add(SMBlocks.MOLTEN_AMBER_BLOCK.get(), noDrop());
        this.dropOther(SMBlocks.AMBER_CAULDRON.get(), Blocks.CAULDRON);

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
        this.dropSelf(SMBlocks.PETRIFIED_SAPLING.get());
        dropPottedContents(SMBlocks.POTTED_PETRIFIED_SAPLING.get());

        this.dropSelf(SMBlocks.ITEM_STAND.get());
        SMBlocks.ANCIENT_SKULLS.forEach(regObj -> dropSelf(regObj.get()));
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
