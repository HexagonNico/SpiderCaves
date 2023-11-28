package io.github.hexagonnico.spidercaves.entities;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * Black recluse entity extending the default spider.
 *
 * @author Nico
 */
public class BlackRecluse extends Spider {

    /**
     * Constructs the entity.
     *
     * @param type Entity type
     * @param world World
     */
    public BlackRecluse(EntityType<? extends BlackRecluse> type, Level world) {
        super(type, world);
    }

    @Override
    @SuppressWarnings("resource")
    public boolean doHurtTarget(@NotNull Entity target) {
        if(super.doHurtTarget(target)) {
            if(target instanceof LivingEntity livingTarget) {
                livingTarget.addEffect(new MobEffectInstance(MobEffects.POISON, switch (this.level().getDifficulty()) {
                    case PEACEFUL -> 0;
                    case EASY -> 5 * 20;
                    case NORMAL -> 10 * 20;
                    case HARD -> 20 * 20;
                }, 1), this);
            }
            return true;
        }
        return false;
    }
}
