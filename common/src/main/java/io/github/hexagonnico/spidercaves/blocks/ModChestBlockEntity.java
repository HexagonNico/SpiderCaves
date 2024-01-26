package io.github.hexagonnico.spidercaves.blocks;

import io.github.hexagonnico.spidercaves.SpiderCaves;
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

    public ModChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(SpiderCaves.CHEST_ENTITY.get(), blockPos, blockState);
    }
}
