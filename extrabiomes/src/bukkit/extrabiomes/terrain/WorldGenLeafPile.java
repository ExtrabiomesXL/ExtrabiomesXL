
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;
import extrabiomes.api.ExtrabiomesBlock;

public class WorldGenLeafPile extends WorldGenerator {
	public WorldGenLeafPile() {}

	public boolean a(World world, Random random, int i, int j, int k) {
		if (ExtrabiomesBlock.leafPile != null) {
			int l;

			for (; Block.byId[l = world.getTypeId(i, j, k)] == null
					|| Block.byId[l].isLeaves(world, i, j, k) && j > 0; j--)
			{}

			for (int i1 = 0; i1 < 128; i1++) {
				final int j1 = i + random.nextInt(8)
						- random.nextInt(8);
				final int k1 = j + random.nextInt(4)
						- random.nextInt(4);
				final int l1 = k + random.nextInt(8)
						- random.nextInt(8);

				if (world.isEmpty(j1, k1, l1)
						&& ExtrabiomesBlock.leafPile.f(world, j1, k1,
								l1))
					world.setRawTypeId(j1, k1, l1,
							ExtrabiomesBlock.leafPile.id);
			}
		}

		return true;
	}
}
