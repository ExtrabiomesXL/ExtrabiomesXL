package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.src.World;
import extrabiomes.api.ExtrabiomesBlock;

public class WorldGenChunkGrass extends WorldGenGrass {

	public WorldGenChunkGrass(int metadata) {
		super(metadata);
	}

	@Override
	public boolean generate(World world, Random rand, int chunk_X, int notUsed,
			int chunk_Z) {
		if (ExtrabiomesBlock.grass != null) {
			final int x = chunk_X + rand.nextInt(16) + 8;
			final int y = rand.nextInt(128);
			final int z = chunk_Z + rand.nextInt(16) + 8;
			return super.generate(world, rand, x, y, z);
		}
		return true;
	}

}
