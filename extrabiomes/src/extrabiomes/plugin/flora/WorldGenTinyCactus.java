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

public class WorldGenTinyCactus extends WorldGenerator {

	private final int	blockID;
	private final int	metadata;

	WorldGenTinyCactus(int blockID, int metadata) {
		this.blockID = blockID;
		this.metadata = metadata;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y,
			int z)
	{
		for (int i = 0; i < 10; i++) {
			final int x1 = x + rand.nextInt(8) - rand.nextInt(8);
			final int y1 = y + rand.nextInt(4) - rand.nextInt(4);
			final int z1 = z + rand.nextInt(8) - rand.nextInt(8);

			if (!world.isAirBlock(x1, y1, z1)) continue;

			final int i1 = 1 + rand.nextInt(rand.nextInt(3) + 1);

			for (int y2 = 0; y2 < i1; y2++)
				if (world.getBlockId(x1, y1 + y2 - 1, z1) == Block.sand.blockID)
					world.setBlockAndMetadata(x1, y1 + y2, z1, blockID,
							metadata);
		}

		return true;
	}
}
