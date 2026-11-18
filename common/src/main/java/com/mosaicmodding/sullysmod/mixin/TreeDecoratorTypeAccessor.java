package com.mosaicmodding.sullysmod.mixin;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TreeDecoratorType.class)
public interface TreeDecoratorTypeAccessor {

    @Invoker("<init>")
    static <P extends TreeDecorator> TreeDecoratorType<P> newTreeDecoratorType(MapCodec<P> codec) {
        throw new AssertionError("Untransformed @Accessor");
    }
}
