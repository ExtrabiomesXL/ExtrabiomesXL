package extrabiomes.items;

import java.util.List;
import java.util.Locale;

import extrabiomes.blocks.BlockNewSapling;
import extrabiomes.utility.MultiItemBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

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
        final BlockNewSapling.BlockType[] validTypes = BlockNewSapling.BlockType.values();
        if (metadata >= validTypes.length )
            metadata = validTypes.length-1;
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getUnlocalizedName() + "." + BlockNewSapling.BlockType.values()[metadata & METADATA_BITMASK].toString().toLowerCase(Locale.ENGLISH);
    }
    
    @Override
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List listOfLines, boolean sneaking) {
    	BlockNewSapling.addInformation(itemForTooltip.getItemDamage(), listOfLines);
    }
    
}
