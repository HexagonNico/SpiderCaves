package io.github.hexagonnico.spidercaves.fabric;

import io.github.hexagonnico.spidercaves.RegistryManager;
import net.fabricmc.api.ModInitializer;

/**
 * Fabric mod initializer.
 *
 * @author Nico
 */
public class FabricInitializer implements ModInitializer {

    @Override
    public void onInitialize() {
        RegistryManager.register();
    }
}
