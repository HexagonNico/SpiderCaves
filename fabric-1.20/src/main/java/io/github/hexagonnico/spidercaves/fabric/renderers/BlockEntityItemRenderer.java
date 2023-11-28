package io.github.hexagonnico.spidercaves.fabric.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Implements fabric's way of rendering block entity items.
 *
 * @author Nico
 */
public class BlockEntityItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {

    private final BlockEntity blockEntity;

    public BlockEntityItemRenderer(BiFunction<BlockPos, BlockState, BlockEntity> blockEntitySupplier, Supplier<? extends Block> blockSupplier) {
        this.blockEntity = blockEntitySupplier.apply(BlockPos.ZERO, blockSupplier.get().defaultBlockState());
    }


    @Override
    public void render(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(this.blockEntity, matrices, vertexConsumers, light, overlay);
    }
}
