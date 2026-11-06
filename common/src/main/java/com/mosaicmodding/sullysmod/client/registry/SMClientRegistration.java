package com.mosaicmodding.sullysmod.client.registry;

import com.mosaicmodding.sullysmod.client.model.BoulderingZombieModel;
import com.mosaicmodding.sullysmod.client.model.LanternfishModel;
import com.mosaicmodding.sullysmod.client.particle.AmberParticle;
import com.mosaicmodding.sullysmod.client.particle.BlotEyesParticle;
import com.mosaicmodding.sullysmod.client.particle.RicochetParticle;
import com.mosaicmodding.sullysmod.client.rendering.blockentity.AmberBER;
import com.mosaicmodding.sullysmod.client.rendering.entity.BoulderingZombieRenderer;
import com.mosaicmodding.sullysmod.client.rendering.entity.LanternfishRenderer;
import com.mosaicmodding.sullysmod.registry.SMBlockEntityTypes;
import com.mosaicmodding.sullysmod.registry.SMEntityTypes;
import com.mosaicmodding.sullysmod.registry.SMParticleTypes;
import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public class SMClientRegistration {

    @EventCalled
    private static void registerModelLayers(ClientHelper.ModelLayerEvent event) {
        LayerDefinition INNER_ARMOR_DEF = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F)), 64, 32);
        LayerDefinition OUTER_ARMOR_DEF = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(1.0F)), 64, 32);

        event.register(BoulderingZombieModel.LAYER_LOCATION, BoulderingZombieModel::createBodyLayer);
        event.register(BoulderingZombieModel.INNER_ARMOR, () -> INNER_ARMOR_DEF);
        event.register(BoulderingZombieModel.OUTER_ARMOR, () -> OUTER_ARMOR_DEF);
        event.register(LanternfishModel.LAYER_LOCATION, LanternfishModel::createBodyLayer);
    }

    @EventCalled
    private static void registerEntityRenderers(ClientHelper.EntityRendererEvent event) {
        event.register(SMEntityTypes.BOULDERING_ZOMBIE.get(), BoulderingZombieRenderer::new);
        event.register(SMEntityTypes.LANTERNFISH.get(), LanternfishRenderer::new);
    }

    @EventCalled
    private static void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        event.register(SMBlockEntityTypes.AMBER.get(), AmberBER::new);
    }

    private static void registerParticleProvider(ClientHelper.ParticleEvent event) {
        event.register(SMParticleTypes.RICOCHET.get(), RicochetParticle.RicochetParticleProvider::new);
        event.register(SMParticleTypes.BLOT_EYES.get(), BlotEyesParticle.BlotEyesParticleProvider::new);
        event.register(SMParticleTypes.AMBER_DRIPPING.get(), AmberParticle.AmberHangParticleProvider::new);
        event.register(SMParticleTypes.AMBER_FALL.get(), AmberParticle.AmberFallParticleProvider::new);
        event.register(SMParticleTypes.AMBER_LAND.get(), AmberParticle.AmberLandedParticleProvider::new);
    }

    public static void init() {
        ClientHelper.addModelLayerRegistration(SMClientRegistration::registerModelLayers);
        ClientHelper.addEntityRenderersRegistration(SMClientRegistration::registerEntityRenderers);
        ClientHelper.addBlockEntityRenderersRegistration(SMClientRegistration::registerBlockEntityRenderers);
        ClientHelper.addEntityLayersRegistration(new SMEntityLayers());
        ClientHelper.addParticleRegistration(SMClientRegistration::registerParticleProvider);
    }

}
