package extrabiomes.utility;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import extrabiomes.api.Stuff;

public class CreativeTab extends CreativeTabs
{
    public CreativeTab(String par2Str)
    {
        super(par2Str);
    }

	@Override
	public Item getTabIconItem() {
		if(Stuff.scarecrow.isPresent()) return Stuff.scarecrow.get();
		else return Item.getItemFromBlock(Blocks.HAY_BLOCK);
	}
    
}
