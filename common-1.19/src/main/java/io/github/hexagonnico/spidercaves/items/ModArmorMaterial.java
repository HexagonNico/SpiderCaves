package io.github.hexagonnico.spidercaves.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Mod armor material. Class needed to implement {@link ArmorMaterial}.
 *
 * @author Nico
 */
public class ModArmorMaterial implements ArmorMaterial {

    /** Spider armor material */
    public static final ModArmorMaterial SPIDER_ARMOR = new ModArmorMaterial("spider", new StatsMap(15 * 13, 15 * 16, 15 * 15, 15 * 11), new StatsMap(2, 5, 4, 1), 17, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.EMPTY);

    /** Set name */
    private final String name;
    /** Armor durability by piece */
    private final StatsMap durabilityMap;
    /** Armor protection by piece */
    private final StatsMap protectionMap;
    /** Enchantment value */
    private final int enchantmentValue;
    /** Armor equip sound */
    private final SoundEvent sound;
    /** Armor toughness attribute */
    private final float toughness;
    /** Knockback resistance attribute */
    private final float knockbackResistance;
    /** Armor repair ingredient */
    private final Supplier<Ingredient> repairIngredient;

    /**
     * Constructs a mod armor material.
     *
     * @param name Set name
     * @param durabilityMap Armor durability by piece
     * @param protectionMap Armor protection by piece
     * @param enchantmentValue Enchantment value
     * @param sound Armor equip sound
     * @param toughness Armor toughness attribute
     * @param knockbackResistance Knockback resistance attribute
     * @param repairIngredient Armor repair ingredient
     */
    public ModArmorMaterial(String name, StatsMap durabilityMap, StatsMap protectionMap, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMap = durabilityMap;
        this.protectionMap = protectionMap;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(@NotNull ArmorItem.Type type) {
        return this.durabilityMap.forType(type);
    }

    @Override
    public int getDefenseForType(@NotNull ArmorItem.Type type) {
        return this.protectionMap.forType(type);
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    /**
     * Utility record used to get armor stats by piece.
     *
     * @param helmet Value for the helmet
     * @param chestplate Value for the chestplate
     * @param leggings Value for the leggings
     * @param boots Value for the boots
     */
    public record StatsMap(int helmet, int chestplate, int leggings, int boots) {

        /**
         * Gets the correct value according to the given type.
         *
         * @param type Armor type
         * @return The value for the given type
         */
        public int forType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> this.helmet();
                case CHESTPLATE -> this.chestplate();
                case LEGGINGS -> this.leggings();
                case BOOTS -> this.boots();
            };
        }
    }
}
