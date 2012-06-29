
package extrabiomes.biomes;

import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.Block;

public class BiomeIceWasteland extends ExtrabiomeGenBase {
	public BiomeIceWasteland(int i) {
		super(i);
		K.clear();
		A = (byte) Block.SNOW_BLOCK.id;
		B = (byte) Block.SNOW_BLOCK.id;
		b();
		b(0x7da0b5);
		a("Ice Wasteland");
		F = 0.0F;
		G = 0.1F;
		D = 0.3F;
		E = 0.4F;
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(0).build();
	}
}
