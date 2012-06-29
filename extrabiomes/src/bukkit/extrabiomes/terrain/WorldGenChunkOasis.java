
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.World;

public class WorldGenChunkOasis extends WorldGenOasis {
	public WorldGenChunkOasis() {
		super(7);
	}

	public boolean a(World world, Random random, int i, int j, int k) {
		final int l = i + random.nextInt(16) + 8;
		final int i1 = k + random.nextInt(16) + 8;
		return super.a(world, random, l, world.g(l, i1), i1);
	}
}
