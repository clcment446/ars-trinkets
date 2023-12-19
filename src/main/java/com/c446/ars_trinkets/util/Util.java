package com.c446.ars_trinkets.util;

import com.hollingsworth.arsnouveau.client.particle.GlowParticleData;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public interface Util {
    ArrayList<Item> focus = new ArrayList<>();


    public static void CreateParticleBeam(Vec3 start, Vec3 end, Level level, ParticleColor color) {
        /**
         * Creates a beam of particles between two points.
         * @param start: Vec3; the vector that represents the start of the line.
         * @param end: Vec3;  the vector that represents the end of the line.
         * @param color: ParticleColor; the particle color of the beam.
         * This function was copied and modified from the Too Many Glyphs project. Github Repo here : https://github.com/DerringersMods/TooManyGlyphs/blob/1.19.x/src/main/java/io/github/derringersmods/toomanyglyphs/common/network/PacketRayEffect.java#L29.
         * */

        double distance = start.distanceTo(end);
        double traceStart = 0.0, increment = 1.0 / 16;
        for (double d = traceStart; d < distance; d += increment) {
            double fractionalDistance = d / distance;
            double speedCoefficient = Mth.lerp(fractionalDistance, 0.2, 0.001);
            level.addParticle(
                    GlowParticleData.createData(color),
                    Mth.lerp(fractionalDistance, start.x, end.x),
                    Mth.lerp(fractionalDistance, start.y, end.y),
                    Mth.lerp(fractionalDistance, start.z, end.z),
                    (level.random.nextFloat() - 0.5) * speedCoefficient,
                    (level.random.nextFloat() - 0.5) * speedCoefficient,
                    (level.random.nextFloat() - 0.5) * speedCoefficient);
        }
    }
    public static void CreateParticleBeam(Vec3 start, Vec3 end, Level level, ParticleColor color, double increment) {
        /**
         * Creates a beam of particles between two points.
         * @param start: Vec3; the vector that represents the start of the line.
         * @param end: Vec3;  the vector that represents the end of the line.
         * @param color: ParticleColor; the particle color of the beam.
         * @param increment: double; how many blocks to increment the beam by.
         * This function was copied and modified from the Too Many Glyphs project. Github Repo here : https://github.com/DerringersMods/TooManyGlyphs/blob/1.19.x/src/main/java/io/github/derringersmods/toomanyglyphs/common/network/PacketRayEffect.java#L29.
         * */

        double distance = start.distanceTo(end);
        double traceStart = 0.0;
        for (double d = traceStart; d < distance; d += increment) {
            double fractionalDistance = d / distance;
            double speedCoefficient = Mth.lerp(fractionalDistance, 0.2, 0.001);
            level.addParticle(
                    GlowParticleData.createData(color),
                    Mth.lerp(fractionalDistance, start.x, end.x),
                    Mth.lerp(fractionalDistance, start.y, end.y),
                    Mth.lerp(fractionalDistance, start.z, end.z),
                    (level.random.nextFloat() - 0.5) * speedCoefficient,
                    (level.random.nextFloat() - 0.5) * speedCoefficient,
                    (level.random.nextFloat() - 0.5) * speedCoefficient);
        }
    }


    public static void CreateParticleBeam(Vec3 start, Vec3 end, Level level, SimpleParticleType particleType) {
        /**
         * Creates a beam of particles between two points.
         * @param start: Vec3; the vector that represents the start of the line.
         * @param end: Vec3;  the vector that represents the end of the line.
         * @param color: ParticleColor; the particle color of the beam.
         * @param increment: double; how many blocks to increment the beam by.
         * This function was copied and modified from the Too Many Glyphs project. Github Repo here : https://github.com/DerringersMods/TooManyGlyphs/blob/1.19.x/src/main/java/io/github/derringersmods/toomanyglyphs/common/network/PacketRayEffect.java#L29.
         * */

        double distance = start.distanceTo(end);
        double traceStart = 0.0,increment=1.0/16;
        for (double d = traceStart; d < distance; d += increment) {
            double fractionalDistance = d / distance;
            double speedCoefficient = Mth.lerp(fractionalDistance, 0.2, 0.001);
            level.addParticle(
                    (ParticleOptions) particleType,
                    Mth.lerp(fractionalDistance, start.x, end.x),
                    Mth.lerp(fractionalDistance, start.y, end.y),
                    Mth.lerp(fractionalDistance, start.z, end.z),
                    (level.random.nextFloat() - 0.5) * speedCoefficient,
                    (level.random.nextFloat() - 0.5) * speedCoefficient,
                    (level.random.nextFloat() - 0.5) * speedCoefficient);
        }
    }

    public static void CreateParticleBeam(Vec3 start, Vec3 end, Level level, SimpleParticleType particleType , double increment) {
        /**
         * Creates a beam of particles between two points.
         * @param start: Vec3; the vector that represents the start of the line.
         * @param end: Vec3;  the vector that represents the end of the line.
         * @param color: ParticleColor; the particle color of the beam.
         * @param increment: double; how many blocks to increment the beam by.
         * This function was copied and modified from the Too Many Glyphs project. Github Repo here : https://github.com/DerringersMods/TooManyGlyphs/blob/1.19.x/src/main/java/io/github/derringersmods/toomanyglyphs/common/network/PacketRayEffect.java#L29.
         * */

        double distance = start.distanceTo(end);
        double traceStart = 0.0;
        for (double d = traceStart; d < distance; d += increment) {
            double fractionalDistance = d / distance;
            double speedCoefficient = Mth.lerp(fractionalDistance, 0.2, 0.001);
            level.addParticle(
                    (ParticleOptions) particleType,
                    Mth.lerp(fractionalDistance, start.x, end.x),
                    Mth.lerp(fractionalDistance, start.y, end.y),
                    Mth.lerp(fractionalDistance, start.z, end.z),
                    (level.random.nextFloat() - 0.5) * speedCoefficient,
                    (level.random.nextFloat() - 0.5) * speedCoefficient,
                    (level.random.nextFloat() - 0.5) * speedCoefficient);
        }
    }
    /*
* double distance = msg.from.distanceTo(msg.to);
            double start = 0.0, increment = 1.0/16.0;
            if (player.position().distanceToSqr(msg.from) < 4.0 && msg.to.subtract(msg.from).normalize().dot(player.getViewVector(1f)) > Mth.SQRT_OF_TWO/2) {
                start = Math.min(2.0, distance / 2.0);
                increment = 1.0 / 8.0;
            }
            for (double d = start; d < distance; d += increment) {
                double fractionalDistance = d / distance;
                double speedCoefficient = Mth.lerp(fractionalDistance, 0.2, 0.001);
                level.addParticle(
                        GlowParticleData.createData(msg.color),
                        Mth.lerp(fractionalDistance, msg.from.x, msg.to.x),
                        Mth.lerp(fractionalDistance, msg.from.y, msg.to.y),
                        Mth.lerp(fractionalDistance, msg.from.z, msg.to.z),
                        (level.random.nextFloat() - 0.5) * speedCoefficient,
                        (level.random.nextFloat() - 0.5) * speedCoefficient,
                        (level.random.nextFloat() - 0.5) * speedCoefficient);
            }
        }

*/
}
