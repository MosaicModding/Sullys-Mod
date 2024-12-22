package com.uraneptus.sullysmod.core.events;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.*;
import com.uraneptus.sullysmod.client.model.ancient_skulls.*;
import com.uraneptus.sullysmod.client.particles.AmberParticle;
import com.uraneptus.sullysmod.client.particles.BlotEyesParticle;
import com.uraneptus.sullysmod.client.particles.RicochetParticle;
import com.uraneptus.sullysmod.client.renderer.be.AmberBER;
import com.uraneptus.sullysmod.client.renderer.be.ItemStandBER;
import com.uraneptus.sullysmod.client.renderer.entities.*;
import com.uraneptus.sullysmod.client.renderer.entities.layer.StuckInAmberLayer;
import com.uraneptus.sullysmod.common.blocks.AncientSkullBlock;
import com.uraneptus.sullysmod.common.items.VenomVialItem;
import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMParticleTypes;
import net.minecraft.Util;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@SuppressWarnings("unused")
public class SMClientModEvents {

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SMEntityTypes.LANTERNFISH.get(), LanternfishRenderer::new);
        event.registerEntityRenderer(SMEntityTypes.TORTOISE.get(), TortoiseRenderer::new);
        event.registerEntityRenderer(SMEntityTypes.TORTOISE_SHELL.get(), TortoiseShellRenderer::new);
        event.registerEntityRenderer(SMEntityTypes.BOULDERING_ZOMBIE.get(), BoulderingZombieRenderer::new);
        event.registerEntityRenderer(SMEntityTypes.JUNGLE_SPIDER.get(), JungleSpiderRenderer::new);
        event.registerEntityRenderer(SMEntityTypes.PIRANHA.get(), PiranhaRenderer::new);
        event.registerEntityRenderer(SMEntityTypes.THROWN_THROWING_KNIFE.get(), ThrownThrowingKnifeRenderer::new);
        event.registerBlockEntityRenderer(SMBlockEntityTypes.AMBER.get(), AmberBER::new);
        event.registerBlockEntityRenderer(SMBlockEntityTypes.ITEM_STAND.get(), ItemStandBER::new);
        event.registerBlockEntityRenderer(SMBlockEntityTypes.ANCIENT_SKULL.get(), SkullBlockRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerLocation(EntityRenderersEvent.RegisterLayerDefinitions event) {
        LayerDefinition INNER_ARMOR_DEF = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F)), 64, 32);
        LayerDefinition OUTER_ARMOR_DEF = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(1.0F)), 64, 32);

        event.registerLayerDefinition(LanternfishModel.LAYER_LOCATION, LanternfishModel::createBodyLayer);
        event.registerLayerDefinition(JadeShieldModel.LAYER_LOCATION, JadeShieldModel::createLayer);
        event.registerLayerDefinition(TortoiseShellModel.LAYER_LOCATION, TortoiseShellModel::createBodyLayer);
        event.registerLayerDefinition(TortoiseModel.LAYER_LOCATION, TortoiseModel::createBodyLayer);
        event.registerLayerDefinition(JungleSpiderModel.LAYER_LOCATION, JungleSpiderModel::createBodyLayer);
        event.registerLayerDefinition(BoulderingZombieModel.LAYER_LOCATION, BoulderingZombieModel::createBodyLayer);
        event.registerLayerDefinition(BoulderingZombieModel.INNER_ARMOR, () -> INNER_ARMOR_DEF);
        event.registerLayerDefinition(BoulderingZombieModel.OUTER_ARMOR, () -> OUTER_ARMOR_DEF);
        event.registerLayerDefinition(PiranhaModel.LAYER_LOCATION, PiranhaModel::createBodyLayer);
        event.registerLayerDefinition(MinersHelmetModel.LAYER_LOCATION, MinersHelmetModel::createBodyLayer);
        event.registerLayerDefinition(CrackedAncientSkullModel.LAYER_LOCATION, CrackedAncientSkullModel::createBodyLayer);
        event.registerLayerDefinition(CrestedAncientSkullModel.LAYER_LOCATION, CrestedAncientSkullModel::createBodyLayer);
        event.registerLayerDefinition(FlatbilledAncientSkullModel.LAYER_LOCATION, FlatbilledAncientSkullModel::createBodyLayer);
        event.registerLayerDefinition(GiganticAncientSkullModel.LAYER_LOCATION, GiganticAncientSkullModel::createBodyLayer);
        event.registerLayerDefinition(HornedAncientSkullModel.LAYER_LOCATION, HornedAncientSkullModel::createBodyLayer);
        event.registerLayerDefinition(LongAncientSkullModel.LAYER_LOCATION, LongAncientSkullModel::createBodyLayer);
        event.registerLayerDefinition(TinyAncientSkullModel.LAYER_LOCATION, TinyAncientSkullModel::createBodyLayer);
        event.registerLayerDefinition(WideAncientSkullModel.LAYER_LOCATION, WideAncientSkullModel::createBodyLayer);
        event.registerLayerDefinition(RibbedAncientSkullModel.LAYER_LOCATION, RibbedAncientSkullModel::createBodyLayer);
        event.registerLayerDefinition(UnicornAncientSkullModel.LAYER_LOCATION, UnicornAncientSkullModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SMParticleTypes.RICOCHET.get(), RicochetParticle.RicochetParticleProvider::new);
        event.registerSpriteSet(SMParticleTypes.BLOT_EYES.get(), BlotEyesParticle.Factory::new);
        event.registerSprite(SMParticleTypes.AMBER_DRIPPING.get(), AmberParticle::createAmberHangParticle);
        event.registerSprite(SMParticleTypes.AMBER_FALL.get(), AmberParticle::createAmberFallParticle);
        event.registerSprite(SMParticleTypes.AMBER_LAND.get(), AmberParticle::createAmberLandParticle);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemProperties.register(SMItems.JADE_SHIELD.get(), new ResourceLocation("blocking"), (itemStack, clientWorld, livingEntity, useTime) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);
        SkullBlockRenderer.SKIN_BY_TYPE.putAll(Util.make(Maps.newHashMap(), SMClientModEvents::addSkull));
    }

    private static void addSkull(Map<SkullBlock.Type, ResourceLocation> map) {
        for (AncientSkullBlock.Type type : AncientSkullBlock.Types.values()) {
            map.put(type, SullysMod.modPrefix("textures/entity/ancient_skulls/" + type.toString().toLowerCase() + ".png"));
        }
    }

    @SubscribeEvent
    public static void onItemColouring(RegisterColorHandlersEvent.Item event) {
        event.register(((stack, tint) -> tint == 0 ? -1 : VenomVialItem.getEffectColours(stack, tint)), SMItems.VENOM_VIAL.get());
    }

    @SubscribeEvent
    public static void onCreateSkullModels(EntityRenderersEvent.CreateSkullModels event) {
        event.registerSkullModel(AncientSkullBlock.Types.CRACKED, new CrackedAncientSkullModel(event.getEntityModelSet().bakeLayer(CrackedAncientSkullModel.LAYER_LOCATION)));
        event.registerSkullModel(AncientSkullBlock.Types.CRESTED, new CrestedAncientSkullModel(event.getEntityModelSet().bakeLayer(CrestedAncientSkullModel.LAYER_LOCATION)));
        event.registerSkullModel(AncientSkullBlock.Types.FLATBILLED, new FlatbilledAncientSkullModel(event.getEntityModelSet().bakeLayer(FlatbilledAncientSkullModel.LAYER_LOCATION)));
        event.registerSkullModel(AncientSkullBlock.Types.GIGANTIC, new GiganticAncientSkullModel(event.getEntityModelSet().bakeLayer(GiganticAncientSkullModel.LAYER_LOCATION)));
        event.registerSkullModel(AncientSkullBlock.Types.HORNED, new HornedAncientSkullModel(event.getEntityModelSet().bakeLayer(HornedAncientSkullModel.LAYER_LOCATION)));
        event.registerSkullModel(AncientSkullBlock.Types.LONG, new LongAncientSkullModel(event.getEntityModelSet().bakeLayer(LongAncientSkullModel.LAYER_LOCATION)));
        event.registerSkullModel(AncientSkullBlock.Types.TINY, new TinyAncientSkullModel(event.getEntityModelSet().bakeLayer(TinyAncientSkullModel.LAYER_LOCATION)));
        event.registerSkullModel(AncientSkullBlock.Types.WIDE, new WideAncientSkullModel(event.getEntityModelSet().bakeLayer(WideAncientSkullModel.LAYER_LOCATION)));
        event.registerSkullModel(AncientSkullBlock.Types.RIBBED, new RibbedAncientSkullModel(event.getEntityModelSet().bakeLayer(RibbedAncientSkullModel.LAYER_LOCATION)));
        event.registerSkullModel(AncientSkullBlock.Types.UNICORN, new UnicornAncientSkullModel(event.getEntityModelSet().bakeLayer(UnicornAncientSkullModel.LAYER_LOCATION)));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @SubscribeEvent
    public static void addRenderLayers(EntityRenderersEvent.AddLayers event) {
        List<EntityType<? extends LivingEntity>> entityTypes = ImmutableList.copyOf(
                ForgeRegistries.ENTITY_TYPES.getValues().stream()
                        .filter(DefaultAttributes::hasSupplier)
                        .map(entityType -> (EntityType<? extends LivingEntity>) entityType)
                        .collect(Collectors.toList())
        );

        entityTypes.forEach(entityType -> {
            LivingEntityRenderer renderer = null;
            if (entityType != EntityType.ENDER_DRAGON) {
                try {
                    renderer = event.getRenderer(entityType);
                } catch (Exception ignored) {}

                if (renderer != null) {
                    renderer.addLayer(new StuckInAmberLayer(renderer));
                }
            }
        });
    }
}
