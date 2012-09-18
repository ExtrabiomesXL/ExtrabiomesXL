/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.features;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Direction;
import net.minecraft.src.Facing;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenMarshGrass extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int x1 = x;
		int z1 = z;
		label0:

		for (int y1 = y; y1 < 63; y1++) {
			if (!world.isAirBlock(x1, y1, z1)) {
				int side = 2;

				do {
					if (side > 5)
						continue label0;

					if (Block.dirt.canPlaceBlockOnSide(world, x1, y1, z1, side)) {
						world.setBlockAndMetadata(
								x1,
								y1,
								z1,
								Block.grass.blockID,
								1 << Direction.vineGrowth[Facing.faceToSide[side]]);
						continue label0;
					}

					side++;
				} while (true);
			}

			x1 = (x + rand.nextInt(4)) - rand.nextInt(4);
			z1 = (z + rand.nextInt(4)) - rand.nextInt(4);
		}

		return true;
	}
}