
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.WorldGenGrass;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;

public class BiomeRainforest extends ExtrabiomeGenBase {
	public BiomeRainforest(int i) {
		super(i);
		b(0xbd626);
		a("Rainforest");
		F = 1.1F;
		G = 1.4F;
		D = 0.4F;
		E = 1.3F;
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(7).grassPerChunk(4)
				.flowersPerChunk(2).build();
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator a(Random random) {
		if (random.nextInt(0x1869f) == 0) return P;

		if (random.nextInt(4) == 0)
			return O;
		else
			return N;
	}

	public WorldGenerator b(Random random) {
		if (random.nextInt(4) == 0)
			return new WorldGenGrass(Block.LONG_GRASS.id, 2);
		else
			return super.b(random);
	}
}
