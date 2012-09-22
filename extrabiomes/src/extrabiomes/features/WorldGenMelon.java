/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.features;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenMelon extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y,
			int z)
	{
		for (int i = 0; i < 64; ++i) {
			final int x1 = x + rand.nextInt(8) - rand.nextInt(8);
			final int y1 = y + rand.nextInt(4) - rand.nextInt(4);
			final int z1 = z + rand.nextInt(8) - rand.nextInt(8);

			if ((world.isAirBlock(x1, y1, z1))
					&& world.getBlockId(x1, y1 - 1, z1) == Block.grass.blockID
					&& Block.pumpkin.canPlaceBlockAt(world, x1, y1, z1))
				world.setBlock(x1, y1, z1, Block.melon.blockID);
		}

		return true;
	}
}
