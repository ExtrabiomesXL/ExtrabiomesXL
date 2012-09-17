/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;

public class BiomeWasteland extends ExtrabiomeGenBase {

	public BiomeWasteland(int id) {
		super(id);

		setColor(0x9E7C41);
		setBiomeName("Wasteland");
		temperature = BiomeGenBase.desert.temperature;
		rainfall = BiomeGenBase.desert.rainfall;
		minHeight = 0.0F;
		maxHeight = 0.0F;
		waterColorMultiplier = 0xF08000;

		spawnableCreatureList.clear();

		disableRain();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this)
				.deadBushPerChunk(3).grassPerChunk(1).build();
	}

}
