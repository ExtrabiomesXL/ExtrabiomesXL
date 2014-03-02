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
    public String getUnlocalizedName(ItemStack itemstack) {
    	return ((BlockCustomFlower)Block.blocksList[itemstack.itemID]).getUnlocalizedName(itemstack.getItemDamage());
    }

    @Override
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List listOfLines, boolean sneaking) {
    	((BlockCustomFlower)Block.blocksList[itemForTooltip.itemID]).addInformation(itemForTooltip.getItemDamage(), listOfLines);
    }
    
}
