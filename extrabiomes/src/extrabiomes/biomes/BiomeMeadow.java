/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import extrabiomes.terrain.CustomBiomeDecorator;
import net.minecraft.src.BiomeDecorator;

public class BiomeMeadow extends ExtrabiomeGenBase {

	public BiomeMeadow(int id) {
		super(id);
		setBiomeName("Meadow");
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.grassPerChunk(12).flowersPerChunk(9).build();
	}

}
