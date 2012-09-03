/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import extrabiomes.terrain.CustomBiomeDecorator;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;

public class BiomeGreenHills extends ExtrabiomeGenBase {

	public BiomeGreenHills(int id) {
		super(id);

		setColor(0x68C474);
		setBiomeName("Green Hills");
		temperature = BiomeGenBase.forest.temperature - 0.1F;
		rainfall = BiomeGenBase.forest.rainfall + 0.1F;
		minHeight = 0.6F;
		maxHeight = 1.2F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(1)
				.build();
	}

}
