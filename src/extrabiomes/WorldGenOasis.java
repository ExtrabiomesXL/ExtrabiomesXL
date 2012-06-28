package extrabiomes;

import java.util.Random;

import extrabiomes.api.TerrainGenBlock;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenOasis extends WorldGenerator {
	private int grassID;
	private int avgRadius;

	public WorldGenOasis(int radius, int grassID) {
		this.avgRadius = radius;
		this.grassID = grassID;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {

		if (world.getBlockMaterial(x, y, z) != Material.water) {
			return false;
		}

		final int xzRadius = rand.nextInt(avgRadius - 2) + 2;
		final int yRadius = 2;

		for (int x1 = x - xzRadius; x1 <= x + xzRadius; x1++) {
			for (int z1 = z - xzRadius; z1 <= z + xzRadius; z1++) {
				final int a = x1 - x;
				final int b = z1 - z;

				if (a * a + b * b > xzRadius * xzRadius) {
					continue;
				}

				for (int y1 = y - yRadius; y1 <= y + yRadius; y1++) {
					final MetaBlock redRock = BlockControl.INSTANCE
							.getTerrainGenBlock(TerrainGenBlock.RED_ROCK);

					final MetaBlock blocktoReplace = new MetaBlock(world, x1,
							y1, z1);

					if (blocktoReplace.blockId() == Block.stone.blockID
							|| blocktoReplace.blockId() == Block.sand.blockID
							|| blocktoReplace.blockId() == Block.sandStone.blockID
							|| (blocktoReplace.equals(redRock))) {
						world.setBlock(x1, y1, z1, grassID);
					}
				}
			}
		}

		return true;
	}
}
