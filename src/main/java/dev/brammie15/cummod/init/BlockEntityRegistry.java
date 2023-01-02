package dev.brammie15.cummod.init;

import dev.brammie15.cummod.CumMod;
import dev.brammie15.cummod.blocks.entity.CumCentrifugeBlockEntity;
import dev.brammie15.cummod.blocks.entity.EndlessCubeBlockEntity;
import io.netty.util.Attribute;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CumMod.MODID);

    public static final RegistryObject<BlockEntityType<EndlessCubeBlockEntity>> ENDLESS_CUBE = BLOCK_ENTITIES.register("endless_cube", () -> BlockEntityType.Builder.of(EndlessCubeBlockEntity::new, BlockInit.ENDLESS_CUBE.get()).build(null));
    public static final RegistryObject<BlockEntityType<CumCentrifugeBlockEntity>> CUM_CENTRIFUGE = BLOCK_ENTITIES.register("cum_centrifuge", () -> BlockEntityType.Builder.of(CumCentrifugeBlockEntity::new, BlockInit.CUM_CENTRIFUGE.get()).build(null));
}
