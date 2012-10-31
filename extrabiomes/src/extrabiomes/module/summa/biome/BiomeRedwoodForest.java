/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;

class BiomeRedwoodForest extends ExtrabiomeGenBase {

	public BiomeRedwoodForest() {
		super(Biome.REDWOODFOREST.getBiomeID());

		setColor(0x0BD626);
		setBiomeName("Redwood Forest");
		temperature = 1.1F;
		rainfall = 1.4F;
		minHeight = 0.9F;
		maxHeight = 1.5F;
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(17)
				.build();
	}
}
