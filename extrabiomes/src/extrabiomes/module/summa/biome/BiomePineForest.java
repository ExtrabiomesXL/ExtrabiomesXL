/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;

class BiomePineForest extends ExtrabiomeGenBase {

	public BiomePineForest() {
		super(Biome.PINEFOREST.getBiomeID());

		setColor(0x469C7E);
		setBiomeName("Pine Forest");
		temperature = 0.4F;
		rainfall = 0.6F;
		minHeight = 0.1F;
		maxHeight = 0.3F;

		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class,
				5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(10)
				.grassPerChunk(1).build();
	}
}
