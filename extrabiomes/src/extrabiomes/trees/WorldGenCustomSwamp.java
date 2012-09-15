/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.trees;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenCustomSwamp extends WorldGenerator {

	public WorldGenCustomSwamp() {
		super();
	}

	public WorldGenCustomSwamp(boolean notify) {
		super(notify);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y,
			int z)
	{

		while (world.getBlockMaterial(x, y - 1, z) == Material.water)
			y--;

		final int height = rand.nextInt(4) + 10;

		if (y < 1 || y + height + 1 > 256) return false;

		for (int y1 = y; y1 <= y + 1 + height; y1++) {

			if (y1 < 0 && y1 >= 256) return false;

			byte clearanceNeededAroundTrunk = 1;

			if (y1 == y) clearanceNeededAroundTrunk = 0;

			if (y1 >= y + 1 + height - 2)
				clearanceNeededAroundTrunk = 3;

			for (int x1 = x - clearanceNeededAroundTrunk; x1 <= x
					+ clearanceNeededAroundTrunk; x1++)
				for (int x2 = z - clearanceNeededAroundTrunk; x2 <= z
						+ clearanceNeededAroundTrunk; x2++)
				{
					final int id = world.getBlockId(x1, y1, x2);

					if (Block.blocksList[id] == null
							|| Block.blocksList[id].isLeaves(world, x1,
									y1, x2)) continue;

					if (id == Block.waterStill.blockID
							|| id == Block.waterMoving.blockID)
					{
						if (y1 > y) return false;
					} else
						return false;
				}
		}

		final int id = world.getBlockId(x, y - 1, z);

		if (id != Block.grass.blockID && id != Block.dirt.blockID
				|| y >= 256 - height - 1) return false;

		world.setBlock(x, y - 1, z, Block.dirt.blockID);

		for (int y1 = y - 3 + height; y1 <= y + height; y1++) {
			final int posTrunk = y1 - (y + height);
			final int canopyRadius = 2 - posTrunk / 2;

			for (int x1 = x - canopyRadius; x1 <= x + canopyRadius; x1++)
			{
				final int xOnRadius = x1 - x;

				for (int z1 = z - canopyRadius; z1 <= z + canopyRadius; z1++)
				{
					final int zOnRadius = z1 - z;

					final Block block = Block.blocksList[world
							.getBlockId(x1, y1, z1)];

					if ((Math.abs(xOnRadius) != canopyRadius
							|| Math.abs(zOnRadius) != canopyRadius || rand
							.nextInt(2) != 0 && posTrunk != 0)
							&& (block == null || block
									.canBeReplacedByLeaves(world, x1,
											y1, z1)))
						world.setBlock(x1, y1, z1, Block.leaves.blockID);
				}
			}
		}

		for (int y1 = 0; y1 < height; y1++) {
			final int id2 = world.getBlockId(x, y + y1, z);

			if (id2 == 0
					|| Block.blocksList[id2].isLeaves(world, x, y + y1,
							z) || id2 == Block.waterMoving.blockID
					|| id2 == Block.waterStill.blockID)
				world.setBlock(x, y + y1, z, Block.wood.blockID);
		}

		for (int y1 = y - 3 + height; y1 <= y + height; y1++) {
			final int posTrunk = y1 - (y + height);
			final int canopyRadius = 2 - posTrunk / 2;

			for (int x1 = x - canopyRadius; x1 <= x + canopyRadius; x1++)
				for (int z1 = z - canopyRadius; z1 <= z + canopyRadius; z1++)
				{
					final int id2 = world.getBlockId(x1, y1, z1);
					if (id2 == 0
							|| !Block.blocksList[id2].isLeaves(world,
									x1, y1, z1)) continue;

					if (rand.nextInt(4) == 0
							&& world.getBlockId(x1 - 1, y1, z1) == 0)
						generateVines(world, x1 - 1, y1, z1, 8);

					if (rand.nextInt(4) == 0
							&& world.getBlockId(x1 + 1, y1, z1) == 0)
						generateVines(world, x1 + 1, y1, z1, 2);

					if (rand.nextInt(4) == 0
							&& world.getBlockId(x1, y1, z1 - 1) == 0)
						generateVines(world, x1, y1, z1 - 1, 1);

					if (rand.nextInt(4) == 0
							&& world.getBlockId(x1, y1, z1 + 1) == 0)
						generateVines(world, x1, y1, z1 + 1, 4);
				}
		}

		return true;
	}

	private void generateVines(World world, int x, int y, int z,
			int metadata)
	{
		world.setBlockAndMetadataWithNotify(x, y, z,
				Block.vine.blockID, metadata);

		for (int i = 4; world.getBlockId(x, --y, z) == 0 && i > 0; i--)
			world.setBlockAndMetadataWithNotify(x, y, z,
					Block.vine.blockID, metadata);
	}
}
