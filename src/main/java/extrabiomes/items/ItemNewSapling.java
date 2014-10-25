package extrabiomes.items;

import java.util.List;
import java.util.Locale;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import extrabiomes.blocks.BlockNewSapling;
import extrabiomes.utility.MultiItemBlock;

public class ItemNewSapling extends MultiItemBlock
{
    
    public ItemNewSapling(Block block)
    {
        super(block);
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
        
        if(BlockNewSapling.BlockType.values()[metadata & METADATA_BITMASK] == null) {
          return null;
        }
        
        return super.getUnlocalizedName() + "." + BlockNewSapling.BlockType.values()[metadata & METADATA_BITMASK].toString().toLowerCase(Locale.ENGLISH);
    }
    
    @Override
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List listOfLines, boolean sneaking) {
    	BlockNewSapling.addInformation(itemForTooltip.getItemDamage(), listOfLines);
    }
    
}
