
package extrabiomes.biomes;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;

public class BiomeGreenHills extends ExtrabiomeGenBase {
	public BiomeGreenHills(int i) {
		super(i);
		b(0x68c474);
		a("Green Hills");
		F = BiomeBase.FOREST.F - 0.1F;
		G = BiomeBase.FOREST.G + 0.1F;
		D = 0.6F;
		E = 1.2F;
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(1).build();
	}
}
