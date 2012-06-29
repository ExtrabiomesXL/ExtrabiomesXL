
package extrabiomes.biomes;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.BiomeMeta;

public class BiomeSnowForest extends ExtrabiomeGenBase {
	public BiomeSnowForest(int i) {
		super(i);
		b(0x5ba68d);
		a("Snow Forest");
		F = BiomeBase.TAIGA_HILLS.F;
		G = BiomeBase.TAIGA_HILLS.G;
		D = 0.1F;
		E = 0.5F;
		b();
		K.add(new BiomeMeta(net.minecraft.server.EntityWolf.class, 5, 4, 4));
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(8).flowersPerChunk(1)
				.grassPerChunk(4).build();
	}
}
