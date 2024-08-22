package com.c446.ars_trinkets.util;

import com.c446.ars_trinkets.registry.ModRegistry;
import com.hollingsworth.arsnouveau.client.particle.GlowParticleData;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static com.c446.ars_trinkets.registry.ModRegistry.*;
import static com.c446.ars_trinkets.registry.ModRegistry.PURPLE_ESSENCE;

public class Util {
    ArrayList<Item> focus = new ArrayList<>();

    public static HashMap<Attribute, Long> fromConfigToLong(HashMap<Attribute, ForgeConfigSpec.IntValue> attributeIntValueHashMap) {
        HashMap<Attribute, Long> convertedMap = new HashMap<>();
        for (Attribute a : attributeIntValueHashMap.keySet()) {
            convertedMap.put(a, attributeIntValueHashMap.get(a).get().longValue());
        }
        return convertedMap;
    }

    public static void CreateParticleBeam(Vec3 start, Vec3 end, ServerLevel level, ParticleColor color) {
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

    @Deprecated
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


    public static void CreateParticleBeam(Vec3 start, Vec3 end, ServerLevel level, SimpleParticleType particle) {
        /**
         * Creates a beam of particles between two points.
         * @param start: Vec3; the vector that represents the start of the line.
         * @param end: Vec3;  the vector that represents the end of the line.
         * @param color: ParticleColor; the particle color of the beam.
         * @param increment: double; how many blocks to increment the beam by.
         * This function was copied and modified from the Too Many Glyphs project. Github Repo here : https://github.com/DerringersMods/TooManyGlyphs/blob/1.19.x/src/main/java/io/github/derringersmods/toomanyglyphs/common/network/PacketRayEffect.java#L29.
         * */

        double distance = start.distanceTo(end);
        double traceStart = 0.0, increment = 1.0 / 16;
        for (double d = traceStart; d < distance; d += increment) {
            double fractionalDistance = d / distance;
            level.sendParticles(particle,
                    Mth.lerp(fractionalDistance, start.x, end.x),
                    Mth.lerp(fractionalDistance, start.y, end.y),
                    Mth.lerp(fractionalDistance, start.z, end.z)
                    , 100, 0, 0, 0, 1);
        }
    }

    public static void CreateParticleBeam(Vec3 start, Vec3 end, ServerLevel level, SimpleParticleType particle, double increment) {
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
            level.sendParticles(particle,
                    Mth.lerp(fractionalDistance, start.x, end.x),
                    Mth.lerp(fractionalDistance, start.y, end.y),
                    Mth.lerp(fractionalDistance, start.z, end.z)
                    , 100, 0, 0, 0, 1);
        }
    }

    public static void CreateParticleBeam(Vec3 start, Vec3 end, ServerLevel level, SimpleParticleType particle, double increment, int count) {
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
            level.sendParticles(particle,
                    Mth.lerp(fractionalDistance, start.x, end.x),
                    Mth.lerp(fractionalDistance, start.y, end.y),
                    Mth.lerp(fractionalDistance, start.z, end.z)
                    , count, 0, 0, 0, 1);
        }
    }

    public static long randomLongFromRange(int lower, int upper) {
        return (lower + (long) (Math.random() * (upper - lower)));
    }

    public static ArrayList<Item> getAllEssences() {
        ArrayList<Item> ESSENCE_LIST = new ArrayList<Item>();

        System.out.println("Essence List created");

        ESSENCE_LIST.add(SILVER_ESSENCE.get());
        ESSENCE_LIST.add(GOLD_ESSENCE.get());
        ESSENCE_LIST.add(CRYSTAL_ESSENCE.get());
        ESSENCE_LIST.add(GREEN_ESSENCE.get());
        ESSENCE_LIST.add(RED_ESSENCE.get());
        ESSENCE_LIST.add(WHITE_ESSENCE.get());
        ESSENCE_LIST.add(YELLOW_ESSENCE.get());
        ESSENCE_LIST.add(PURPLE_ESSENCE.get());

        return ESSENCE_LIST;
    }

    public static HashMap<Item, Integer> getAllEssencesValues() {
        HashMap<Item, Integer> ESSENCE_VALUE = new HashMap<Item, Integer>();

        System.out.println("Essence Values created");

        ESSENCE_VALUE.put(ModRegistry.IRON_ESSENCE.get(), 25);
        ESSENCE_VALUE.put(ModRegistry.COPPER_ESSENCE.get(), 50);
        ESSENCE_VALUE.put(ModRegistry.SILVER_ESSENCE.get(), 100);
        ESSENCE_VALUE.put(ModRegistry.GOLD_ESSENCE.get(), 200);
        ESSENCE_VALUE.put(ModRegistry.CRYSTAL_ESSENCE.get(), 300);
        ESSENCE_VALUE.put(ModRegistry.GREEN_ESSENCE.get(), 1000);
        ESSENCE_VALUE.put(ModRegistry.RED_ESSENCE.get(), 2500);
        ESSENCE_VALUE.put(ModRegistry.WHITE_ESSENCE.get(), 7000);
        ESSENCE_VALUE.put(ModRegistry.YELLOW_ESSENCE.get(), 14000);
        ESSENCE_VALUE.put(ModRegistry.PURPLE_ESSENCE.get(), 27000);

        return ESSENCE_VALUE;
    }

    public static HashMap<Integer, Integer> getAllRefinementStages() {
        HashMap<Integer, Integer> refinement = new HashMap<Integer, Integer>();
//        refinement.put(0,450);
        refinement.put(0, 770);
        refinement.put(1, 6_134);
        refinement.put(2, 12_204);
        refinement.put(3, 56_180);
        refinement.put(4, 273_912);
        refinement.put(5, 770_077);
        refinement.put(6, 1_497_450);
        refinement.put(7, 2_994_900);
        refinement.put(8, 5_989_800);
        refinement.put(9, 17_969_400);
        return refinement;
    }

}