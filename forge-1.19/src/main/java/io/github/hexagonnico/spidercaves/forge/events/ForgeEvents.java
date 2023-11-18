package io.github.hexagonnico.spidercaves.forge.events;

import io.github.hexagonnico.spidercaves.RegistryManager;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = RegistryManager.MOD_ID)
public class ForgeEvents {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        LivingEntity target = event.getEntity();
        if(target.getItemBySlot(EquipmentSlot.HEAD).is(RegistryManager.SPIDER_HELMET.get()) &&
                target.getItemBySlot(EquipmentSlot.CHEST).is(RegistryManager.SPIDER_CHESTPLATE.get()) &&
                target.getItemBySlot(EquipmentSlot.LEGS).is(RegistryManager.SPIDER_LEGGINGS.get()) &&
                target.getItemBySlot(EquipmentSlot.FEET).is(RegistryManager.SPIDER_BOOTS.get()) &&
                event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
            attacker.addEffect(new MobEffectInstance(MobEffects.POISON, 200), target);
        }
    }
}
