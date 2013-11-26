/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.items;

import java.util.Locale;

import net.minecraft.item.ItemStack;
import extrabiomes.blocks.BlockAutumnLeaves;
import extrabiomes.utility.MultiItemBlock;

public class ItemCustomLeaves extends MultiItemBlock
{
    
    private static final int METADATA_BITMASK = 0x3;
    
    protected static int unmarkedMetadata(int metadata)
    {
        return metadata & METADATA_BITMASK;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        int metadata = unmarkedMetadata(itemstack.getItemDamage());
        if (metadata > 3)
            metadata = 3;
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getUnlocalizedName() + "." + BlockAutumnLeaves.BlockType.values()[metadata].toString().toLowerCase(Locale.ENGLISH);
    }
    
    private static final int METADATA_USERPLACEDBIT = 0x4;
    
    private static int setUserPlacedOnMetadata(final int metadata)
    {
        return metadata | METADATA_USERPLACEDBIT;
    }
    
    public ItemCustomLeaves(final int id)
    {
        super(id);
    }
    
    @Override
    public int getMetadata(final int metadata)
    {
        return setUserPlacedOnMetadata(metadata);
    }
}
