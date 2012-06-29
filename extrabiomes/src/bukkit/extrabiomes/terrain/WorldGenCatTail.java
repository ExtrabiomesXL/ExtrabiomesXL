
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.Material;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import extrabiomes.api.ExtrabiomesBlock;

public class WorldGenCatTail extends WorldGenerator {
	public WorldGenCatTail() {}

	@Override
	public boolean a(World world, Random random, int i, int j, int k) {
		if (ExtrabiomesBlock.catTail != null)
			for (int l = 0; l < 20; l++) {
				final int i1 = i + random.nextInt(4)
						- random.nextInt(4);
				final int j1 = j;
				final int k1 = k + random.nextInt(4)
						- random.nextInt(4);

				if (!world.isEmpty(i1, j1, k1)
						|| world.getMaterial(i1 - 1, j1 - 1, k1) != Material.WATER
						&& world.getMaterial(i1 + 1, j1 - 1, k1) != Material.WATER
						&& world.getMaterial(i1, j1 - 1, k1 - 1) != Material.WATER
						&& world.getMaterial(i1, j1 - 1, k1 + 1) != Material.WATER)
					continue;

				final int l1 = 1 + random
						.nextInt(random.nextInt(1) + 1);

				for (int i2 = 0; i2 < l1; i2++)
					if (ExtrabiomesBlock.catTail.canPlace(world, i1, j1
							+ i2, k1))
						world.setTypeId(i1, j1 + i2, k1,
								ExtrabiomesBlock.catTail.id);
			}

		return true;
	}
}
