/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;

public class BiomeShrubland extends ExtrabiomeGenBase {

	public BiomeShrubland(int id) {
		super(id);

		setColor(0x51B57D);
		setBiomeName("Shrubland");
		temperature = 0.4F;
		rainfall = 0.6F;
		minHeight = 0.1F;
		maxHeight = 0.3F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.flowersPerChunk(3).grassPerChunk(1).build();
	}
}
