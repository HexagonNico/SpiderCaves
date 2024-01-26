package io.github.hexagonnico.spidercaves.integration.terrablender;

import com.mojang.datafixers.util.Pair;
import io.github.hexagonnico.spidercaves.SpiderCaves;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class SpiderCavesRegion extends Region {

    private static final ResourceKey<Biome> SPIDER_CAVE_BIOME = ResourceKey.create(Registries.BIOME, new ResourceLocation(SpiderCaves.modId(), "spider_cave"));

    public SpiderCavesRegion() {
        super(new ResourceLocation(SpiderCaves.modId(), "region"), RegionType.OVERWORLD, 1);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> builder.replaceBiome(Biomes.DRIPSTONE_CAVES, SPIDER_CAVE_BIOME));
    }
}
