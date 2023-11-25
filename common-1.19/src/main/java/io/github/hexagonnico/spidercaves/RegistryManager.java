package io.github.hexagonnico.spidercaves;

import io.github.hexagonnico.spidercaves.blocks.ModChestBlock;
import io.github.hexagonnico.spidercaves.blocks.ModChestBlockEntity;
import io.github.hexagonnico.spidercaves.blocks.SpiderEggBlock;
import io.github.hexagonnico.spidercaves.entities.BlackRecluse;
import io.github.hexagonnico.spidercaves.items.SpiderArmorItem;
import io.github.hexagonnico.spidercaves.worldgen.SimpleChestFeature;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.ServiceLoader;
import java.util.function.Supplier;

/**
 * Mod registry manager.
 * Abstracts the registration process across different mod loaders.
 *
 * @author Nico
 */
public final class RegistryManager {

    public static final String MOD_ID = "spider_caves";

    /** The mod registry of the current loader */
    private static final ModRegistry REGISTRY = ServiceLoader.load(ModRegistry.class).findFirst().orElseThrow();

    public static final Supplier<Item> SPIDER_FANG = REGISTRY.registerItem("spider_fang");

    public static final Supplier<ArmorItem> SPIDER_HELMET = REGISTRY.registerItem("spider_helmet", () -> new SpiderArmorItem(ArmorItem.Type.HELMET));
    public static final Supplier<ArmorItem> SPIDER_CHESTPLATE = REGISTRY.registerItem("spider_chestplate", () -> new SpiderArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final Supplier<ArmorItem> SPIDER_LEGGINGS = REGISTRY.registerItem("spider_leggings", () -> new SpiderArmorItem(ArmorItem.Type.LEGGINGS));
    public static final Supplier<ArmorItem> SPIDER_BOOTS = REGISTRY.registerItem("spider_boots", () -> new SpiderArmorItem(ArmorItem.Type.BOOTS));

    public static final Supplier<ModChestBlock> WEB_COVERED_CHEST = REGISTRY.registerBlockAndItem("web_covered_chest", () -> new ModChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST)));
    public static final Supplier<BlockEntityType<? extends ChestBlockEntity>> WEB_COVERED_CHEST_ENTITY = REGISTRY.registerBlockEntity("web_covered_chest", WEB_COVERED_CHEST, ModChestBlockEntity::new);

    public static final Supplier<SimpleChestFeature> SIMPLE_CHEST_FEATURE = REGISTRY.registerFeature("simple_chest", SimpleChestFeature::new);

    public static final Supplier<EntityType<BlackRecluse>> BLACK_RECLUSE = REGISTRY.registerEntity("black_recluse", EntityType.Builder.of(BlackRecluse::new, MobCategory.MONSTER).sized(1.4f, 0.9f).clientTrackingRange(8));
    public static final Supplier<SpawnEggItem> BLACK_RECLUSE_SPAWN_EGG = REGISTRY.registerSpawnEgg("black_recluse_spawn_egg", BLACK_RECLUSE, 0x000000, 0xFF0000);

    public static final Supplier<SpiderEggBlock> SPIDER_EGG = REGISTRY.registerBlockAndItem("spider_egg", () -> new SpiderEggBlock(BlockBehaviour.Properties.of(Material.EGG, MaterialColor.WOOL).strength(0.5f).sound(SoundType.METAL).noOcclusion().noLootTable()));

    /**
     * Finalizes the registration process by registering all the objects added to the registry.
     * Different mod loaders should call this method from their initializer.
     */
    public static void register() {
        REGISTRY.register();
    }
}
