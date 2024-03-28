package com.c446.ars_trinkets.util;

import com.hollingsworth.arsnouveau.client.particle.ColorParticleTypeData;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.client.registry.ModParticles;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.phys.Vec3;
import org.w3c.dom.css.RGBColor;

public class ParticleUtil {
    public static DustParticleOptions CreateDustParticle(int red, int green, int blue){
        int color = ((red << 16) | (green << 8) | blue);
        return new DustParticleOptions(Vec3.fromRGB24(color).toVector3f(), 1.0F);
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
