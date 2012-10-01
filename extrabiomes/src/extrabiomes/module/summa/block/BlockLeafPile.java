/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.block;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

class BlockLeafPile extends Block {

	static private boolean canThisPlantGrowOnThisBlockID(int blockId) {
		return blockId == Block.grass.blockID
				|| blockId == Block.dirt.blockID;
	}

	public BlockLeafPile(int id) {
		super(id, 64, Material.vine);
		final float f = 0.5F;
		final float f1 = 0.015625F;
		setTickRandomly(true);
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
		setStepSound(soundGrassFootstep);
		Block.setBurnProperties(blockID, 30, 60);
		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1,
				z));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return super.canPlaceBlockAt(world, x, y, z)
				&& canThisPlantGrowOnThisBlockID(world.getBlockId(x,
						y - 1, z));
	}

	private void checkFlowerChange(World world, int x, int y, int z) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z,
					world.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess iBlockAccess, int x, int y,
			int z)
	{
		return iBlockAccess.getBiomeGenForCoords(x, z)
				.getBiomeFoliageColor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return ColorizerFoliage.getFoliageColorBasic();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world,
			int x, int y, int z)
	{
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int metadata) {
		return getBlockColor();
	}

	@Override
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z,
			int neigborId)
	{
		super.onNeighborBlockChange(world, x, y, z, neigborId);
		checkFlowerChange(world, x, y, z);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		checkFlowerChange(world, x, y, z);
	}
}
