package com.uraneptus.sullysmod.client.model.ancient_skulls;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class GiganticAncientSkullModel extends AbstractAncientSkullModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(SullysMod.modPrefix("gigantic"), "main");


	public GiganticAncientSkullModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -15.0F, -5.0F, 13.0F, 15.0F, 11.0F, new CubeDeformation(0.01F))
				.texOffs(0, 26).addBox(-6.5F, -14.0F, -14.0F, 13.0F, 14.0F, 9.0F, new CubeDeformation(0.01F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public float headRenderScale() {
		return 0.8F;
	}

	@Override
	public float headRenderHeight() {
		return 0;
	}
}