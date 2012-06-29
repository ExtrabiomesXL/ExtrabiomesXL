
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;
import extrabiomes.api.ExtrabiomesBlock;

public class WorldGenPit extends WorldGenerator {
	public WorldGenPit() {}

	public boolean a(World world, Random random, int i, int j, int k) {
		if (ExtrabiomesBlock.quickSand != null) {
			for (; world.isEmpty(i, j, k) && j > 2; j--) {}

			if (world.getTypeId(i, j, k) != Block.SAND.id)
				return false;

			for (int l = -2; l <= 2; l++)
				for (int j1 = -2; j1 <= 2; j1++)
					if (world.isEmpty(i + l, j - 1, k + j1)
							&& world.isEmpty(i + l, j - 2, k + j1))
						return false;

			for (int i1 = -1; i1 <= 1; i1++)
				for (int k1 = -1; k1 <= 1; k1++)
					for (int l1 = -2; l1 <= 0; l1++)
						world.setRawTypeId(i + i1, j + l1, k + k1,
								ExtrabiomesBlock.quickSand.id);
		}

		return true;
	}
}
