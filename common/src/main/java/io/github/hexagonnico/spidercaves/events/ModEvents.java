package io.github.hexagonnico.spidercaves.events;

import io.github.hexagonnico.spidercaves.SpiderCaves;
import io.github.hexagonnico.spidercaves.entities.BlackRecluse;
import io.github.phantomloader.library.events.ModEventHandler;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.BiConsumer;

public class ModEvents implements ModEventHandler {

    @Override
    public void registerEntityAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> event) {
        event.accept(SpiderCaves.BLACK_RECLUSE.get(), BlackRecluse.createAttributes());
    }
}
