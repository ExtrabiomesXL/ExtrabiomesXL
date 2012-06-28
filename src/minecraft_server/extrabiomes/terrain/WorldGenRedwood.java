package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.TerrainGenManager;

public class WorldGenRedwood extends WorldGenerator {

	final int blockLeaf, metaLeaf, blockWood, metaWood;

	public WorldGenRedwood(boolean doNotify) {
		super(doNotify);
		blockLeaf = TerrainGenManager.blockRedwoodLeaves.blockID;
		metaLeaf = TerrainGenManager.metaRedwoodLeaves;
		blockWood = TerrainGenManager.blockRedwoodWood.blockID;
		metaWood = TerrainGenManager.metaRedwoodWood;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int i = rand.nextInt(8) + 24;
		int j = 1 + rand.nextInt(12);
		int k = i - j;
		int l = 2 + rand.nextInt(6);

		if (y < 1 || y + i + 1 > 256) {
			return false;
		}

		int id = world.getBlockId(x, y - 1, z);

		if (!TerrainGenManager.treesCanGrowOnIDs.contains(Integer.valueOf(id))
				|| y >= 256 - i - 1) {
			return false;
		}

		for (int y1 = y; y1 <= y + 1 + i; y1++) {
			int k1 = 1;

			if (y1 - y < j) {
				k1 = 0;
			} else {
				k1 = l;
			}

			for (int x1 = x - k1; x1 <= x + k1; x1++) {
				for (int z1 = z - k1; z1 <= z + k1; z1++) {
					if (y1 >= 0 && y1 < 256) {
						int id1 = world.getBlockId(x1, y1, z1);

						if (Block.blocksList[id1] != null
								&& Block.blocksList[id1].isLeaves(world, x1,
										y1, z1))
							return false;
					} else
						return false;
				}
			}
		}

		world.setBlock(x, y - 1, z, Block.dirt.blockID);
		world.setBlock(x - 1, y - 1, z, Block.dirt.blockID);
		world.setBlock(x, y - 1, z - 1, Block.dirt.blockID);
		world.setBlock(x - 1, y - 1, z - 1, Block.dirt.blockID);
		int l1 = rand.nextInt(2);
		int j2 = 1;
		boolean flag1 = false;

		for (int i3 = 0; i3 <= k; i3++) {
			int y1 = (y + i) - i3;

			for (int x1 = x - l1; x1 <= x + l1; x1++) {
				int k4 = x1 - x;

				for (int z1 = z - l1; z1 <= z + l1; z1++) {
					int i5 = z1 - z;

					Block block = Block.blocksList[world.getBlockId(x1, y1, z1)];

					if ((Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0)
							&& (block == null || block.canBeReplacedByLeaves(
									world, x1, y1, z1))) {
						setBlockAndMetadata(world, x1, y1, z1, blockLeaf,
								metaLeaf);
						setBlockAndMetadata(world, x1 - 1, y1, z1, blockLeaf,
								metaLeaf);
						setBlockAndMetadata(world, x1, y1, z1 - 1, blockLeaf,
								metaLeaf);
						setBlockAndMetadata(world, x1 - 1, y1, z1 - 1,
								blockLeaf, metaLeaf);
					}
				}
			}

			if (l1 >= j2) {
				l1 = ((flag1) ? 1 : 0);
				flag1 = true;

				if (++j2 > l) {
					j2 = l;
				}
			} else {
				l1++;
			}
		}

		int j3 = rand.nextInt(3);

		for (int y1 = 0; y1 < i - j3; y1++) {
			int j4 = world.getBlockId(x, y + y1, z);

			if (Block.blocksList[j4] == null
					|| Block.blocksList[j4].isLeaves(world, x, y + y1, z)) {
				setBlockAndMetadata(world, x, y + y1, z, blockWood, metaWood);
				setBlockAndMetadata(world, x, y + y1, z, blockWood, metaWood);
				setBlockAndMetadata(world, x - 1, y + y1, z, blockWood,
						metaWood);
				setBlockAndMetadata(world, x, y + y1, z - 1, blockWood,
						metaWood);
				setBlockAndMetadata(world, x - 1, y + y1, z - 1, blockWood,
						metaWood);
			}
		}

		return true;
	}

}
