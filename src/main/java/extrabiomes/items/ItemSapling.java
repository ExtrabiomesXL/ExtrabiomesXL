package extrabiomes.items;

import java.util.List;
import java.util.Locale;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.utility.MultiItemBlock;

public class ItemSapling extends MultiItemBlock
{
    
    public ItemSapling(Block block)
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
        return super.getUnlocalizedName() + "." + BlockCustomSapling.BlockType.values()[metadata & METADATA_BITMASK].toString().toLowerCase(Locale.ENGLISH);
        //return super.getUnlocalizedName(itemstack);
    }
    
    @Override
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List listOfLines, boolean sneaking) {
    	BlockCustomSapling.addInformation(itemForTooltip.getItemDamage(), listOfLines);
    }
}
