package com.c446.ars_trinkets.util;

import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class Util {
    public static ArrayList<double[]> Vec1Vec2TraceGet(Vec3 start, Vec3 end, double maximum, double minimum, int divisors) {
        /**
         * Returns an Array made of a Double[] array, which represents a number of points divisors from start to end.
         * @param start: Vec3; the vector that represents the start of the line.
         * @param end: Vec3;  the vector that represents the end of the line.
         * @param maximum: double; the maximum distance between two points.
         * @param minimum: double; the minimum distance between two points.
         * @param divisors: int;  the total number of points that will be traced.
         * @return ArrayList\<double[]\>
         * */
        ArrayList<double[]> particlePos = new ArrayList<double[]>();
        if (divisors > 0 && minimum > 0 && start.distanceTo(end) <= maximum) {
            double LENGTH_X = end.x - start.x;
            double LENGTH_Y = end.y - start.y;
            double LENGTH_Z = end.z - start.z;
            double DivisorDivX = LENGTH_X / divisors;
            double DivisorDivY = LENGTH_Y / divisors;
            double DivisorDivZ = LENGTH_Z / divisors;
            for (int i = 0; i < divisors; i++) {
                double x = i * DivisorDivX;
                double y = i * DivisorDivY;
                double z = i * DivisorDivZ;
                double[] par = {x, y, z};
                particlePos.add(par);
            }
        }
        return (particlePos);
    }
}
