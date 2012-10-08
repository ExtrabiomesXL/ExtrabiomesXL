/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;

class BiomeGreenHills extends ExtrabiomeGenBase {

	public BiomeGreenHills() {
		super(Biome.GREENHILLS.getBiomeID());

		setColor(0x68C474);
		setBiomeName("Green Hills");
		temperature = 0.7F;
		rainfall = 0.8F;
		minHeight = 0.6F;
		maxHeight = 1.2F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(1)
				.build();
	}

}
