/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.utility;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class MultiItemBlock extends ItemBlock
{   
    public MultiItemBlock(Block block)
    {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return super.getUnlocalizedName(itemstack) + '.' + itemstack.getItemDamage();
    }
    
    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }
}
