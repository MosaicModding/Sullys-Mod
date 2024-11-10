package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.fluids.MoltenAmberFluidType;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SMFluidTypes {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, SullysMod.MOD_ID);

    public static final RegistryObject<FluidType> MOLTEN_AMBER_FLUID_TYPE = register("molten_amber_fluid",
            FluidType.Properties.create().lightLevel(10).density(10).viscosity(5).supportsBoating(false).canSwim(false).canPushEntity(true).temperature(1));



    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new MoltenAmberFluidType(properties));
    }
}
