/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.Block;

class BiomeGlacier extends ExtrabiomeGenBase {

	public BiomeGlacier() {
		super(Biome.GLACIER.getBiomeID());
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
