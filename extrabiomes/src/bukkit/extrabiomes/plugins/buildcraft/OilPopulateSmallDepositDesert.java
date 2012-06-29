
package extrabiomes.plugins.buildcraft;

import java.util.Random;

import net.minecraft.server.World;
import net.minecraft.server.Block;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.plugins.PluginBuildCraft;

public class OilPopulateSmallDepositDesert extends OilPopulate
		implements IBiomeDecoration
{
	public OilPopulateSmallDepositDesert() {}

	public void decorate(World world, Random random, int i, int j) {
		if (!PluginBuildCraft.modifyWorld) return;

		if (random.nextFloat() > 0.96999999999999997D) {
			final int k = random.nextInt(10) + 2;
			final int l = random.nextInt(10) + 2;
			int i1 = 128;

			do {
				if (i1 <= 65) break;

				final int j1 = k + i;
				final int k1 = l + j;

				if (world.getTypeId(j1, i1, k1) != 0) {
					if (world.getTypeId(j1, i1, k1) == Block.SAND.id)
						generateSurfaceDeposit(world, random, j1, i1,
								k1, 3);

					break;
				}

				i1--;
			} while (true);
		}
	}
}
