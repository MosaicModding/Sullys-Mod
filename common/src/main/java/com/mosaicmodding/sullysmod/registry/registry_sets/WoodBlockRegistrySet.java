package com.mosaicmodding.sullysmod.registry.registry_sets;

import com.mosaicmodding.sullysmod.core.blocks.SMStairBlock;
import com.mosaicmodding.sullysmod.registry.properties.SMBlockProperties;
import com.mosaicmodding.tessera.TesseraBlockProperties;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.misc.TriFunction;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.platform.TabAdderHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils.path;

public class WoodBlockRegistrySet extends SMRegistrySet {
    private final RegSupplier<Block> planksBlock;
    private final RegSupplier<Block> strippedLogBlock;
    private final RegSupplier<Block> logBlock;
    private final RegSupplier<Block> strippedWoodBlock;
    private final RegSupplier<Block> woodBlock;
    private final RegSupplier<Block> pressurePlateBlock;
    private final RegSupplier<Block> buttonBlock;
    private final RegSupplier<Block> trapdoorBlock;
    private final RegSupplier<Block> doorBlock;
    private final RegSupplier<Block> stairsBlock;
    private final RegSupplier<Block> slabBlock;
    private final RegSupplier<Block> fenceBlock;
    private final RegSupplier<Block> fenceGateBlock;
    private final RegSupplier<Block> saplingBlock;
    private final RegSupplier<Block> pottedSaplingBlock;
    /*todo
    private final RegSupplier<Block> signBlock;
    private final RegSupplier<Block> wallSignBlock;
    private final RegSupplier<Block> hangingSignBlock;
    private final RegSupplier<Block> wallHangingSignBlock;

     */
    private ResourceKey<CreativeModeTab> blocksTab = CreativeModeTabs.BUILDING_BLOCKS;
    private ResourceKey<CreativeModeTab> signsTab = CreativeModeTabs.FUNCTIONAL_BLOCKS;
    private ResourceKey<CreativeModeTab> naturalsTab = CreativeModeTabs.NATURAL_BLOCKS;

    private WoodBlockRegistrySet(String id, String feature,
                                 Supplier<Block> planks, Supplier<Block> strippedLog, Supplier<Block> log,
                                 Supplier<Block> strippedWood, Supplier<Block> wood, Supplier<Block> pressurePlate,
                                 Supplier<Block> button, Supplier<Block> trapdoor, Supplier<Block> door,
                                 Supplier<Block> stairs, Supplier<Block> slab, Supplier<Block> fence,
                                 Supplier<Block> fenceGate, RegSupplier<Block> sapling, Supplier<Block> pottedSapling) {
        super(id, feature);
        planksBlock = RegHelper.registerBlockWithItem(path(name("%s_planks")), planks);
        strippedLogBlock = RegHelper.registerBlockWithItem(path(name("stripped_%s_log")), strippedLog);
        logBlock = RegHelper.registerBlockWithItem(path(name("%s_log")), log);

        strippedWoodBlock = RegHelper.registerBlockWithItem(path(name("stripped_%s_wood")), strippedWood);
        woodBlock = RegHelper.registerBlockWithItem(path(name("%s_wood")), wood);

        pressurePlateBlock = RegHelper.registerBlockWithItem(path(name("%s_planks_pressure_plate")), pressurePlate);
        buttonBlock = RegHelper.registerBlockWithItem(path(name("%s_planks_button")), button);

        trapdoorBlock = RegHelper.registerBlockWithItem(path(name("%s_trapdoor")), trapdoor);
        doorBlock = RegHelper.registerBlockWithItem(path(name("%s_door")), door);

        stairsBlock = RegHelper.registerBlockWithItem(path(name("%s_planks_stairs")), stairs);
        slabBlock = RegHelper.registerBlockWithItem(path(name("%s_planks_slab")), slab);

        fenceBlock = RegHelper.registerBlockWithItem(path(name("%s_planks_fence")), fence);
        fenceGateBlock = RegHelper.registerBlockWithItem(path(name("%s_planks_fence_gate")), fenceGate);

        saplingBlock = sapling;
        pottedSaplingBlock = RegHelper.registerBlock(path(name("potted_%s_sapling")), pottedSapling);
    }

    public RegSupplier<Block> getPlanks() {
        return planksBlock;
    }

    public RegSupplier<Block> getStrippedLog() {
        return strippedLogBlock;
    }

    public RegSupplier<Block> getLog() {
        return logBlock;
    }

    public RegSupplier<Block> getStrippedWood() {
        return strippedWoodBlock;
    }

    public RegSupplier<Block> getWood() {
        return woodBlock;
    }

    public RegSupplier<Block> getPressurePlate() {
        return pressurePlateBlock;
    }

    public RegSupplier<Block> getButton() {
        return buttonBlock;
    }

    public RegSupplier<Block> getTrapdoor() {
        return trapdoorBlock;
    }

    public RegSupplier<Block> getDoor() {
        return doorBlock;
    }

    public RegSupplier<Block> getStairs() {
        return stairsBlock;
    }

    public RegSupplier<Block> getSlab() {
        return slabBlock;
    }

    public RegSupplier<Block> getFence() {
        return fenceBlock;
    }

    public RegSupplier<Block> getFenceGate() {
        return fenceGateBlock;
    }

    public RegSupplier<Block> getSaplingBlock() {
        return saplingBlock;
    }

    public RegSupplier<Block> getPottedSaplingBlock() {
        return pottedSaplingBlock;
    }

    public void setBlocksTab(ResourceKey<CreativeModeTab> tab) {
        this.blocksTab = tab;
    }

    public void setSignsTab(ResourceKey<CreativeModeTab> tab) {
        this.signsTab = tab;
    }

    public void setNaturalsTab(ResourceKey<CreativeModeTab> tab) {
        this.naturalsTab = tab;
    }

    public void addToTab(TabAdderHelper helper) {
        helper.after(Items.CHERRY_BUTTON, this.blocksTab, this.feature, logBlock, woodBlock, strippedLogBlock,
                strippedWoodBlock, planksBlock, stairsBlock, slabBlock, fenceBlock, fenceGateBlock,
                doorBlock, trapdoorBlock, pressurePlateBlock, buttonBlock
        );
        helper.after(Items.CHERRY_LOG, this.naturalsTab, this.feature, logBlock);
        helper.after(Items.CHERRY_SAPLING, this.naturalsTab, this.feature, saplingBlock);
    }

    public static class Builder {
        private final TesseraBlockProperties defaultProperties;
        private final String id;
        private final String feature;
        private final WoodType type;

        private int buttonTicksPressed = 30;

        private Supplier<Block> planksBlock;
        private Supplier<Block> strippedLogBlock;
        private Supplier<Block> logBlock;
        private Supplier<Block> strippedWoodBlock;
        private Supplier<Block> woodBlock;
        private Supplier<Block> pressurePlateBlock;
        private Supplier<Block> buttonBlock;
        private Supplier<Block> trapdoorBlock;
        private Supplier<Block> doorBlock;
        private Supplier<Block> stairsBlock;
        private Supplier<Block> slabBlock;
        private Supplier<Block> fenceBlock;
        private Supplier<Block> fenceGateBlock;
        private final RegSupplier<Block> saplingBlock;
        private Supplier<Block> pottedSaplingBlock;
        private Supplier<Block> signBlock;
        private Supplier<Block> wallSignBlock;
        private Supplier<Block> hangingSignBlock;
        private Supplier<Block> wallHangingSignBlock;

        public Builder(String id, TesseraBlockProperties properties, String feature, WoodType type, Function<TesseraBlockProperties, Block> sapling) {
            this.id = id;
            this.defaultProperties = properties;
            this.feature = feature;
            this.type = type;
            this.planksBlock = () -> new Block(properties);

            this.strippedLogBlock = () -> new RotatedPillarBlock(properties);
            this.logBlock = () -> new RotatedPillarBlock(properties);

            this.strippedWoodBlock = () -> new RotatedPillarBlock(properties);
            this.woodBlock = () -> new RotatedPillarBlock(properties);

            this.pressurePlateBlock = () -> new PressurePlateBlock(type.setType(), TesseraBlockProperties.copy(properties).forceSolidOn().noCollission().pushReaction(PushReaction.DESTROY).strength(0.5F));
            this.buttonBlock = () -> new ButtonBlock(type.setType(), this.buttonTicksPressed, TesseraBlockProperties.copy(properties).noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY));

            this.trapdoorBlock = () -> new TrapDoorBlock(type.setType(), TesseraBlockProperties.copy(properties).setCutout().noOcclusion().isValidSpawn(SMBlockProperties::never).strength(3.0F));
            this.doorBlock = () -> new DoorBlock(type.setType(), TesseraBlockProperties.copy(properties).setCutout());

            this.stairsBlock = () -> new SMStairBlock(properties);
            this.slabBlock = () -> new SlabBlock(properties);

            this.fenceBlock = () -> new FenceBlock(properties);
            this.fenceGateBlock = () -> new FenceGateBlock(type, properties);

            this.saplingBlock = RegHelper.registerBlockWithItem(path(id + "_sapling"), () -> sapling.apply(SMBlockProperties.SAPLING()));
            //TODO on Neo: Make sapling placable in pot using addPlant
            this.pottedSaplingBlock = () -> PlatHelper.newFlowerPot(() -> (FlowerPotBlock) Blocks.FLOWER_POT, this.saplingBlock, SMBlockProperties.FLOWER_POT()); //TODO should this be final?
        }

        public Builder customPlanks(Function<TesseraBlockProperties, Block> block) {
            this.planksBlock = () -> block.apply(this.defaultProperties);
            return this;
        }

        public Builder customPlanks(Function<TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.planksBlock = () -> block.apply(properties);
            return this;
        }

        public Builder customStrippedLog(Function<TesseraBlockProperties, Block> block) {
            this.strippedLogBlock = () -> block.apply(this.defaultProperties);
            return this;
        }

        public Builder customStrippedLog(Function<TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.strippedLogBlock = () -> block.apply(properties);
            return this;
        }

        public Builder customLog(Function<TesseraBlockProperties, Block> block) {
            this.logBlock = () -> block.apply(this.defaultProperties);
            return this;
        }

        public Builder customLog(Function<TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.logBlock = () -> block.apply(properties);
            return this;
        }

        /**
         * modifies the log block, but provides the previously created stripped log block
         */
        public Builder customLogWithStripped(BiFunction<Supplier<Block>, TesseraBlockProperties, Block> block) {
            this.logBlock = () -> block.apply(this.strippedLogBlock, this.defaultProperties);
            return this;
        }

        /**
         * modifies the log block, but provides the previously created stripped log block
         */
        public Builder customLogWithStripped(BiFunction<Supplier<Block>, TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.logBlock = () -> block.apply(this.strippedLogBlock, properties);
            return this;
        }

        public Builder customStrippedWood(Function<TesseraBlockProperties, Block> block) {
            this.strippedWoodBlock = () -> block.apply(this.defaultProperties);
            return this;
        }

        public Builder customStrippedWood(Function<TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.strippedWoodBlock = () -> block.apply(properties);
            return this;
        }

        public Builder customWood(Function<TesseraBlockProperties, Block> block) {
            this.woodBlock = () -> block.apply(this.defaultProperties);
            return this;
        }

        public Builder customWood(Function<TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.woodBlock = () -> block.apply(properties);
            return this;
        }

        /**
         * modifies the wood block, but provides the previously created stripped wood block
         */
        public Builder customWoodWithStripped(BiFunction<Supplier<Block>, TesseraBlockProperties, Block> block) {
            this.woodBlock = () -> block.apply(this.strippedWoodBlock, this.defaultProperties);
            return this;
        }

        /**
         * modifies the wood block, but provides the previously created stripped wood block
         */
        public Builder customWoodWithStripped(BiFunction<Supplier<Block>, TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.woodBlock = () -> block.apply(this.strippedWoodBlock, properties);
            return this;
        }

        public Builder customPressurePlate(Function<TesseraBlockProperties, Block> block) {
            this.pressurePlateBlock = () -> block.apply(this.defaultProperties);
            return this;
        }

        public Builder customPressurePlate(Function<TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.pressurePlateBlock = () -> block.apply(properties);
            return this;
        }

        public Builder customButton(TriFunction<BlockSetType, Integer, TesseraBlockProperties, ButtonBlock> block) {
            this.buttonBlock = () -> block.apply(this.type.setType(), this.buttonTicksPressed, this.defaultProperties);
            return this;
        }

        public Builder customButton(TriFunction<BlockSetType, Integer, TesseraBlockProperties, ButtonBlock> block, TesseraBlockProperties properties) {
            this.buttonBlock = () -> block.apply(this.type.setType(), this.buttonTicksPressed, properties);
            return this;
        }

        public Builder setButtonTicksPressed(int ticksToStayPressed) {
            this.buttonTicksPressed = ticksToStayPressed;
            return customButton(ButtonBlock::new); //Update with new tick amount
        }

        public Builder customTrapdoor(Function<TesseraBlockProperties, Block> block) {
            this.trapdoorBlock = () -> block.apply(this.defaultProperties);
            return this;
        }

        public Builder customTrapdoor(Function<TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.trapdoorBlock = () -> block.apply(properties);
            return this;
        }

        public Builder customDoor(Function<TesseraBlockProperties, Block> block) {
            this.doorBlock = () -> block.apply(this.defaultProperties);
            return this;
        }

        public Builder customDoor(Function<TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.doorBlock = () -> block.apply(properties);
            return this;
        }

        public Builder customStairs(BiFunction<Supplier<Block>, TesseraBlockProperties, Block> block) {
            this.stairsBlock = () -> block.apply(this.planksBlock, this.defaultProperties);
            return this;
        }

        public Builder customStairs(BiFunction<Supplier<Block>, TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.stairsBlock = () -> block.apply(this.planksBlock, properties);
            return this;
        }

        public Builder customSlabs(Function<TesseraBlockProperties, Block> block) {
            this.slabBlock = () -> block.apply(this.defaultProperties);
            return this;
        }

        public Builder customSlabs(Function<TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.slabBlock = () -> block.apply(properties);
            return this;
        }

        public Builder customFence(Function<TesseraBlockProperties, Block> block) {
            this.fenceBlock = () -> block.apply(this.defaultProperties);
            return this;
        }

        public Builder customFence(Function<TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.fenceBlock = () -> block.apply(properties);
            return this;
        }

        public Builder customFenceGate(Function<TesseraBlockProperties, Block> block) {
            this.fenceGateBlock = () -> block.apply(this.defaultProperties);
            return this;
        }

        public Builder customFenceGate(Function<TesseraBlockProperties, Block> block, TesseraBlockProperties properties) {
            this.fenceGateBlock = () -> block.apply(properties);
            return this;
        }

        public WoodBlockRegistrySet build() {
            return new WoodBlockRegistrySet(this.id, this.feature,
                    this.planksBlock, this.strippedLogBlock, this.logBlock, this.strippedWoodBlock, this.woodBlock,
                    this.pressurePlateBlock, this.buttonBlock, this.trapdoorBlock, this.doorBlock, this.stairsBlock,
                    this.slabBlock, this.fenceBlock, this.fenceGateBlock, this.saplingBlock, this.pottedSaplingBlock);
        }
    }
}
