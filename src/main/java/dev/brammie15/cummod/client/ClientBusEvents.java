package dev.brammie15.cummod.client;

import dev.brammie15.cummod.CumMod;
import dev.brammie15.cummod.init.BlockEntityRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CumMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientBusEvents {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        // Register renderers here
        event.registerBlockEntityRenderer(BlockEntityRegistry.ENDLESS_CUBE.get(), EndlessCubeRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.CUM_CENTRIFUGE.get(), CumCentrifugeRenderer::new);
    }


}
