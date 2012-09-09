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

public class WorldGenMetadataFlowers extends WorldGenerator {
	private final int	blockId;
	private final int	metadata;

	public WorldGenMetadataFlowers(int blockId, int metadata) {
		this.blockId = blockId;
		this.metadata = metadata;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y,
			int z)
	{
		for (int var6 = 0; var6 < 64; ++var6) {
			final int x1 = x + rand.nextInt(8) - rand.nextInt(8);
			final int y1 = y + rand.nextInt(4) - rand.nextInt(4);
			final int z1 = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.isAirBlock(x1, y1, z1)
					&& Block.blocksList[blockId].canBlockStay(world,
							x1, y1, z1))
				world.setBlockAndMetadata(x1, y1, z1, blockId, metadata);
		}

		return true;
	}
}
