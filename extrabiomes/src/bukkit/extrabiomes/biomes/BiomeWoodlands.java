
package extrabiomes.biomes;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.BiomeMeta;

public class BiomeWoodlands extends ExtrabiomeGenBase {
	public BiomeWoodlands(int i) {
		super(i);
		b(0x85b53e);
		a("Woodlands");
		F = BiomeBase.FOREST.F;
		G = BiomeBase.FOREST.G;
		D = 0.2F;
		E = 0.4F;
		K.add(new BiomeMeta(net.minecraft.server.EntityWolf.class, 5, 4, 4));
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(8).grassPerChunk(3).build();
	}
}
