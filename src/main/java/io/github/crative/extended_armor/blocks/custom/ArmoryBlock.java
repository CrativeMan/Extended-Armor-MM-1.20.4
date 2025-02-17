package io.github.crative.extended_armor.blocks.custom;

import com.mojang.serialization.MapCodec;
import io.github.crative.extended_armor.blocks.entity.ArmoryBlockEntity;
import io.github.crative.extended_armor.blocks.entity.ExtendedArmorBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ArmoryBlock extends BlockWithEntity implements BlockEntityProvider {
	private static final VoxelShape SHAPE = Block.createCuboidShape(0,0,0,16, 11, 16);


	public ArmoryBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return null;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new ArmoryBlockEntity(pos, state);
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {

		if(state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if(blockEntity instanceof ArmoryBlockEntity) {
				ItemScatterer.spawn(world, pos, ((ArmoryBlockEntity) blockEntity));
				world.updateComparators(pos, this);
			}
			super.onStateReplaced(state, world, pos, newState, moved);
		}
	}



	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if(!world.isClient) {
			NamedScreenHandlerFactory screenHandlerFactory = ((ArmoryBlockEntity) world.getBlockEntity(pos));

			if(screenHandlerFactory != null) {
				player.openHandledScreen(screenHandlerFactory);
			}
		}

		return ActionResult.SUCCESS;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, ExtendedArmorBlockEntities.ARMORY_BLOCK_ENTITY,
			(world1, blockPos, blockState, blockEntity) -> blockEntity.tick(world1, blockPos, blockState));
	}
}
