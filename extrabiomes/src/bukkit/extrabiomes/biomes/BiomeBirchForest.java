
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.BiomeMeta;
import net.minecraft.server.WorldGenerator;

public class BiomeBirchForest extends ExtrabiomeGenBase {
	public BiomeBirchForest(int i) {
		super(i);
		b(0x62bf6c);
		a("Birch Forest");
		F = 0.4F;
		G = 0.7F;
		D = 0.0F;
		E = 0.4F;
		K.add(new BiomeMeta(net.minecraft.server.EntityWolf.class, 5, 4, 4));
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(7).grassPerChunk(1).build();
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator a(Random random) {
		if (random.nextInt(100) == 0) {
			if (random.nextInt(100) == 0)
				return O;
			else
				return N;
		} else
			return P;
	}
}
