
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.Direction;
import net.minecraft.server.Facing;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;

public class WorldGenMarsh extends WorldGenerator {
	public WorldGenMarsh() {}

	public boolean a(World world, Random random, int i, int j, int k) {
		int l = i;
		int i1 = k;
		label0:

		for (int j1 = j; j1 < 63; j1++) {
			if (!world.isEmpty(l, j1, i1)) {
				int k1 = 2;

				do {
					if (k1 > 5) continue label0;

					if (Block.DIRT.canPlace(world, l, j1, i1, k1)) {
						world.setRawTypeIdAndData(
								l,
								j1,
								i1,
								Block.GRASS.id,
								1 << Direction.d[Facing.OPPOSITE_FACING[k1]]);
						continue label0;
					}

					k1++;
				} while (true);
			}

			l = i + random.nextInt(4) - random.nextInt(4);
			i1 = k + random.nextInt(4) - random.nextInt(4);
		}

		return true;
	}
}
