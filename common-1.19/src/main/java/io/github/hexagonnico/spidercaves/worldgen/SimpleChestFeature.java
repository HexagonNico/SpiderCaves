package io.github.hexagonnico.spidercaves.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.jetbrains.annotations.NotNull;

/**
 * Feature representing a simple chest with loot.
 * Can be used as a {@code type} for {@code configured_feature} json files.
 *
 * @author Nico
 */
public class SimpleChestFeature extends Feature<SimpleChestConfiguration> {

    /**
     * Constructs a simple chest feature.
     */
    public SimpleChestFeature() {
        super(SimpleChestConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<SimpleChestConfiguration> featurePlaceContext) {
        WorldGenLevel world = featurePlaceContext.level();
        BlockPos pos = featurePlaceContext.origin();
        // Behold! The ugliest if-statement you've ever seen!
        if(!world.getBlockState(pos.below()).isAir()
                && world.getBlockState(pos.above()).isAir()
                && (world.getBlockState(pos.north()).isAir()
                || world.getBlockState(pos.south()).isAir()
                || world.getBlockState(pos.west()).isAir()
                || world.getBlockState(pos.east()).isAir())) {
            SimpleChestConfiguration configuration = featurePlaceContext.config();
            RandomSource random = featurePlaceContext.random();
            BlockState chest = configuration.chest().getState(random, pos);
            world.setBlock(pos, chest, 2);
            RandomizableContainerBlockEntity.setLootTable(world, random, pos, configuration.lootTable());
            return true;
        }
        return false;
    }
}
