package com.mosaicmodding.sullysmod.core.data_maps;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

public record PickaxeStrippable(Block strippedBlock) {
    public static final Codec<PickaxeStrippable> STRIPPED_BLOCK_CODEC = BuiltInRegistries.BLOCK.byNameCodec()
            .xmap(PickaxeStrippable::new, PickaxeStrippable::strippedBlock);

    public static final Codec<PickaxeStrippable> CODEC = Codec.withAlternative(
            RecordCodecBuilder.create(inst -> inst.group(
                            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("stripped_block").forGetter(PickaxeStrippable::strippedBlock)
            ).apply(inst, PickaxeStrippable::new)), STRIPPED_BLOCK_CODEC);
}
