package extrabiomes;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.forge.ITextureProvider;

class ItemCatTail extends ItemBlock implements ITextureProvider {

	private static final int ICON_INDEX = 95;

	public ItemCatTail(int par1) {
		super(par1);
		setIconIndex(ICON_INDEX);
	}
}
