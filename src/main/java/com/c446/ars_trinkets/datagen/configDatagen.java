package com.c446.ars_trinkets.datagen;

import com.c446.ars_trinkets.Config;

public class configDatagen {
    public static void main(String[] args) {
        StringBuilder cfgBuilder = new StringBuilder();


//        for (int i = 1; i <= 10; i++) {
//            cfgBuilder.append(createItemMap("mana_monocle", i));
//            cfgBuilder.append(concatenateGenericAttribute("mana_monocle", i, "damage_total_boost"));
//            cfgBuilder.append(concatenateGenericAttribute("mana_monocle",i, "mana_boost"));
//            cfgBuilder.append(concatenateGenericAttribute("mana_monocle",i, "regen_boost"));
//
//        }
//
//        for (int i = 1; i <= 10; i++) {
//            cfgBuilder.append(createItemMap("mana_ring", i));
//            cfgBuilder.append(concatenateGenericAttribute("mana_ring",i, "mana_boost"));
//            cfgBuilder.append(concatenateGenericAttribute("mana_ring",i, "damage_total_boost"));
//            cfgBuilder.append(concatenateGenericAttribute("mana_ring",i, "regen_boost"));
//        }

        for (int i = 3; i <= 10; i++) {
            cfgBuilder.append(createItemMap("mana_monocle", i));
        }
        for (int i = 3; i <= 10; i++) {

            cfgBuilder.append(concatenateGenericAttribute("MAX_MANA.get()","mana_monocle", i, "mana_boost"));
            cfgBuilder.append(concatenateGenericAttribute("MANA_REGEN_BONUS.get()","mana_monocle", i, "damage_total_boost"));
            cfgBuilder.append(concatenateGenericAttribute("PerkAttributes.SPELL_DAMAGE_PCT.get()","mana_monocle", i, "regen_boost"));
        }

        System.out.println(cfgBuilder.toString());
    }


    public static String createItemMap(String name, int level) {
        StringBuilder bd = new StringBuilder();
        bd.append("HashMap<Integer, Integer> ");
        bd.append(name);
        bd.append("_cfg_map = new HashMap<>();\n");
        return bd.toString();
    }

    public static String concatenateGenericAttribute(String attributePath, String name, int i, String attribute_name) {
        /*
         * mana_monocle_3.put(PerkAttributes.SPELL_DAMAGE_PCT.get(), Config.monocle_1_damage_total_boost);
         * mana_monocle_4.put(MAX_MANA.get(), Config.monocle_1_mana_boost);
         * mana_monocle_4.put(MANA_REGEN_BONUS.get(), Config.monocle_1_mana_boost);
         * */
        StringBuilder bd = new StringBuilder();

        bd.append(name);
        bd.append("_");
        bd.append(i);
        bd.append(".put(");
        bd.append(attributePath);
        bd.append(",");
        bd.append("Config.monocle_");
        bd.append(i);
        bd.append("_");
        bd.append(attribute_name);
        //bd.append(".get()");
        //bd.append(")");
        bd.append(");");
        bd.append('\n');

        return bd.toString();
    }
}
