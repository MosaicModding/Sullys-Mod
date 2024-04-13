package com.uraneptus.sullysmod.client.model.ancient_skulls;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CrestedAncientSkullModel extends BaseAncientSkullModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(SullysMod.modPrefix("crested"), "main");

	public CrestedAncientSkullModel(ModelPart root) {
		super(root, 0.8F);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -8.0F, -5.0F, 13.0F, 8.0F, 11.0F, new CubeDeformation(0.01F))
				.texOffs(0, 19).addBox(-3.5F, -12.0F, -12.0F, 7.0F, 12.0F, 12.0F, new CubeDeformation(0.01F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public float headRenderScale() {
		return 0.8F;
	}

	@Override
	public float headRenderHeight() {
		return 0.1F;
	}
}