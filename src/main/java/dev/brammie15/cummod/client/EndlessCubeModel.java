package dev.brammie15.cummod.client;

import dev.brammie15.cummod.CumMod;
import dev.brammie15.cummod.blocks.entity.EndlessCubeBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EndlessCubeModel extends AnimatedGeoModel<EndlessCubeBlockEntity> {
    @Override
    public ResourceLocation getModelResource(EndlessCubeBlockEntity object) {
        return new ResourceLocation(CumMod.MODID, "geo/endless_cube.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EndlessCubeBlockEntity object) {
        return new ResourceLocation(CumMod.MODID, "textures/blocks/endless_cube.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EndlessCubeBlockEntity animatable) {
        return new ResourceLocation(CumMod.MODID, "animations/endless_cube.animation.json");
    }
}
