package io.github.hexagonnico.spidercaves;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("spider_caves")
public class ForgeInitializer {

    public ForgeInitializer() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
