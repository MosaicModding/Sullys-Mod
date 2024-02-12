package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.levelgen.PetrifiedTreeGravelDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SMTreeDecoratorTypes {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, SullysMod.MOD_ID);

    public static final RegistryObject<TreeDecoratorType<PetrifiedTreeGravelDecorator>> GRAVEL_DECORATOR = TREE_DECORATORS.register("petrified_gravel_decorator", () -> new TreeDecoratorType<>(PetrifiedTreeGravelDecorator.CODEC));
}
