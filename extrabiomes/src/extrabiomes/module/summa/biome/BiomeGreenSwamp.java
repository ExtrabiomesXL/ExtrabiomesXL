/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntitySlime;
import net.minecraft.src.SpawnListEntry;

class BiomeGreenSwamp extends ExtrabiomeGenBase {

	public BiomeGreenSwamp() {
		super(Biome.GREENSWAMP.getBiomeID());

		setColor(0x68C474);
		setBiomeName("Green Swamplands");
		temperature = BiomeGenBase.swampland.temperature - 0.1F;
		rainfall = BiomeGenBase.swampland.rainfall;
		minHeight = -0.2F;
		maxHeight = 0.1F;
		spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class,
				1, 1, 1));
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(4)
				.flowersPerChunk(0).deadBushPerChunk(0)
				.mushroomsPerChunk(8).reedsPerChunk(10).clayPerChunk(1)
				.waterlilyPerChunk(4).sandPerChunk(0, 0).build();
	}
}
