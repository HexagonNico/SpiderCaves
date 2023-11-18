package io.github.hexagonnico.spidercaves.forge;

import io.github.hexagonnico.spidercaves.RegistryManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(RegistryManager.MOD_ID)
public class ForgeInitializer {

    public ForgeInitializer() {
        RegistryManager.register();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
