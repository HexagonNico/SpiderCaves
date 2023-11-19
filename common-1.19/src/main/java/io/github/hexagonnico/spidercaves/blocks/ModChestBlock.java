package io.github.hexagonnico.spidercaves.blocks;

import io.github.hexagonnico.spidercaves.RegistryManager;
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

    /**
     * Constructs a mod chest block.
     * Uses {@link RegistryManager#WEB_COVERED_CHEST_ENTITY}.
     *
     * @param properties Block properties
     */
    public ModChestBlock(Properties properties) {
        // TODO: Replace with generic MOD_CHEST_BLOCK_ENTITY
        super(properties, RegistryManager.WEB_COVERED_CHEST_ENTITY);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new ModChestBlockEntity(blockPos, blockState);
    }
}
