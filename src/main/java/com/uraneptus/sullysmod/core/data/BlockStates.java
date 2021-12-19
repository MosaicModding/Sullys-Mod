package com.uraneptus.sullysmod.core.data;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SullysMod.MOD_ID, exFileHelper);
    }

    private String name(Block block) {
        return block.getRegistryName().getPath();
    }

    public ResourceLocation location(String path) {
        return new ResourceLocation(SullysMod.MOD_ID, "block/" + path);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
