
package extrabiomes.biomes;

import net.minecraft.server.BiomeDecorator;

public class BiomeMeadow extends ExtrabiomeGenBase {
	public BiomeMeadow(int i) {
		super(i);
		a("Meadow");
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(0).grassPerChunk(12)
				.flowersPerChunk(9).build();
	}
}
