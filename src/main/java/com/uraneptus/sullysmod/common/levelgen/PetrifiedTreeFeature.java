package com.uraneptus.sullysmod.common.levelgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class PetrifiedTreeFeature extends Feature<PetrifiedTreeConfig> {

    public PetrifiedTreeFeature(Codec<PetrifiedTreeConfig> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<PetrifiedTreeConfig> context) {
        //TODO add sus gravel loot table
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

        StructurePlaceSettings placeSettings = new StructurePlaceSettings().setBoundingBox(boundingbox).setRandom(random).setRotation(rotation);
        Vec3i size = template.getSize();
        BlockPos centerPos = blockPos.offset(-size.getX() / 2, -5, -size.getZ() / 2);
        BlockPos offsetPos = template.getZeroPositionWithTransform(centerPos.atY(blockPos.getY()), Mirror.NONE, Rotation.NONE);

        if (level.getBlockState(centerPos.offset(0, size.getY() - 2, 0)).is(Blocks.AIR)) {

        }
        int tries = 15;
        int blobAmount = random.nextInt(4);

        for (int j = 0; j < tries; ++j) {
            //TODO configure to only spawn with big trees
            config.amberBlobs().get().place(level, context.chunkGenerator(), random, offsetPos.offset(1 + random.nextIntBetweenInclusive(-1, 2), 0, 1 + random.nextIntBetweenInclusive(-1, 2)));
            //TODO blobs need to be bigger and be better spread out
        }


        return template.placeInWorld(level, new BlockPos(offsetPos.getX(), offsetPos.getY() - 2, offsetPos.getZ()), offsetPos, placeSettings, random, Block.UPDATE_ALL);

    }
}
