/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.terrain;

import java.util.Random;

import extrabiomes.api.ExtrabiomesBlock;


import net.minecraft.src.World;

public class WorldGenChunkCustomFlower extends WorldGenCustomFlower {

	public WorldGenChunkCustomFlower(int metadata) {
		super(metadata);
	}

	@Override
	public boolean generate(World world, Random rand, int chunk_X, int notUsed,
			int chunk_Z) {
		if (ExtrabiomesBlock.flower != null) {
			final int x = chunk_X + rand.nextInt(16) + 8;
			final int y = rand.nextInt(128);
			final int z = chunk_Z + rand.nextInt(16) + 8;
			return super.generate(world, rand, x, y, z);
		}
		return true;
	}

}
