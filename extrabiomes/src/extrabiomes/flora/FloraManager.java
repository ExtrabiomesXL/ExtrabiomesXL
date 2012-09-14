/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.flora;

import net.minecraft.src.Block;
import extrabiomes.trees.TreeBlocks;

public class FloraManager {

	private static boolean	initialized	= false;

	private static void configureTrees() {
		TreeBlocks.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.grass.blockID));
		TreeBlocks.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.dirt.blockID));
		TreeBlocks.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.tilledField.blockID));
	}

	public void addCustomFlora() {
		if (initialized) return;
		initialized = true;

		configureTrees();
	}

}
