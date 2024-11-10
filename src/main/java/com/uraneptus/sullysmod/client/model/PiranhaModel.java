package com.uraneptus.sullysmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.animations.AnimUtil;
import com.uraneptus.sullysmod.client.animations.PiranhaAnimation;
import com.uraneptus.sullysmod.common.entities.Piranha;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class PiranhaModel<E extends Piranha> extends EntityModel<E> implements RootModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(SullysMod.modPrefix("piranha"), "main");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart mouth;
    private final ModelPart tail_fin;

    public PiranhaModel(ModelPart pRoot) {
        this.root = pRoot;
        this.body = root.getChild("body");
        this.head = body.getChild("head");
        this.mouth = head.getChild("mouth");
        this.tail_fin = body.getChild("tail_fin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -4.0F, 0.0F, 4.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(14, 14).addBox(0.0F, -8.0F, 1.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, 3.0F, 3.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, -1.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(12, 13).addBox(-2.5F, -1.0F, -4.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(14, 0).addBox(-2.5F, -2.0F, -4.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -1.0F, -0.4363F, 0.0F, 0.0F));

        body.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(20, 5).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.6109F));
        body.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(20, 5).mirror().addBox(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 2.0F, 1.0F, 0.0F, 0.0F, -0.6109F));
        body.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(E pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        float f = 1.0F;
        if (!pEntity.isInWater()) {
            f = 1.5F;
        }

        AnimUtil.animate(this, pEntity.swimState, PiranhaAnimation.SWIM, pAgeInTicks);
        AnimUtil.animate(this, pEntity.angrySwimState, PiranhaAnimation.SWIM_ANGRY, pAgeInTicks);

        this.tail_fin.yRot = -f * 0.45F * Mth.sin(0.6F * pAgeInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.root().render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
