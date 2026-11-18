package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.content.petrified.PetrifiedTreeGravelDecorator;
import com.mosaicmodding.sullysmod.mixin.TreeDecoratorTypeAccessor;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.function.Supplier;

import static com.mosaicmodding.sullysmod.core.SMUtils.path;

public class SMTreeDecoratorTypes {

    public static final RegSupplier<TreeDecoratorType<PetrifiedTreeGravelDecorator>> GRAVEL_DECORATOR = register("petrified_gravel_decorator", () -> TreeDecoratorTypeAccessor.newTreeDecoratorType(PetrifiedTreeGravelDecorator.CODEC));


    public static <P extends TreeDecorator> RegSupplier<TreeDecoratorType<P>> register(String name, Supplier<TreeDecoratorType<P>> type) {
        return RegHelper.register(path(name), type, Registries.TREE_DECORATOR_TYPE);
    }

    public static void init() {

    }
}
