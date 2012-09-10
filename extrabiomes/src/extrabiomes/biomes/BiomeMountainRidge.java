/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import extrabiomes.terrain.CustomBiomeDecorator;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;

public class BiomeMountainRidge extends ExtrabiomeGenBase {

	public BiomeMountainRidge(int id) {
		super(id);
		setColor(0xC4722F);
		setBiomeName("Mountain Ridge");
		temperature = BiomeGenBase.desert.temperature;
		rainfall = BiomeGenBase.desert.rainfall;
		minHeight = 1.7F;
		maxHeight = 1.7F;
		disableRain();
		spawnableCreatureList.clear();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.grassPerChunk(12).build();
	}
}
