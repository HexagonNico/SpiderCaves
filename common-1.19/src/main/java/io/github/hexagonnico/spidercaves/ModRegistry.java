package io.github.hexagonnico.spidercaves;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Interface used to implement a mod registry.
 * Different mod loaders should implement this interface to provide their own way of registering things.
 * The registry should also be added to {@code META-INF/services} for it to be used in {@link RegistryManager}.
 *
 * @author Nico
 */
public interface ModRegistry {

    /**
     * Registers an {@link Item}.
     *
     * @param name The item's registry name
     * @param item A supplier returning the item to register
     * @return A supplier returning the registered item
     * @param <T> The item's class
     */
    <T extends Item> Supplier<T> registerItem(String name, Supplier<T> item);

    /**
     * Registers an {@link Item} with the given properties.
     *
     * @param name The item's registry name
     * @param properties The item's properties
     * @return A supplier returning the registered item
     */
    default Supplier<Item> registerItem(String name, Item.Properties properties) {
        return this.registerItem(name, () -> new Item(properties));
    }

    /**
     * Registers an {@link Item} with the default properties.
     *
     * @param name The item's registry name
     * @return A supplier returning the registered item
     */
    default Supplier<Item> registerItem(String name) {
        return this.registerItem(name, new Item.Properties());
    }

    /**
     * Registers a {@link Block}.
     *
     * @param name The block's registry name
     * @param block A supplier returning the block to register
     * @return A supplier returning the registered block
     * @param <T> The block's class
     */
    <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block);

    /**
     * Registers a {@link BlockItem} from the given block.
     *
     * @param name The item's registry name
     * @param block The block on which this item is based
     * @return A supplier returning the registered item
     */
    default Supplier<BlockItem> registerBlockItem(String name, Supplier<? extends Block> block) {
        return this.registerItem(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    /**
     * Registers a {@link Block} and a {@link BlockItem}.
     * Combines {@link ModRegistry#registerBlock(String, Supplier)} and {@link ModRegistry#registerBlockItem(String, Supplier)}.
     *
     * @param name The block and the item's registry name
     * @param block A supplier returning the block to register
     * @return A supplier returning the registered block
     * @param <T> The block's class
     */
    default <T extends Block> Supplier<T> registerBlockAndItem(String name, Supplier<T> block) {
        block = this.registerBlock(name, block);
        this.registerBlockItem(name, block);
        return block;
    }

    /**
     * Registers a {@link Block} by copying another one and optionally a {@link BlockItem}.
     *
     * @param name The block and the item's registry name
     * @param base The block on which this one is based
     * @param registerItem If true, this method will also register an item using {@link ModRegistry#registerBlockItem(String, Supplier)}
     * @return A supplier returning the registered block
     */
    default Supplier<Block> registerBlockVariant(String name, Supplier<? extends Block> base, boolean registerItem) {
        Supplier<Block> block = this.registerBlock(name, () -> new Block(BlockBehaviour.Properties.copy(base.get())));
        if(registerItem) {
            this.registerBlockItem(name, block);
        }
        return block;
    }

    /**
     * Registers a {@link Block} by copying another one and a {@link BlockItem}.
     *
     * @param name The block and the item's registry name
     * @param base The block on which this one is based
     * @return A supplier returning the registered block
     */
    default Supplier<Block> registerBlockVariant(String name, Supplier<? extends Block> base) {
        return this.registerBlockVariant(name, base, true);
    }

    // TODO: Allow multiple blocks for block entities

    /**
     * Registers a {@link BlockEntityType}.
     *
     * @param name The block entity's registry name
     * @param block The block on which this block entity is based
     * @param blockEntity The block entity's constructor
     * @return A supplier returning the registered block entity
     * @param <T> The block entity's class
     */
    <T extends BlockEntity> Supplier<BlockEntityType<? extends T>> registerBlockEntity(String name, Supplier<? extends Block> block, BiFunction<BlockPos, BlockState, T> blockEntity);

    /**
     * Registers an {@link EntityType}.
     *
     * @param name The entity's registry name
     * @param builder The entity type builder
     * @return A supplier returning the registered entity
     * @param <T> The entity's class
     */
    <T extends Entity> Supplier<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder);

    /**
     * Registers a {@link SpawnEggItem} for the given entity.
     *
     * @param name The item's registry name
     * @param entity A supplier returning the entity spawned by this spawn egg
     * @param primaryColor The spawn egg's primary color
     * @param secondaryColor The spawn egg's secondary color
     * @return A supplier returning the registered item
     * @param <T> The entity's class
     */
    default <T extends Mob> Supplier<SpawnEggItem> registerSpawnEgg(String name, Supplier<EntityType<T>> entity, int primaryColor, int secondaryColor) {
        return this.registerItem(name, () -> new SpawnEggItem(entity.get(), primaryColor, secondaryColor, new Item.Properties()));
    }

    /**
     * Registers a {@link Feature} type.
     * Features can be used in the {@code type} parameter in {@code configured_feature} json files.
     *
     * @param name Name of the feature
     * @param feature Supplier returning the feature to register
     * @return The registered feature
     * @param <T> The feature's class
     */
    <T extends Feature<?>> Supplier<T> registerFeature(String name, Supplier<T> feature);

    /**
     * Finalizes the registration process by registering all the objects added to the registry.
     * Called from {@link RegistryManager#register()}.
     */
    void register();
}
