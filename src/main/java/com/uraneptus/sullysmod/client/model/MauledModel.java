package com.uraneptus.sullysmod.client.model;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.Mauled;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MauledModel<T extends Mauled> extends HumanoidModel<T> {
    public static final ModelLayerLocation MAIN_LAYER = new ModelLayerLocation(SullysMod.modPrefix("mauled"), "main");
    public static final ModelLayerLocation INNER_ARMOR = new ModelLayerLocation(SullysMod.modPrefix("mauled"), "inner_armor");
    public static final ModelLayerLocation OUTER_ARMOR = new ModelLayerLocation(SullysMod.modPrefix("mauled"), "outer_armor");

    public static final LayerDefinition INNER_ARMOR_DEF = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(1.2F)), 64, 32);
    public static final LayerDefinition OUTER_ARMOR_DEF = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(1.5F)), 64, 32);

    public MauledModel(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partDefinition = meshdefinition.getRoot();
        CubeDeformation noDeformation = CubeDeformation.NONE;

        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 8.0F, 8.0F, noDeformation)
                .texOffs(0, 16).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 6.0F, -1.0F));
        partDefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-5.0F, 2.0F, -2.0F, 10.0F, 11.0F, 4.0F, noDeformation)
                .texOffs(32, 0).addBox(-5.0F, 2.0F, -2.0F, 10.0F, 11.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 47).addBox(0.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, noDeformation)
                .texOffs(46, 30).addBox(0.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offset(5.0F, 3.0F, 0.0F));
        partDefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(8, 47).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, noDeformation)
                .texOffs(46, 46).addBox(-4.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offset(-5.0F, 3.0F, 0.0F));
        partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 47).addBox(0.0F, 1.0F, -1.1F, 2.0F, 11.0F, 2.0F, noDeformation)
                .texOffs(32, 15).addBox(-2.0F, 1.0F, -2.1F, 5.0F, 11.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offset(2.0F, 12.0F, 0.1F));
        partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(24, 47).addBox(-2.0F, 1.0F, -1.1F, 2.0F, 11.0F, 2.0F, noDeformation)
                .texOffs(28, 32).addBox(-3.0F, 1.0F, -2.1F, 5.0F, 11.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offset(-2.0F, 12.0F, 0.1F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
