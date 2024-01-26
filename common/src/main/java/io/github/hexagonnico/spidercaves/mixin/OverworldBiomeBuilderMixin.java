package io.github.hexagonnico.spidercaves.mixin;

import com.mojang.datafixers.util.Pair;
import io.github.hexagonnico.spidercaves.SpiderCaves;
import io.github.phantomloader.library.platform.PlatformHelper;
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

    private static final Climate.Parameter TEMPERATURE = Climate.Parameter.span(-0.9f, 0.3f);
    private static final Climate.Parameter HUMIDITY = Climate.Parameter.span(-1.0f, -0.4f);
    private static final Climate.Parameter CONTINENTALNESS = Climate.Parameter.span(0.5f, 1.0f);
    private static final Climate.Parameter EROSION = Climate.Parameter.span(-1.0f, 1.0f);
    private static final Climate.Parameter DEPTH = Climate.Parameter.span(0.2f, 0.9f);
    private static final Climate.Parameter WEIRDNESS = Climate.Parameter.span(0.7f, 1.0f);

    /**
     * Adds the spider cave biome to the overworld biome manager.
     *
     * @param consumer Climate parameters and biome key consumer
     * @param ci Mixin callback info
     */
    @Inject(at = @At("RETURN"), method = "addUndergroundBiomes")
    public void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, CallbackInfo ci) {
        if(!PlatformHelper.isModLoaded("terrablender")) {
            ResourceKey<Biome> undergroundJungleKey = ResourceKey.create(Registries.BIOME, new ResourceLocation(SpiderCaves.modId(), "spider_cave"));
            Climate.ParameterPoint undergroundJungleClimate = Climate.parameters(TEMPERATURE, HUMIDITY, CONTINENTALNESS, EROSION, DEPTH, WEIRDNESS, 0.0f);
            consumer.accept(Pair.of(undergroundJungleClimate, undergroundJungleKey));
        }
    }
}