package dev.brammie15.cummod.init;

import dev.brammie15.cummod.CumMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CumMod.MODID);

    public static final RegistryObject<Item> RAW_CUM = ITEMS.register("raw_cum", () -> new Item(new Item.Properties().tab(CumMod.TAB).food(new FoodProperties.Builder().alwaysEat().fast().nutrition(5).build())));
}
