/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class SaplingFuelHandler implements IFuelHandler
{
    
    private final Block saplingBlock;
    
    public SaplingFuelHandler(Block sb)
    {
        this.saplingBlock = sb;
    }
    
    @Override
    public int getBurnTime(ItemStack fuel)
    {
        if (fuel.getItem().equals(Item.getItemFromBlock(this.saplingBlock)))
            return 100;
        return 0;
    }
    
}
