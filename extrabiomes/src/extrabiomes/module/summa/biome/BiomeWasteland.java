/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;

class BiomeWasteland extends ExtrabiomeGenBase {

	public BiomeWasteland() {
		super(Biome.WASTELAND.getBiomeID());

		setColor(0x9E7C41);
		setBiomeName("Wasteland");
		temperature = BiomeGenBase.desert.temperature;
		rainfall = BiomeGenBase.desert.rainfall;
		minHeight = 0.0F;
		maxHeight = 0.0F;
		waterColorMultiplier = 0xF08000;

		spawnableCreatureList.clear();

		disableRain();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this)
				.deadBushPerChunk(3).grassPerChunk(1).build();
	}

}
