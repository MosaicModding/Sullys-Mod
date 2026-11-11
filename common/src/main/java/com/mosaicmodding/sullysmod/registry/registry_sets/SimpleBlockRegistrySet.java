package com.mosaicmodding.sullysmod.registry.registry_sets;

import com.mosaicmodding.sullysmod.registry.properties.SMBlockProperties;
import com.mosaicmodding.tessera.TesseraBlockProperties;
import net.mehvahdjukaar.moonlight.api.block.ModStairBlock;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.platform.TabAdderHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils.path;

public class SimpleBlockRegistrySet extends SMRegistrySet {
    private final TesseraBlockProperties defaultProperties;
    private final RegSupplier<Block> bricksBlock;
    private final RegSupplier<Block> stairsBlock;
    private final RegSupplier<Block> slabBlock;
    private @Nullable RegSupplier<Block> wallBlock;
    private @Nullable RegSupplier<Block> pillarBlock;
    private @Nullable RegSupplier<Block> chiseledBlock;
    private ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

    public SimpleBlockRegistrySet(String id, Function<TesseraBlockProperties, Block> bricksBlock, BiFunction<Supplier<Block>, BlockBehaviour.Properties, Block> stairsBlock, Function<TesseraBlockProperties, Block> slabBlock, TesseraBlockProperties properties, String feature) {
        super(id, feature);

        this.bricksBlock = RegHelper.registerBlockWithItem(path(name("%s_bricks")), () -> bricksBlock.apply(properties));
        this.stairsBlock = RegHelper.registerBlockWithItem(path(name("%s_bricks_stairs")), () -> stairsBlock.apply(this.bricksBlock, properties));
        this.slabBlock = RegHelper.registerBlockWithItem(path(name("%s_bricks_slab")), () -> slabBlock.apply(properties));
        this.defaultProperties = properties;
    }

    public SimpleBlockRegistrySet(String id, TesseraBlockProperties properties, String feature) {
        this(id, Block::new, ModStairBlock::new, SlabBlock::new, properties, feature);
    }

    public SimpleBlockRegistrySet(String id, String feature) {
        this(id, Block::new, ModStairBlock::new, SlabBlock::new, SMBlockProperties.BRICKS_DEFAULT(), feature);
    }

    public SimpleBlockRegistrySet withWall() {
        this.wallBlock = RegHelper.registerBlockWithItem(path(name("%s_bricks_wall")), () -> createBlock(WallBlock::new, this.defaultProperties.forceSolidOn()));
        return this;
    }

    public SimpleBlockRegistrySet withWall(Function<TesseraBlockProperties, Block> blockFunction) {
        this.wallBlock = RegHelper.registerBlockWithItem(path(name("%s_bricks_wall")), () -> createBlock(blockFunction, this.defaultProperties.forceSolidOn()));
        return this;
    }

    public SimpleBlockRegistrySet withWall(Function<TesseraBlockProperties, Block> blockFunction, TesseraBlockProperties properties) {
        this.wallBlock = RegHelper.registerBlockWithItem(path(name("%s_bricks_wall")), () -> createBlock(blockFunction, properties));
        return this;
    }

    public SimpleBlockRegistrySet withPillar() {
        this.pillarBlock = RegHelper.registerBlockWithItem(path(name("%s_pillar")), () -> createBlock(RotatedPillarBlock::new, this.defaultProperties));
        return this;
    }

    public SimpleBlockRegistrySet withPillar(Function<TesseraBlockProperties, Block> blockFunction) {
        this.pillarBlock = RegHelper.registerBlockWithItem(path(name("%s_pillar")), () -> createBlock(blockFunction, this.defaultProperties));
        return this;
    }

    public SimpleBlockRegistrySet withPillar(Function<TesseraBlockProperties, Block> blockFunction, TesseraBlockProperties properties) {
        this.pillarBlock = RegHelper.registerBlockWithItem(path(name("%s_pillar")), () -> createBlock(blockFunction, properties));
        return this;
    }

    public SimpleBlockRegistrySet withChiseled() {
        this.chiseledBlock = RegHelper.registerBlockWithItem(path(name("chiseled_%s")), () -> createBlock(Block::new, this.defaultProperties));
        return this;
    }

    public SimpleBlockRegistrySet withChiseled(Function<TesseraBlockProperties, Block> blockFunction) {
        this.chiseledBlock = RegHelper.registerBlockWithItem(path(name("chiseled_%s")), () -> createBlock(blockFunction, this.defaultProperties));
        return this;
    }

    public SimpleBlockRegistrySet withChiseled(Function<TesseraBlockProperties, Block> blockFunction, TesseraBlockProperties properties) {
        this.chiseledBlock = RegHelper.registerBlockWithItem(path(name("chiseled_%s")), () -> createBlock(blockFunction, properties));
        return this;
    }

    private Block createBlock(Function<TesseraBlockProperties, Block> blockFunction, TesseraBlockProperties properties) {
        return blockFunction.apply(properties);
    }

    public RegSupplier<Block> getBricksBlock() {
        return bricksBlock;
    }

    public RegSupplier<Block> getStairsBlock() {
        return stairsBlock;
    }

    public RegSupplier<Block> getSlabBlock() {
        return slabBlock;
    }

    public Optional<RegSupplier<Block>> getWallBlock() {
        return wallBlock != null ? Optional.of(wallBlock) : Optional.empty();
    }

    public Optional<RegSupplier<Block>> getPillarBlock() {
        return pillarBlock != null ? Optional.of(pillarBlock) : Optional.empty();
    }

    public Optional<RegSupplier<Block>> getChiseledBlock() {
        return chiseledBlock != null ? Optional.of(chiseledBlock) : Optional.empty();
    }

    public ResourceKey<CreativeModeTab> getTab() {
        return tab;
    }

    public void setTab(ResourceKey<CreativeModeTab> tab) {
        this.tab = tab;
    }

    public void addToTab(TabAdderHelper helper) {
        helper.add(this.tab, this.feature, bricksBlock, stairsBlock, slabBlock);
        if (getWallBlock().isPresent()) {
            helper.add(this.tab, this.feature, getWallBlock().get());
        }
        if (getPillarBlock().isPresent()) {
            helper.add(this.tab, this.feature, getPillarBlock().get());
        }
        if (getChiseledBlock().isPresent()) {
            helper.add(this.tab, this.feature, getChiseledBlock().get());
        }
    }

}
