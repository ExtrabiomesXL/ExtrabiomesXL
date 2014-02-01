package extrabiomes.items;

import java.util.Collection;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.helpers.LogHelper;
import extrabiomes.utility.MultiItemBlock;

public class ItemFlower extends MultiItemBlock
{
	public final int	group;
	public final int	max_meta;

    public ItemFlower(int id)
    {
        super(id);
		BlockCustomFlower b = (BlockCustomFlower) Block.blocksList[id + 256];

		this.group = b.group;

		//LogHelper.finer("ItemFlower - " + id + ", " + ( b != null ? b.blockID : "null" ) + ", group = " + group);
		
		max_meta = b.getGroupTypes().size();
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        int metadata = itemstack.getItemDamage();
		if (metadata > max_meta) metadata = max_meta;
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getUnlocalizedName(itemstack);
    }

    @Override
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List listOfLines, boolean sneaking) {
    	//BlockNewSapling.addInformation(itemForTooltip.getItemDamage(), listOfLines);
    	//BlockCustomFlower()
    	//groupType[]
    	((BlockCustomFlower)Block.blocksList[itemForTooltip.itemID]).addInformation(itemForTooltip.getItemDamage(), listOfLines);
    	
    	//listOfLines.add("Group: " + Block.blocksList[itemForTooltip.itemID].getUnlocalizedName());
    	
    }
    
}
