
package extrabiomes.biomes;

import net.minecraft.server.BiomeDecorator;

public class BiomeTundra extends ExtrabiomeGenBase {
	public BiomeTundra(int i) {
		super(i);
		b(0x305a85);
		a("Tundra");
		F = 0.0F;
		G = 0.0F;
		D = 0.0F;
		E = 0.2F;
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).flowersPerChunk(0).grassPerChunk(0)
				.sandPerChunk(0, 0).build();
	}
}
