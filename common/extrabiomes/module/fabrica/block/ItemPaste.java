package extrabiomes.module.fabrica.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemPaste extends Item{

	public ItemPaste(int par1) {
		super(par1);		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
    	itemIcon = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "cactuspaste");
    }
}
