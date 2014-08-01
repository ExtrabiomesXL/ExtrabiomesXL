/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.items;

import java.util.Locale;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import extrabiomes.blocks.BlockMiniLog;
import extrabiomes.utility.MultiItemBlock;

public class ItemCustomMiniLog extends ItemBlock
{
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return super.getUnlocalizedName() + "." + BlockMiniLog.BlockType.values()[0].toString().toLowerCase(Locale.ENGLISH);
    }
    
    public ItemCustomMiniLog(final Block block)
    {
        super(block);
    }
    
}
