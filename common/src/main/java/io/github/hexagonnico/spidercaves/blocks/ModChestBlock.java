package io.github.hexagonnico.spidercaves.blocks;

import io.github.hexagonnico.spidercaves.SpiderCaves;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * Mod chest block.
 * Needed because vanilla chests are weird.
 *
 * @author Nico
 */
public class ModChestBlock extends ChestBlock {

    @SuppressWarnings({"Convert2MethodRef"})
    public ModChestBlock(Properties properties) {
        // Qualifier cannot be used because of circular dependency
        super(properties, () -> SpiderCaves.CHEST_ENTITY.get());
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new ModChestBlockEntity(blockPos, blockState);
    }
}
