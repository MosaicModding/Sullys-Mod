package com.mosaicmodding.sullysmod.registry.registry_sets;

import com.mosaicmodding.sullysmod.core.blocks.SMStairBlock;
import com.mosaicmodding.sullysmod.registry.properties.SMBlockProperties;
import com.mosaicmodding.tessera.TesseraBlockProperties;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.platform.TabAdderHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.mosaicmodding.sullysmod.core.SMUtils.path;

public class WoodBlockRegistrySet extends AbstractBlockRegistrySet {
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

    private final Pair<RegSupplier<Block>, RegSupplier<Block>> signBlock;
    private final Pair<RegSupplier<Block>, RegSupplier<Block>> hangingSignBlock;

    private final int buttonTicksPressed;

    private final BlockItemTagBundle logsTagBundle;

    private ResourceKey<CreativeModeTab> blocksTab = CreativeModeTabs.BUILDING_BLOCKS;
    private ResourceKey<CreativeModeTab> signsTab = CreativeModeTabs.FUNCTIONAL_BLOCKS;
    private ResourceKey<CreativeModeTab> naturalsTab = CreativeModeTabs.NATURAL_BLOCKS;

    private WoodBlockRegistrySet(String id, String feature, WoodType type, Supplier<Block> sapling, int buttonTicksPressed,
                                 Supplier<TesseraBlockProperties> planksProps, Supplier<TesseraBlockProperties> logProps,
                                 Supplier<TesseraBlockProperties> strippedLogProps, Supplier<TesseraBlockProperties> woodProps,
                                 Supplier<TesseraBlockProperties> strippedWoodProps, Supplier<TesseraBlockProperties> pressurePlateProps,
                                 Supplier<TesseraBlockProperties> buttonProps, Supplier<TesseraBlockProperties> trapdoorProps,
                                 Supplier<TesseraBlockProperties> doorProps, Supplier<TesseraBlockProperties> stairsProps,
                                 Supplier<TesseraBlockProperties> slabProps, Supplier<TesseraBlockProperties> fenceProps,
                                 Supplier<TesseraBlockProperties> fenceGateProps, Supplier<TesseraBlockProperties> signProps,
                                 Supplier<TesseraBlockProperties> hangingSignProps,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedPlanks,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedLog,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedStrippedLog,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedWood,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedStrippedWood,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedPressurePlate,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedButton,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedTrapdoor,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedDoor,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedStairs,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedSlab,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedFence,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedFenceGate,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedStandingSign,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedWallSign,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedHangingSign,
                                 @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedWallHangingSign) {
        super(id, feature);

        this.buttonTicksPressed = buttonTicksPressed;

        logsTagBundle = createTag("logs");

        planksBlock = registerBasic("%s_planks", planksProps, Block::new, modifiedPlanks);

        strippedLogBlock = registerBasic("stripped_%s_log", () -> strippedLogProps.get().addTag(logsTagBundle.blockTag()), RotatedPillarBlock::new, modifiedStrippedLog);
        logBlock = registerBasic("%s_log", () -> logProps.get().addTag(logsTagBundle.blockTag()), RotatedPillarBlock::new, modifiedLog);

        strippedWoodBlock = registerBasic("stripped_%s_wood", () -> strippedWoodProps.get().addTag(logsTagBundle.blockTag()), RotatedPillarBlock::new, modifiedStrippedWood);
        woodBlock = registerBasic("%s_wood", () -> woodProps.get().addTag(logsTagBundle.blockTag()), RotatedPillarBlock::new, modifiedWood);

        pressurePlateBlock = registerBasic("%s_planks_pressure_plate", pressurePlateProps, p -> new PressurePlateBlock(type.setType(), p), modifiedPressurePlate);
        buttonBlock = registerBasic("%s_planks_button", buttonProps, p -> new ButtonBlock(type.setType(), this.buttonTicksPressed, p), modifiedButton);

        trapdoorBlock = registerBasic("%s_trapdoor", trapdoorProps, p -> new TrapDoorBlock(type.setType(), p), modifiedTrapdoor);
        doorBlock = registerBasic("%s_door", doorProps, p -> new DoorBlock(type.setType(), p), modifiedDoor);

        stairsBlock = registerBasic("%s_planks_stairs", stairsProps, SMStairBlock::new, modifiedStairs);
        slabBlock = registerBasic("%s_planks_slab", slabProps, SlabBlock::new, modifiedSlab);

        fenceBlock = registerBasic("%s_planks_fence", fenceProps, FenceBlock::new, modifiedFence);
        fenceGateBlock = registerBasic("%s_planks_fence_gate", fenceGateProps, p -> new FenceGateBlock(type, p), modifiedFenceGate);

        saplingBlock = RegHelper.registerBlockWithItem(path(name("%s_sapling")), sapling);
        pottedSaplingBlock = RegHelper.registerBlock(path(name("potted_%s_sapling")), () -> PlatHelper.newFlowerPot(() -> (FlowerPotBlock) Blocks.FLOWER_POT, this.saplingBlock, SMBlockProperties.FLOWER_POT()));

        var standingSign = registerBasicNoItem("%s_sign", signProps, p -> new StandingSignBlock(type, p), modifiedStandingSign);
        var wallSign = registerBasicNoItem("%s_wall_sign", () -> signProps.get().dropsLike(standingSign.get()), p -> new WallSignBlock(type, p), modifiedWallSign);
        RegHelper.registerItem(path(name("%s_sign")), () -> new SignItem(new Item.Properties(), standingSign.get(), wallSign.get()));
        this.signBlock = Pair.of(standingSign, wallSign);

        var hangingSign = registerBasicNoItem("%s_hanging_sign", hangingSignProps, p -> new CeilingHangingSignBlock(type, p), modifiedHangingSign);
        var wallHangingSign = registerBasicNoItem("%s_wall_hanging_sign", () -> hangingSignProps.get().dropsLike(hangingSign.get()), p -> new WallHangingSignBlock(type, p), modifiedWallHangingSign);
        RegHelper.registerItem(path(name("%s_hanging_sign")), () -> new HangingSignItem(hangingSign.get(), wallHangingSign.get(), new Item.Properties()));
        this.hangingSignBlock = Pair.of(hangingSign, wallHangingSign);

    }

    private RegSupplier<Block> registerBasic(String name, Supplier<TesseraBlockProperties> properties, Function<TesseraBlockProperties, Block> defaultBlock, @Nullable Supplier<Function<TesseraBlockProperties, Block>> possiblyModified) {
        var fullName = path(name(name));
        if (possiblyModified == null) {
            return RegHelper.registerBlockWithItem(fullName, () -> defaultBlock.apply(properties.get()));
        } else {
            return RegHelper.registerBlockWithItem(fullName, () -> possiblyModified.get().apply(properties.get()));
        }
    }

    private RegSupplier<Block> registerBasicNoItem(String name, Supplier<TesseraBlockProperties> properties, Function<TesseraBlockProperties, Block> defaultBlock, @Nullable Supplier<Function<TesseraBlockProperties, Block>> possiblyModified) {
        var fullName = path(name(name));
        if (possiblyModified == null) {
            return RegHelper.registerBlock(fullName, () -> defaultBlock.apply(properties.get()));
        } else {
            return RegHelper.registerBlock(fullName, () -> possiblyModified.get().apply(properties.get()));
        }
    }

    @Override
    public void addToTab(TabAdderHelper helper) {
        helper.after(Items.CHERRY_BUTTON, this.blocksTab, this.feature, logBlock, woodBlock, strippedLogBlock,
                strippedWoodBlock, planksBlock, stairsBlock, slabBlock, fenceBlock, fenceGateBlock,
                doorBlock, trapdoorBlock, pressurePlateBlock, buttonBlock
        );
        helper.after(Items.CHERRY_LOG, this.naturalsTab, this.feature, logBlock);
        helper.after(Items.CHERRY_SAPLING, this.naturalsTab, this.feature, saplingBlock);
        helper.after(Items.CHERRY_HANGING_SIGN, this.signsTab, this.feature, signBlock.getLeft(), hangingSignBlock.getLeft());
    }

    public void bindSignsToBE(RegHelper.ExtraBEStatesEvent event) {
        event.addBlocks(BlockEntityType.SIGN, signBlock.getLeft().get(), signBlock.getRight().get());
        event.addBlocks(BlockEntityType.HANGING_SIGN, hangingSignBlock.getLeft().get(), hangingSignBlock.getRight().get());
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

    public RegSupplier<Block> getSapling() {
        return saplingBlock;
    }

    public RegSupplier<Block> getPottedSaplingBlock() {
        return pottedSaplingBlock;
    }

    public BlockItemTagBundle getLogsTagBundle() {
        return logsTagBundle;
    }

    public Pair<RegSupplier<Block>, RegSupplier<Block>> getSign() {
        return signBlock;
    }

    public Pair<RegSupplier<Block>, RegSupplier<Block>> getHangingSign() {
        return hangingSignBlock;
    }

    public static class Builder {
        Supplier<TesseraBlockProperties> planksProps, logProps, strippedLogProps, woodProps, strippedWoodProps,
                pressurePlateProps, buttonProps, trapdoorProps, doorProps, stairsProps, slabProps, fenceProps,
                fenceGateProps, signProps, hangingSignProps;

        @Nullable Supplier<Function<TesseraBlockProperties, Block>> modifiedPlanks = null, modifiedLog = null,
                modifiedStrippedLog = null, modifiedWood = null, modifiedStrippedWood = null, modifiedPressurePlate = null,
                modifiedButton = null, modifiedTrapdoor = null, modifiedDoor = null, modifiedStairs = null, modifiedSlab = null,
                modifiedFence = null, modifiedFenceGate = null, modifiedStandingSign = null, modifiedWallSign = null,
                modifiedHangingSign = null, modifiedWallHangingSign = null;

        int buttonTicksPressed = 30;

        public Builder(Supplier<TesseraBlockProperties> baseProperties) {
            this.planksProps = baseProperties;
            this.logProps = baseProperties;
            this.strippedLogProps = baseProperties;
            this.woodProps = baseProperties;
            this.strippedWoodProps = baseProperties;
            this.pressurePlateProps = () -> baseProperties.get().forceSolidOn().noCollission().pushReaction(PushReaction.DESTROY).strength(0.5F);
            this.buttonProps = () -> baseProperties.get().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
            this.trapdoorProps = () -> baseProperties.get().setCutout().noOcclusion().isValidSpawn(SMBlockProperties::never).strength(3.0F);
            this.doorProps = () -> baseProperties.get().setCutout();
            this.stairsProps = baseProperties;
            this.slabProps = baseProperties;
            this.fenceProps = baseProperties;
            this.fenceGateProps = baseProperties;

            Supplier<TesseraBlockProperties> signProps = () -> baseProperties.get().forceSolidOn().noCollission().strength(1.0F);
            this.signProps = signProps;
            this.hangingSignProps = signProps;
        }

        //TODO add the rest: I don't wanna do that rn

        public Builder modifyPlanks(Supplier<Function<TesseraBlockProperties, Block>> block) {
            this.modifiedPlanks = block;
            return this;
        }

        public Builder modifyPlankProps(Supplier<TesseraBlockProperties> prop) {
            this.planksProps = prop;
            return this;
        }

        public Builder modifyLogProps(Supplier<TesseraBlockProperties> prop) {
            this.logProps = prop;
            return this;
        }

        public Builder modifyStrippedLogProps(Supplier<TesseraBlockProperties> prop) {
            this.strippedLogProps = prop;
            return this;
        }

        public Builder modifyWoodProps(Supplier<TesseraBlockProperties> prop) {
            this.woodProps = prop;
            return this;
        }

        public Builder modifyStrippedWoodProps(Supplier<TesseraBlockProperties> prop) {
            this.strippedWoodProps = prop;
            return this;
        }

        public Builder modifyButtonPressedTicks(int buttonTicksPressed) {
            this.buttonTicksPressed = buttonTicksPressed;
            return this;
        }

        public WoodBlockRegistrySet build(String id, String feature, WoodType type, Supplier<Block> sapling) {
            return new WoodBlockRegistrySet(id, feature, type, sapling, buttonTicksPressed, planksProps, logProps, strippedLogProps,
                    woodProps, strippedWoodProps, pressurePlateProps, buttonProps, trapdoorProps, doorProps, stairsProps,
                    slabProps, fenceProps, fenceGateProps, signProps, hangingSignProps, modifiedPlanks, modifiedLog, modifiedStrippedLog, modifiedWood,
                    modifiedStrippedWood, modifiedPressurePlate, modifiedButton, modifiedTrapdoor, modifiedDoor,
                    modifiedStairs, modifiedSlab, modifiedFence, modifiedFenceGate, modifiedStandingSign, modifiedWallSign,
                    modifiedHangingSign, modifiedWallHangingSign
            );
        }

    }
}
