package io.github.hexagonnico.spidercaves.mixin;

import io.github.hexagonnico.spidercaves.RegistryManager;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin class for {@link Sheets}.
 * Used to add the chest texture to the chest renderer.
 *
 * @author Nico
 */
@Mixin(Sheets.class)
@SuppressWarnings("unused")
public class SheetsMixin {

    /** Chest texture location */
    private static final Material CHEST_MATERIAL = new Material(Sheets.CHEST_SHEET, new ResourceLocation(RegistryManager.MOD_ID, "chest/web_covered"));
    /** Left chest texture location */
    private static final Material CHEST_MATERIAL_LEFT = new Material(Sheets.CHEST_SHEET, new ResourceLocation(RegistryManager.MOD_ID, "chest/web_covered_left"));
    /** Right chest texture location */
    private static final Material CHEST_MATERIAL_RIGHT = new Material(Sheets.CHEST_SHEET, new ResourceLocation(RegistryManager.MOD_ID, "chest/web_covered_right"));

    /**
     * Returns {@link SheetsMixin#CHEST_MATERIAL} if the given chest is a {@link RegistryManager#WEB_COVERED_CHEST}.
     *
     * @param blockEntity Block entity
     * @param type Single, left, or right
     * @param christmas True for Christmas chests
     * @param callbackInfo Mixin callback info
     */
    @Inject(at = @At("HEAD"), method = "chooseMaterial", cancellable = true)
    private static void chooseMaterial(BlockEntity blockEntity, ChestType type, boolean christmas, CallbackInfoReturnable<Material> callbackInfo) {
        if(blockEntity.getBlockState().is(RegistryManager.WEB_COVERED_CHEST.get())) {
            callbackInfo.setReturnValue(switch (type) {
                case SINGLE -> CHEST_MATERIAL;
                case LEFT -> CHEST_MATERIAL_LEFT;
                case RIGHT -> CHEST_MATERIAL_RIGHT;
            });
        }
    }
}
