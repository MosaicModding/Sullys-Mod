package com.uraneptus.sullysmod.client.model.ancient_skulls;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SnoutedAncientSkullModel extends AbstractAncientSkullModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(SullysMod.modPrefix("snouted"), "main");


	public SnoutedAncientSkullModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 10).addBox(-3.0F, -6.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("bill", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -8.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));


		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public float headRenderScale() {
		return 1.5F;
	}

	@Override
	public float headRenderHeight() {
		return 0.0F;
	}
}