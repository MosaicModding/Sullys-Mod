package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.JadeBlock;
import com.uraneptus.sullysmod.common.blocks.JadePillar;
import com.uraneptus.sullysmod.common.blocks.JadeTotemBlock;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMBlocks {
    public static final BlockSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> JADE_ORE = HELPER.createBlock("jade_ore", () -> new OreBlock(Properties.JADE_ORE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> DEEPSLATE_JADE_ORE = HELPER.createBlock("deepslate_jade_ore", () -> new OreBlock(Properties.DEEPSLATE_JADE_ORE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RAW_JADE_BLOCK = HELPER.createBlock("raw_jade_block", () -> new Block(Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RAW_JADE_BRICKS = HELPER.createBlock("raw_jade_bricks", () -> new Block(Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RAW_JADE_TILE = HELPER.createBlock("raw_jade_tile", () -> new Block(Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RAW_JADE_TILES = HELPER.createBlock("raw_jade_tiles", () -> new Block(Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_BRICKS = HELPER.createBlock("jade_bricks", () -> new JadeBlock(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMALL_JADE_BRICKS = HELPER.createBlock("small_jade_bricks", () -> new JadeBlock(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_SHINGLES = HELPER.createBlock("jade_shingles", () -> new JadeBlock(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_TILES = HELPER.createBlock("jade_tiles", () -> new JadeBlock(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_JADE = HELPER.createBlock("chiseled_jade", () -> new JadeBlock(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_TOTEM = HELPER.createBlock("jade_totem", () -> new JadeTotemBlock(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_PILLAR = HELPER.createBlock("jade_pillar", () -> new JadePillar(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final class Properties {
        public static final BlockBehaviour.Properties JADE_ORE = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F);
        public static final BlockBehaviour.Properties DEEPSLATE_JADE_ORE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE);
        public static final BlockBehaviour.Properties RAW_JADE_BLOCKS = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(5.0F, 6.0F);
        public static final BlockBehaviour.Properties JADE_BLOCKS = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.EMERALD).requiresCorrectToolForDrops().strength(5.0F, 6.0F);
    }
}
