/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.WorldGenerator;

import com.google.common.base.Optional;


public class BiomeRedwoodLush extends ExtrabiomeGenBase {

	private final Optional<WorldGenerator>	worldGenRedwood	= Optional
																	.absent();
	private final Optional<WorldGenerator>	worldGenFirTree	= Optional
																	.absent();

	public BiomeRedwoodLush(int id) {
		super(id);
		setColor(0x4AC758);
		setBiomeName("Lush Redwoods");
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
