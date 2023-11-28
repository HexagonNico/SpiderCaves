package io.github.hexagonnico.spidercaves.forge.events;

import io.github.hexagonnico.spidercaves.RegistryManager;
import io.github.hexagonnico.spidercaves.items.SpiderArmorItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
        if(SpiderArmorItem.isWearingFullSet(target) && event.getSource().getDirectEntity() instanceof LivingEntity attacker && attacker.getRandom().nextFloat() <= 0.3) {
            attacker.addEffect(new MobEffectInstance(MobEffects.POISON, 200), target);
        }
    }
}
