package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class SMPaintingVariants {
    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, SullysMod.MOD_ID);
    public static Map<String, String> PAINTING_TRANSLATIONS = new HashMap<>();

    public static final RegistryObject<PaintingVariant> UNNERVING_NIGHT = registerPainting("unnerving_night", "RealSpidey", 16, 32);
    public static final RegistryObject<PaintingVariant> AMBER = registerPainting("amber", "Graus", 16, 32);

    public static RegistryObject<PaintingVariant> registerPainting(String name, String author, int width, int height) {
        PAINTING_TRANSLATIONS.put(name, author);
        return PAINTINGS.register(name, () -> new PaintingVariant(width, height));
    }
}
