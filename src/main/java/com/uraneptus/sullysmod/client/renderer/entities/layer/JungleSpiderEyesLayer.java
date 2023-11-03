package com.uraneptus.sullysmod.client.renderer.entities.layer;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.JungleSpiderModel;
import com.uraneptus.sullysmod.common.entities.JungleSpider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class JungleSpiderEyesLayer<T extends JungleSpider, M extends JungleSpiderModel<T>> extends EyesLayer<T, M> {
    private static final RenderType SPIDER_EYES = RenderType.eyes(SullysMod.modPrefix("textures/entity/jungle_spider/eyes.png"));

    public JungleSpiderEyesLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    @Override
    public @NotNull RenderType renderType() {
        return SPIDER_EYES;
    }
}
