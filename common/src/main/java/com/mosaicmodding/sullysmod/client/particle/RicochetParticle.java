package com.mosaicmodding.sullysmod.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.mosaicmodding.sullysmod.core.particle.DirectionalParticleData;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

//TODO test
public class RicochetParticle extends TextureSheetParticle {
    private final SpriteSet animatedSprite;
    private final Direction face;

    public RicochetParticle(SpriteSet animatedSprite, ClientLevel level, double posX, double posY, double posZ, Direction face) {
        super(level, posX, posY, posZ);
        this.quadSize = 0.45F;
        this.animatedSprite = animatedSprite;
        this.lifetime = 5;
        this.hasPhysics = false;
        this.setSpriteFromAge(animatedSprite);
        this.face = face;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.animatedSprite);
        }
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float partialTicks) {
        Vec3 cameraPos = camera.getPosition();
        float posX = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float posY = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float posZ = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        Quaternionf quaternion = this.face.getRotation();
        quaternion.mul(Axis.XP.rotationDegrees(90.0F));

        float roll = Mth.lerp(partialTicks, this.oRoll, this.roll);
        quaternion.mul(Axis.ZP.rotation(roll));

        Vector3f spriteNormal = new Vector3f(-1.0F, -1.0F, 0.0F);
        spriteNormal.rotate(quaternion); // was transform method before, does it still work?
        Vector3f[] faces = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        float quadSize = this.getQuadSize(partialTicks);

        for (int i = 0; i < 4; ++i) {
            Vector3f face = faces[i];
            face.rotate(quaternion);
            face.mul(quadSize);
            face.add(posX, posY, posZ);
        }

        int light = this.getLightColor(partialTicks);
        vertexConsumer.addVertex(faces[0].x(), faces[0].y(), faces[0].z()).setUv(this.getU1(), this.getV1()).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(light);
        vertexConsumer.addVertex(faces[1].x(), faces[1].y(), faces[1].z()).setUv(this.getU1(), this.getV0()).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(light);
        vertexConsumer.addVertex(faces[2].x(), faces[2].y(), faces[2].z()).setUv(this.getU0(), this.getV0()).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(light);
        vertexConsumer.addVertex(faces[3].x(), faces[3].y(), faces[3].z()).setUv(this.getU0(), this.getV1()).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(light);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    public static class RicochetParticleProvider implements ParticleProvider<DirectionalParticleData> {
        private final SpriteSet sprites;

        public RicochetParticleProvider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        public Particle createParticle(DirectionalParticleData data, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new RicochetParticle(this.sprites, pLevel, pX, pY, pZ, data.direction());
        }
    }
}
