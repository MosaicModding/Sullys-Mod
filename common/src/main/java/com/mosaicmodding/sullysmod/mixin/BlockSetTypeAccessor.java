package com.mosaicmodding.sullysmod.mixin;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockSetType.class)
public interface BlockSetTypeAccessor {

    @Invoker("register")
    static BlockSetType register(BlockSetType value) {
        throw new AssertionError("Untransformed @Accessor");
    }
}
