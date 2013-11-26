package extrabiomes.items;

import java.util.Locale;

import net.minecraft.item.ItemStack;
import extrabiomes.blocks.BlockNewLeaves;

public class ItemCustomNewLeaves extends ItemCustomLeaves
{
    
    public ItemCustomNewLeaves(int id)
    {
        super(id);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        int metadata = unmarkedMetadata(itemstack.getItemDamage());
        //if (metadata > 2) metadata = 0;
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getUnlocalizedName() + "." + BlockNewLeaves.BlockType.values()[metadata].toString().toLowerCase(Locale.ENGLISH);
    }
    
}
