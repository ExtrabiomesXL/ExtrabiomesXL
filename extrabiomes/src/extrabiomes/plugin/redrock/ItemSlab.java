/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.redrock;

import net.minecraft.src.BlockHalfSlab;

public class ItemSlab extends net.minecraft.src.ItemSlab {

	public ItemSlab(int blockID) {
		super(blockID, RedRock.getHalfSlab(), RedRock.getDoubleSlab(),
				blockID == RedRock.getDoubleSlab().blockID);
	}

}
