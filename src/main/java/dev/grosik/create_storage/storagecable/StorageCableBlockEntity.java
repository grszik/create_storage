package dev.grosik.create_storage.storagecable;

import dev.grosik.create_storage.CreateStorage;
import dev.grosik.create_storage.init.BlockEntityTypes;
import dev.grosik.create_storage.init.Blocks;
import dev.grosik.create_storage.networkunit.NetworkUnitBlockEntity;
import dev.grosik.create_storage.storageunit.StorageUnitBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Logger;

public class StorageCableBlockEntity extends BlockEntity {

    private BlockPos network;
    private boolean connected = false;

    public StorageCableBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(BlockEntityTypes.STORAGE_CABLE.get(), worldPosition, blockState);
    }

    // ... (getSpeed, setSpeed, isSpeedRequirementMet, getMinimumRequiredSpeed, isNetworkActive, updateNetworkState)

    public void checkCable(StorageCableBlockEntity cable) {
        if(cable.getNetwork() == null) return;
        network = cable.getNetwork();
    }
    public void setNetwork(NetworkUnitBlockEntity nu) {
        if(!nu.isActive()) return;
        network = nu.getBlockPos();
        Logger.getLogger(CreateStorage.MODID).warning("Network at %d/%d/%d works: %s".formatted(network.getX(), network.getY(), network.getZ(), (nu.getCapability(ForgeCapabilities.ITEM_HANDLER) == null ? "y" : "n").toString()));
    }

    public BlockPos getNetwork() {
        return network;
    }

    // ... (saveAdditional, load, invalidateCaps)
}