package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Direction;
import net.minecraft.src.Facing;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenDirtBed extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int x1 = x;
		int z1 = z;
		label0:

		for (int y1 = y; y1 < 62; y1++) {
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
								Block.dirt.blockID,
								1 << Direction.vineGrowth[Facing.faceToSide[side]]);
						continue label0;
					}

					side++;
				} while (true);
			}

			x1 = (x + 16);
			z1 = (z + 16);
		}

		return true;
	}

}