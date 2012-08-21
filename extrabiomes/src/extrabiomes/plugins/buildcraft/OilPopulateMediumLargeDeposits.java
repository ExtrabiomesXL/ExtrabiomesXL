/** 
 * portions Copyright (c) SpaceToad, 2011
 * http://www.mod-buildcraft.com
 * 
 * BuildCraft is distributed under the terms of the Minecraft Mod Public 
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package extrabiomes.plugins.buildcraft;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.plugins.PluginBuildCraft;

public class OilPopulateMediumLargeDeposits extends OilPopulate implements
		IBiomeDecoration {

	@Override
	public void decorate(World world, Random rand, int x, int z) {
		if (!PluginBuildCraft.modifyWorld)
			return;

		BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(
				x, z);

		boolean mediumDeposit = rand.nextDouble() <= (0.15 / 100.0);
		boolean largeDeposit = rand.nextDouble() <= ((biomegenbase.biomeID != BiomeManager.wasteland.biomeID ? 0.005
				: 0.020) / 100.0);

		if (mediumDeposit || largeDeposit) {
			// Generate a large cave deposit

			int cx = x, cy = 20 + rand.nextInt(10), cz = z;

			int r = 0;

			if (largeDeposit) {
				r = 8 + rand.nextInt(9);
			} else if (mediumDeposit) {
				r = 4 + rand.nextInt(4);
			}

			int r2 = r * r;

			for (int bx = -r; bx <= r; bx++) {
				for (int by = -r; by <= r; by++) {
					for (int bz = -r; bz <= r; bz++) {
						int d2 = bx * bx + by * by + bz * bz;

						if (d2 <= r2) {
							world.setBlockWithNotify(bx + cx, by + cy, bz + cz,
									PluginBuildCraft.oilStill.blockID);
						}
					}
				}
			}

			boolean started = false;

			for (int y = 128; y >= cy; --y) {
				int id = world.getBlockId(cx, y, cz);

				if (!started && Block.blocksList[id] != null
						&& !Block.blocksList[id].isLeaves(world, cx, y, cz)
						&& !Block.blocksList[id].isLeaves(world, cx, y, cz)
						&& id != Block.grass.blockID) {

					started = true;

					if (largeDeposit) {
						generateSurfaceDeposit(world, rand, cx, y, cz,
								20 + rand.nextInt(20));
					} else if (mediumDeposit) {
						generateSurfaceDeposit(world, rand, cx, y, cz,
								5 + rand.nextInt(5));
					}

					int ymax = 0;

					if (largeDeposit) {
						ymax = (y + 30 < 128 ? y + 30 : 128);
					} else if (mediumDeposit) {
						ymax = (y + 4 < 128 ? y + 4 : 128);
					}

					for (int h = y + 1; h <= ymax; ++h) {
						world.setBlockWithNotify(cx, h, cz,
								PluginBuildCraft.oilStill.blockID);
					}

				} else if (started) {
					world.setBlockWithNotify(cx, y, cz,
							PluginBuildCraft.oilStill.blockID);
				}
			}

		}
	}

}
