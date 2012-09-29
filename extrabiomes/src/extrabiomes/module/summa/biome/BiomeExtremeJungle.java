/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityOcelot;
import net.minecraft.src.SpawnListEntry;

class BiomeExtremeJungle extends ExtrabiomeGenBase {

	public BiomeExtremeJungle() {
		super(Biome.EXTREMEJUNGLE.getBiomeID());
		setColor(0x2c4205);
		setBiomeName("Extreme Jungle");
		temperature = BiomeGenBase.jungle.temperature;
		rainfall = BiomeGenBase.jungle.rainfall;
		minHeight = 2.1F;
		maxHeight = 2.3F;

		spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class,
				2, 1, 1));
		spawnableCreatureList.add(new SpawnListEntry(
				EntityChicken.class, 10, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(50)
				.grassPerChunk(25).flowersPerChunk(4).build();
	}
}
