
package extrabiomes.plugins.forestry;

import java.util.Random;

import net.minecraft.server.World;

public class WorldGenChunkBogEarth extends WorldGenBogEarth {
	public WorldGenChunkBogEarth() {}

	public boolean a(World world, Random random, int i, int j, int k) {
		for (int l = 0; l < 10; l++) {
			final int i1 = i + random.nextInt(16) + 8;
			final int j1 = random.nextInt(256);
			final int k1 = k + random.nextInt(16) + 8;
			super.a(world, random, i1, j1, k1);
		}

		return true;
	}
}
