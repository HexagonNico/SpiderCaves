package io.github.hexagonnico.spidercaves.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Spider armor item. Class needed to override {@link SpiderArmorItem#appendHoverText(ItemStack, Level, List, TooltipFlag)}.
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
}
