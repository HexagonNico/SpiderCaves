package io.github.hexagonnico.spidercaves;

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

    /**
     * Finalizes the registration process by registering all the objects added to the registry.
     * Different mod loaders should call this method from their initializer.
     */
    public static void register() {
        REGISTRY.register();
    }
}
