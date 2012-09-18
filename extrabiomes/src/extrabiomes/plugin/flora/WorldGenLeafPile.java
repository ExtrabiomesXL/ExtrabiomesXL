/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.flora;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenLeafPile extends WorldGenerator {

	private final int	blockID;

	WorldGenLeafPile(int blockID) {
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

		for (int j = 0; j < 128; j++) {
			final int x1 = x + rand.nextInt(8) - rand.nextInt(8);
			final int y1 = y + rand.nextInt(4) - rand.nextInt(4);
			final int z1 = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.isAirBlock(x1, y1, z1)
					&& Block.blocksList[blockID].canBlockStay(world,
							x1, y1, z1))
				world.setBlock(x1, y1, z1, blockID);
		}

		return true;
	}
}
