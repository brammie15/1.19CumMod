package dev.brammie15.cummod.events;

import dev.brammie15.cummod.CumMod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CumMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnCatEvent {

    @SubscribeEvent
    public static void onPlayerTick(final TickEvent.PlayerTickEvent event) {
        if (!event.player.level.isClientSide) {
            Entity cat = EntityType.CAT.create(event.player.level);
            cat.setPos(event.player.getX(), event.player.getY(), event.player.getZ());

//                event.player.level.addFreshEntity(cat);
        }

    }
}
