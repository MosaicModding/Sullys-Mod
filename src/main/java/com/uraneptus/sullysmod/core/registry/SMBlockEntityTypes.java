package com.uraneptus.sullysmod.core.registry;

import com.google.common.collect.Sets;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blockentities.AmberBE;
import com.uraneptus.sullysmod.common.blockentities.AncientSkullBE;
import com.uraneptus.sullysmod.common.blockentities.FlingerTotemBE;
import com.uraneptus.sullysmod.common.blockentities.ItemStandBE;
import com.uraneptus.sullysmod.common.blocks.AmberBlock;
import com.uraneptus.sullysmod.common.blocks.FlingerTotem;
import com.uraneptus.sullysmod.common.blocks.ItemStandBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SullysMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<FlingerTotemBE>> FLINGER_TOTEM = registerBE("flinger_totem", FlingerTotemBE::new, FlingerTotem.class);
    public static final RegistryObject<BlockEntityType<AmberBE>> AMBER = registerBE("amber", AmberBE::new, AmberBlock.class);
    public static final RegistryObject<BlockEntityType<ItemStandBE>> ITEM_STAND = registerBE("item_stand", ItemStandBE::new, ItemStandBlock.class);
    public static final RegistryObject<BlockEntityType<AncientSkullBE>> ANCIENT_SKULL = registerBE("ancient_skull", AncientSkullBE::new, () -> AncientSkullBE.SKULLS);

    public static Block[] collectBlocks(Class<?> blockClass) {
        return ForgeRegistries.BLOCKS.getValues().stream().filter(blockClass::isInstance).toArray(Block[]::new);
    }

    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBE(String name, BlockEntityType.BlockEntitySupplier<? extends T> blockEntity, Supplier<Set<Block>> validBlocks) {
        return BLOCK_ENTITY.register(name, () -> new BlockEntityType<>(blockEntity, validBlocks.get(), null));
    }

    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBE(String name, BlockEntityType.BlockEntitySupplier<? extends T> blockEntity, Class<? extends Block> blockClass) {
        return BLOCK_ENTITY.register(name, () -> new BlockEntityType<>(blockEntity, Sets.newHashSet(collectBlocks(blockClass)), null));
    }
}