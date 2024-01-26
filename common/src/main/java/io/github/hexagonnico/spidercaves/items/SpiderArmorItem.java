package io.github.hexagonnico.spidercaves.items;

import io.github.hexagonnico.spidercaves.SpiderCaves;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Spider armor item.
 * Class needed to override {@link SpiderArmorItem#appendHoverText(ItemStack, Level, List, TooltipFlag)}.
 *
 * @author Nico
 */
public class SpiderArmorItem extends ArmorItem {

    /**
     * Constructs a spider armor item.
     *
     * @param type Armor piece
     */
    public SpiderArmorItem(Type type) {
        super(ModArmorMaterial.SPIDER_ARMOR, type, new Properties());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level world, @NotNull List<Component> components, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, world, components, flag);
        components.add(Component.empty());
        components.add(Component.translatable("item.modifiers.full_set").withStyle(ChatFormatting.GRAY));
        components.add(Component.translatable("item.modifiers.spider_armor_full_set").withStyle(ChatFormatting.BLUE));
    }

    /**
     * Utility function used to check if an entity is wearing the full set of armor.
     *
     * @param entity The entity to check
     * @return True if the given entity is wearing the full set of armor, otherwise false
     */
    public static boolean isWearingFullSet(LivingEntity entity) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(SpiderCaves.SPIDER_HELMET.get())
            && entity.getItemBySlot(EquipmentSlot.CHEST).is(SpiderCaves.SPIDER_CHESTPLATE.get())
            && entity.getItemBySlot(EquipmentSlot.LEGS).is(SpiderCaves.SPIDER_LEGGINGS.get())
            && entity.getItemBySlot(EquipmentSlot.FEET).is(SpiderCaves.SPIDER_BOOTS.get());
    }
}
