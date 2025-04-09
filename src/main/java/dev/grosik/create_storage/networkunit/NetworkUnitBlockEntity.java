package dev.grosik.create_storage.networkunit;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import dev.grosik.create_storage.CreateStorage;
import dev.grosik.create_storage.init.BlockEntityTypes;
import dev.grosik.create_storage.storageunit.StorageUnitBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class NetworkUnitBlockEntity extends KineticBlockEntity {
    private static int slots = 0;
    private final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            NetworkUnitBlockEntity.this.setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> optional = LazyOptional.of(() -> this.inventory);
    private boolean active = false;

    public NetworkUnitBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypes.NETWORK_UNIT.get(), pos, state);
    }

    @Override
    public void onSpeedChanged(float prevSpeed) {
        super.onSpeedChanged(prevSpeed);
        active = getSpeed() >= 64;
    }

    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        CompoundTag modded = tag.getCompound(CreateStorage.MODID);
        inventory.deserializeNBT(modded.getCompound("Inventory"));

        super.read(tag, clientPacket);
    }

    @Override
    public void write(CompoundTag tag, boolean clientPacket) {
        CompoundTag modded = tag.getCompound(CreateStorage.MODID);
        modded.put("Inventory", inventory.serializeNBT());

        super.write(tag, clientPacket);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        write(nbt, false);
        return nbt;
    }

    public boolean isActive() {
        return active;
    }

    public int registerSU(Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if(be instanceof StorageUnitBlockEntity su) {
            slots += 9;
            inventory.setSize(slots);
            return slots;
        }
        return 0;
    }

    public int removeSU(Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if(be instanceof StorageUnitBlockEntity su) {
            for(int i = slots; i > slots-9; i--) {
                ItemStack stack = inventory.getStackInSlot(i);
                inventory.setStackInSlot(i, ItemStack.EMPTY);
                var entity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
            }

            slots -= 9;
            inventory.setSize(slots);
            return slots;
        }
        return 0;
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }
    public LazyOptional<ItemStackHandler> getOptional() {
        return optional;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) return this.optional.cast();
        return super.getCapability(cap);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.optional.invalidate();
    }
}
