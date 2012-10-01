/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.block;

import net.minecraft.src.Block;
import net.minecraft.src.BlockFlower;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.World;

class BlockCatTail extends BlockFlower {

	public BlockCatTail(int id) {
		super(id, 79, Material.plants);
		setHardness(0.0F);
		setStepSound(Block.soundGrassFootstep);
		disableStats();
		final float f = 0.375F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F,
				0.5F + f);
		setCreativeTab(CreativeTabs.tabDecorations);
		setTextureFile("/extrabiomes/extrabiomes.png");
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		final int blockId = world.getBlockId(x, y - 1, z);

		if (blockId != Block.grass.blockID
				&& blockId != Block.dirt.blockID) return false;

		y--;

		for (int offset = -1; offset < 2; offset += 2)
			if (world.getBlockMaterial(x + offset, y, z) == Material.water
					|| world.getBlockMaterial(x, y, z + offset) == Material.water)
				return true;

		return false;
	}

	@Override
	public int getRenderType() {
		return 6;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z,
			int idNeighbor)
	{
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z,
					world.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}
}
