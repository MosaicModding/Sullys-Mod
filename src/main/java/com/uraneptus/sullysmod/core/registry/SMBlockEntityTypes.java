package com.uraneptus.sullysmod.core.registry;

import com.google.common.collect.Sets;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blockentities.*;
import com.uraneptus.sullysmod.common.blocks.AmberBlockSolid;
import com.uraneptus.sullysmod.common.blocks.FlingerTotem;
import com.uraneptus.sullysmod.common.blocks.ItemStandBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;


public class SMBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, SullysMod.MOD_ID);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            BuiltInRegistries.BLOCK,
            SullysMod.MOD_ID
    );

    public static final Supplier<BlockEntityType<FlingerTotemBE>> FLINGER_TOTEM = registerBE("flinger_totem", FlingerTotemBE::new, FlingerTotem.class);
    public static final Supplier<BlockEntityType<AmberBE>> AMBER = registerBE("amber", AmberBE::new, AmberBlockSolid.class);
    public static final Supplier<BlockEntityType<ItemStandBE>> ITEM_STAND = registerBE("item_stand", ItemStandBE::new, ItemStandBlock.class);
    public static final Supplier<BlockEntityType<AncientSkullBE>> ANCIENT_SKULL = registerBE("ancient_skull", AncientSkullBE::new, () -> AncientSkullBE.SKULLS);

    public static Block[] collectBlocks(Class<?> blockClass) {
        return BLOCKS.getEntries().stream().filter(blockClass::isInstance).toArray(Block[]::new);
    }

    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBE(String name, BlockEntityType.BlockEntitySupplier<? extends T> blockEntity, Supplier<Set<Block>> validBlocks) {
        return BLOCK_ENTITY.register(name, () -> new BlockEntityType<>(blockEntity, validBlocks.get(), null));
    }

    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBE(String name, BlockEntityType.BlockEntitySupplier<? extends T> blockEntity, Class<? extends Block> blockClass) {
        return BLOCK_ENTITY.register(name, () -> new BlockEntityType<>(blockEntity, Sets.newHashSet(collectBlocks(blockClass)), null));
    }
}