package com.c446.ars_trinkets.util;

import alexthw.ars_elemental.api.item.ISchoolFocus;
import com.dkmk100.arsomega.util.RegistryHandler;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.hollingsworth.arsnouveau.setup.ItemsRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Util {
    ArrayList<Item> focus = new ArrayList<>();

    public Util() {
    }

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
/*
@Deprecated
    static Item getFociSchool(@NotNull Player player) {
        InteractionHand[] var1 = InteractionHand.values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            InteractionHand curHand = var1[var3];
            Item item = player.getItemInHand(curHand).getItem();
            if ((item == RegistryHandler.FOCUS_OF_LIFE.get() || item == RegistryHandler.FOCUS_OF_ALCHEMY.get() || item == RegistryHandler.FOCUS_OF_ADVANCED_ALCHEMY.get()) || item instanceof ISchoolFocus) {
                return item;
            }
        }
        SlotResult curio = (SlotResult) CuriosApi.getCuriosHelper().findFirstCurio(player, (c) -> {
            Item item = c.getItem();

            if ((item == RegistryHandler.FOCUS_OF_LIFE.get() || item == RegistryHandler.FOCUS_OF_ALCHEMY.get() || item == RegistryHandler.FOCUS_OF_ADVANCED_ALCHEMY.get()) || item instanceof ISchoolFocus) {
                return item;
            }
        }).orElse((Object) null);
        if (curio != null) {
            Item var9 = curio.stack().getItem();
            if ((var9 == RegistryHandler.FOCUS_OF_LIFE.get() || var9 == RegistryHandler.FOCUS_OF_ALCHEMY.get() || var9 == RegistryHandler.FOCUS_OF_ADVANCED_ALCHEMY.get()) || var9 instanceof ISchoolFocus) {
                ISchoolFocus focus = (ISchoolFocus) var9;
                return focus.getSchool();
            }
        }
        return null;
    }*/
}
