
package extrabiomes.biomes;

import net.minecraft.server.Block;

public class BiomeGlacier extends ExtrabiomeGenBase {
	public BiomeGlacier(int i) {
		super(i);
		K.clear();
		A = (byte) Block.SNOW_BLOCK.id;
		B = (byte) Block.ICE.id;
		b(0x77a696);
		a("Glacier");
		b();
		F = 0.0F;
		G = 0.0F;
		D = 1.4F;
		E = 2.1F;
	}
}
