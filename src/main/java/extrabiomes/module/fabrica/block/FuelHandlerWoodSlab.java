/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

class FuelHandlerWoodSlabs implements IFuelHandler
{
    
    private final Block slab;
    
    FuelHandlerWoodSlabs(Block slab)
    {
        this.slab = slab;
    }
    
    @Override
    public int getBurnTime(ItemStack fuel)
    {
        if (fuel.getItem().equals(Item.getItemFromBlock(slab)))
            return 150;
        return 0;
    }
    
}
