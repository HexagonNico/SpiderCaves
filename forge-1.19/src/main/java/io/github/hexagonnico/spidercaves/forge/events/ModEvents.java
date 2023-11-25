package io.github.hexagonnico.spidercaves.forge.events;

import io.github.hexagonnico.spidercaves.RegistryManager;
import io.github.hexagonnico.spidercaves.entities.BlackRecluse;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = RegistryManager.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    /**
     * Creates attributes for entities.
     *
     * @param event Attribute creation event
     */
    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent event) {
        event.put(RegistryManager.BLACK_RECLUSE.get(), BlackRecluse.createAttributes().build());
    }
}
