
package extrabiomes.biomes;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;

public class BiomeSnowRainforest extends BiomeTemporateRainforest {
	public BiomeSnowRainforest(int i) {
		super(i);
		b(0x338277);
		a("Snowy Rainforest");
		F = BiomeBase.TAIGA_HILLS.F;
		G = 1.3F;
		b();
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	@Override
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(17).mushroomsPerChunk(2)
				.grassPerChunk(16).build();
	}
}
