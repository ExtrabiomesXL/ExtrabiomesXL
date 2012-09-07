/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeRedwoodForest extends ExtrabiomeGenBase {

	public BiomeRedwoodForest(int id) {
		super(id);

		setColor(0x0BD626);
		setBiomeName("Redwood Forest");
		temperature = 1.1F;
		rainfall = 1.4F;
		minHeight = 0.9F;
		maxHeight = 1.5F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(17)
				.build();
	}
}
