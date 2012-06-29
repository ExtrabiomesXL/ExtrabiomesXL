
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.World;

import extrabiomes.api.ExtrabiomesBlock;

public class WorldGenChunkCustomFlower extends WorldGenCustomFlower {
	public WorldGenChunkCustomFlower(int i) {
		super(i);
	}

	public boolean a(World world, Random random, int i, int j, int k) {
		if (ExtrabiomesBlock.flower != null) {
			final int l = i + random.nextInt(16) + 8;
			final int i1 = random.nextInt(128);
			final int j1 = k + random.nextInt(16) + 8;
			return super.a(world, random, l, i1, j1);
		} else
			return true;
	}
}
