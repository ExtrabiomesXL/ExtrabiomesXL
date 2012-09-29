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

class BiomeAutumnWoods extends ExtrabiomeGenBase {

	public BiomeAutumnWoods() {
		super(Biome.AUTUMNWOODS.getBiomeID());
		setColor(0xF29C11);
		setBiomeName("Autumn Woods");
		temperature = BiomeGenBase.forest.temperature;
		rainfall = BiomeGenBase.forest.rainfall;
		minHeight = 0.2F;
		maxHeight = 0.8F;

		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class,
				5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(9)
				.grassPerChunk(6).mushroomsPerChunk(3).build();
	}

}
