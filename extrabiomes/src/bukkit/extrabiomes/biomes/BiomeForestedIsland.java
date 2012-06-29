
package extrabiomes.biomes;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.BiomeMeta;

public class BiomeForestedIsland extends ExtrabiomeGenBase {
	public BiomeForestedIsland(int i) {
		super(i);
		b(0x62bf6c);
		a("Forested Island");
		F = BiomeBase.FOREST.F + 0.1F;
		G = BiomeBase.FOREST.G;
		D = -0.8F;
		E = 0.8F;
		K.add(new BiomeMeta(net.minecraft.server.EntityWolf.class, 5, 4, 4));
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(7).grassPerChunk(3).build();
	}
}
