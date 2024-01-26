package io.github.hexagonnico.spidercaves.integration;

import io.github.phantomloader.library.ModEntryPoint;
import io.github.phantomloader.library.integration.FabricCustomEntryPoint;
import io.github.phantomloader.library.platform.PlatformHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IntegrationHelper {

    @ModEntryPoint(side = ModEntryPoint.Side.COMMON)
    @FabricCustomEntryPoint(name = "terrablender", interfaceName = "terrablender.api.TerraBlenderApi")
    @SuppressWarnings("JavaReflectionInvocation")
    public static void addTerraBlenderRegions() {
        if(PlatformHelper.isModLoaded("terrablender")) try {
            Class<?> regionClass = Class.forName("terrablender.api.Region");
            Method registerMethod = Class.forName("terrablender.api.Regions").getMethod("register", regionClass);
            Object regionInstance = Class.forName("io.github.hexagonnico.spidercaves.integration.terrablender.SpiderCavesRegion").getConstructor().newInstance();
            registerMethod.invoke(null, regionInstance);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | ClassNotFoundException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
