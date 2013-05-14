package extrabiomes.utility;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.api.Stuff;

public class CreativeTab extends CreativeTabs {
	public CreativeTab(String par2Str) {
		super(par2Str);
	}

	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		if (!Stuff.scarecrow.isPresent())
			return Item.itemsList[this.getTabIconItemIndex()];
		return Stuff.scarecrow.get();
	}
}
