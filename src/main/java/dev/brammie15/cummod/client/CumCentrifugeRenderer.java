package dev.brammie15.cummod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.brammie15.cummod.blocks.entity.CumCentrifugeBlockEntity;
import dev.brammie15.cummod.blocks.entity.EndlessCubeBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class CumCentrifugeRenderer extends GeoBlockRenderer<CumCentrifugeBlockEntity> {
    public CumCentrifugeRenderer(BlockEntityRendererProvider.Context rendererProvider) {
        super(rendererProvider, new CumCentrifugeModel());
    }

    @Override
    public RenderType getRenderType(CumCentrifugeBlockEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
