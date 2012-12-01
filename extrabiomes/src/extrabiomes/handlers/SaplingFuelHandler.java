/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.handlers;

import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class SaplingFuelHandler implements IFuelHandler {

    private final int saplingID;

    public SaplingFuelHandler(int saplingID) {
        this.saplingID = saplingID;
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel.itemID == saplingID) return 100;
        return 0;
    }

}
