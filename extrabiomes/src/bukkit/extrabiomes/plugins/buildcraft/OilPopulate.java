
package extrabiomes.plugins.buildcraft;

import java.util.Random;

import net.minecraft.server.World;
import net.minecraft.server.Block;
import extrabiomes.plugins.PluginBuildCraft;

public class OilPopulate {
	public static void generateSurfaceDeposit(World world,
			Random random, int i, int j, int k, int l)
	{
		setOilWithProba(world, random, 1.0F, i, j, k, true);

		for (int i1 = 1; i1 <= l; i1++) {
			final float f = (float) (l - i1 + 4) / (float) (l + 4);

			for (int l1 = -i1; l1 <= i1; l1++) {
				setOilWithProba(world, random, f, i + l1, j, k + i1,
						false);
				setOilWithProba(world, random, f, i + l1, j, k - i1,
						false);
				setOilWithProba(world, random, f, i + i1, j, k + l1,
						false);
				setOilWithProba(world, random, f, i - i1, j, k + l1,
						false);
			}
		}

		for (int j1 = i - l; j1 <= i + l; j1++)
			for (int k1 = k - l; k1 <= k + l; k1++)
				if (world.getTypeId(j1, j - 1, k1) != PluginBuildCraft.oilStill.id
						&& isOil(world, j1 + 1, j - 1, k1)
						&& isOil(world, j1 - 1, j - 1, k1)
						&& isOil(world, j1, j - 1, k1 + 1)
						&& isOil(world, j1, j - 1, k1 - 1))
					setOilWithProba(world, random, 1.0F, j1, j, k1,
							true);
	}

	private static boolean isOil(World world, int i, int j, int k) {
		return world.getTypeId(i, j, k) == PluginBuildCraft.oilStill.id
				|| world.getTypeId(i, j, k) == PluginBuildCraft.oilMoving.id;
	}

	public static void setOilWithProba(World world, Random random,
			float f, int i, int j, int k, boolean flag)
	{
		if (random.nextFloat() <= f
				&& world.getTypeId(i, j - 2, k) != 0 || flag)
		{
			boolean flag1 = false;

			for (int l = -1; l <= 1; l++)
				if (isOil(world, i + l, j - 1, k)
						|| isOil(world, i - l, j - 1, k)
						|| isOil(world, i, j - 1, k + l)
						|| isOil(world, i, j - 1, k - l)) flag1 = true;

			if (flag1 || flag) {
				if (world.getTypeId(i, j, k) == Block.WATER.id
						|| world.getTypeId(i, j, k) == Block.STATIONARY_WATER.id
						|| isOil(world, i, j, k))
					world.setTypeId(i, j, k,
							PluginBuildCraft.oilStill.id);
				else
					world.setTypeId(i, j, k, 0);

				world.setTypeId(i, j - 1, k,
						PluginBuildCraft.oilStill.id);
			}
		}
	}

	public OilPopulate() {}
}
