
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.Block;
import net.minecraft.server.Material;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;

public class WorldGenCustomSwamp extends WorldGenerator {
	public WorldGenCustomSwamp() {}

	public WorldGenCustomSwamp(boolean flag) {
		super(flag);
	}

	@Override
	public boolean a(World world, Random random, int i, int j, int k) {
		for (; world.getMaterial(i, j - 1, k) == Material.WATER; j--) {}

		final int l = random.nextInt(4) + 10;

		if (j < 1 || j + l + 1 > 256) return false;

		for (int i1 = j; i1 <= j + 1 + l; i1++) {
			if (i1 < 0 && i1 >= 256) return false;

			byte byte0 = 1;

			if (i1 == j) byte0 = 0;

			if (i1 >= j + 1 + l - 2) byte0 = 3;

			for (int j2 = i - byte0; j2 <= i + byte0; j2++)
				for (int j3 = k - byte0; j3 <= k + byte0; j3++) {
					final int i4 = world.getTypeId(j2, i1, j3);

					if (Block.byId[i4] == null
							|| Block.byId[i4].isLeaves(world, j2, i1,
									j3)) continue;

					if (i4 == Block.STATIONARY_WATER.id
							|| i4 == Block.WATER.id)
					{
						if (i1 > j) return false;
					} else
						return false;
				}
		}

		final int j1 = world.getTypeId(i, j - 1, k);

		if (j1 != Block.GRASS.id && j1 != Block.DIRT.id
				|| j >= 256 - l - 1) return false;

		world.setRawTypeId(i, j - 1, k, Block.DIRT.id);

		for (int k1 = j - 3 + l; k1 <= j + l; k1++) {
			final int k2 = k1 - (j + l);
			final int k3 = 2 - k2 / 2;

			for (int j4 = i - k3; j4 <= i + k3; j4++) {
				final int l4 = j4 - i;

				for (int j5 = k - k3; j5 <= k + k3; j5++) {
					final int k5 = j5 - k;
					final Block block = Block.byId[world.getTypeId(j4,
							k1, j5)];

					if ((Math.abs(l4) != k3 || Math.abs(k5) != k3 || random
							.nextInt(2) != 0 && k2 != 0)
							&& (block == null || block
									.canBeReplacedByLeaves(world, j4,
											k1, j5)))
						world.setRawTypeId(j4, k1, j5, Block.LEAVES.id);
				}
			}
		}

		for (int l1 = 0; l1 < l; l1++) {
			final int l2 = world.getTypeId(i, j + l1, k);

			if (l2 == 0 || l2 == Block.LEAVES.id
					|| l2 == Block.WATER.id
					|| l2 == Block.STATIONARY_WATER.id)
				world.setRawTypeId(i, j + l1, k, Block.LOG.id);
		}

		for (int i2 = j - 3 + l; i2 <= j + l; i2++) {
			final int i3 = i2 - (j + l);
			final int l3 = 2 - i3 / 2;

			for (int k4 = i - l3; k4 <= i + l3; k4++)
				for (int i5 = k - l3; i5 <= k + l3; i5++) {
					if (world.getTypeId(k4, i2, i5) != Block.LEAVES.id)
						continue;

					if (random.nextInt(4) == 0
							&& world.getTypeId(k4 - 1, i2, i5) == 0)
						generateVines(world, k4 - 1, i2, i5, 8);

					if (random.nextInt(4) == 0
							&& world.getTypeId(k4 + 1, i2, i5) == 0)
						generateVines(world, k4 + 1, i2, i5, 2);

					if (random.nextInt(4) == 0
							&& world.getTypeId(k4, i2, i5 - 1) == 0)
						generateVines(world, k4, i2, i5 - 1, 1);

					if (random.nextInt(4) == 0
							&& world.getTypeId(k4, i2, i5 + 1) == 0)
						generateVines(world, k4, i2, i5 + 1, 4);
				}
		}

		return true;
	}

	private void generateVines(World world, int i, int j, int k, int l)
	{
		world.setTypeIdAndData(i, j, k, Block.VINE.id, l);

		for (int i1 = 4; world.getTypeId(i, --j, k) == 0 && i1 > 0; i1--)
			world.setTypeIdAndData(i, j, k, Block.VINE.id, l);
	}
}
