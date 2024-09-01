package com.uraneptus.sullysmod.client.model.ancient_skulls;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class UnicornAncientSkullModel extends AbstractAncientSkullModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(SullysMod.modPrefix("unicorn"), "main");

    public UnicornAncientSkullModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();

        PartDefinition head = meshdefinition.getRoot().addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.0F, -4.0F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(-2.0F, -5.0F, -9.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(19, 0).addBox(-1.0F, -5.0F, 3.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.6F, 0.0F));

        head.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(18, 12).addBox(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, -1.75F, 0.4363F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public float headRenderScale() {
        return 1.2F;
    }

    @Override
    public float headRenderHeight() {
        return 0;
    }
}
