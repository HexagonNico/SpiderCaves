package io.github.hexagonnico.spidercaves;

import io.github.hexagonnico.spidercaves.blocks.ModChestBlock;
import io.github.hexagonnico.spidercaves.blocks.ModChestBlockEntity;
import io.github.hexagonnico.spidercaves.blocks.SpiderEggBlock;
import io.github.hexagonnico.spidercaves.entities.BlackRecluse;
import io.github.hexagonnico.spidercaves.items.SpiderArmorItem;
import io.github.hexagonnico.spidercaves.worldgen.SimpleChestFeature;
import io.github.phantomloader.library.ModEntryPoint;
import io.github.phantomloader.library.registry.ModRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

public final class SpiderCaves {

    private static final ModRegistry REGISTRY = ModRegistry.instantiate("spider_caves");

    public static final Supplier<Item> SPIDER_FANG = REGISTRY.registerItem("spider_fang");

    public static final Supplier<SpiderArmorItem> SPIDER_HELMET = REGISTRY.registerItem("spider_helmet", () -> new SpiderArmorItem(ArmorItem.Type.HELMET));
    public static final Supplier<SpiderArmorItem> SPIDER_CHESTPLATE = REGISTRY.registerItem("spider_chestplate", () -> new SpiderArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final Supplier<SpiderArmorItem> SPIDER_LEGGINGS = REGISTRY.registerItem("spider_leggings", () -> new SpiderArmorItem(ArmorItem.Type.LEGGINGS));
    public static final Supplier<SpiderArmorItem> SPIDER_BOOTS = REGISTRY.registerItem("spider_boots", () -> new SpiderArmorItem(ArmorItem.Type.BOOTS));

    public static final Supplier<ModChestBlock> WEB_COVERED_CHEST = REGISTRY.registerBlockVariantAndItem("web_covered_chest", ModChestBlock::new, () -> Blocks.CHEST);
    public static final Supplier<BlockEntityType<ModChestBlockEntity>> CHEST_ENTITY = REGISTRY.registerBlockEntity("web_covered_chest", ModChestBlockEntity::new, WEB_COVERED_CHEST);

    public static final Supplier<SimpleChestFeature> SIMPLE_CHEST_FEATURE = REGISTRY.registerFeature("simple_chest", SimpleChestFeature::new);

    public static final Supplier<EntityType<BlackRecluse>> BLACK_RECLUSE = REGISTRY.registerEntity("black_recluse", EntityType.Builder.of(BlackRecluse::new, MobCategory.MONSTER).sized(1.4f, 0.9f).clientTrackingRange(8));
    public static final Supplier<SpawnEggItem> BLACK_RECLUSE_SPAWN_EGG = REGISTRY.registerSpawnEgg("black_recluse_spawn_egg", BLACK_RECLUSE::get, 0x000000, 0xFF0000);

    public static final Supplier<SpiderEggBlock> SPIDER_EGG = REGISTRY.registerBlockAndItem("spider_egg", () -> new SpiderEggBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).strength(0.5f).sound(SoundType.METAL).noOcclusion().noLootTable().forceSolidOn().pushReaction(PushReaction.DESTROY)));

    public static String modId() {
        return REGISTRY.mod;
    }

    @ModEntryPoint
    public static void register() {
        REGISTRY.register();
    }
}
