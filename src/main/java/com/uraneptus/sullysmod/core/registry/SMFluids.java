package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.fluids.MoltenAmberFluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//TODO reformat
public class SMFluids {

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, SullysMod.MOD_ID);


    public static final RegistryObject<FlowingFluid> SOURCE_MOLTEN_AMBER = FLUIDS.register("molten_amber_fluid",
            () -> new MoltenAmberFluid.Source(SMFluids.MOLTEN_AMBER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_MOLTEN_AMBER = FLUIDS.register("flowing_molten_amber",
            () -> new MoltenAmberFluid.Flowing(SMFluids.MOLTEN_AMBER_PROPERTIES));

    public static final ForgeFlowingFluid.Properties MOLTEN_AMBER_PROPERTIES = new ForgeFlowingFluid.Properties(
             SMFluidTypes.MOLTEN_AMBER_FLUID_TYPE, SOURCE_MOLTEN_AMBER, FLOWING_MOLTEN_AMBER)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(SMBlocks.MOLTEN_AMBER_BLOCK)
            .bucket(SMItems.MOLTEN_AMBER_BUCKET);

}
