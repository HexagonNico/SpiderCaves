package io.github.hexagonnico.spidercaves.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.hexagonnico.spidercaves.RegistryManager;
import io.github.hexagonnico.spidercaves.entities.BlackRecluse;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Black recluse renderer.
 * Adds the black recluse texture to the default spider renderer and changes the scale.
 *
 * @author Nico
 */
public class BlackRecluseRenderer extends SpiderRenderer<BlackRecluse> {

    /** Location pointing to the texture */
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(RegistryManager.MOD_ID, "textures/entity/black_recluse.png");

    /**
     * Constructs the renderer.
     *
     * @param context Rendering context
     */
    public BlackRecluseRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(@NotNull BlackRecluse blackRecluse, @NotNull PoseStack poseStack, float v) {
        poseStack.scale(1.1f, 1.1f, 1.1f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BlackRecluse blackRecluse) {
        return TEXTURE_LOCATION;
    }
}
