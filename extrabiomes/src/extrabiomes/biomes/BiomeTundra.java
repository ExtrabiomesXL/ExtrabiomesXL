/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;

public class BiomeTundra extends ExtrabiomeGenBase {

	public BiomeTundra(int id) {
		super(id);

		setColor(0x305A85);
		setBiomeName("Tundra");
		temperature = 0.0F;
		rainfall = 0.0F;
		minHeight = 0.0F;
		maxHeight = 0.2F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this)
				.flowersPerChunk(0).grassPerChunk(0).sandPerChunk(0, 0)
				.build();
	}

}
