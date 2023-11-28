package io.github.hexagonnico.spidercaves.mixin;

import com.mojang.datafixers.util.Pair;
import io.github.hexagonnico.spidercaves.RegistryManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

/**
 * Mixin class for {@link OverworldBiomeBuilder}.
 * Used to make the spider cave biome generate in the overworld.
 *
 * @author Nico
 */
@SuppressWarnings("unused")
@Mixin(OverworldBiomeBuilder.class)
public class OverworldBiomeBuilderMixin {

    private static final float TEMPERATURE_MIN = -0.9f;
    private static final float TEMPERATURE_MAX = 0.3f;
    private static final float HUMIDITY_MIN = -1.0f;
    private static final float HUMIDITY_MAX = -0.4f;
    private static final float CONTINENTALNESS_MIN = 0.5f;
    private static final float CONTINENTALNESS_MAX = 1.0f;
    private static final float EROSION_MIN = -1.0f;
    private static final float EROSION_MAX = 1.0f;
    private static final float DEPTH_MIN = 0.2f;
    private static final float DEPTH_MAX = 0.9f;
    private static final float WEIRDNESS_MIN = 0.7f;
    private static final float WEIRDNESS_MAX = 1.0f;

    /**
     * Adds the spider cave biome to the overworld biome manager.
     *
     * @param consumer Climate parameters and biome key consumer
     * @param ci Mixin callback info
     */
    @Inject(at = @At("RETURN"), method = "addUndergroundBiomes")
    public void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, CallbackInfo ci) {
        ResourceKey<Biome> spiderCave = ResourceKey.create(Registries.BIOME, new ResourceLocation(RegistryManager.MOD_ID, "spider_cave"));
        Climate.ParameterPoint climate = Climate.parameters(
            Climate.Parameter.span(TEMPERATURE_MIN, TEMPERATURE_MAX),
            Climate.Parameter.span(HUMIDITY_MIN, HUMIDITY_MAX),
            Climate.Parameter.span(CONTINENTALNESS_MIN, CONTINENTALNESS_MAX),
            Climate.Parameter.span(EROSION_MIN, EROSION_MAX),
            Climate.Parameter.span(DEPTH_MIN, DEPTH_MAX),
            Climate.Parameter.span(WEIRDNESS_MIN, WEIRDNESS_MAX),
            0.0f
        );
        consumer.accept(Pair.of(climate, spiderCave));
    }
}