/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import extrabiomes.terrain.CustomBiomeDecorator;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;

public class BiomeSnowRainforest extends BiomeTemporateRainforest {

	public BiomeSnowRainforest(int id) {
		super(id);

		setColor(0x338277);
		setBiomeName("Snowy Rainforest");
		temperature = BiomeGenBase.taigaHills.temperature;
		rainfall = 1.3F;
		setEnableSnow();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(17)
				.mushroomsPerChunk(2).grassPerChunk(16).build();
	}

}
