/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.terrain;

import java.util.Random;

import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.TerrainGenManager;


import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenCustomFlower extends WorldGenerator {
	private int metadata;

	public WorldGenCustomFlower(int metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (ExtrabiomesBlock.flower != null) {
			int id;

			while ((Block.blocksList[(id = world.getBlockId(x, y, z))] == null
					|| Block.blocksList[id].isLeaves(world, x, y, z)) && y > 0)
				y--;

			for (int i = 0; i < 128; ++i) {
				final int x1 = x + rand.nextInt(8) - rand.nextInt(8);
				final int y1 = y + rand.nextInt(4) - rand.nextInt(4);
				final int z1 = z + rand.nextInt(8) - rand.nextInt(8);

				if (world.isAirBlock(x1, y1, z1)
						&& ExtrabiomesBlock.flower.get().canBlockStay(world, x1, y1,
								z1))
					world.setBlockAndMetadata(x1, y1, z1,
							ExtrabiomesBlock.flower.get().blockID, metadata);
			}
		}
		return true;
	}
}
