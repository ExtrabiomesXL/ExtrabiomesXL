/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import extrabiomes.features.WorldGenMarshDirt;
import extrabiomes.features.WorldGenMarshGrass;

public class BiomeMarsh extends ExtrabiomeGenBase {

	public BiomeMarsh(int id) {
		super(id);
		setColor(255);
		setBiomeName("Marsh");
		temperature = BiomeGenBase.swampland.temperature;
		rainfall = BiomeGenBase.swampland.rainfall;
		minHeight = -0.4F;
		maxHeight = 0.0F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.grassPerChunk(999).build();
	}

}
