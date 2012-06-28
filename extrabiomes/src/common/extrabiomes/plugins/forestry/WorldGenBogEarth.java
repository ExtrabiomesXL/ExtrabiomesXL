package extrabiomes.plugins.forestry;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.forestry.api.core.ForestryBlock;

public class WorldGenBogEarth extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		for (int l = 0; l < 20; l++) {
			int x = i + random.nextInt(4) - random.nextInt(4);
			int y = j;
			int z = k + random.nextInt(4) - random.nextInt(4);

			if (world.getBlockId(x, y, z) != Block.sand.blockID) {
				continue;
			}
			if ((world.getBlockMaterial(x - 1, y, z) != Material.water)
					&& (world.getBlockMaterial(x + 1, y, z) != Material.water)
					&& (world.getBlockMaterial(x, y, z - 1) != Material.water)
					&& (world.getBlockMaterial(x, y, z + 1) != Material.water)) {
				continue;
			}

			world.setBlockAndMetadata(x, y, z, ForestryBlock.soil.blockID, 1);
		}

		return true;
	}

}
