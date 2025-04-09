package dev.grosik.create_storage.storageunit;

import dev.grosik.create_storage.CreateStorage;
import dev.grosik.create_storage.init.BlockEntityTypes;
import dev.grosik.create_storage.networkunit.NetworkUnitBlockEntity;
import dev.grosik.create_storage.storagecable.StorageCableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

public class StorageUnitBlockEntity extends BlockEntity {
    private BlockPos network;
    private ItemStackHandler inventory;
    private LazyOptional<ItemStackHandler> optional;
    public StorageUnitBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypes.STORAGE_UNIT.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        if(inventory != null) {
            CompoundTag modded = tag.getCompound(CreateStorage.MODID);
            inventory.deserializeNBT(modded.getCompound("Inventory"));
        }

        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        CompoundTag modded = tag.getCompound(CreateStorage.MODID);
        if(inventory != null) {
            modded.put("Inventory", inventory.serializeNBT());
        } else {
            modded.remove("Inventory");
        }

        super.saveAdditional(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    public void setNetwork(StorageCableBlockEntity cable) {
        if(cable.getNetwork() == null) {
            if(network != null && level.getBlockEntity(network) instanceof NetworkUnitBlockEntity nu) {
                nu.removeSU(level, this.getBlockPos());
                network = null;
                inventory = null;
                optional = null;

                CompoundTag nbt = super.getUpdateTag();
                CompoundTag modded = nbt.getCompound(CreateStorage.MODID);
                modded.remove("Inventory");
            }
            return;
        }
        network = cable.getNetwork();
        if(level.getBlockEntity(network) instanceof NetworkUnitBlockEntity nu) {
            nu.registerSU(level, this.getBlockPos());
            inventory = nu.getInventory();
            optional = nu.getOptional();
        }
    }
}