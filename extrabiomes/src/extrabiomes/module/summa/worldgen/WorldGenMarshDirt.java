/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

class WorldGenMarshDirt extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y,
			int z)
	{
		int x1 = x;
		int z1 = z;
		label0:

		for (int y1 = y; y1 < 62; y1++) {
			if (!world.isAirBlock(x1, y1, z1)) {
				int side = 2;

				do {
					if (side > 5) continue label0;

					if (Block.dirt.canPlaceBlockOnSide(world, x1, y1,
							z1, side))
					{
						world.setBlockAndMetadata(
								x1,
								y1,
								z1,
								Block.dirt.blockID,
								1 << Direction.vineGrowth[Facing.faceToSide[side]]);
						continue label0;
					}

					side++;
				} while (true);
			}

			x1 = x + 16;
			z1 = z + 16;
		}

		return true;
	}

}