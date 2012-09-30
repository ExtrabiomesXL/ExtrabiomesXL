/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.WeightedRandomItem;
import net.minecraft.src.WorldGenerator;

class WeightedWorldGenerator extends WeightedRandomItem {

	private final WorldGenerator	worldGen;

	WeightedWorldGenerator(WorldGenerator worldGen, int weight) {
		super(weight);
		this.worldGen = worldGen;
	}

	WorldGenerator getWorldGen() {
		return worldGen;
	}

}
