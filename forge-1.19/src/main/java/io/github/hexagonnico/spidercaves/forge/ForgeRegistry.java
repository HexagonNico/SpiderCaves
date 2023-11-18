package io.github.hexagonnico.spidercaves.forge;

import io.github.hexagonnico.spidercaves.ModRegistry;
import io.github.hexagonnico.spidercaves.RegistryManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Forge implementation of the registry.
 *
 * @author Nico
 */
public class ForgeRegistry implements ModRegistry {

    /** Blocks deferred register */
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RegistryManager.MOD_ID);
    /** Items deferred register */
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RegistryManager.MOD_ID);
    /** Block entities deferred register */
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RegistryManager.MOD_ID);
    /** Entities deferred register */
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RegistryManager.MOD_ID);

    @Override
    public <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public <T extends BlockEntity> Supplier<BlockEntityType<? extends T>> registerBlockEntity(String name, Supplier<? extends Block> block, BiFunction<BlockPos, BlockState, T> blockEntity) {
        // Block entity types must be created here because BlockEntityType.BlockEntitySupplier has private access in the common module
        return BLOCK_ENTITIES.register(name, () -> BlockEntityType.Builder.of(blockEntity::apply, block.get()).build(null));
    }

//    @Override
//    public Supplier<BlockItem> registerBlockEntityItem(String name, Supplier<? extends Block> block, BiFunction<BlockPos, BlockState, ? extends BlockEntity> blockEntity) {
//        // Use forge's IClientItemExtensions to render block entity items
//        return this.registerItem(name, () -> new BlockEntityItem(block.get(), blockEntity::apply, new Item.Properties()));
//    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(name));
    }

    @Override
    public <T extends Mob> Supplier<SpawnEggItem> registerSpawnEgg(String name, Supplier<EntityType<T>> entity, int primaryColor, int secondaryColor) {
        // The constructor of SpawnEggItem is deprecated by forge
        return this.registerItem(name, () -> new ForgeSpawnEggItem(entity, primaryColor, secondaryColor, new Item.Properties()));
    }

    @Override
    public void register() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
        ITEMS.register(eventBus);
        ENTITIES.register(eventBus);
    }
}
