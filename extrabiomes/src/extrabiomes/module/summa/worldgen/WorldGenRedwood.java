/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import extrabiomes.plugin.trees.BlockCustomSapling;

public class WorldGenRedwood extends WorldGenerator {

	private static Block	trunkBlockNE	= Block.wood;
	private static Block	trunkBlockNW	= Block.wood;
	private static Block	trunkBlockSE	= Block.wood;
	private static Block	trunkBlockSW	= Block.wood;
	private static int		trunkMetadata	= 1;
	private static Block	leavesBlock		= Block.leaves;
	private static int		leavesMetadata	= 0;

	public static void setLeavesBlock(Block block, int metadata) {
		WorldGenRedwood.leavesBlock = block;
		WorldGenRedwood.leavesMetadata = metadata;
	}

	public static void setTrunkBlock(Block blockNW, Block blockNE,
			Block blockSW, Block blockSE, int metadata)
	{
		WorldGenRedwood.trunkBlockNW = blockNW;
		WorldGenRedwood.trunkBlockNE = blockNE;
		WorldGenRedwood.trunkBlockSW = blockSW;
		WorldGenRedwood.trunkBlockSE = blockSE;
		WorldGenRedwood.trunkMetadata = metadata;
	}

	public WorldGenRedwood(boolean doNotify) {
		super(doNotify);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y,
			int z)
	{
		final int height = rand.nextInt(30) + 32;
		final int j = 1 + rand.nextInt(12);
		final int k = height - j;
		final int l = 2 + rand.nextInt(6);

		if (y < 1 || y + height + 1 > 256) return false;

		if (!BlockCustomSapling.isValidSoilID(world.getBlockId(x,
				y - 1, z)) || y >= 256 - height - 1) return false;

		for (int y1 = y; y1 <= y + 1 + height; y1++) {
			int k1 = 1;

			if (y1 - y < j)
				k1 = 0;
			else
				k1 = l;

			for (int x1 = x - k1; x1 <= x + k1; x1++)
				for (int z1 = z - k1; z1 <= z + k1; z1++)
					if (y1 >= 0 && y1 < 256) {
						final int id = world.getBlockId(x1, y1, z1);

						if (Block.blocksList[id] != null
								&& Block.blocksList[id].isLeaves(world,
										x1, y1, z1)) return false;
					} else
						return false;
		}

		world.setBlock(x, y - 1, z, Block.dirt.blockID);
		world.setBlock(x - 1, y - 1, z, Block.dirt.blockID);
		world.setBlock(x, y - 1, z - 1, Block.dirt.blockID);
		world.setBlock(x - 1, y - 1, z - 1, Block.dirt.blockID);
		int l1 = rand.nextInt(2);
		int j2 = 1;
		boolean flag1 = false;

		for (int i3 = 0; i3 <= k; i3++) {
			final int y1 = y + height - i3;

			for (int x1 = x - l1; x1 <= x + l1; x1++) {
				final int k4 = x1 - x;

				for (int z1 = z - l1; z1 <= z + l1; z1++) {
					final int i5 = z1 - z;

					final Block block = Block.blocksList[world
							.getBlockId(x1, y1, z1)];

					if ((Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0)
							&& (block == null || block
									.canBeReplacedByLeaves(world, x1,
											y1, z1)))
					{
						setBlockAndMetadata(world, x1, y1, z1,
								leavesBlock.blockID, leavesMetadata);
						setBlockAndMetadata(world, x1 - 1, y1, z1,
								leavesBlock.blockID, leavesMetadata);
						setBlockAndMetadata(world, x1, y1, z1 - 1,
								leavesBlock.blockID, leavesMetadata);
						setBlockAndMetadata(world, x1 - 1, y1, z1 - 1,
								leavesBlock.blockID, leavesMetadata);
					}
				}
			}

			if (l1 >= j2) {
				l1 = flag1 ? 1 : 0;
				flag1 = true;

				if (++j2 > l) j2 = l;
			} else
				l1++;
		}

		final int j3 = rand.nextInt(3);

		for (int y1 = 0; y1 < height - j3; y1++) {
			final int j4 = world.getBlockId(x, y + y1, z);

			if (Block.blocksList[j4] == null
					|| Block.blocksList[j4].isLeaves(world, x, y + y1,
							z))
			{
				setBlockAndMetadata(world, x, y + y1, z,
						trunkBlockSE.blockID, trunkMetadata);
				setBlockAndMetadata(world, x - 1, y + y1, z,
						trunkBlockSW.blockID, trunkMetadata);
				setBlockAndMetadata(world, x, y + y1, z - 1,
						trunkBlockNE.blockID, trunkMetadata);
				setBlockAndMetadata(world, x - 1, y + y1, z - 1,
						trunkBlockNW.blockID, trunkMetadata);
			}
		}

		return true;
	}

}
