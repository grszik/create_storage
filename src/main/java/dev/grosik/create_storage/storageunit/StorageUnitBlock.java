package dev.grosik.create_storage.storageunit;

import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import dev.grosik.create_storage.init.BlockEntityTypes;
import dev.grosik.create_storage.networkunit.NetworkUnitBlockEntity;
import dev.grosik.create_storage.storagecable.StorageCableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class StorageUnitBlock  extends Block implements EntityBlock, IBE<StorageUnitBlockEntity> {

    public StorageUnitBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return BlockEntityTypes.STORAGE_UNIT.get().create(pos, state);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if(be instanceof StorageUnitBlockEntity se) {

            }
        }
        return super.use(state, level, pos, player, hand, result);
    }

    @Override
    public Class<StorageUnitBlockEntity> getBlockEntityClass() {
        return StorageUnitBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends StorageUnitBlockEntity> getBlockEntityType() {
        return BlockEntityTypes.STORAGE_UNIT.get();
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state,level,pos,oldState,isMoving);
        if(level.isClientSide()) return;
        BlockEntity og = level.getBlockEntity(pos);
        if(og instanceof StorageUnitBlockEntity oc) {
            for (Direction direction : Direction.values()) {
                BlockPos np = pos.relative(direction);
                BlockEntity be = level.getBlockEntity(np);
                if (be instanceof StorageCableBlockEntity nc) {
                    oc.setNetwork(nc);
                }
            }
        }
    }

    @Override
    public void neighborChanged(BlockState state, net.minecraft.world.level.Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide()) {
            BlockEntity og = level.getBlockEntity(pos);
            if(og instanceof StorageUnitBlockEntity oc) {
                for (Direction direction : Direction.values()) {
                    BlockPos np = pos.relative(direction);
                    BlockEntity be = level.getBlockEntity(np);
                    if (be instanceof StorageCableBlockEntity nc) {
                        oc.setNetwork(nc);
                    }
                }
                og.setChanged();
            }
        }
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
    }
}