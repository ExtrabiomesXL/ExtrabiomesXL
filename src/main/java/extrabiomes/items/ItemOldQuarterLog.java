package extrabiomes.items;

import java.util.List;
import java.util.Locale;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import extrabiomes.utility.MultiItemBlock;

public class ItemOldQuarterLog extends MultiItemBlock
{
    
    public ItemOldQuarterLog(Block block)
    {
        super(block);
    }
    
    @Override
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List listOfLines, boolean sneaking) {
    	listOfLines.add("�o�lNo longer Used�r");
    	listOfLines.add("");
    	listOfLines.add("Place in crafting grid to");
    	listOfLines.add("convert to normal logs.");
    }
    
}
