package dev.brammie15.cummod.init;

import dev.brammie15.cummod.CumMod;
import dev.brammie15.cummod.screen.CumCentrifugeMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, CumMod.MODID);

    public static final RegistryObject<MenuType<CumCentrifugeMenu>> CUM_CENTRIFUGE_MENU = registerMenuType(CumCentrifugeMenu::new, "cum_centrifuge_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

}
