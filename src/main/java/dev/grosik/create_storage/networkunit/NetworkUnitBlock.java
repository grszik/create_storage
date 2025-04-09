package dev.grosik.create_storage.networkunit;

import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import dev.grosik.create_storage.init.BlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.items.ItemStackHandler;

public class NetworkUnitBlock extends DirectionalKineticBlock implements IBE<NetworkUnitBlockEntity> {
    private static final BooleanProperty active = BooleanProperty.create("active");

    public NetworkUnitBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.FACING, Direction.NORTH).setValue(active, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(active));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        Direction preferred = getPreferredFacing(context);

        if ((context.getPlayer() != null && context.getPlayer().isShiftKeyDown()) || preferred == null)
            return super.getStateForPlacement(context);

        return defaultBlockState().setValue(FACING, preferred);
    }

    // IRotate:

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == state.getValue(FACING);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(FACING).getAxis();
    }

    @Override
    public Class<NetworkUnitBlockEntity> getBlockEntityClass() {
        return NetworkUnitBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends NetworkUnitBlockEntity> getBlockEntityType() {
        return BlockEntityTypes.NETWORK_UNIT.get();
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if(!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(blockPos);
            if(be instanceof NetworkUnitBlockEntity nu) {
                ItemStackHandler sh = nu.getInventory();
                for (int i = 0; i < sh.getSlots(); i++) {
                    ItemStack stack = sh.getStackInSlot(i);
                    var entity = new ItemEntity(level, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, stack);
                }
            }
        }
        super.onRemove(state, level, blockPos, newState, isMoving);
    }
}
