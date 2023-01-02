package dev.brammie15.cummod.client;

import dev.brammie15.cummod.CumMod;
import dev.brammie15.cummod.blocks.CumCentrifugeBlock;
import dev.brammie15.cummod.blocks.entity.CumCentrifugeBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CumCentrifugeModel extends AnimatedGeoModel<CumCentrifugeBlockEntity> {
    @Override
    public ResourceLocation getModelResource(CumCentrifugeBlockEntity object) {
        return new ResourceLocation(CumMod.MODID, "geo/cum_centrifuge.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CumCentrifugeBlockEntity object) {
        return new ResourceLocation(CumMod.MODID, "textures/blocks/cum_centrifuge.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CumCentrifugeBlockEntity animatable) {
        return new ResourceLocation(CumMod.MODID, "animations/cum_centrifuge.animation.json");
    }
}
