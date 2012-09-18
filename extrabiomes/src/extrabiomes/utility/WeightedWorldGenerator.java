/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.utility;

import net.minecraft.src.WeightedRandomItem;
import net.minecraft.src.WorldGenerator;

public class WeightedWorldGenerator extends WeightedRandomItem {
	
	private final WorldGenerator worldGen; 

	public WeightedWorldGenerator(WorldGenerator worldGen, int weight) {
		super(weight);
		this.worldGen = worldGen;
	}

	public WorldGenerator getWorldGen() {
		return worldGen;
	}

}
