package dev.brammie15.cummod.init;

import dev.brammie15.cummod.CumMod;
import dev.brammie15.cummod.blocks.CumCentrifugeBlock;
import dev.brammie15.cummod.blocks.EndlessCubeBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CumMod.MODID);

    public static final RegistryObject<Block> EXAMPLE_BLOCK = register("example_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).friction(0.5f).strength(1f, 5f).requiresCorrectToolForDrops()),
            new Item.Properties().tab(CumMod.TAB));

    public static final RegistryObject<EndlessCubeBlock> ENDLESS_CUBE = register("endless_cube",
            () -> new EndlessCubeBlock(BlockBehaviour.Properties.of(Material.AMETHYST).friction(0.5f).strength(1f, 5f).requiresCorrectToolForDrops().noOcclusion()),
            new Item.Properties());

    public static final RegistryObject<CumCentrifugeBlock> CUM_CENTRIFUGE = register("cum_centrifuge",
            () -> new CumCentrifugeBlock(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()),
            new Item.Properties());
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> supplier, Item.Properties properties) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
        return block;
    }
}
