/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.blocks;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;

import extrabiomes.api.TerrainGenManager;

import net.minecraft.src.Block;
import net.minecraft.src.BlockFlower;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockCatTail extends BlockFlower {

	public BlockCatTail(int id) {
		super(id, 79, Material.plants);
		setHardness(0.0F);
		setStepSound(Block.soundGrassFootstep);
		disableStats();
		final float f = 0.375F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F,
				0.5F + f);

		TerrainGenManager.enableCattailGen = true;
		setTextureFile("/extrabiomes/extrabiomes.png");
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		checkNotNull(itemList).add(new ItemStack(this));
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockAt(checkNotNull(world), x, y, z);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		final int blockId = checkNotNull(world).getBlockId(x, y - 1, z);

		if (blockId != Block.grass.blockID
				&& blockId != Block.dirt.blockID) return false;

		if (world.getBlockMaterial(x - 1, y - 1, z) == Material.water)
			return true;

		if (world.getBlockMaterial(x + 1, y - 1, z) == Material.water)
			return true;

		if (world.getBlockMaterial(x, y - 1, z - 1) == Material.water)
			return true;

		return world.getBlockMaterial(x, y - 1, z + 1) == Material.water;
	}

	@Override
	public int getRenderType() {
		return 6;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z,
			int idNeighbor)
	{
		if (!canBlockStay(checkNotNull(world), x, y, z)) {
			dropBlockAsItem(world, x, y, z,
					world.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}
}
