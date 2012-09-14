/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;

public class BiomeRainforest extends ExtrabiomeGenBase {

	public BiomeRainforest(int id) {
		super(id);

		setColor(0x0BD626);
		setBiomeName("Rainforest");
		temperature = 1.1F;
		rainfall = 1.4F;
		minHeight = 0.4F;
		maxHeight = 1.3F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(7)
				.grassPerChunk(4).flowersPerChunk(2).build();
	}
}
