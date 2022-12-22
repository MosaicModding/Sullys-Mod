package com.uraneptus.sullysmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

//Made by Sully using Blockbench
@SuppressWarnings("unused")
public class LanternfishModel <T extends Entity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(SullysMod.modPrefix("lanternfish"), "main");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftFin;
    private final ModelPart rightFin;
    private final ModelPart tailFin;

    public LanternfishModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftFin = root.getChild("leftFin");
        this.rightFin = root.getChild("rightFin");
        this.tailFin = root.getChild("tailFin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.0F, 1.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(17, 4).addBox(0.0F, -6.0F, 3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 2).addBox(0.0F, -1.0F, 4.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(6, 9).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(18, 11).addBox(-1.0F, 1.0F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));
        PartDefinition leftFin = partdefinition.addOrReplaceChild("leftFin", CubeListBuilder.create().texOffs(10, 3).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 23.0F, 1.0F, 0.0F, 0.0F, 0.6109F));
        PartDefinition rightFin = partdefinition.addOrReplaceChild("rightFin", CubeListBuilder.create().texOffs(10, 0).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 23.0F, 1.0F, 0.0F, 0.0F, -0.6109F));
        PartDefinition tailFin = partdefinition.addOrReplaceChild("tailFin", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -5.0F, 0.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 7.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float f = 1.0F;
        if (!pEntity.isInWater()) {
            f = 1.5F;
        }

        this.tailFin.yRot = -f * 0.45F * Mth.sin(0.6F * pAgeInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, buffer, packedLight, packedOverlay);
        head.render(poseStack, buffer, packedLight, packedOverlay);
        leftFin.render(poseStack, buffer, packedLight, packedOverlay);
        rightFin.render(poseStack, buffer, packedLight, packedOverlay);
        tailFin.render(poseStack, buffer, packedLight, packedOverlay);
    }
}
