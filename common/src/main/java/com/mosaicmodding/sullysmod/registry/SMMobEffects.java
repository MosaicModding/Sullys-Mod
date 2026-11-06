package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.content.gourd.SizeChangeEffect;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import static com.mosaicmodding.sullysmod.registry.SMRegUtils.path;

public class SMMobEffects {

    public static final RegSupplier<MobEffect> MINIMU_EFFECT = RegHelper.registerEffect(path("minimu_effect"), () -> new SizeChangeEffect(0xBF6CAD)
            .addAttributeModifier(Attributes.SCALE, path("effect.minimu"), -0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static final RegSupplier<MobEffect> GIGANTO_EFFECT = RegHelper.registerEffect(path("giganto_effect"), () -> new SizeChangeEffect(0x64A97E)
            .addAttributeModifier(Attributes.SCALE, path("effect.giganto"), 0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static void init() {}
}
