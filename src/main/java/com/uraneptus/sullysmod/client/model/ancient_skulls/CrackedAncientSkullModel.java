package com.uraneptus.sullysmod.client.model.ancient_skulls;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrackedAncientSkullModel extends AbstractAncientSkullModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(SullysMod.modPrefix("cracked"), "main");

	public CrackedAncientSkullModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 25).addBox(-7.0F, -14.0F, -20.0F, 14.0F, 5.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-8.0F, -14.0F, -5.0F, 16.0F, 14.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 45).addBox(4.0F, -12.0F, -10.0F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 30).addBox(4.0F, -1.0F, -10.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(43, 25).addBox(-9.0F, -12.0F, -10.0F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 25).addBox(-9.0F, -3.0F, -10.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)),
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