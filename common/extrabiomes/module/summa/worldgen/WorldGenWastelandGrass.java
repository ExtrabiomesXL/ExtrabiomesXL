/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWastelandGrass extends WorldGenerator {
	private final int	grassID;
	private final int	grassMeta;

	public WorldGenWastelandGrass(int grassID, int grassMeta) {
		this.grassID = grassID;
		this.grassMeta = grassMeta;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y,
			int z)
	{
		Block block = null;

		for (int i = 0; i < 128; ++i) {
			final int x1 = x + rand.nextInt(8) - rand.nextInt(8);
			final int z1 = z + rand.nextInt(8) - rand.nextInt(8);

			int y1 = world.getHeightValue(x1, z1);
			while (y1 > 0) {
				block = Block.blocksList[world.getBlockId(x1, y1, z1)];
				if (block != null && block.isOpaqueCube()) break;
				y1--;
			}
			y1++;

			if (world.isAirBlock(x1, y1, z1)
					&& Block.blocksList[grassID].canBlockStay(world,
							x1, y1, z1))
				world.setBlock(x1, y1, z1, grassID,
						grassMeta, 3);
		}

		return true;
	}
}
