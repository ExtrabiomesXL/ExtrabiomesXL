/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.src.World;

public class WorldGenChunkOasis extends WorldGenOasis {

	public WorldGenChunkOasis() {
		super(7);
	}

	@Override
	public boolean generate(World world, Random rand, int chunk_X, int notUsed,
			int chunk_Z) {
		final int x = chunk_X + rand.nextInt(16) + 8;
		final int z = chunk_Z + rand.nextInt(16) + 8;
		return super.generate(world, rand, x,
				world.getTopSolidOrLiquidBlock(x, z), z);
	}

}
