package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blockentities.*;
import com.uraneptus.sullysmod.common.blocks.AmberBlock;
import com.uraneptus.sullysmod.common.blocks.FlingerTotem;
import com.uraneptus.sullysmod.common.blocks.ItemStandBlock;
import com.uraneptus.sullysmod.common.blocks.SolidAmberBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMBlockEntityTypes {
    public static final BlockEntitySubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getBlockEntitySubHelper();
    private static final Supplier<Set<Block>> ANCIENT_SKULLS = () -> Set.of(
            SMBlocks.CRACKED_ANCIENT_SKULL.getFirst().get(),
            SMBlocks.CRACKED_ANCIENT_SKULL.getSecond().get(),
            SMBlocks.CRESTED_ANCIENT_SKULL.getFirst().get(),
            SMBlocks.CRESTED_ANCIENT_SKULL.getSecond().get(),
            SMBlocks.FLATBILLED_ANCIENT_SKULL.getFirst().get(),
            SMBlocks.FLATBILLED_ANCIENT_SKULL.getSecond().get(),
            SMBlocks.GIGANTIC_ANCIENT_SKULL.getFirst().get(),
            SMBlocks.GIGANTIC_ANCIENT_SKULL.getSecond().get(),
            SMBlocks.HORNED_ANCIENT_SKULL.getFirst().get(),
            SMBlocks.HORNED_ANCIENT_SKULL.getSecond().get(),
            SMBlocks.LONG_ANCIENT_SKULL.getFirst().get(),
            SMBlocks.LONG_ANCIENT_SKULL.getSecond().get(),
            SMBlocks.TINY_ANCIENT_SKULL.getFirst().get(),
            SMBlocks.TINY_ANCIENT_SKULL.getSecond().get(),
            SMBlocks.WIDE_ANCIENT_SKULL.getFirst().get(),
            SMBlocks.WIDE_ANCIENT_SKULL.getSecond().get(),
            SMBlocks.RIBBED_ANCIENT_SKULL.getFirst().get(),
            SMBlocks.RIBBED_ANCIENT_SKULL.getSecond().get(),
            SMBlocks.UNICORN_ANCIENT_SKULL.getFirst().get(),
            SMBlocks.UNICORN_ANCIENT_SKULL.getSecond().get()
    );

    public static final RegistryObject<BlockEntityType<FlingerTotemBE>> FLINGER_TOTEM = HELPER.createBlockEntity("flinger_totem", FlingerTotemBE::new, FlingerTotem.class);
    public static final RegistryObject<BlockEntityType<AmberBE>> AMBER = HELPER.createBlockEntity("amber", AmberBE::new, AmberBlock.class);
    public static final RegistryObject<BlockEntityType<ItemStandBE>> ITEM_STAND = HELPER.createBlockEntity("item_stand", ItemStandBE::new, ItemStandBlock.class);
    public static final RegistryObject<BlockEntityType<AncientSkullBE>> ANCIENT_SKULL = HELPER.createBlockEntity("ancient_skull", AncientSkullBE::new, ANCIENT_SKULLS);
}
