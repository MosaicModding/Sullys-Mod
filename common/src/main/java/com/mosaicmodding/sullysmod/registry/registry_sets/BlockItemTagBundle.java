package com.mosaicmodding.sullysmod.registry.registry_sets;

import com.mosaicmodding.sullysmod.registry.datapack.SMTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public record BlockItemTagBundle(TagKey<Item> itemTag, TagKey<Block> blockTag) {

    public BlockItemTagBundle(String id) {
        this(SMTags.Items.modTag(id), SMTags.Blocks.modTag(id));
    }
}
