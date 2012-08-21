package extrabiomes.terrain;

import java.util.Random;

import extrabiomes.api.ExtrabiomesBlock;

import net.minecraft.src.World;

public class WorldGenChunkLeafPile extends WorldGenLeafPile {

	@Override
	public boolean generate(World world, Random rand, int x, int notUsed, int z) {
		if (ExtrabiomesBlock.leafPile != null) {
			final int xGen = x + rand.nextInt(16) + 8;
			final int yGen = rand.nextInt(128);
			final int zGen = z + rand.nextInt(16) + 8;
			return super.generate(world, rand, xGen, yGen, zGen);
		}
		return true;
	}

}
