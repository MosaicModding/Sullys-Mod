package com.uraneptus.sullysmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class TortoiseShellModel <T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(SullysMod.modPrefix("tortoise_shell"), "main");
    private final ModelPart shell;

    public TortoiseShellModel(ModelPart root) {
        this.shell = root.getChild("shell");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition shell = partdefinition.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -9.0F, 16.0F, 13.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(-8.0F, -11.0F, -9.0F, 16.0F, 7.0F, 19.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 13.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

        return LayerDefinition.create(meshdefinition, 96, 96);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.shell.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}