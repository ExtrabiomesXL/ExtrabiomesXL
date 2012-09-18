/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.Block;

public class BiomeGlacier extends ExtrabiomeGenBase {

	public BiomeGlacier(int id) {
		super(id);
		spawnableCreatureList.clear();
		topBlock = (byte) Block.blockSnow.blockID;
		fillerBlock = (byte) Block.ice.blockID;
		setColor(0x77A696);
		setBiomeName("Glacier");
		setEnableSnow();
		temperature = 0.0F;
		rainfall = 0.0F;
		minHeight = 1.4F;
		maxHeight = 2.1F;
	}

}
