package com.uraneptus.sullysmod.client.model.ancient_skulls;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrackedAncientSkullModel extends SkullModelBase {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(SullysMod.modPrefix("cracked_ancient_skull"), "main");
	private final ModelPart head;

	public CrackedAncientSkullModel(ModelPart root) {
		this.head = root.getChild("head");
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
	public void setupAnim(float pMouthAnimation, float pYRot, float pXRot) {
		this.head.yRot = pYRot * ((float)Math.PI / 180F);
		this.head.xRot = pXRot * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();
		float scale = 0.8F;
		poseStack.scale(scale, scale, scale);
		this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}


}