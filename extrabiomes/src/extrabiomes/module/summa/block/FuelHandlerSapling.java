/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.block;

import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

class FuelHandlerSapling implements IFuelHandler {
	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.itemID == Cube.SAPLING.getBlock().get().blockID)
			return 100;
		return 0;
	}

}
