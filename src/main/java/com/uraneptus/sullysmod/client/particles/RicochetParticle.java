package com.uraneptus.sullysmod.client.particles;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.uraneptus.sullysmod.common.particletypes.DirectionParticleOptions;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
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
        float posX = (float) (Mth.lerp((double) partialTicks, this.xo, this.x) - cameraPos.x());
        float posY = (float) (Mth.lerp((double) partialTicks, this.yo, this.y) - cameraPos.y());
        float posZ = (float) (Mth.lerp((double) partialTicks, this.zo, this.z) - cameraPos.z());

        Quaternion quaternion = this.face.getRotation();
        quaternion.mul(Vector3f.XP.rotationDegrees(90.0F));

        float roll = Mth.lerp(partialTicks, this.oRoll, this.roll);
        quaternion.mul(Vector3f.ZP.rotation(roll));

        Vector3f spriteNormal = new Vector3f(-1.0F, -1.0F, 0.0F);
        spriteNormal.transform(quaternion);
        Vector3f[] faces = new Vector3f[] { new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F) };
        float quadSize = this.getQuadSize(partialTicks);

        for (int i = 0; i < 4; ++i) {
            Vector3f face = faces[i];
            face.transform(quaternion);
            face.mul(quadSize);
            face.add(posX, posY, posZ);
        }

        int light = this.getLightColor(partialTicks);
        vertexConsumer.vertex((double) faces[0].x(), (double) faces[0].y(), (double) faces[0].z()).uv(this.getU1(), this.getV1()).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        vertexConsumer.vertex((double) faces[1].x(), (double) faces[1].y(), (double) faces[1].z()).uv(this.getU1(), this.getV0()).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        vertexConsumer.vertex((double) faces[2].x(), (double) faces[2].y(), (double) faces[2].z()).uv(this.getU0(), this.getV0()).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        vertexConsumer.vertex((double) faces[3].x(), (double) faces[3].y(), (double) faces[3].z()).uv(this.getU0(), this.getV1()).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class RicochetParticleProvider implements ParticleProvider<DirectionParticleOptions> {
        private final SpriteSet sprites;

        public RicochetParticleProvider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        public Particle createParticle(DirectionParticleOptions pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new RicochetParticle(this.sprites, pLevel, pX, pY, pZ, pType.getFace());
        }
    }
}
