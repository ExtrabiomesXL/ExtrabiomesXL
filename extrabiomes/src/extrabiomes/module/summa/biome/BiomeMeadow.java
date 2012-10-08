/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;

class BiomeMeadow extends ExtrabiomeGenBase {

	public BiomeMeadow() {
		super(Biome.MEADOW.getBiomeID());
		temperature = 2.3F;
		rainfall = 3.0F;
		minHeight = 0.0F;
		maxHeight = 0.0F;
		setBiomeName("Meadow");
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.grassPerChunk(12).flowersPerChunk(9).build();
	}

}
