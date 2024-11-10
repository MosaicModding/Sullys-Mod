package com.uraneptus.sullysmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.animations.AnimUtil;
import com.uraneptus.sullysmod.client.animations.TortoiseAnimation;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class TortoiseModel<E extends Tortoise> extends EntityModel<E> implements RootModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(SullysMod.modPrefix("tortoise"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart left_back_leg;
    private final ModelPart right_back_leg;
    private final ModelPart right_front_leg;
    private final ModelPart left_front_leg;
    private final ModelPart shell;
    private final ModelPart workstation_saddle;
    private final ModelPart head;
    private final ModelPart tail;

    public TortoiseModel(ModelPart pRoot) {
        this.root = pRoot;
        this.body = root.getChild("body");
        this.left_back_leg = body.getChild("left_back_leg");
        this.right_back_leg = body.getChild("right_back_leg");
        this.right_front_leg = body.getChild("right_front_leg");
        this.left_front_leg = body.getChild("left_front_leg");
        this.shell = body.getChild("shell");
        this.workstation_saddle = shell.getChild("workstation_saddle");
        this.workstation_saddle.visible = false;
        this.head = shell.getChild("head");
        this.tail = shell.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        body.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(50, 39).addBox(-2.0F, -1.0F, -3.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -5.0F, 9.0F, 0.0F, -1.1345F, 0.0F));
        body.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(50, 39).mirror().addBox(-3.0F, -1.0F, -3.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, -5.0F, 9.0F, 0.0F, 1.1345F, 0.0F));
        body.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(50, 39).mirror().addBox(-3.0F, -1.0F, -2.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, -5.0F, -9.0F, 0.0F, 0.3927F, 0.0F));
        body.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(50, 39).addBox(-2.0F, -1.0F, -2.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -5.0F, -9.0F, 0.0F, -0.3927F, 0.0F));

        PartDefinition shell = body.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -17.0F, -8.0F, 16.0F, 14.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(-8.0F, -17.0F, -8.0F, 16.0F, 14.0F, 18.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, -0.5F));

        shell.addOrReplaceChild("workstation_saddle", CubeListBuilder.create().texOffs(0, 64).addBox(-8.0F, -18.0F, -7.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -3.0F, 0.0F));
        shell.addOrReplaceChild("head", CubeListBuilder.create().texOffs(50, 0).addBox(-3.0F, -2.0F, -9.0F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(80, 0).addBox(-3.0F, -2.5F, -9.0F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -5.5F, -6.0F));
        shell.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 9.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(E pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.workstation_saddle.visible = pEntity.hasAppliedWorkstation();
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);

        if (pEntity.getHideTimerDuration() == 0) {
            AnimUtil.animateWalk(this, TortoiseAnimation.WALK, pLimbSwing, pLimbSwingAmount, 8, 20);
        }
        AnimUtil.animate(this, pEntity.hideState, TortoiseAnimation.HIDE, pAgeInTicks);
        AnimUtil.animate(this, pEntity.hiddenState, TortoiseAnimation.HIDING, pAgeInTicks);
        AnimUtil.animate(this, pEntity.reveal_state, TortoiseAnimation.REVEAL, pAgeInTicks);
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
