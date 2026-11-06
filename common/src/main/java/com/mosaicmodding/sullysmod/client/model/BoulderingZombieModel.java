package com.mosaicmodding.sullysmod.client.model;

import com.mosaicmodding.sullysmod.client.animation.BoulderingZombieAnimation;
import com.mosaicmodding.sullysmod.content.undead_variants.BoulderingZombieEntity;
import net.mehvahdjukaar.moonlight.api.client.anim.KeyframeAnimationHandler;
import net.mehvahdjukaar.moonlight.api.client.model.RootModel;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import static com.mosaicmodding.sullysmod.registry.SMRegUtils.path;

public class BoulderingZombieModel<E extends BoulderingZombieEntity> extends HumanoidModel<E> implements RootModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(path("bouldering_zombie"), "main");
    public static final ModelLayerLocation INNER_ARMOR = new ModelLayerLocation(path("bouldering_zombie"), "inner_armor");
    public static final ModelLayerLocation OUTER_ARMOR = new ModelLayerLocation(path("bouldering_zombie"), "outer_armor");
    private final ModelPart root;

    public BoulderingZombieModel(ModelPart pRoot) {
        super(pRoot);
        this.root = pRoot;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partDefinition = meshdefinition.getRoot();
        CubeDeformation deformationNone = CubeDeformation.NONE;

        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, deformationNone)
                .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, deformationNone), PartPose.offset(0.0F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, deformationNone), PartPose.offset(-5.0F, 2.0F, 0.0F));
        partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, deformationNone).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
        partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformationNone), PartPose.offset(-1.9F, 12.0F, 0.0F));
        partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 34).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformationNone).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(E pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, pEntity.isAggressive(), this.attackTime, pAgeInTicks);
        KeyframeAnimationHandler.animate(this, pEntity.climbAnimationState, BoulderingZombieAnimation.CLIMB, pAgeInTicks);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
