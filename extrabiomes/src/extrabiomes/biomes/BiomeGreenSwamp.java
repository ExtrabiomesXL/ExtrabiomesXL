/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeGreenSwamp extends ExtrabiomeGenBase {

	public BiomeGreenSwamp(int id) {
		super(id);
		setColor(0x68C474);
		setBiomeName("Green Swamplands");
		temperature = BiomeGenBase.swampland.temperature - 0.1F;
		rainfall = BiomeGenBase.swampland.rainfall;
		minHeight = -0.2F;
		maxHeight = 0.1F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(4)
				.flowersPerChunk(0).deadBushPerChunk(0)
				.mushroomsPerChunk(8).reedsPerChunk(10).clayPerChunk(1)
				.waterlilyPerChunk(4).sandPerChunk(0, 0).build();
	}
}
