package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMBlockEntityTypes {
    public static final BlockEntitySubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final RegistryObject<BlockEntityType<FlingerTotemBlockEntity>> FLINGER_TOTEM = HELPER.createBlockEntity("flinger_totem", FlingerTotemBlockEntity::new, FlingerTotem.class);
    public static final RegistryObject<BlockEntityType<AmberBlockEntity>> AMBER = HELPER.createBlockEntity("amber", AmberBlockEntity::new, AmberBlock.class);
    public static final RegistryObject<BlockEntityType<ItemStandBlockEntity>> ITEM_STAND = HELPER.createBlockEntity("item_stand", ItemStandBlockEntity::new, ItemStandBlock.class);
}
