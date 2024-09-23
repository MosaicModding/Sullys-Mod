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

    public static final RegistryObject<PaintingVariant> UNNERVING_NIGHT = registerPainting("unnerving_night", "RealSpidey", 16, 48);
    public static final RegistryObject<PaintingVariant> AMBER = registerPainting("amber", "Graus", 16, 32);
    public static final RegistryObject<PaintingVariant> INFESTATION = registerPainting("infestation", "Ninni", 32, 32);
    public static final RegistryObject<PaintingVariant> LAKE = registerPainting("lake", "Farcr", 16, 48);
    public static final RegistryObject<PaintingVariant> CAVERNS = registerPainting("caverns", "SennaHN", 16, 32);
    public static final RegistryObject<PaintingVariant> RIVER_TERROR = registerPainting("river_terror", "Graus", 32, 32);
    public static final RegistryObject<PaintingVariant> MESMURRIZING = registerPainting("mesmurrizing", "Graus", 32, 32);
    public static final RegistryObject<PaintingVariant> JADE_DRAGON = registerPainting("jade_dragon", "Shable", 64, 64);
    public static final RegistryObject<PaintingVariant> A_VISITOR = registerPainting("a_visitor", "Ibrokemyribcage", 64, 32);
    public static final RegistryObject<PaintingVariant> BEGINNING = registerPainting("beginning", "Sully", 16, 48);
    public static final RegistryObject<PaintingVariant> HOME = registerPainting("home", "Ibrokemyribcage", 64, 64);
    public static final RegistryObject<PaintingVariant> ILLAGER_BEAST = registerPainting("illager_beast", "Angery", 64, 48);
    public static final RegistryObject<PaintingVariant> THANK_YOU = registerPainting("thank_you", "Sully", 64, 64);

    public static RegistryObject<PaintingVariant> registerPainting(String name, String author, int width, int height) {
        PAINTING_TRANSLATIONS.put(name, author);
        return PAINTINGS.register(name, () -> new PaintingVariant(width, height));
    }
}
