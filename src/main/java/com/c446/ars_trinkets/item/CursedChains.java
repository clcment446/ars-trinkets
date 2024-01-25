//package com.c446.ars_trinkets.item;
//
//import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
//import com.hollingsworth.arsnouveau.api.item.ArsNouveauCurio;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import top.theillusivec4.curios.api.SlotContext;
//
//public class CursedChains extends ArsNouveauCurio {
//    public CursedChains() {
//    }
//    public void curioTick(SlotContext context, ItemStack stack){
//        LivingEntity entity = (LivingEntity) context.entity();
//        if (entity instanceof Player player){
//            player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a->a.setCursedChains(true));
//        }
//    }
//}
