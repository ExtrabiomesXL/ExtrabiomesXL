
package extrabiomes.biomes;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.BiomeMeta;

public class BiomeForestedHills extends ExtrabiomeGenBase {
	public BiomeForestedHills(int i) {
		super(i);
		a("Forested Hills");
		F = BiomeBase.FOREST.F - 0.1F;
		G = BiomeBase.FOREST.G;
		K.add(new BiomeMeta(net.minecraft.server.EntityWolf.class, 5, 4, 4));
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(7).flowersPerChunk(1)
				.grassPerChunk(5).build();
	}
}
