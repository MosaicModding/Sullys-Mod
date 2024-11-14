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
        //TODO add amber entities in nbt
        PetrifiedTreeConfig config = context.config();
        RandomSource random = context.random();
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        BlockPos.MutableBlockPos blockPos = origin.mutable();
        Rotation rotation = Rotation.getRandom(random);

        int amount = config.structures().size();
        ResourceLocation structure = config.structures().get(amount <= 1 ? 0 : random.nextInt(amount - 1));
        StructureTemplateManager templateManager = level.getLevel().getServer().getStructureManager();
        StructureTemplate template = templateManager.getOrCreate(structure);
        ChunkPos chunkPos = new ChunkPos(blockPos);
        BoundingBox boundingbox = new BoundingBox(chunkPos.getMinBlockX() - 16, level.getMinBuildHeight(), chunkPos.getMinBlockZ() - 16, chunkPos.getMaxBlockX() + 16, level.getMaxBuildHeight(), chunkPos.getMaxBlockZ() + 16);
        boolean isSmall = structure.getPath().contains("small");
        int processorLimit = isSmall ? 2 : 15;
        StructurePlaceSettings placeSettings = new StructurePlaceSettings()
                .setBoundingBox(boundingbox).setRandom(random).setRotation(rotation)
                .addProcessor(randomGravelProcessor(SMBuiltInLootTables.GRAVEL_BIG_PETRIFIED_TREE, processorLimit))
                .addProcessor(setLoottableProcessor(SMBuiltInLootTables.GRAVEL_BIG_PETRIFIED_TREE));
        Vec3i size = template.getSize();
        BlockPos centerPos = blockPos.offset(-size.getX() / 2, -5, -size.getZ() / 2);
        BlockPos offsetPos = template.getZeroPositionWithTransform(centerPos.atY(blockPos.getY()), Mirror.NONE, Rotation.NONE);


        int tries = 5;
        int blobAmount = random.nextInt(4);

        if (!isSmall) {
            //TODO fine tune | make it generate better around tree
            config.amberBlobs().get().place(level, context.chunkGenerator(), random, offsetPos.offset(-1 + random.nextInt(4), 1 + random.nextInt(4), -1 + random.nextInt(4)));
            config.amberBlobs().get().place(level, context.chunkGenerator(), random, offsetPos.offset(1 + -random.nextInt(4), 1 + random.nextInt(4), 1 + -random.nextInt(4)));

            for (int j = 0; j < tries; ++j) {

            }
        }


        return template.placeInWorld(level, new BlockPos(offsetPos.getX(), offsetPos.getY() - 2, offsetPos.getZ()), offsetPos, placeSettings, random, Block.UPDATE_ALL);
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
