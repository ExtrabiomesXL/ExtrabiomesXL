/** 
 * Copyright (c) SpaceToad, 2011
 * http://www.mod-buildcraft.com
 * 
 * BuildCraft is distributed under the terms of the Minecraft Mod Public 
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package extrabiomes.plugins.buildcraft;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import extrabiomes.plugins.PluginBuildCraft;

public class OilPopulate {

	public static void generateSurfaceDeposit(World world, Random rand, int x,
			int y, int z, int radius) {
		setOilWithProba(world, rand, 1, x, y, z, true);

		for (int w = 1; w <= radius; ++w) {
			float proba = (float) (radius - w + 4) / (float) (radius + 4);

			for (int d = -w; d <= w; ++d) {
				setOilWithProba(world, rand, proba, x + d, y, z + w, false);
				setOilWithProba(world, rand, proba, x + d, y, z - w, false);
				setOilWithProba(world, rand, proba, x + w, y, z + d, false);
				setOilWithProba(world, rand, proba, x - w, y, z + d, false);
			}
		}

		for (int dx = x - radius; dx <= x + radius; ++dx) {
			for (int dz = z - radius; dz <= z + radius; ++dz) {

				if (world.getBlockId(dx, y - 1, dz) != PluginBuildCraft.oilStill.blockID) {
					if (isOil(world, dx + 1, y - 1, dz)
							&& isOil(world, dx - 1, y - 1, dz)
							&& isOil(world, dx, y - 1, dz + 1)
							&& isOil(world, dx, y - 1, dz - 1)) {
						setOilWithProba(world, rand, 1.0F, dx, y, dz, true);
					}
				}
			}
		}
	}

	private static boolean isOil(World world, int x, int y, int z) {
		return (world.getBlockId(x, y, z) == PluginBuildCraft.oilStill.blockID || world
				.getBlockId(x, y, z) == PluginBuildCraft.oilMoving.blockID);
	}

	public static void setOilWithProba(World world, Random rand, float proba,
			int x, int y, int z, boolean force) {
		if ((rand.nextFloat() <= proba && world.getBlockId(x, y - 2, z) != 0)
				|| force) {
			boolean adjacentOil = false;

			for (int d = -1; d <= 1; ++d) {
				if (isOil(world, x + d, y - 1, z)
						|| isOil(world, x - d, y - 1, z)
						|| isOil(world, x, y - 1, z + d)
						|| isOil(world, x, y - 1, z - d)) {
					adjacentOil = true;
				}
			}

			if (adjacentOil || force) {
				if (world.getBlockId(x, y, z) == Block.waterMoving.blockID
						|| world.getBlockId(x, y, z) == Block.waterStill.blockID
						|| isOil(world, x, y, z)) {

					world.setBlockWithNotify(x, y, z,
							PluginBuildCraft.oilStill.blockID);
				} else {
					world.setBlockWithNotify(x, y, z, 0);
				}

				world.setBlockWithNotify(x, y - 1, z,
						PluginBuildCraft.oilStill.blockID);
			}
		}
	}

}
