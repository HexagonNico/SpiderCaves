package io.github.hexagonnico.spidercaves.blocks;

import io.github.hexagonnico.spidercaves.RegistryManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Mod chest block entity.
 * Needed because vanilla chests are weird.
 *
 * @author Nico
 */
public class ModChestBlockEntity extends ChestBlockEntity {

    /**
     * Constructs a mod chest block entity.
     * Uses {@link RegistryManager#WEB_COVERED_CHEST_ENTITY}.
     *
     * @param blockPos Block pos
     * @param blockState Block state
     */
    public ModChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        // TODO: Replace with generic MOD_CHEST_BLOCK_ENTITY
        super(RegistryManager.WEB_COVERED_CHEST_ENTITY.get(), blockPos, blockState);
    }
}
