/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;

public class BiomeMountainDesert extends ExtrabiomeGenBase {

	public BiomeMountainDesert(int id) {
		super(id);
		setColor(0xFA9418);
		setBiomeName("Mountainous Desert");
		temperature = BiomeGenBase.desertHills.temperature;
		rainfall = BiomeGenBase.desertHills.rainfall;
		minHeight = 0.4F;
		maxHeight = 1.4F;
		topBlock = (byte) Block.sand.blockID;
		fillerBlock = (byte) Block.sand.blockID;
		spawnableCreatureList.clear();
		disableRain();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.deadBushPerChunk(2).reedsPerChunk(50)
				.cactiPerChunk(10).build();
	}
}
