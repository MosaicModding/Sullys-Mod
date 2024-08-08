package com.uraneptus.sullysmod.common.fluids;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;

import java.util.function.Consumer;

public class MoltenAmberFluidType extends FluidType {
    private final ResourceLocation stillTexture = new ResourceLocation(SullysMod.MOD_ID,"block/amber");;
    private final ResourceLocation flowingTexture = new ResourceLocation(SullysMod.MOD_ID,"block/flowing_molten_amber");;

    public MoltenAmberFluidType(final Properties properties) {
        super(properties);
    }


    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return stillTexture;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return flowingTexture;
            }

        });
    }
}
