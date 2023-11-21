package io.github.hexagonnico.spidercaves.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record SimpleChestConfiguration(BlockStateProvider chest, ResourceLocation lootTable) implements FeatureConfiguration {

    public static final Codec<SimpleChestConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        BlockStateProvider.CODEC.fieldOf("chest").forGetter(SimpleChestConfiguration::chest),
        ResourceLocation.CODEC.fieldOf("loot_table").forGetter(SimpleChestConfiguration::lootTable)
    ).apply(instance, SimpleChestConfiguration::new));
}
