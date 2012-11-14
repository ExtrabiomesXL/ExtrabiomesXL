/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;

class BiomeMountainDesert extends ExtrabiomeGenBase {

	public BiomeMountainDesert() {
		super(Biome.MOUNTAINDESERT.getBiomeID());

		setColor(0xFA9418);
		setBiomeName("Mountainous Desert");
		temperature = BiomeGenBase.desertHills.temperature;
		rainfall = BiomeGenBase.desertHills.rainfall;
		minHeight = 0.4F;
		maxHeight = 1.4F;
		topBlock = (byte) Block.sand.blockID;
		fillerBlock = (byte) Block.sand.blockID;
		spawnableCreatureList.clear();
		setDisableRain();
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.deadBushPerChunk(2).reedsPerChunk(50)
				.cactiPerChunk(10).build();
	}
}
