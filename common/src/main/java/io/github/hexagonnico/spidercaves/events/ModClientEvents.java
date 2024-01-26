package io.github.hexagonnico.spidercaves.events;

import io.github.hexagonnico.spidercaves.SpiderCaves;
import io.github.hexagonnico.spidercaves.renderers.BlackRecluseRenderer;
import io.github.phantomloader.library.events.ClientEventHandler;
import io.github.phantomloader.library.events.RegisterBlockEntityRenderersEvent;
import io.github.phantomloader.library.events.RegisterEntityRenderersEvent;
import io.github.phantomloader.library.utils.CreativeTabsUtils;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModClientEvents implements ClientEventHandler {

    @Override
    public void addItemsToCreativeTab(ResourceKey<CreativeModeTab> resourceKey, Consumer<Supplier<? extends ItemLike>> event) {
        if(resourceKey.equals(CreativeTabsUtils.COMBAT)) {
            event.accept(SpiderCaves.SPIDER_HELMET);
            event.accept(SpiderCaves.SPIDER_CHESTPLATE);
            event.accept(SpiderCaves.SPIDER_LEGGINGS);
            event.accept(SpiderCaves.SPIDER_BOOTS);
        } else if(resourceKey.equals(CreativeTabsUtils.INGREDIENTS)) {
            event.accept(SpiderCaves.SPIDER_FANG);
        } else if(resourceKey.equals(CreativeTabsUtils.FUNCTIONAL_BLOCKS)) {
            event.accept(SpiderCaves.WEB_COVERED_CHEST);
        } else if(resourceKey.equals(CreativeTabsUtils.SPAWN_EGGS)) {
            event.accept(SpiderCaves.BLACK_RECLUSE_SPAWN_EGG);
        } else if(resourceKey.equals(CreativeTabsUtils.NATURAL_BLOCKS)) {
            event.accept(SpiderCaves.SPIDER_EGG);
        }
    }

    @Override
    public void registerBlockEntityRenderers(RegisterBlockEntityRenderersEvent event) {
        event.register(SpiderCaves.CHEST_ENTITY.get(), ChestRenderer::new);
        event.registerItemRenderer(SpiderCaves.WEB_COVERED_CHEST);
    }

    @Override
    public void registerEntityRenderers(RegisterEntityRenderersEvent event) {
        event.register(SpiderCaves.BLACK_RECLUSE.get(), BlackRecluseRenderer::new);
    }
}
