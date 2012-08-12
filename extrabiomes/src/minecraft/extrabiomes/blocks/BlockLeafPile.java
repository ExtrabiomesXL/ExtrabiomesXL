
package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockLeafPile extends Block implements ITextureProvider {

	static private boolean canThisPlantGrowOnThisBlockID(
			final int blockId)
	{
		return blockId == Block.grass.blockID
				|| blockId == Block.dirt.blockID;
	}

	public BlockLeafPile(final int id) {
		super(id, 64, Material.vine);
		final float f = 0.5F;
		final float f1 = 0.015625F;
		setTickRandomly(true);
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
		setStepSound(soundGrassFootstep);
		Block.setBurnProperties(id, 30, 60);
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	@Override
	public boolean canBlockStay(final World world, final int x,
			final int y, final int z)
	{
		return canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1,
				z));
	}

	@Override
	public boolean canPlaceBlockAt(final World world, final int x,
			final int y, final int z)
	{
		return super.canPlaceBlockAt(world, x, y, z)
				&& canThisPlantGrowOnThisBlockID(world.getBlockId(x,
						y - 1, z));
	}

	private void checkFlowerChange(final World world, final int x,
			final int y, final int z)
	{
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z,
					world.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}

	@Override
	public int colorMultiplier(final IBlockAccess iBlockAccess,
			final int x, final int y, final int z)
	{
		return iBlockAccess.getBiomeGenForCoords(x, z)
				.getBiomeFoliageColor();
	}

	@Override
	public int getBlockColor() {
		return ColorizerFoliage.getFoliageColorBasic();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(
			final World world, final int x, final int y, final int z)
	{
		return null;
	}

	@Override
	public int getRenderColor(final int metadata) {
		return getBlockColor();
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	@Override
	public boolean isBlockReplaceable(final World world, final int x,
			final int y, final int z)
	{
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(final World world, final int x,
			final int y, final int z, final int neigborId)
	{
		super.onNeighborBlockChange(world, x, y, z, neigborId);
		checkFlowerChange(world, x, y, z);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void updateTick(final World world, final int x, final int y,
			final int z, final Random rand)
	{
		checkFlowerChange(world, x, y, z);
	}

}
