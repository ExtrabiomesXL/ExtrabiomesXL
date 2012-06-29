
package extrabiomes.plugins.forestry;

import java.util.Random;

import net.minecraft.server.Material;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;
import forestry.api.core.ForestryBlock;

public class WorldGenBogEarth extends WorldGenerator {
	public WorldGenBogEarth() {}

	public boolean a(World world, Random random, int i, int j, int k) {
		for (int l = 0; l < 20; l++) {
			final int i1 = i + random.nextInt(4) - random.nextInt(4);
			final int j1 = j;
			final int k1 = k + random.nextInt(4) - random.nextInt(4);

			if (world.getTypeId(i1, j1, k1) == Block.SAND.id
					&& (world.getMaterial(i1 - 1, j1, k1) == Material.WATER
							|| world.getMaterial(i1 + 1, j1, k1) == Material.WATER
							|| world.getMaterial(i1, j1, k1 - 1) == Material.WATER || world
							.getMaterial(i1, j1, k1 + 1) == Material.WATER))
				world.setRawTypeIdAndData(i1, j1, k1,
						ForestryBlock.soil.id, 1);
		}

		return true;
	}
}
