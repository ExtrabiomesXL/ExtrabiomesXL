
package extrabiomes.plugins.buildcraft;

import java.util.Random;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.Block;
import net.minecraft.server.World;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.plugins.PluginBuildCraft;

public class OilPopulateMediumLargeDeposits extends OilPopulate
		implements IBiomeDecoration
{
	public OilPopulateMediumLargeDeposits() {}

	public void decorate(World world, Random random, int i, int j) {
		if (!PluginBuildCraft.modifyWorld) return;

		final BiomeBase biomebase = world.getWorldChunkManager()
				.getBiome(i, j);
		final boolean flag = random.nextDouble() <= 0.0015D;
		final boolean flag1 = random.nextDouble() <= (biomebase.id == BiomeManager.wasteland.id ? 0.02D
				: 0.0050000000000000001D) / 100D;

		if (flag || flag1) {
			final int k = i;
			final int l = 20 + random.nextInt(10);
			final int i1 = j;
			int j1 = 0;

			if (flag1)
				j1 = 8 + random.nextInt(9);
			else if (flag) j1 = 4 + random.nextInt(4);

			final int k1 = j1 * j1;

			for (int l1 = -j1; l1 <= j1; l1++)
				for (int i2 = -j1; i2 <= j1; i2++)
					for (int k2 = -j1; k2 <= j1; k2++) {
						final int i3 = l1 * l1 + i2 * i2 + k2 * k2;

						if (i3 <= k1)
							world.setTypeId(l1 + k, i2 + l, k2 + i1,
									PluginBuildCraft.oilStill.id);
					}

			boolean flag2 = false;

			for (int j2 = 128; j2 >= l; j2--) {
				final int l2 = world.getTypeId(k, j2, i1);

				if (!flag2 && Block.byId[l2] != null
						&& !Block.byId[l2].isLeaves(world, k, j2, i1)
						&& !Block.byId[l2].isLeaves(world, k, j2, i1)
						&& l2 != Block.GRASS.id)
				{
					flag2 = true;

					if (flag1)
						generateSurfaceDeposit(world, random, k, j2,
								i1, 20 + random.nextInt(20));
					else if (flag)
						generateSurfaceDeposit(world, random, k, j2,
								i1, 5 + random.nextInt(5));

					int j3 = 0;

					if (flag1)
						j3 = j2 + 30 >= 128 ? 128 : j2 + 30;
					else if (flag) j3 = j2 + 4 >= 128 ? 128 : j2 + 4;

					for (int k3 = j2 + 1; k3 <= j3; k3++)
						world.setTypeId(k, k3, i1,
								PluginBuildCraft.oilStill.id);

					continue;
				}

				if (flag2)
					world.setTypeId(k, j2, i1,
							PluginBuildCraft.oilStill.id);
			}
		}
	}
}
