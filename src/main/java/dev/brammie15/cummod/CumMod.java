package dev.brammie15.cummod;

import dev.brammie15.cummod.init.BlockEntityRegistry;
import dev.brammie15.cummod.init.BlockInit;
import dev.brammie15.cummod.init.ItemInit;
import dev.brammie15.cummod.init.MenuInit;
import dev.brammie15.cummod.screen.CumCentrifugeScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;

@Mod(CumMod.MODID)
public class CumMod {
    public static final String MODID = "cummod";

    public CumMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockEntityRegistry.BLOCK_ENTITIES.register(bus);
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        MenuInit.MENUS.register(bus);
    }
    public static final CreativeModeTab TAB = new CreativeModeTab(MODID) {
        @Override
        public @NotNull
        ItemStack makeIcon() {
            return ItemInit.RAW_CUM.get().getDefaultInstance();
        }
    };


    @Mod.EventBusSubscriber(modid = CumMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents{

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            MenuScreens.register(MenuInit.CUM_CENTRIFUGE_MENU.get(), CumCentrifugeScreen::new);
        }
    }

}


