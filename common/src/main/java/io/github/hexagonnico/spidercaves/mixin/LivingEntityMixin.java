package io.github.hexagonnico.spidercaves.mixin;

import io.github.hexagonnico.spidercaves.items.SpiderArmorItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
        if((Object) this instanceof LivingEntity livingEntity && damageSource.getDirectEntity() instanceof LivingEntity attacker) {
            if(SpiderArmorItem.isWearingFullSet(livingEntity) && attacker.getRandom().nextFloat() <= 0.3) {
                attacker.addEffect(new MobEffectInstance(MobEffects.POISON, 200), livingEntity);
            }
        }
    }
}