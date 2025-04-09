package dev.grosik.create_storage.storagecable;

import dev.grosik.create_storage.networkunit.NetworkUnitBlockEntity;
import dev.grosik.create_storage.storageunit.StorageUnitBlock;
import dev.grosik.create_storage.storageunit.StorageUnitBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public class StorageCableBlock extends Block implements EntityBlock { // Implement EntityBlock if you use a Tile Entity

    public StorageCableBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    // Optional: Implement neighbor changed logic to potentially trigger network updates

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state,level,pos,oldState,isMoving);
        if(level.isClientSide()) return;
        BlockEntity og = level.getBlockEntity(pos);
        if(og instanceof StorageCableBlockEntity oc) {
            for (Direction direction : Direction.values()) {
                BlockPos np = pos.relative(direction);
                BlockEntity be = level.getBlockEntity(np);
                if (be instanceof StorageCableBlockEntity nc) {
                    oc.checkCable(nc);
                } else if(be instanceof NetworkUnitBlockEntity nu) {
                    oc.setNetwork(nu);
                } else if(be instanceof StorageUnitBlockEntity su) {
                    for (Direction d : Direction.values()) {
                        BlockPos cp = pos.relative(direction);
                        BlockEntity cbe = level.getBlockEntity(cp);
                        if(cp == pos) continue;
                        if (cbe instanceof StorageCableBlockEntity nc) {
                            oc.checkCable(nc);
                        } else if(cbe instanceof NetworkUnitBlockEntity nu) {
                            oc.setNetwork(nu);
                        }
                    }
                }
            }
            og.setChanged();
        }
    }

    @Override
    public void neighborChanged(BlockState state, net.minecraft.world.level.Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide()) {
            BlockEntity og = level.getBlockEntity(pos);
            if(og instanceof StorageCableBlockEntity oc) {
                for (Direction direction : Direction.values()) {
                    BlockPos np = pos.relative(direction);
                    BlockEntity be = level.getBlockEntity(np);
                    if (be instanceof StorageCableBlockEntity nc) {
                        oc.checkCable(nc);
                    } else if(be instanceof NetworkUnitBlockEntity nu) {
                        oc.setNetwork(nu);
                    } else if(be instanceof StorageUnitBlockEntity su) {
                        for (Direction d : Direction.values()) {
                            BlockPos cp = pos.relative(direction);
                            BlockEntity cbe = level.getBlockEntity(cp);
                            if(cp == pos) continue;
                            if (cbe instanceof StorageCableBlockEntity nc) {
                                oc.checkCable(nc);
                            } else if(cbe instanceof NetworkUnitBlockEntity nu) {
                                oc.setNetwork(nu);
                            }
                        }
                    }
                }
                og.setChanged();
            }
        }
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
    }
}