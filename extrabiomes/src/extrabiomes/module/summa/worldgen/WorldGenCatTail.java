/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

class WorldGenCatTail extends WorldGenerator {

	private final int	blockID;

	WorldGenCatTail(int blockID) {
		this.blockID = blockID;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y,
			int z)
	{
		int i;
		while ((Block.blocksList[i = world.getBlockId(x, y, z)] == null || Block.blocksList[i]
				.isLeaves(world, x, y, z)) && y > 0)
			y--;
		
		y++;
		
		for (int l = 0; l < 20; l++) {
			final int x1 = x + rand.nextInt(4) - rand.nextInt(4);
			final int z1 = z + rand.nextInt(4) - rand.nextInt(4);
			if (!world.isAirBlock(x1, y, z1)
					|| world.getBlockMaterial(x1 - 1, y - 1, z1) != Material.water
					&& world.getBlockMaterial(x1 + 1, y - 1, z1) != Material.water
					&& world.getBlockMaterial(x1, y - 1, z1 - 1) != Material.water
					&& world.getBlockMaterial(x1, y - 1, z1 + 1) != Material.water)
				continue;

			final int i1 = 1 + rand.nextInt(rand.nextInt(1) + 1);

			for (i = 0; i < i1; i++)
				if (Block.blocksList[blockID].canBlockStay(world, x1,
						y + i, z1))
					world.func_94575_c(x1, y + i, z1, blockID);
		}

		return true;
	}

}
