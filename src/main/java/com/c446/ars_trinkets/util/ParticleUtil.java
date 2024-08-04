package com.c446.ars_trinkets.util;

import com.hollingsworth.arsnouveau.client.particle.ColorParticleTypeData;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.client.registry.ModParticles;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.phys.Vec3;

public class ParticleUtil {
    public static int createIntColor(int red, int green, int blue){
        return ((red << 16) | (green << 8) | blue);
    }
    public static DustParticleOptions CreateDustParticle(int red, int green, int blue){
        return new DustParticleOptions(Vec3.fromRGB24(createIntColor(red,green,blue)).toVector3f(), 1.0F);
    }

    public class GlowParticleData {

        public static ParticleOptions createData(ParticleColor color) {
            return new ColorParticleTypeData(ModParticles.GLOW_TYPE.get(), color, false);
        }

        public static ParticleOptions createData(ParticleColor color, boolean disableDepthTest) {
            return new ColorParticleTypeData(ModParticles.GLOW_TYPE.get(), color, disableDepthTest, 0.25f, 0.75f, 36);
        }

        public static ParticleOptions createData(ParticleColor color, boolean disableDepthTest, float size, float alpha, int age) {
            return new ColorParticleTypeData(color, disableDepthTest, size, alpha, age);
        }

        public static ParticleOptions createData(ParticleColor color, float size, float alpha, int age) {
            return new ColorParticleTypeData(color, false, size, alpha, age);
        }
    }
}
