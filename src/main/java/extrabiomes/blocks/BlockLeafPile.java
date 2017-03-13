/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.Random;

import extrabiomes.Extrabiomes;
import extrabiomes.lib.ITextureRegisterer;
import extrabiomes.utility.ModelUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLeafPile extends Block implements ITextureRegisterer {
	private static final float SIZE = 0.5F, HEIGHT = 0.015625F;
	protected static final AxisAlignedBB LEAF_PILE_AABB = new AxisAlignedBB(0.5F - SIZE, 0.0F, 0.5F - SIZE, 0.5F + SIZE, HEIGHT, 0.5F + SIZE);
	
	public BlockLeafPile() {
		super(Material.VINE);
		
		setHardness(0.0F);
		setTickRandomly(true);
		setSoundType(SoundType.GROUND);
		setCreativeTab(Extrabiomes.tabsEBXL);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return LEAF_PILE_AABB;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos) {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerTexture() {
		ModelUtil.registerTexture(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED; //Perhaps?
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("deprecation")
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.UP ? true : super.shouldSideBeRendered(state, world, pos, side);
	}
	
	@Override
	public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}
	
	private static boolean canThisPlantGrowOnThisBlock(IBlockState state) {
		Block block = state.getBlock();
		return block.equals(Blocks.GRASS) || block.equals(Blocks.DIRT);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return super.canPlaceBlockAt(world, pos) && canThisPlantGrowOnThisBlock(world.getBlockState(pos.down()));
	}

	@Override
	public boolean isReplaceable(IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		super.onNeighborChange(world, pos, neighbor);

		canThisPlantGrowOnThisBlock(world.getBlockState(pos.down()));
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		canThisPlantGrowOnThisBlock(world.getBlockState(pos.down()));
	}
}