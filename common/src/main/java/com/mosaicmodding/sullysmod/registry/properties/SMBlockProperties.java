package com.mosaicmodding.sullysmod.registry.properties;

import com.mosaicmodding.sullysmod.registry.SMTags;
import com.mosaicmodding.tessera.TesseraBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class SMBlockProperties {
    public static TesseraBlockProperties AMBER(boolean isSolid) {
        return TesseraBlockProperties.of()
                .strength(isSolid ? 4.3F : 1.3F, 3F)
                .mapColor(MapColor.COLOR_ORANGE)
                .requiresCorrectToolForDrops()
                .randomTicks()
                .noOcclusion()
                .dynamicShape()
                .isViewBlocking(SMBlockProperties::always)
                .needsPickaxe()
                .setRenderType(TesseraBlockProperties.BlockRenderType.TRANSLUCENT);
    }

    public static TesseraBlockProperties AMBER_DECORATION() {
        return TesseraBlockProperties.of()
                .strength(1.3F, 3F)
                .mapColor(MapColor.COLOR_ORANGE)
                .requiresCorrectToolForDrops()
                .randomTicks()
                .dynamicShape()
                .needsPickaxe()
                .lootDropSelf();
    }

    public static TesseraBlockProperties GOURD(MapColor color, boolean lantern) {
        return TesseraBlockProperties.of()
                .mapColor(color)
                .instrument(NoteBlockInstrument.DIDGERIDOO)
                .strength(1.0F)
                .sound(SoundType.WOOD)
                .lightLevel(state -> lantern ? 15 : 0)
                .pushReaction(PushReaction.DESTROY)
                .isValidSpawn(SMBlockProperties::always)
                .needsAxe()
                .lootDropSelf();
    }

    public static TesseraBlockProperties PETRIFIED() {
        return TesseraBlockProperties.of()
                .instrument(NoteBlockInstrument.BASEDRUM)
                .strength(2.0F, 6.0F)
                /*todo .sound(SMSounds.PETRIFIED_WOOD)*/
                .mapColor(MapColor.TERRACOTTA_ORANGE)
                .requiresCorrectToolForDrops()
                .needsPickaxe()
                .lootDropSelf()
                .addTag(SMTags.Blocks.IS_PETRIFIED);
    }

    public static TesseraBlockProperties PETRIFIED_LOGS(MapColor topColor, MapColor sideColor) {
        return TesseraBlockProperties.copy(PETRIFIED()).mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : sideColor);
    }

    public static TesseraBlockProperties FLOWER_POT() {
        return TesseraBlockProperties.of()
                .instabreak()
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY)
                .setCutout();
    }

    public static TesseraBlockProperties SAPLING() {
        return TesseraBlockProperties.of()
                .mapColor(MapColor.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS)
                .pushReaction(PushReaction.DESTROY)
                .lootDropSelf()
                .setCutout();
    }

    public static TesseraBlockProperties BRICKS_DEFAULT() {
        return TesseraBlockProperties.copy(Blocks.STONE_BRICKS).needsPickaxe().lootDropSelf();
    }

    public static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }
    public static boolean never(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entity) {return false;}
    public static boolean always(BlockState state, BlockGetter getter, BlockPos pos) {return true;}
    public static boolean always(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entity) {return true;}
}
