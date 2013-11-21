/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

class FuelHandlerWoodSlabs implements IFuelHandler
{
    
    private final int slabID;
    
    FuelHandlerWoodSlabs(int slabID)
    {
        this.slabID = slabID;
    }
    
    @Override
    public int getBurnTime(ItemStack fuel)
    {
        if (fuel.itemID == slabID)
            return 150;
        return 0;
    }
    
}
