package extrabiomes.items;

import net.minecraft.item.ItemStack;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.utility.MultiItemBlock;

public class ItemSapling extends MultiItemBlock
{
    
    public ItemSapling(int id)
    {
        super(id);
    }
    
    private static int unmarkedMetadata(int metadata)
    {
        return metadata & METADATA_BITMASK;
    }
    
    private static final int METADATA_BITMASK = 0x7;
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        int metadata = unmarkedMetadata(itemstack.getItemDamage());
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getUnlocalizedName() + "." + BlockCustomSapling.BlockType.values()[metadata].toString().toLowerCase();
        //return super.getUnlocalizedName(itemstack);
    }
}
