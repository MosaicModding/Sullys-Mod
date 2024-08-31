package com.uraneptus.sullysmod.client.model.ancient_skulls;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class RibbedAncientSkullModel extends BaseAncientSkullModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(SullysMod.modPrefix("ribbed"), "main");

	public RibbedAncientSkullModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();

		meshdefinition.getRoot().addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -3.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.2F, 0.2F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public float headRenderScale() {
		return 1.1F;
	}

	@Override
	public float headRenderHeight() {
		return 0;
	}
}