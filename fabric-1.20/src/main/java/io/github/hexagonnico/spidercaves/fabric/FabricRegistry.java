package io.github.hexagonnico.spidercaves.fabric;

import io.github.hexagonnico.spidercaves.ModRegistry;
import io.github.hexagonnico.spidercaves.RegistryManager;
import io.github.hexagonnico.spidercaves.entities.BlackRecluse;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Fabric implementation of the registry.
 *
 * @author Nico
 */
public class FabricRegistry implements ModRegistry {

    @Override
    public <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        T registered = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(RegistryManager.MOD_ID, name), block.get());
        return () -> registered;
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String name, Supplier<T> item) {
        T registered = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(RegistryManager.MOD_ID, name), item.get());
        return () -> registered;
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<? extends T>> registerBlockEntity(String name, Supplier<? extends Block> block, BiFunction<BlockPos, BlockState, T> blockEntity) {
        // Block entity types must be created here because BlockEntityType.BlockEntitySupplier has private access in the common module
        BlockEntityType<T> registered = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(RegistryManager.MOD_ID, name), FabricBlockEntityTypeBuilder.create(blockEntity::apply, block.get()).build());
        return () -> registered;
    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) {
        EntityType<T> registered = Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(RegistryManager.MOD_ID, name), builder.build(name));
        return () -> registered;
    }

    @Override
    public <T extends Feature<?>> Supplier<T> registerFeature(String name, Supplier<T> feature) {
        T registered = Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(RegistryManager.MOD_ID, name), feature.get());
        return () -> registered;
    }

    @Override
    public void register() {
        FabricDefaultAttributeRegistry.register(RegistryManager.BLACK_RECLUSE.get(), BlackRecluse.createAttributes());
    }
}
