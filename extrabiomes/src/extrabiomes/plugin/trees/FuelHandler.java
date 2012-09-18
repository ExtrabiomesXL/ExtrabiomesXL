/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.trees;

import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	private final int	saplingID;

	FuelHandler(int saplingID) {
		this.saplingID = saplingID;
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.itemID == saplingID) return 100;
		return 0;
	}

}
