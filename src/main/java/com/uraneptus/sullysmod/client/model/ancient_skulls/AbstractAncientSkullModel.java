package com.uraneptus.sullysmod.client.model.ancient_skulls;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;

public abstract class AbstractAncientSkullModel extends SkullModelBase {
    private final ModelPart head;

    public AbstractAncientSkullModel(ModelPart root) {
        this.head = root.getChild("head");
    }

    @Override
    public void setupAnim(float pMouthAnimation, float pYRot, float pXRot) {
        this.head.yRot = pYRot * ((float)Math.PI / 180F);
        this.head.xRot = pXRot * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();
        this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.popPose();
    }

    public abstract float headRenderScale();
    public abstract float headRenderHeight();
}
