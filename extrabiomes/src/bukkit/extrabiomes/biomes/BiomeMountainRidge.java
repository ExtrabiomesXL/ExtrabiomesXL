
package extrabiomes.biomes;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;

public class BiomeMountainRidge extends ExtrabiomeGenBase {
	public BiomeMountainRidge(int i) {
		super(i);
		b(0xc4722f);
		a("Mountain Ridge");
		F = BiomeBase.DESERT.F;
		G = BiomeBase.DESERT.G;
		D = 1.7F;
		E = 1.7F;
		disableRain();
		K.clear();
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(0).grassPerChunk(0).build();
	}
}
