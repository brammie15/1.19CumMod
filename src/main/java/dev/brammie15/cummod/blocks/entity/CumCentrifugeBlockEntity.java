package dev.brammie15.cummod.blocks.entity;

import com.mojang.serialization.Decoder;
import dev.brammie15.cummod.init.BlockEntityRegistry;
import dev.brammie15.cummod.init.ItemInit;
import dev.brammie15.cummod.screen.CumCentrifugeMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.example.registry.ItemRegistry;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.builder.RawAnimation;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class CumCentrifugeBlockEntity extends BlockEntity implements IAnimatable, MenuProvider {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    private final ItemStackHandler itemHandler = new ItemStackHandler(4){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    public boolean isRunning = false;
    public boolean isDeployed = false;

    public CumCentrifugeBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityRegistry.CUM_CENTRIFUGE.get(),
                blockPos,
                blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> isRunning ? 1 : 0;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> isRunning = value == 1;
                }
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<CumCentrifugeBlockEntity>(this, "controller", 0, this::predicate));
    }



    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController<E> controller = event.getController();
        if(!isDeployed) {
            boolean isDeploying = controller.getAnimationState().equals(AnimationState.Running);
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.cum_centrifuge.deploy", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
            if (!isDeploying) {
                isDeployed = true;
            }
        } else if(isRunning){
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.cum_centrifuge.running", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.cum_centrifuge.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public void startCentrifuge() {
        isRunning = !isRunning;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Cum Centrifuge");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new CumCentrifugeMenu(id, inventory, this, this.data);
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, CumCentrifugeBlockEntity blockEntity) {
        if(level.isClientSide){
            return;
        }

        SimpleContainer inventory = new SimpleContainer(blockEntity.itemHandler.getSlots());
        for (int i = 0; i < blockEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, blockEntity.itemHandler.getStackInSlot(i));
        }

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if(stack.getItem() == ItemInit.RAW_CUM.get()){
                blockEntity.setRunning(true);
                if(stack.getCount() >= 2){
                    stack.shrink(2);
                    inventory.setItem(i, stack);
                    inventory.setItem(5, new ItemStack(Items.DIAMOND));
                    blockEntity.setRunning(false);
                }
            }
        }


    }

    private void setRunning(boolean b) {
        this.isRunning = b;
    }
}

