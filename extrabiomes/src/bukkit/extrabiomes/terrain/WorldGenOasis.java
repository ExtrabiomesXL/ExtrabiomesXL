
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.Material;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;
import extrabiomes.api.TerrainGenManager;

public class WorldGenOasis extends WorldGenerator {
	private final int	avgRadius;

	public WorldGenOasis(int i) {
		avgRadius = i;
	}

	public boolean a(World world, Random random, int i, int j, int k) {
		if (world.getMaterial(i, j, k) != Material.WATER) return false;

		final int l = random.nextInt(avgRadius - 2) + 2;
		final byte byte0 = 2;

		for (int i1 = i - l; i1 <= i + l; i1++)
			for (int j1 = k - l; j1 <= k + l; j1++) {
				final int k1 = i1 - i;
				final int l1 = j1 - k;

				if (k1 * k1 + l1 * l1 > l * l) continue;

				for (int i2 = j - 2; i2 <= j + 2; i2++) {
					final int j2 = world.getTypeId(i1, i2, j1);

					if (j2 == Block.STONE.id
							|| j2 == Block.SAND.id
							|| j2 == Block.SANDSTONE.id
							|| j2 == TerrainGenManager.blockMountainRidge.id)
						world.setRawTypeId(i1, i2, j1, Block.GRASS.id);
				}
			}

		return true;
	}
}
