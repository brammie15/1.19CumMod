package dev.brammie15.cummod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.brammie15.cummod.blocks.entity.EndlessCubeBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class EndlessCubeRenderer extends GeoBlockRenderer<EndlessCubeBlockEntity> {
    public EndlessCubeRenderer(BlockEntityRendererProvider.Context rendererProvider) {
        super(rendererProvider, new EndlessCubeModel());
    }

    @Override
    public RenderType getRenderType(EndlessCubeBlockEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
