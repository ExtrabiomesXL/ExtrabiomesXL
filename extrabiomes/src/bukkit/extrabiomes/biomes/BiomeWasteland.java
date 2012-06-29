
package extrabiomes.biomes;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;

public class BiomeWasteland extends ExtrabiomeGenBase {
	public BiomeWasteland(int i) {
		super(i);
		b(0x9e7c41);
		a("Wasteland");
		F = BiomeBase.DESERT.F;
		G = BiomeBase.DESERT.G;
		D = 0.0F;
		E = 0.0F;
		H = 0xf08000;
		K.clear();
		disableRain();
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).deadBushPerChunk(3).build();
	}
}
