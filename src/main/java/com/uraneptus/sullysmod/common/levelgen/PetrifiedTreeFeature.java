package com.uraneptus.sullysmod.common.levelgen;

import com.mojang.serialization.Codec;
import com.uraneptus.sullysmod.common.levelgen.configs.PetrifiedTreeConfig;
import com.uraneptus.sullysmod.core.other.loot.SMBuiltInLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.rule.blockentity.AppendLoot;

import java.util.List;

public class PetrifiedTreeFeature extends Feature<PetrifiedTreeConfig> {

    public PetrifiedTreeFeature(Codec<PetrifiedTreeConfig> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<PetrifiedTreeConfig> context) {
        PetrifiedTreeConfig config = context.config();
        RandomSource random = context.random();
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        BlockPos.MutableBlockPos blockPos = origin.mutable();
        Rotation rotation = Rotation.getRandom(random);

        int amount = config.variants().size();
        PetrifiedTreeVariant variant = config.variants().get(amount <= 1 ? 0 : random.nextInt(amount - 1));
        ResourceLocation structure = variant.name();
        StructureTemplateManager templateManager = level.getLevel().getServer().getStructureManager();
        StructureTemplate template = templateManager.getOrCreate(structure);
        ChunkPos chunkPos = new ChunkPos(blockPos);
        BoundingBox boundingbox = new BoundingBox(chunkPos.getMinBlockX() - 16, level.getMinBuildHeight(), chunkPos.getMinBlockZ() - 16, chunkPos.getMaxBlockX() + 16, level.getMaxBuildHeight(), chunkPos.getMaxBlockZ() + 16);

        StructurePlaceSettings placeSettings = new StructurePlaceSettings()
                .setBoundingBox(boundingbox).setRandom(random).setRotation(rotation)
                .addProcessor(randomGravelProcessor(SMBuiltInLootTables.GRAVEL_BIG_PETRIFIED_TREE, variant.susGravelLimit()))
                .addProcessor(setLoottableProcessor(SMBuiltInLootTables.GRAVEL_BIG_PETRIFIED_TREE));
        Vec3i size = template.getSize();
        BlockPos centerPos = blockPos.offset(-size.getX() / 2, -5, -size.getZ() / 2);
        BlockPos offsetPos = template.getZeroPositionWithTransform(centerPos.atY(blockPos.getY()), Mirror.NONE, Rotation.NONE);

        return place(context, template, level, offsetPos, placeSettings, random, variant, config);
    }

    private static boolean place(FeaturePlaceContext<PetrifiedTreeConfig> context, StructureTemplate template, WorldGenLevel level, BlockPos offsetPos, StructurePlaceSettings placeSettings, RandomSource random, PetrifiedTreeVariant variant, PetrifiedTreeConfig config) {
        if (template.placeInWorld(level, new BlockPos(offsetPos.getX(), offsetPos.getY() - 2, offsetPos.getZ()), offsetPos, placeSettings, random, Block.UPDATE_ALL)) {
            if (variant.allowAmber()) {
                int radius = 2;
                int tries = 2;

                for (int i = 0; i < tries; i++) {
                    BlockPos amberPos = offsetPos.offset(random.nextInt(4) * radius - radius, 0, random.nextInt(4) * radius - radius);
                    config.amberBlobs().get().place(level, context.chunkGenerator(), random, amberPos);
                }
                return true;
            }
        }
        return false;
    }

    //This method replaces some gravel with sus gravel and adds a loottable
    private static CappedProcessor randomGravelProcessor(ResourceLocation lootTable, int limit) {
        return new CappedProcessor(
                new RuleProcessor(
                        List.of(
                                new ProcessorRule(
                                        new BlockMatchTest(Blocks.GRAVEL),
                                        AlwaysTrueTest.INSTANCE,
                                        PosAlwaysTrueTest.INSTANCE,
                                        Blocks.SUSPICIOUS_GRAVEL.defaultBlockState(),
                                        new AppendLoot(lootTable)
                                )
                        )
                ),
                ConstantInt.of(limit)
        );
    }

    //This method replaces the specifically placed blocks in the structure and adds the loottable.
    // This is done to make sure there are always some sus gravel blocks, in case the other gravel falls down and gets destroyed
    private static RuleProcessor setLoottableProcessor(ResourceLocation lootTable) {
        return new RuleProcessor(
                List.of(
                        new ProcessorRule(
                                new BlockMatchTest(Blocks.SUSPICIOUS_GRAVEL),
                                AlwaysTrueTest.INSTANCE,
                                PosAlwaysTrueTest.INSTANCE,
                                Blocks.SUSPICIOUS_GRAVEL.defaultBlockState(),
                                new AppendLoot(lootTable)
                        )
                )
        );
    }
}
