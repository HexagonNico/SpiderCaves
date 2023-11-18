package io.github.hexagonnico.spidercaves;

import io.github.hexagonnico.spidercaves.items.SpiderArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

import java.util.ServiceLoader;
import java.util.function.Supplier;

/**
 * Mod registry manager.
 * Abstracts the registration process across different mod loaders.
 *
 * @author Nico
 */
public final class RegistryManager {

    public static final String MOD_ID = "spider_caves";

    /** The mod registry of the current loader */
    private static final ModRegistry REGISTRY = ServiceLoader.load(ModRegistry.class).findFirst().orElseThrow();

    public static final Supplier<Item> TEST_ITEM = REGISTRY.registerItem("test_item");

    public static final Supplier<ArmorItem> SPIDER_HELMET = REGISTRY.registerItem("spider_helmet", () -> new SpiderArmorItem(ArmorItem.Type.HELMET));
    public static final Supplier<ArmorItem> SPIDER_CHESTPLATE = REGISTRY.registerItem("spider_chestplate", () -> new SpiderArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final Supplier<ArmorItem> SPIDER_LEGGINGS = REGISTRY.registerItem("spider_leggings", () -> new SpiderArmorItem(ArmorItem.Type.LEGGINGS));
    public static final Supplier<ArmorItem> SPIDER_BOOTS = REGISTRY.registerItem("spider_boots", () -> new SpiderArmorItem(ArmorItem.Type.BOOTS));

    /**
     * Finalizes the registration process by registering all the objects added to the registry.
     * Different mod loaders should call this method from their initializer.
     */
    public static void register() {
        REGISTRY.register();
    }
}
