package com.uraneptus.sullysmod.client.renderer.entities;

import com.uraneptus.sullysmod.common.entities.Mauled;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class MauledArmorModel <E extends Mauled> extends HumanoidModel<E> {
    public MauledArmorModel(ModelPart pRoot) {
        super(pRoot);
    }

    public static MeshDefinition createBodyLayer(CubeDeformation pCubDeformation) {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(pCubDeformation, 0.0F);
        PartDefinition partDefinition = meshdefinition.getRoot();
        CubeDeformation noDeformation = CubeDeformation.NONE;

        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 8.0F, 8.0F, pCubDeformation)
                .texOffs(0, 0), PartPose.offset(0.0F, 6.0F, -1.0F));

        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-5.0F, 2.0F, -2.0F, 10.0F, 11.0F, 4.0F, pCubDeformation.extend(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).addBox(0.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, pCubDeformation.extend(0.5F)), PartPose.offset(5.0F, 3.0F, 0.0F));

        partDefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, pCubDeformation.extend(0.5F)), PartPose.offset(-5.0F, 3.0F, 0.0F));
//unhappy

        /*
        partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 1.0F, -2.1F, 5.0F, 11.0F, 4.0F, pCubDeformation), PartPose.offset(-1.9F, 12.0F, 0.0F));
        partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs().mirror().addBox(-3.0F, 1.0F, -2.1F, 5.0F, 11.0F, 4.0F, pCubDeformation), PartPose.offset(1.9F, 12.0F, 0.0F));
         */

        partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 1.0F, -1.1F, 2.0F, 11.0F, 2.0F, pCubDeformation.extend(0.5F)), PartPose.offset(2.0F, 12.0F, 0.1F));
        partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 1.0F, -1.1F, 2.0F, 11.0F, 2.0F, pCubDeformation.extend(0.5F)), PartPose.offset(-2.0F, 12.0F, 0.1F));


        return meshdefinition;
    }
}