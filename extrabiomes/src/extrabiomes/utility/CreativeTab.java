package extrabiomes.utility;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import extrabiomes.api.Stuff;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class CreativeTab extends CreativeTabs {
	public CreativeTab(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		if (!Stuff.scarecrow.isPresent())
			return Item.itemsList[this.getTabIconItemIndex()];
		return Stuff.scarecrow.get();
	}
}