/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;

import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import extrabiomes.api.ExtrabiomesBlock;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (ExtrabiomesBlock.sapling.isPresent()
				&& fuel.itemID == ExtrabiomesBlock.sapling.get().blockID)
			return 100;
		return 0;
	}

}
