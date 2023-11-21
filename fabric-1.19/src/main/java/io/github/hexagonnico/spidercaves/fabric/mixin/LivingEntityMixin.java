package io.github.hexagonnico.spidercaves.fabric.mixin;

import io.github.hexagonnico.spidercaves.RegistryManager;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
@SuppressWarnings("unused")
public class LivingEntityMixin {

    @SuppressWarnings("ConstantConditions")
    @Inject(at = @At("HEAD"), method = "hurt", cancellable = true)
    public void hurt(DamageSource damageSource, float damage, CallbackInfoReturnable<Boolean> callbackInfo) {
        if((Object) this instanceof LivingEntity livingEntity) {
            if(livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(RegistryManager.SPIDER_HELMET.get()) &&
                livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(RegistryManager.SPIDER_CHESTPLATE.get()) &&
                livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(RegistryManager.SPIDER_LEGGINGS.get()) &&
                livingEntity.getItemBySlot(EquipmentSlot.FEET).is(RegistryManager.SPIDER_BOOTS.get()) &&
                damageSource.getDirectEntity() instanceof LivingEntity attacker &&
                attacker.getRandom().nextFloat() <= 0.3) {
                attacker.addEffect(new MobEffectInstance(MobEffects.POISON, 200), livingEntity);
            }
        }
    }
}
