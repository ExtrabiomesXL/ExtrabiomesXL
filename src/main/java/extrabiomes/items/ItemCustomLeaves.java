/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.items;

import java.util.Locale;

import net.minecraft.block.Block;
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
        BlockAutumnLeaves.BlockType[] validTypes = BlockAutumnLeaves.BlockType.values();
        if (metadata >= validTypes.length )
            metadata = validTypes.length-1;
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getUnlocalizedName() + "." + validTypes[metadata].toString().toLowerCase(Locale.ENGLISH);
    }
    
    private static final int METADATA_USERPLACEDBIT = 0x4;
    
    private static int setUserPlacedOnMetadata(final int metadata)
    {
        return metadata | METADATA_USERPLACEDBIT;
    }
    
    public ItemCustomLeaves(final Block block)
    {
        super(block);
    }
    
    @Override
    public int getMetadata(final int metadata)
    {
        return setUserPlacedOnMetadata(metadata);
    }
}
