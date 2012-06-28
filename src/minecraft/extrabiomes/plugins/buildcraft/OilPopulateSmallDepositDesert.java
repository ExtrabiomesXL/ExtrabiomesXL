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

import net.minecraft.src.Block;
import net.minecraft.src.World;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.plugins.PluginBuildCraft;

public class OilPopulateSmallDepositDesert extends OilPopulate implements
		IBiomeDecoration {

	@Override
	public void decorate(World world, Random rand, int x, int z) {
		if (!PluginBuildCraft.modifyWorld)
			return;

		if (rand.nextFloat() > 0.97) {
			// Generate a small desert deposit

			int startX = rand.nextInt(10) + 2;
			int startZ = rand.nextInt(10) + 2;

			for (int j = 128; j > 65; --j) {
				int i = startX + x;
				int k = startZ + z;

				if (world.getBlockId(i, j, k) != 0) {
					if (world.getBlockId(i, j, k) == Block.sand.blockID) {
						generateSurfaceDeposit(world, rand, i, j, k, 3);
					}

					break;
				}
			}
		}
	}

}
