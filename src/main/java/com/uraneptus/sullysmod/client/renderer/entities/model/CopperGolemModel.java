package com.uraneptus.sullysmod.client.renderer.entities.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.CopperGolemEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

// Made with Blockbench 4.0.2 by Sully

public class CopperGolemModel extends AnimatedGeoModel<CopperGolemEntity> {

    @Override
    public ResourceLocation getModelLocation(CopperGolemEntity entity) {
        return new ResourceLocation(SullysMod.MOD_ID, "geo/copper_golem.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CopperGolemEntity entity) {
        int id = entity.getEntityData().get(CopperGolemEntity.OXIDIZATION);
        return new ResourceLocation(SullysMod.MOD_ID, "textures/entity/copper_golem/copper_golem_" + id + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CopperGolemEntity entity) {
        return new ResourceLocation(SullysMod.MOD_ID, "animations/copper_golem.animation.json");
    }

    @SuppressWarnings({ "unchecked", "unused"})
    @Override
    public void setLivingAnimations(CopperGolemEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        LivingEntity entityIn = entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}

/*public class CopperGolemModel extends EntityModel<CopperGolemEntity> {

	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(SullysMod.MOD_ID, "copper_golem"), "main");
	private final ModelPart ROOT;
	protected final ModelPart HEAD;
	protected final ModelPart LIGHTNING_ROD;
	protected final ModelPart LEFT_ARM;
	protected final ModelPart RIGHT_ARM;
	protected final ModelPart RIGHT_LEG;
	protected final ModelPart LEFT_LEG;

	public CopperGolemModel(ModelPart root) {
		this.ROOT = root.getChild("ROOT");
		this.HEAD = this.ROOT.getChild("HEAD");
		this.LIGHTNING_ROD = this.HEAD.getChild("LIGHTNING_ROD");
		this.LEFT_ARM = this.ROOT.getChild("LEFT_ARM");
		this.RIGHT_ARM = this.ROOT.getChild("RIGHT_ARM");
		this.RIGHT_LEG = this.ROOT.getChild("RIGHT_LEG");
		this.LEFT_LEG = this.ROOT.getChild("LEFT_LEG");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ROOT = partdefinition.addOrReplaceChild("ROOT", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -1.0F));

		PartDefinition BODY = ROOT.addOrReplaceChild("BODY", CubeListBuilder.create().texOffs(0, 18).addBox(-4.5F, -2.0F, -3.0F, 9.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition HEAD = ROOT.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -7.0F, -5.0F, 11.0F, 7.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.5F, -4.0F, -7.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, 0.0F));

		PartDefinition LIGHTNING_ROD = HEAD.addOrReplaceChild("LIGHTNING_ROD", CubeListBuilder.create().texOffs(16, 31).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(33, 0).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, 1.0F));

		PartDefinition LEFT_ARM = ROOT.addOrReplaceChild("LEFT_ARM", CubeListBuilder.create().texOffs(28, 27).mirror().addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.5F, -10.0F, 0.0F));

		PartDefinition RIGHT_ARM = ROOT.addOrReplaceChild("RIGHT_ARM", CubeListBuilder.create().texOffs(28, 27).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, -10.0F, 0.0F));

		PartDefinition RIGHT_LEG = ROOT.addOrReplaceChild("RIGHT_LEG", CubeListBuilder.create().texOffs(32, 18).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -5.0F, 0.0F));

		PartDefinition LEFT_LEG = ROOT.addOrReplaceChild("LEFT_LEG", CubeListBuilder.create().texOffs(32, 18).mirror().addBox(-2.0F, 0.0F, -1.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, -5.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 96, 96);
	}

	@Override
	public void setupAnim(CopperGolemEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		ROOT.render(poseStack, buffer, packedLight, packedOverlay);
	}
}*/