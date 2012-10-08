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

class BiomeForestedIsland extends ExtrabiomeGenBase {

	public BiomeForestedIsland() {
		super(Biome.FORESTEDISLAND.getBiomeID());

		setColor(0x62BF6C);
		setBiomeName("Forested Island");
		temperature = 0.4F;
		rainfall = 0.7F;
		minHeight = -0.8F;
		maxHeight = 0.8F;

		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class,
				5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(7)
				.grassPerChunk(3).build();
	}

}
