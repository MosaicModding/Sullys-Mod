package com.uraneptus.sullysmod.common.fluids;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class MoltenAmberFluidType extends FluidType {
    private final ResourceLocation stillTexture = new ResourceLocation(SullysMod.MOD_ID,"block/amber");
    private final ResourceLocation flowingTexture = new ResourceLocation(SullysMod.MOD_ID,"block/flowing_molten_amber");

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

            @NotNull
            @Override
            public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                return new Vector3f(1.0F, 0.5F, 0.01F);
            }

        });
    }
}
