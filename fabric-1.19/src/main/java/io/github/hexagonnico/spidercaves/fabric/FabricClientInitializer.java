package io.github.hexagonnico.spidercaves.fabric;

import io.github.hexagonnico.spidercaves.RegistryManager;
import io.github.hexagonnico.spidercaves.blocks.ModChestBlockEntity;
import io.github.hexagonnico.spidercaves.fabric.renderers.BlockEntityItemRenderer;
import io.github.hexagonnico.spidercaves.renderers.BlackRecluseRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.world.item.CreativeModeTabs;

/**
 * Fabric client initializer.
 *
 * @author Nico
 */
public class FabricClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(RegistryManager.WEB_COVERED_CHEST_ENTITY.get(), ChestRenderer::new);
        EntityRendererRegistry.register(RegistryManager.BLACK_RECLUSE.get(), BlackRecluseRenderer::new);
        BuiltinItemRendererRegistry.INSTANCE.register(RegistryManager.WEB_COVERED_CHEST.get(), new BlockEntityItemRenderer(ModChestBlockEntity::new, RegistryManager.WEB_COVERED_CHEST));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(listener -> {
            listener.accept(RegistryManager.SPIDER_FANG.get());
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(listener -> {
            listener.accept(RegistryManager.WEB_COVERED_CHEST.get());
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(listener -> {
            listener.accept(RegistryManager.SPIDER_HELMET.get());
            listener.accept(RegistryManager.SPIDER_CHESTPLATE.get());
            listener.accept(RegistryManager.SPIDER_LEGGINGS.get());
            listener.accept(RegistryManager.SPIDER_BOOTS.get());
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(listener -> {
            listener.accept(RegistryManager.BLACK_RECLUSE_SPAWN_EGG.get());
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(listener -> {
            listener.accept(RegistryManager.SPIDER_EGG.get());
        });
    }
}
