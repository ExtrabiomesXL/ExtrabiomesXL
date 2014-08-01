package extrabiomes.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import extrabiomes.utility.MultiItemBlock;

public class ItemGrass extends MultiItemBlock
{
    
    public ItemGrass(Block block)
    {
        super(block);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        int metadata = itemstack.getItemDamage();
        if (metadata > 4)
            metadata = 4;
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getUnlocalizedName(itemstack);
    }
    
}
