package com.uraneptus.sullysmod.client.animations;

import com.uraneptus.sullysmod.client.model.RootModel;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import org.joml.Vector3f;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AnimUtil {
    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

    public static <M extends EntityModel<?> & RootModel> void animate(M model, AnimationState pAnimationState, AnimationDefinition pAnimationDefinition, float pAgeInTicks) {
        animate(model, pAnimationState, pAnimationDefinition, pAgeInTicks, 1.0F);
    }

    public static <M extends EntityModel<?> & RootModel> void animateWalk(M model, AnimationDefinition pAnimationDefinition, float pLimbSwing, float pLimbSwingAmount, float pMaxAnimationSpeed, float pAnimationScaleFactor) {
        long i = (long)(pLimbSwing * 50.0F * pMaxAnimationSpeed);
        float f = Math.min(pLimbSwingAmount * pAnimationScaleFactor, 1.0F);
        animate(model, pAnimationDefinition, i, f, ANIMATION_VECTOR_CACHE);
    }

    public static <M extends EntityModel<?> & RootModel> void animate(M model, AnimationState pAnimationState, AnimationDefinition pAnimationDefinition, float pAgeInTicks, float pSpeed) {
        pAnimationState.updateTime(pAgeInTicks, pSpeed);
        pAnimationState.ifStarted((p_233392_) -> animate(model, pAnimationDefinition, p_233392_.getAccumulatedTime(), 1.0F, ANIMATION_VECTOR_CACHE));
    }

    public static <M extends EntityModel<?> & RootModel> void animate(M model, AnimationDefinition pAnimationDefinition, long pAccumulatedTime, float pScale, Vector3f pAnimationVecCache) {
        float f = getElapsedSeconds(pAnimationDefinition, pAccumulatedTime);

        for(Map.Entry<String, List<AnimationChannel>> entry : pAnimationDefinition.boneAnimations().entrySet()) {
            Optional<ModelPart> optional = getAnyDescendantWithName(model, entry.getKey());
            List<AnimationChannel> list = entry.getValue();
            optional.ifPresent((p_232330_) -> list.forEach((p_288241_) -> {
                Keyframe[] akeyframe = p_288241_.keyframes();
                int i = Math.max(0, Mth.binarySearch(0, akeyframe.length, (p_232315_) -> f <= akeyframe[p_232315_].timestamp()) - 1);
                int j = Math.min(akeyframe.length - 1, i + 1);
                Keyframe keyframe = akeyframe[i];
                Keyframe keyframe1 = akeyframe[j];
                float f1 = f - keyframe.timestamp();
                float f2;
                if (j != i) {
                    f2 = Mth.clamp(f1 / (keyframe1.timestamp() - keyframe.timestamp()), 0.0F, 1.0F);
                } else {
                    f2 = 0.0F;
                }

                keyframe1.interpolation().apply(pAnimationVecCache, f2, akeyframe, i, j, pScale);
                p_288241_.target().apply(p_232330_, pAnimationVecCache);
            }));
        }

    }

    private static <M extends EntityModel<?> & RootModel> Optional<ModelPart> getAnyDescendantWithName(M model, String pName) {
        return pName.equals("root") ? Optional.of(model.root()) : model.root().getAllParts().filter((p_233400_)
                -> p_233400_.hasChild(pName)).findFirst().map((p_233397_) -> p_233397_.getChild(pName));
    }

    private static float getElapsedSeconds(AnimationDefinition pAnimationDefinition, long pAccumulatedTime) {
        float f = (float)pAccumulatedTime / 1000.0F;
        return pAnimationDefinition.looping() ? f % pAnimationDefinition.lengthInSeconds() : f;
    }
}
