/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;

class BiomeSnowRainforest extends BiomeTemporateRainforest {

	public BiomeSnowRainforest() {
		super(Biome.SNOWYRAINFOREST.getBiomeID());

		setColor(0x338277);
		setBiomeName("Snowy Rainforest");
		temperature = 0.0F;
		rainfall = 0.1F;
		minHeight = 0.4F;
		maxHeight = 1.5F;
		setEnableSnow();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(17)
				.mushroomsPerChunk(2).grassPerChunk(16).build();
	}

}
